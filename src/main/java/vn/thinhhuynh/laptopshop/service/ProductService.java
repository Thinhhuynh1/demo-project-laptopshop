package vn.thinhhuynh.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.thinhhuynh.laptopshop.domain.Cart;
import vn.thinhhuynh.laptopshop.domain.CartDetail;
import vn.thinhhuynh.laptopshop.domain.Order;
import vn.thinhhuynh.laptopshop.domain.OrderDetail;
import vn.thinhhuynh.laptopshop.domain.Product;
import vn.thinhhuynh.laptopshop.domain.User;
import vn.thinhhuynh.laptopshop.repository.CartDetailRepository;
import vn.thinhhuynh.laptopshop.repository.CartRepository;
import vn.thinhhuynh.laptopshop.repository.OrderDetailRepository;
import vn.thinhhuynh.laptopshop.repository.OrderRepository;
import vn.thinhhuynh.laptopshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(
            ProductRepository productRepository,
            CartRepository cartRepository,
            CartDetailRepository cartDetailRepository,
            UserService userService,
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public Product handleSaveProduct(Product product) {
        Product eric = this.productRepository.save(product);
        return eric;
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            // check user đã có Cart chưa ? nếu chưa -> tạo mới
            Cart cart = this.cartRepository.findByUser(user);

            if (cart == null) {
                // tao moi cart
                Cart createCart = new Cart();
                createCart.setUser(user);
                createCart.setSum(0);

                cart = this.cartRepository.save(createCart);
            }

            // save cart_detail
            // tim product by id

            Product product = this.productRepository.findById(productId); //
            // vì dùng jpa nên trả về Optional
            if (product != null) {
                CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(cart, product);

                if (cartDetail == null) {
                    CartDetail createCartDetail = new CartDetail();

                    createCartDetail.setCart(cart);
                    createCartDetail.setProduct(product);
                    createCartDetail.setPrice(product.getPrice());
                    createCartDetail.setQuantity(1);
                    this.cartDetailRepository.save(createCartDetail);

                } else {
                    cartDetail.setQuantity(cartDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(cartDetail);
                }

                // update cart sum
                int s = cart.getSum() + 1;
                cart.setSum(cart.getSum() + 1);
                this.cartRepository.save(cart);
                session.setAttribute("sum", s); // lay tong so luong san pham trong gio hang

            }

        }

        // lưu cart_detail
    }

    public void handleRemoveCartDetail(long cartDetailId, HttpSession session) {
        Optional<CartDetail> cartDetaiOptional = this.cartDetailRepository.findById(cartDetailId);
        if (cartDetaiOptional.isPresent()) {
            CartDetail cartDetail = cartDetaiOptional.get();

            Cart currentCart = cartDetail.getCart();
            int sumCartDetail = (int) cartDetail.getQuantity();
            int sum = currentCart.getSum();

            this.deleteCartProduct(cartDetailId);

            sum -= sumCartDetail;

            if (sum < 1) {
                currentCart.setSum(0);
                this.deleteCart(currentCart.getId());
            } else {
                currentCart.setSum(sum);
                this.saveCart(currentCart);
            }
            session.setAttribute("sum", sum);
        }

    }

    public Cart fetchByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public Cart saveCart(Cart cart) {
        return this.cartRepository.save(cart);
    }

    public void deleteCart(long id) {
        this.cartRepository.deleteById(id);
    }

    public void deleteCartProduct(long id) {
        this.cartDetailRepository.deleteById(id);
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(
            User user, HttpSession session,
            String receiverName, String receiverAddress, String receiverPhone) {

        // create orderDetail

        // Step1 : get cart by user
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            if (cartDetails != null) {

                // create order
                Order order = new Order();

                order.setUser(user);
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("Pending");

                double sum = 0;
                for (CartDetail cd : cartDetails) {
                    sum += cd.getPrice() * cd.getQuantity();
                }

                order.setTotalPrice(sum);
                order = this.orderRepository.save(order); // lay duoc id cua order

                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setQuantity(cd.getQuantity());
                    orderDetail.setPrice(cd.getPrice());
                    this.orderDetailRepository.save(orderDetail);
                }

                // Step 2: delete cart_detail and cart
                for (CartDetail cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getId());
                }

                this.cartRepository.deleteById(cart.getId());

                // step 3 : update session
                session.setAttribute("sum", 0);
            }

        }
    }

}

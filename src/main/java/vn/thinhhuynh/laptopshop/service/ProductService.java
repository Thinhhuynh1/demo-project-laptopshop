package vn.thinhhuynh.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.thinhhuynh.laptopshop.domain.Cart;
import vn.thinhhuynh.laptopshop.domain.CartDetail;
import vn.thinhhuynh.laptopshop.domain.Product;
import vn.thinhhuynh.laptopshop.domain.User;
import vn.thinhhuynh.laptopshop.repository.CartDetailRepository;
import vn.thinhhuynh.laptopshop.repository.CartRepository;
import vn.thinhhuynh.laptopshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(
            ProductRepository productRepository,
            CartRepository cartRepository,
            CartDetailRepository cartDetailRepository,
            UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
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

    public Cart fetchByUser(User user) {
        return this.cartRepository.findByUser(user);
    }
}

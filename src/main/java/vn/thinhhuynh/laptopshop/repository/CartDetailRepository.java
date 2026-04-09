package vn.thinhhuynh.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.thinhhuynh.laptopshop.domain.Cart;
import vn.thinhhuynh.laptopshop.domain.CartDetail;
import vn.thinhhuynh.laptopshop.domain.Product;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    CartDetail findByCartAndProduct(Cart cart, Product product);
}

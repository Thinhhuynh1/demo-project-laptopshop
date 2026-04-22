package vn.thinhhuynh.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import vn.thinhhuynh.laptopshop.domain.Order;
import vn.thinhhuynh.laptopshop.domain.OrderDetail;
import vn.thinhhuynh.laptopshop.repository.OrderDetailRepository;
import vn.thinhhuynh.laptopshop.repository.OrderRepository;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final OrderRepository orderRespository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderController(
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.orderRespository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @GetMapping("/admin/order")
    public String getDashboard(Model model) {

        List<Order> order = this.orderRespository.findAll();
        model.addAttribute("orders", order);

        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getDetailPage(Model model, @PathVariable long id) {
        Order order = this.orderRespository.findById(id);

        model.addAttribute("id", id);
        model.addAttribute("order", order);

        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdatePage(Model model, @PathVariable long id) {
        Order order = this.orderRespository.findById(id);
        model.addAttribute("updateOrder", order);
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postUpdate(Model model, @ModelAttribute("updateOrder") Order order) {

        Order currentOrder = this.orderRespository.findById(order.getId());
        if (currentOrder == null) {
            return "redirect:/admin/order";
        }

        currentOrder.setStatus(order.getStatus());
        this.orderRespository.save(currentOrder);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeletePage(Model model, @PathVariable long id) {

        Order order = this.orderRespository.findById(id);

        model.addAttribute("order", order);
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String postMethodName(@ModelAttribute("order") Order order) {
        Order currentOrder = this.orderRespository.findById(order.getId());
        if (currentOrder == null) {
            return "redirect:/admin/order";
        }

        List<OrderDetail> orderDetails = currentOrder.getOrderDetails();
        if (orderDetails != null) {
            this.orderDetailRepository.deleteAll(orderDetails);
        }

        this.orderRespository.delete(currentOrder);
        return "redirect:/admin/order";
    }

}

package vn.thinhhuynh.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import vn.thinhhuynh.laptopshop.domain.Product;
import vn.thinhhuynh.laptopshop.domain.User;
import vn.thinhhuynh.laptopshop.domain.dbo.RegisterDTO;
import vn.thinhhuynh.laptopshop.service.ProductService;
import vn.thinhhuynh.laptopshop.service.UserService;

@Controller
public class HomePageController {

    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder; // ma hoa password

    public HomePageController(
            ProductService productService,
            UserService userService,
            PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("registerUser") @Valid RegisterDTO register,
            BindingResult bindingResult) {

        // List<FieldError> errors = bindingResult.getFieldErrors();
        // for (FieldError error : errors) {
        // System.out.println(">>>>>>" + error.getField() + " - " +
        // error.getDefaultMessage());
        // }

        // Validate
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        User user = this.userService.registerDTOToUser(register);

        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));

        // save
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {

        return "client/auth/login";
    }

}
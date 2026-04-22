package vn.thinhhuynh.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.thinhhuynh.laptopshop.domain.Product;
import vn.thinhhuynh.laptopshop.service.ProductService;
import vn.thinhhuynh.laptopshop.service.UploadService;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService,
            UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProducctPage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String postCreateProduct(Model model,
            @ModelAttribute("newProduct") @Valid Product pr,
            BindingResult newProductBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {

        // validate
        if (newProductBindingResult.hasErrors()) {
            return "admin/product/create"; // khong redirect de hien thi loi, neu co thi se bi reset mat thong bao
        }

        // upload image
        String image = this.uploadService.handleSaveUploadFile(file, "product");
        pr.setImage(image);

        this.productService.handleSaveProduct(pr);

        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetail(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProduct(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("updateProduct", product);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateProduct(Model model,
            @ModelAttribute("updateProduct") @Valid Product prd, // product cua submit
            BindingResult updateProductBindingResult,
            @RequestParam("imageFile") MultipartFile file) {

        // validate
        if (updateProductBindingResult.hasErrors()) {
            return "admin/product/update"; // khong redirect de hien thi loi, neu co thi se bi reset mat thong bao
        }

        Product currentProduct = this.productService.getProductById(prd.getId()); // lay product tu database
        if (currentProduct == null) {
            return "redirect:/admin/product";
        }
        if (currentProduct != null) {
            currentProduct.setName(prd.getName());
            currentProduct.setPrice(prd.getPrice());
            currentProduct.setDetailDesc(prd.getDetailDesc());
            currentProduct.setShortDesc(prd.getShortDesc());
            currentProduct.setQuantity(prd.getQuantity());
            currentProduct.setFactory(prd.getFactory());
            currentProduct.setTarget(prd.getTarget());
        }

        if (!file.isEmpty()) {
            String image = this.uploadService.handleSaveUploadFile(file, "product");
            currentProduct.setImage(image);
        }

        this.productService.handleSaveProduct(currentProduct);

        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProduct(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        Product product = new Product();
        product.setId(id);
        model.addAttribute("newProduct", product);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(Model model, @ModelAttribute("newProduct") Product prd) {
        this.productService.deleteProduct(prd.getId());
        return "redirect:/admin/product";
    }

}

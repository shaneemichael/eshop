package id.ac.ui.cs.advprog.eshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController<Product, String, ProductService> {

    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";
    private static final String PRODUCT_NOT_FOUND = "Product not found";

    public ProductController(ProductService service) {
        super(service);
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        addEntitiesToModel(model, allProducts, "products");
        return "ProductList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        Product product = service.findById(id);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@PathVariable("id") String id, @ModelAttribute Product product, 
                                  RedirectAttributes redirectAttributes) {
        product.setProductId(id);
        service.update(product.getProductId(), product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        return REDIRECT_PRODUCT_LIST;
    }
}

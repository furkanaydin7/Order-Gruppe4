package org.example.order;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@SessionScope
@Controller
public class ShoppingCartController {

    private final ShoppingCart shoppingCart = new ShoppingCart();

    private final CatalogClient catalogClient;

    public ShoppingCartController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartBooks", shoppingCart.getBooks());
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam String isbn) {
        Book book = catalogClient.getBook(isbn);
        shoppingCart.addBook(book);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam String isbn) {
        shoppingCart.removeBook(isbn);
        return "redirect:/cart";
    }

}

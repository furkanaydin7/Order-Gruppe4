package org.example.order;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "Cart_Tyhmeleaf";
    }

    @PostMapping("/cart/add/{isbn}")
    public String addToCart(@PathVariable String isbn, RedirectAttributes redirectAttributes) {
        Book book = catalogClient.getBook(isbn);
        shoppingCart.addBook(book);
        redirectAttributes.addFlashAttribute("message", "Das Buch wurde zum Warenkorb hinzugefügt.");
        return "redirect:/";
    }

    @PostMapping("/cart/remove/{isbn}")
    public String removeFromCart(@PathVariable String isbn) {
        shoppingCart.removeBook(isbn);
        return "redirect:/cart";
    }

}

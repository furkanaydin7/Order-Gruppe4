package org.example.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatalogController {

    private final CatalogClient catalogClient;

    public CatalogController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping
    public String catalog(Model model, @RequestParam(required = false) String keywords) {
        if (keywords != null) {
            model.addAttribute("books", catalogClient.findBooks(keywords));
        } else {
            model.addAttribute("books", catalogClient.findBooks("")); // Leere Suche für alle Bücher
        }
        return "Catalog_Thymeleaf";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(required = false) String keywords, Model model) {
        if (keywords.isEmpty()) {
            // Wenn das Suchfeld leer ist, alle Bücher anzeigen
            model.addAttribute("books", catalogClient.findBooks(""));
        } else {
            // Suche nach den eingegebenen Keywords
            model.addAttribute("books", catalogClient.findBooks(keywords));
        }
        return "Catalog_Thymeleaf";
    }
}

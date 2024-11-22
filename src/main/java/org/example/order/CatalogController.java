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
    public String catalog() {
        return "Catalog_Thymeleaf";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String keywords, Model model) {
        model.addAttribute("books", catalogClient.findBooks(keywords));
        return "Catalog_Thymeleaf";
    }
}

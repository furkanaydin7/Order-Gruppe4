package org.example.order;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogClient {

    private final RestClient restClient;
    private final String catalogUrl;

    public CatalogClient(RestClient.Builder restClientBuilder, @Value("${catalog.url}") String catalogUrl) {
        this.restClient = restClientBuilder.build();
        this.catalogUrl = catalogUrl;
    }

    @Retry(name = "catalogService", fallbackMethod = "fallbackFindBooks")
    public Book[] findBooks(String keywords) {
        return restClient
                .get()
                .uri("%s/api/books?keywords=%s".formatted(catalogUrl, keywords))
                .retrieve()
                .body(Book[].class);
    }

    @Retry(name = "catalogService", fallbackMethod = "fallbackGetBook")
    public Book getBook(String isbn) {
        return restClient
                .get()
                .uri("%s/api/books/%s".formatted(catalogUrl, isbn))
                .retrieve()
                .body(Book.class);
    }

    // Fallback für findBooks
    public Book[] fallbackFindBooks(String keyword, Throwable throwable) {
        System.err.println("Fallback für findBooks aufgerufen: " + throwable.getMessage());
        return new Book[]{};
    }

    // Fallback für getBook
    public Book fallbackGetBook(String isbn, Throwable throwable) {
        System.err.println("Fallback für getBook aufgerufen: " + throwable.getMessage());
        return new Book(isbn, "N/A", "N/A", "N/A"); // Rückgabe eines Platzhalter-Buchs
    }
}

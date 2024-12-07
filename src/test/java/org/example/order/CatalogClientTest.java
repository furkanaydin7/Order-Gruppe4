package org.example.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class CatalogClientTest {

    @Autowired
    private CatalogClient catalogClient;

    @MockBean
    private RestClient restClient;

    @Test
    public void testFindBooksWithFallback() {
        doThrow(new RestClientException("Service unavailable"))
                .when(restClient)
                .get();

        Book[] books = catalogClient.findBooks("Spring Boot");

        assertEquals(0, books.length);
    }

    @Test
    public void testGetBookWithFallback() {
        doThrow(new RestClientException("Service unavailable"))
                .when(restClient)
                .get();

        Book book = catalogClient.getBook("12345");

        assertEquals("N/A", book.getTitle());
        assertEquals("N/A", book.getAuthor());
    }
}
package org.example.order;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogClient {

    private final RestClient restClient;

    public CatalogClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }
}

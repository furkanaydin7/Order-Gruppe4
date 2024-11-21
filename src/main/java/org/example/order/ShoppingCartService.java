package org.example.order;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    private final List<Book> cart = new ArrayList<>();

    public void addToCart(Book book) {
        cart.add(book);
    }

    public List<Book> getCart() {
        return cart;
    }
}

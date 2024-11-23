package org.example.order;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ShoppingCart {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String isbn) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getIsbn().equals(isbn)) {
                iterator.remove();
                break;
            }
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}

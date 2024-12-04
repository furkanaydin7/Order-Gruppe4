package org.example.order.playwright;

import com.microsoft.playwright.*;

public class CatalogOrderE2ETest {

    public static void main(String[] args) {
        // Playwright initialisieren
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // 1. Öffne die Seite
            page.navigate("http://localhost:8081");

            // 2. Suchfeld ausfüllen und Suche ausführen
            page.locator("form[action='/search'] input[name='keywords']").fill("Spring Boot");
            page.locator("form[action='/search'] button[type='submit']").click();

            // 3. Überprüfe, ob die Suchergebnisse korrekt angezeigt werden
            Locator resultRow = page.locator("tbody tr");
            assert resultRow.count() == 1 : "Es wurde genau ein Buch erwartet.";
            assert resultRow.locator("td:nth-child(2)").innerText().equals("Spring Boot in Action") : "Das Buch 'Spring Boot in Action' wurde nicht gefunden.";

            // 4. Füge das Buch in den Warenkorb
            page.locator("form[action*='/cart/add']").first().locator("button").click();

            // 5. Navigiere zum Warenkorb
            page.navigate("http://localhost:8081/cart");

            // 6. Überprüfe, ob das Buch im Warenkorb angezeigt wird
            Locator cartRow = page.locator("tbody tr");
            assert cartRow.count() == 1 : "Es wurde genau ein Buch im Warenkorb erwartet.";
            assert cartRow.locator("td:nth-child(2)").innerText().equals("Spring Boot in Action") : "Das Buch 'Spring Boot in Action' ist nicht im Warenkorb.";

            System.out.println("E2E-Test erfolgreich abgeschlossen!");
        }
    }
}

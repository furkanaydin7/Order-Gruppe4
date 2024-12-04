package org.example.order.playwright;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CatalogOrderE2ETest {

    public static void main(String[] args) {
        // Playwright initialisieren
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false) // Headless deaktivieren für sichtbare Tests
                            .setSlowMo(770)     // Verlangsamt Aktionen für Debugging
            );

            Page page = browser.newPage();

            // Schritt 1: Öffne die URL
            page.navigate("http://localhost:8081");

            // Schritt 2: Suchfeld ausfüllen und suchen
            page.locator("input[name='keywords']").fill("Spring Boot");
            page.locator("button:has-text('Suchen')").click();

            // Schritt 3: Überprüfen, ob die erwarteten Bücher angezeigt werden
            Locator bookTitle = page.locator("td:has-text('Spring Boot in Action')");
            assertThat(bookTitle).isVisible();

            // Schritt 4: Ein Buch in den Warenkorb legen
            Locator addToCartButton = page.locator("form button:has-text('Zum Warenkorb hinzufügen')");
            addToCartButton.first().click(); // Das erste Buch hinzufügen

            // Schritt 5: Klick auf den "Warenkorb ansehen"-Button
            Locator cartLink = page.locator("a:has-text('Warenkorb ansehen')");
            cartLink.click();

            // Schritt 6: Überprüfen, ob das Buch im Warenkorb ist
            Locator cartBookTitle = page.locator("td:has-text('Spring Boot in Action')");
            assertThat(cartBookTitle).isVisible();

            System.out.println("E2E-Test erfolgreich abgeschlossen!");
        }
    }
}

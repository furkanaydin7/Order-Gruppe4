import { test, expect } from '@playwright/test';

test('E2E-Test: Buchsuche und Warenkorb-Funktionalität', async ({ page }) => {
    // Schritt 1: Öffne die URL
    await page.goto('http://localhost:8081');

    // Schritt 2: Suchfeld ausfüllen und suchen
    await page.fill('input[name="keywords"]', 'Spring Boot');
    await page.click('button', { hasText: 'Suchen' });

    // Schritt 3: Überprüfen, ob die erwarteten Bücher angezeigt werden
    const bookTitle = page.locator('td', { hasText: 'Spring Boot in Action' });
    await expect(bookTitle).toBeVisible();

    // Schritt 4: Ein Buch in den Warenkorb legen
    const addToCartButton = page.locator('form button', { hasText: 'Zum Warenkorb hinzufügen' });
    await addToCartButton.nth(0).click();

    // Schritt 5: Klick auf den "Warenkorb ansehen"-Button
    await page.waitForSelector('a:has-text("Warenkorb ansehen")');
    const cartLink = page.locator('a', { hasText: 'Warenkorb ansehen' });
    await cartLink.click();

    // Schritt 6: Überprüfen, ob das Buch im Warenkorb ist
    const cartBookTitle = page.locator('td', { hasText: 'Spring Boot in Action' });
    await expect(cartBookTitle).toBeVisible();
});

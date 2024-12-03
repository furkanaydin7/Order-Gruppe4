const { defineConfig } = require('@playwright/test');

module.exports = defineConfig({
  testDir: './tests', // Verzeichnis für die Tests
  timeout: 30000, // Zeitlimit für jeden Test
  retries: 1, // Anzahl der Wiederholungen bei Fehlern
  reporter: 'list', // Ausgabeformat der Testergebnisse
  use: {
    baseURL: 'http://localhost:8081', // Basis-URL deiner Anwendung
    headless: true, // Headless-Browser verwenden
    screenshot: 'only-on-failure', // Screenshots bei Fehlern
    video: 'retain-on-failure', // Videos bei Fehlern speichern
  },
});

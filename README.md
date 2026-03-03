Quiz-Applikation (Fullstack)
Diese Anwendung ist ein interaktives Quiz-System, das es Nutzern ermöglicht, Fachfragen aus verschiedenen Kategorien zu beantworten. Der individuelle Lernfortschritt wird pro Kategorie getrackt und visualisiert.

🛠 Technologien
Frontend: Next.js (TypeScript, Tailwind CSS)

Backend: Spring Boot (Java, Hibernate, JPA)

Datenbank: MariaDB

Containerisierung: Docker Compose

📂 Projektstruktur
/demo: Das Spring Boot Backend-Projekt.

/quiz-frontend: Die Next.js Frontend-Anwendung.

docker-compose.yml: Konfiguration für die MariaDB-Instanz.

import.sql: Enthält die Seed-Daten (8 Fragen in 2 Kategorien).

🚀 Installation und Start
1. Datenbank starten

Die Anwendung nutzt MariaDB. Starten Sie den Container über Docker Compose:

Bash
docker-compose up -d
2. Backend starten

Navigieren Sie in das Backend-Verzeichnis und starten Sie den Server. Die Datenbank wird beim Start automatisch initialisiert.

Bash
cd demo
./gradlew bootRun
Das Backend läuft auf http://localhost:8080.

3. Frontend starten

Navigieren Sie in das Frontend-Verzeichnis, installieren Sie die Pakete und starten Sie den Entwicklungsserver:

Bash
cd quiz-frontend
npm install
npm run dev
Die Anwendung ist unter http://localhost:3000 erreichbar.

📋 Funktionsweise
API-Endpunkte

GET /api/categories: Liefert alle Kategorien und den aktuellen Fortschritt des Nutzers (berechnet in Prozent und absoluten Zahlen).

GET /api/categories/{id}/next-question: Liefert die nächste noch nicht korrekt beantwortete Frage der gewählten Kategorie.

POST /api/questions/{id}/answer: Überprüft die Antwort des Nutzers und speichert den Erfolg in der Datenbank.

Daten-Seeding

Die Anwendung startet mit einem vordefinierten Fragenkatalog zum Thema Motorboot-Recht und Navigation. Diese Daten werden beim ersten Start automatisch über die import.sql in die MariaDB geladen.
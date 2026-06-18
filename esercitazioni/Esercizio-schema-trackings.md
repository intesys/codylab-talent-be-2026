# Esercitazione SQL — Aggiunta della tabella `trackings` (timesheet)

## Prerequisito

Questa esercitazione è la continuazione di `Esercizio-schema-timesheet.md`.

Prima di procedere, assicuratevi di avere già creato correttamente le seguenti tabelle:

- `clients`
- `projects`
- `users`
- `activities`

---

## Obiettivo dell'esercizio

In questa parte dovete estendere lo schema esistente aggiungendo la tabella `trackings`.

Questa tabella rappresenta il **registro delle ore lavorate**: ogni utente può annotare il tempo speso su una specifica attività, accompagnandolo da una descrizione.

---

## Scenario

Gli utenti del sistema devono poter registrare il tempo speso sulle attività dei progetti.

Ad esempio:

- Mario ha lavorato 90 minuti sull'attività "Sviluppo backend" e vuole lasciare una nota: *"Implementate le API di autenticazione"*
- Sara ha lavorato 120 minuti sull'attività "Testing" con nota: *"Verificati i casi limite del form di login"*

Il sistema deve quindi permettere di rispondere a domande come:

- quanto tempo ha registrato in totale un certo utente?
- quanti minuti sono stati spesi su una certa attività?
- chi ha lavorato su una determinata attività e per quanto tempo?

---

## Entità da aggiungere

### `trackings`
Rappresenta i tracciamenti del tempo effettuati dagli utenti sulle attività.

Ogni tracciamento deve avere:

- un identificativo univoco generato automaticamente
- una descrizione obbligatoria
- il numero di minuti spesi, obbligatorio
- un riferimento all'attività su cui è stato registrato il tempo
- un riferimento all'utente che ha registrato il tempo
- una data di creazione obbligatoria
- una data di aggiornamento facoltativa

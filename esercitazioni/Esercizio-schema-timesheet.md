# Esercitazione SQL — Progettazione di uno schema per la gestione di progetti e timesheet

## Obiettivo dell'esercizio

In questa esercitazione dovete progettare e scrivere uno script SQL per creare lo schema relazionale di una piccola applicazione che gestisce:

- clienti
- progetti
- utenti
- attività

L'obiettivo non è solo far eseguire delle `CREATE TABLE`, ma imparare a:

- individuare le entità principali di un dominio
- tradurre i requisiti in tabelle relazionali
- scegliere correttamente chiavi primarie e chiavi esterne
- impostare vincoli `NOT NULL`
- gestire l'ordine corretto di creazione delle tabelle quando sono presenti relazioni

---

## Scenario

Immaginate di dover realizzare il database di un piccolo gestionale interno.

L'azienda lavora per diversi clienti. Ogni cliente può avere uno o più progetti. Un progetto contiene diverse attività operative. Gli utenti del sistema registrano il tempo speso sulle attività attraverso dei tracciamenti (timesheet).

Il database dovrà quindi essere in grado di rispondere a domande come:

- quali progetti appartengono a un certo cliente?
- quali attività fanno parte di un progetto?
- quanto tempo ha registrato un certo utente?
- quanto tempo è stato speso su una certa attività?

---

## Entità da modellare

Dovete creare **4 tabelle**. Per convenzione, usate nomi in inglese.

### 1. `clients`
Rappresenta i clienti dell'azienda.

Ogni cliente deve avere:

- un identificativo univoco generato automaticamente
- un nome obbligatorio
- una data di creazione obbligatoria
- una data di aggiornamento facoltativa

---

### 2. `projects`
Rappresenta i progetti svolti per i clienti.

Ogni progetto deve avere:

- un identificativo univoco generato automaticamente
- un titolo obbligatorio
- una descrizione facoltativa
- un riferimento al cliente a cui appartiene
- un numero di ore stimate obbligatorio
- una data di inizio obbligatoria
- una data di fine obbligatoria
- una data di creazione obbligatoria
- una data di aggiornamento facoltativa
- uno stato obbligatorio di tipo enum ['CREATED', 'WORKING', 'STANDBY', 'COMPLETED', 'CLOSED'] con default `CREATED`

---

### 3. `users`
Rappresenta gli utenti che lavorano sui progetti e registrano il tempo.

Ogni utente deve avere:

- un identificativo univoco generato automaticamente
- nome obbligatorio
- cognome obbligatorio
- username obbligatorio
- password obbligatoria
- data di creazione obbligatoria
- data di aggiornamento facoltativa

---

### 4. `activities`
Rappresenta le attività operative appartenenti a un progetto.

Ogni attività deve avere:

- un identificativo univoco generato automaticamente
- un nome obbligatorio
- un numero di ore stimate obbligatorio
- un riferimento al progetto a cui appartiene
- una data di creazione obbligatoria
- una data di aggiornamento facoltativa

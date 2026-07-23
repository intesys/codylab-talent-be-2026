# Esercitazione – Progettazione di API REST con OpenAPI 3.1

## Obiettivo

L'obiettivo dell'esercitazione è consolidare le conoscenze acquisite durante la lezione sulla scrittura di una specifica **OpenAPI 3.1**, progettando autonomamente nuove risorse REST seguendo lo stesso approccio utilizzato per la risorsa **Project**.

L'attività dovrà essere svolta in gruppo e permetterà di prendere confidenza con la definizione di endpoint, modelli dati, request, response e codici di stato HTTP.

---

# Organizzazione dei gruppi

La classe dovrà essere suddivisa in **3 gruppi**, ciascuno composto da **2 studenti**.

Ad ogni gruppo verrà assegnata una risorsa differente.

| Gruppo | Risorsa |
|---------|----------|
| Gruppo 1 | Activity |
| Gruppo 2 | Tracking |
| Gruppo 3 | Customer |

---

# Attività richiesta

Ogni gruppo dovrà realizzare la specifica OpenAPI relativa alla propria risorsa, mantenendo la stessa struttura e lo stesso livello di dettaglio utilizzati durante la lezione per la risorsa **Project**.

La specifica dovrà essere scritta in formato **YAML** e integrata all'interno del progetto OpenAPI esistente.

---

# Requisiti

Per la risorsa assegnata dovranno essere definiti i seguenti endpoint REST:

| Metodo | Endpoint | Descrizione |
|---------|----------|-------------|
| GET | `/risorsa` | Restituisce tutti gli elementi |
| POST | `/risorsa` | Crea un nuovo elemento |
| GET | `/risorsa/{id}` | Restituisce un elemento specifico |
| PUT | `/risorsa/{id}` | Aggiorna un elemento esistente |
| DELETE | `/risorsa/{id}` | Elimina un elemento |

Sostituire **risorsa** con il nome assegnato al proprio gruppo.

---

# Modello dati

Ogni gruppo dovrà definire lo schema (`schema`) della propria risorsa nella sezione:

```yaml
components:
  schemas:
```

Lo schema dovrà contenere un insieme coerente di proprietà, scegliendo opportunamente:

- tipo dei campi (`string`, `integer`, `boolean`, `number`, ecc.);
- eventuali `format` (`date`, `date-time`, `email`, `int64`, ecc.);
- descrizioni (`description`);
- esempi (`example`);
- eventuali valori enumerati (`enum`);
- campi obbligatori (`required`).

---

# Responses

Per ogni endpoint dovranno essere documentate le principali risposte HTTP.

In particolare:

| Codice | Quando utilizzarlo |
|---------|--------------------|
| 200 | Operazione completata con successo |
| 201 | Creazione avvenuta correttamente |
| 204 | Eliminazione completata |
| 400 | Richiesta non valida |
| 404 | Risorsa non trovata |

Per gli errori dovrà essere riutilizzato lo schema **Problem**, già definito durante la lezione.

---

# Convenzioni

Per mantenere uniforme la specifica, attenersi alle seguenti convenzioni:

- assegnare una `tag` dedicata alla risorsa (es. `activity-controller`);
- definire un `summary` descrittivo per ogni operazione;
- utilizzare un `operationId` significativo;
- utilizzare `$ref` per richiamare gli schemi definiti nella sezione `components`;
- utilizzare `application/json` come formato per request e response.

---

# Consegna

Al termine dell'attività ogni gruppo dovrà presentare:

- il file OpenAPI aggiornato;
- una breve spiegazione della struttura realizzata;
- le motivazioni delle scelte effettuate nella modellazione della propria risorsa.

Durante la correzione verranno confrontate le tre specifiche per evidenziare analogie, differenze e possibili miglioramenti.

---

# Criteri di valutazione

Saranno valutati i seguenti aspetti:

- correttezza sintattica della specifica OpenAPI;
- utilizzo appropriato dei metodi HTTP;
- corretta definizione delle request e delle response;
- riutilizzo degli schemi mediante `$ref`;
- completezza della documentazione;
- coerenza con la specifica della risorsa **Project** sviluppata durante la lezione.
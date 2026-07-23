# Lezione – Definizione di una API REST mediante OpenAPI 3.1

## Obiettivo della lezione

Durante questa lezione è stata realizzata la specifica **OpenAPI** di un semplice servizio REST per la gestione dei progetti (*Project Management*).

Gli obiettivi della lezione erano:

* comprendere il ruolo di OpenAPI nella progettazione e documentazione delle API REST;
* conoscere la struttura di un file OpenAPI in formato YAML;
* descrivere endpoint REST utilizzando i principali metodi HTTP;
* definire modelli dati riutilizzabili;
* documentare richieste, risposte e codici di stato HTTP.

Al termine della lezione gli studenti sono in grado di leggere e scrivere una semplice specifica OpenAPI e comprenderne la struttura.

---

# Cos'è OpenAPI

**OpenAPI** è uno standard che permette di descrivere in maniera formale un servizio REST.

Una specifica OpenAPI rappresenta un **contratto** tra client e server: definisce in modo univoco come un'applicazione può interagire con il servizio senza doverne conoscere l'implementazione.

Tra i principali vantaggi vi sono:

* generazione automatica della documentazione (Swagger UI);
* generazione di client per diversi linguaggi (Java, TypeScript, C#, Python, ecc.);
* generazione dello scheletro del server;
* validazione automatica delle richieste e delle risposte;
* miglioramento della collaborazione tra frontend e backend.

Nel nostro esempio utilizziamo la versione **3.1.0** dello standard.

```yaml
openapi: 3.1.0
```

---

# Struttura di una specifica OpenAPI

Una specifica OpenAPI è generalmente composta dalle seguenti sezioni:

```text
openapi
info
paths
components
```

## Sezione `openapi`

Indica la versione dello standard utilizzata.

```yaml
openapi: 3.1.0
```

---

## Sezione `info`

Contiene le informazioni descrittive dell'API.

```yaml
info:
  title: CodyLab OpenAPI definition
  version: v0
```

Normalmente questa sezione contiene:

* titolo dell'API;
* versione;
* descrizione;
* informazioni di contatto;
* licenza.

---

# La sezione `paths`

La sezione `paths` rappresenta il cuore della specifica.

Ogni elemento definisce una risorsa REST.

Ad esempio:

```yaml
/projects
```

Su una stessa risorsa possono essere disponibili più operazioni HTTP:

* GET
* POST
* PUT
* DELETE

Ogni operazione viene descritta in modo indipendente.

---

# Descrizione di un endpoint

Ogni endpoint contiene diverse informazioni che permettono di documentarne il comportamento.

Esempio:

```yaml
get:
  tags:
    - project-controller
  summary: Get all projects
  operationId: getAllProjects
```

## `tags`

Le **tag** servono a raggruppare gli endpoint nella documentazione.

Nel nostro esempio troviamo due gruppi:

* `auth-controller`
* `project-controller`

---

## `summary`

Fornisce una breve descrizione dell'operazione.

Esempio:

```
Get all projects
```

Questa descrizione viene visualizzata automaticamente da Swagger UI.

---

## `operationId`

Identifica univocamente l'operazione.

È particolarmente importante perché viene utilizzato dagli strumenti di generazione automatica del codice.

Ad esempio:

```
getAllProjects
```

può diventare automaticamente il nome di un metodo Java nell'interfaccia del controller.

---

# Request Body

Le operazioni che ricevono dati dal client (ad esempio **POST** e **PUT**) definiscono un `requestBody`.

Esempio:

```yaml
requestBody:
  required: true
  content:
    application/json:
      schema:
        $ref: '#/components/schemas/Project'
```

In questo caso viene specificato che:

* il body della richiesta è obbligatorio;
* il formato è JSON;
* il contenuto segue lo schema `Project`.

---

# Responses

Ogni endpoint deve descrivere le possibili risposte.

Esempio:

```yaml
responses:
  '200':
    description: Successful response
```

Per ciascun codice HTTP possono essere definiti:

* descrizione;
* tipo di contenuto;
* schema dei dati restituiti.

Ad esempio:

```yaml
content:
  application/json:
    schema:
      $ref: '#/components/schemas/Project'
```

indica che la risposta contiene un oggetto `Project` in formato JSON.

---

# Codici di stato HTTP

Durante la lezione sono stati utilizzati i principali codici di stato HTTP.

| Codice | Significato                                            |
| ------ | ------------------------------------------------------ |
| 200    | Operazione eseguita con successo                       |
| 201    | Risorsa creata correttamente                           |
| 204    | Eliminazione completata senza contenuto nella risposta |
| 400    | Richiesta non valida                                   |
| 401    | Credenziali non valide                                 |
| 404    | Risorsa non trovata                                    |

L'utilizzo corretto dei codici HTTP permette al client di comprendere immediatamente l'esito della richiesta.

---

# Parametri del Path

Quando un endpoint opera su una specifica risorsa viene utilizzato un parametro nel percorso.

Esempio:

```text
/projects/{projectId}
```

Il parametro viene definito nella sezione `parameters`.

```yaml
parameters:
  - name: projectId
    in: path
    required: true
    schema:
      type: integer
      format: int64
```

In questo modo vengono documentati:

* nome del parametro;
* posizione (`path`);
* tipo;
* obbligatorietà.

---

# La sezione `components`

La sezione `components` contiene gli elementi riutilizzabili della specifica.

Nel nostro esempio vengono definiti gli schemi dati all'interno di:

```yaml
components:
  schemas:
```

Gli schemi possono essere richiamati in qualsiasi punto mediante il riferimento:

```yaml
$ref
```

Ad esempio:

```yaml
$ref: '#/components/schemas/Project'
```

Questo approccio evita duplicazioni e rende la specifica più semplice da mantenere.

---

# Schema `LoginRequest`

Lo schema `LoginRequest` rappresenta il body della richiesta di autenticazione.

Contiene due proprietà:

* `username`
* `password`

Entrambe sono obbligatorie.

```yaml
required:
  - username
  - password
```

---

# Schema `User`

Lo schema `User` rappresenta la risposta del servizio di autenticazione.

Le informazioni principali sono:

* identificativo utente;
* username;
* email;
* nome;
* cognome;
* access token.

L'`accessToken` rappresenta il token JWT che il client utilizzerà per autenticare le chiamate successive.

---

# Schema `Project`

Lo schema `Project` rappresenta il modello principale dell'applicazione.

Le informazioni descritte sono:

* identificativo;
* titolo;
* descrizione;
* ore stimate;
* stato del progetto;
* data di inizio;
* data di fine;
* data di creazione;
* data di aggiornamento.

Durante la lezione è stato mostrato come documentare accuratamente i campi utilizzando:

* `description`;
* `example`;
* `format`;
* `enum`.

Ad esempio, il campo `status` può assumere esclusivamente i seguenti valori:

```yaml
status:
  type: string
  enum:
    - CREATED
    - WORKING
    - STANDBY
    - COMPLETED
    - CLOSED
```

---

# Schema `Problem`

Per documentare le risposte di errore è stato definito uno schema denominato `Problem`.

Questo modello segue lo standard **RFC 7807 – Problem Details for HTTP APIs** e consente di restituire errori strutturati e facilmente interpretabili dai client.

Le informazioni principali contenute nello schema sono:

* tipo dell'errore;
* titolo;
* codice HTTP;
* descrizione dettagliata;
* identificativo dell'errore.

L'utilizzo di uno schema standardizzato semplifica la gestione degli errori lato client e rende l'API più uniforme.

---

# Endpoint realizzati

Nel corso della lezione sono stati definiti i seguenti endpoint REST.

| Metodo | Endpoint                | Descrizione                       |
| ------ | ----------------------- | --------------------------------- |
| POST   | `/api/auth/login`       | Autenticazione dell'utente        |
| GET    | `/projects`             | Restituisce tutti i progetti      |
| POST   | `/projects`             | Crea un nuovo progetto            |
| GET    | `/projects/{projectId}` | Restituisce un progetto specifico |
| PUT    | `/projects/{projectId}` | Aggiorna un progetto esistente    |
| DELETE | `/projects/{projectId}` | Elimina un progetto               |

---

# Concetti appresi

Al termine della lezione gli studenti hanno acquisito i seguenti concetti:

* struttura di una specifica OpenAPI 3.1;
* organizzazione delle API tramite la sezione `paths`;
* utilizzo dei principali metodi HTTP (GET, POST, PUT e DELETE);
* definizione di request e response;
* utilizzo corretto dei codici di stato HTTP;
* modellazione dei dati mediante `components/schemas`;
* riutilizzo degli schemi attraverso `$ref`;
* documentazione dei campi mediante `description`, `example`, `format` ed `enum`;
* gestione standardizzata degli errori tramite il modello `Problem`.

---

# Conclusioni

La specifica OpenAPI costituisce il punto di partenza per lo sviluppo dell'applicazione.

Nelle lezioni successive essa verrà utilizzata per:

* generare automaticamente il codice Java dei modelli e delle interfacce REST;
* integrare la documentazione interattiva mediante Swagger UI;
* garantire la coerenza tra implementazione del server e applicazioni client.

L'approccio **API First**, basato sulla definizione preventiva del contratto OpenAPI, favorisce uno sviluppo più strutturato, una migliore collaborazione tra i componenti del team e una maggiore qualità complessiva del software.

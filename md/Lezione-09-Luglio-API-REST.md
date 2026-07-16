# Lezione: Introduzione alle API REST e relativa implementazione con Spring Boot

## Obiettivi

In questa lezione abbiamo imparato a:

-   comprendere cos'è un'API REST;
-   conoscere i principali metodi HTTP;
-   utilizzare status code, body e path variable;
-   realizzare una semplice API CRUD;
-   organizzare il codice in livelli
    (`RestController -> Service -> Repository`);
-   generare la documentazione OpenAPI.

## Cos'è un'API REST

Un'API REST (Representational State Transfer) permette a client e server
di comunicare tramite il protocollo HTTP.

Una richiesta HTTP è composta principalmente da:

-   **Metodo HTTP** (GET, POST, PUT, DELETE)
-   **URL**
-   **Header**
-   **Body** (quando necessario)

Il server elabora la richiesta e restituisce una risposta contenente:

-   uno **status code**;
-   eventuale **body** con i dati.
-   occasionalmente, uno o più **header** con informazioni aggiuntive.

## Metodi HTTP

| Metodo | Utilizzo |
| --- | --- |
| GET | Lettura dei dati |
| POST | Creazione di una nuova risorsa |
| PUT | Aggiornamento completo di una risorsa |
| DELETE | Eliminazione di una risorsa |

## Status Code più comuni

| Codice | Significato |
| --- | --- |
| 200 OK | Operazione completata con successo |
| 201 Created | Risorsa creata |
| 204 No Content | Eliminazione completata senza body |
| 400 Bad Request | Richiesta non valida |
| 404 Not Found | Risorsa non trovata |
| 500 Internal Server Error | Errore del server |

## Path Variable

Le Path Variable permettono di identificare una risorsa.

Esempio:

``` http
GET /projects/3
```

Il valore `3` rappresenta l'identificativo del progetto.

## Request Body

Per le operazioni di creazione e modifica il client invia i dati nel
body della richiesta.

Esempio:

``` json
{
  "title": "CRM Aziendale",
  "estimatedHours": 320
}
```

## Dal protocollo HTTP al `RestController`

Spring Boot mette a disposizione l'annotazione `@RestController`, che
permette di esporre facilmente una classe Java come API REST.

Ogni metodo del controller viene associato ad un endpoint HTTP tramite
apposite annotazioni.

Nel nostro progetto abbiamo realizzato il controller
`ProjectController`, che espone tutte le operazioni sull'entità
`Project`.

``` java
@RestController
@RequestMapping("/projects")
public class ProjectController {
    ...
}
```

Questo significa che:

-   la classe espone una API REST;
-   tutti gli endpoint iniziano con il prefisso `/projects`.

## Metodi HTTP e mapping

### GET - Elenco dei progetti

``` java
@GetMapping
public ResponseEntity<List<Project>> findAll()
```

Richiesta:

``` http
GET /projects
```

Restituisce:

-   **200 OK**
-   elenco dei progetti nel body.

### GET - Dettaglio

``` java
@GetMapping("/{projectId}")
public ResponseEntity<Project> findById(@PathVariable Long projectId)
```

Richiesta:

``` http
GET /projects/5
```

`@PathVariable` legge l'identificativo direttamente dall'URL.

Risposte:

-   **200 OK** se il progetto esiste;
-   **404 Not Found** se non esiste.

### POST - Inserimento

``` java
@PostMapping
public ResponseEntity<Long> insert(@RequestBody Project project)
```

Richiesta:

``` http
POST /projects
```

Il body contiene il JSON del nuovo progetto.

`@RequestBody` converte automaticamente il JSON in un oggetto `Project`.

Risposta:

| Elemento | Descrizione |
| --- | --- |
| 201 Created | Risposta della creazione |
| Location | Header con l'URL della nuova risorsa |

### PUT - Aggiornamento

``` java
@PutMapping("/{projectId}")
public ResponseEntity<Void> update(...)
```

Richiesta:

``` http
PUT /projects/6
```

Risposte:

-   **200 OK**
-   **404 Not Found**

### DELETE - Eliminazione

``` java
@DeleteMapping("/{projectId}")
public ResponseEntity<Void> delete(...)
```

Richiesta:

``` http
DELETE /projects/6
```

Risposte:

-   **200 OK**
-   **404 Not Found**

## Architettura

    RestController
          |
       Service
          |
     Repository

In questa fase del corso il repository restituisce semplici oggetti Java
(`Project`). Non è ancora stata introdotta JPA.

## CRUD dell'entità Project

Abbiamo realizzato le cinque operazioni principali.

| Metodo | Endpoint | Descrizione |
| --- | --- | --- |
| GET | /projects | Restituisce tutti i progetti |
| GET | /projects/{id} | Restituisce un progetto tramite id |
| POST | /projects | Inserisce un nuovo progetto |
| PUT | /projects/{id} | Aggiorna un progetto |
| DELETE | /projects/{id} | Elimina un progetto |

Durante questa esercitazione il `Repository` restituisce direttamente
gli oggetti `Project`, che vengono inviati come risposta dal controller.

## Test delle API REST

Per verificare il corretto funzionamento dell'API abbiamo utilizzato:

``` java
@SpringBootTest
@AutoConfigureMockMvc
```

e l'oggetto:

``` java
@Autowired
MockMvc mvc;
```

Con `MockMvc` è possibile simulare richieste HTTP senza avviare un
server.

Esempio:

``` java
mvc.perform(get("/projects"))
```

### Verifica dello status HTTP

``` java
.andExpect(status().isOk())
```

Altri controlli:

-   `isCreated()`
-   `isBadRequest()`
-   `isNotFound()`

### Verifica del JSON

``` java
.andExpect(jsonPath("$[0].title").value("CRM Aziendale"))
```

oppure

``` java
.andExpect(jsonPath("$.status").value("CLOSED"))
```

## Cosa abbiamo testato

-   GET `/projects`
-   GET `/projects/{id}`
-   GET con id inesistente
-   POST con creazione di un progetto
-   POST con dati non validi
-   PUT di aggiornamento
-   DELETE

## OpenAPI

Infine abbiamo configurato il progetto per generare automaticamente la
documentazione OpenAPI, che descrive:

-   endpoint disponibili;
-   parametri;
-   request body;
-   response;
-   status code.


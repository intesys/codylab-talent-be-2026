# Esercitazione - Implementazione di API REST

## Obiettivo

Dividersi in gruppi di **2 persone** e realizzare le API REST complete
per l'entità assegnata.

Ogni gruppo dovrà seguire la stessa architettura vista a lezione:

``` text
RestController
      |
   Service
      |
 Repository
```

In questa esercitazione **non utilizzeremo ancora JPA**: il repository
continuerà a gestire semplici oggetti Java.

## Attività richieste

Per l'entità assegnata implementare:

-   GET di tutti gli elementi
-   GET per id
-   POST di inserimento
-   PUT di aggiornamento
-   DELETE di eliminazione

Gli endpoint dovranno restituire gli opportuni status HTTP (`200`,
`201`, `404`, `400` quando necessario).

## Gruppi

### Gruppo 1 - Activities

Implementare le CRUD dell'entità `Activity`.

Endpoint:

-   `GET /activities`
-   `GET /activities/{activityId}`
-   `POST /activities`
-   `PUT /activities/{activityId}`
-   `DELETE /activities/{activityId}`

------------------------------------------------------------------------

### Gruppo 2 - Trackings

Implementare le CRUD dell'entità `Tracking`.

Endpoint:

-   `GET /trackings`
-   `GET /trackings/{trackingId}`
-   `POST /trackings`
-   `PUT /trackings/{trackingId}`
-   `DELETE /trackings/{trackingId}`

------------------------------------------------------------------------

### Gruppo 3 - Customers

Implementare le CRUD dell'entità `Customer`.

Endpoint:

-   `GET /customers`
-   `GET /customers/{customerId}`
-   `POST /customers`
-   `PUT /customers/{customerId}`
-   `DELETE /customers/{customerId}`

## Test

Per ogni endpoint realizzare i test di integrazione utilizzando:

``` java
@SpringBootTest
@AutoConfigureMockMvc
```

Verificare almeno i seguenti casi:

-   recupero della lista;
-   recupero di un elemento esistente;
-   recupero di un elemento inesistente (404);
-   inserimento corretto (201 Created);
-   aggiornamento di un elemento esistente;
-   eliminazione di un elemento esistente

## Consegna

Ogni gruppo dovrà consegnare:

-   RestController
-   Service
-   Repository
-   Test di integrazione con MockMvc


# Esercitazione – Implementazione del Controller REST (a partire dalla specifica OpenAPI)

## Obiettivo

Dopo aver definito la specifica OpenAPI e aver generato automaticamente le interfacce dei controller e i DTO, l'obiettivo di questa esercitazione è completare l'implementazione delle API fino al database.

Ogni gruppo dovrà sviluppare il livello REST della propria risorsa, riutilizzando l'architettura vista durante la lezione.

---

# Suddivisione dei gruppi

| Gruppo | Risorsa |
|---------|----------|
| Gruppo 1 | Activity |
| Gruppo 2 | Tracking |
| Gruppo 3 | Customer |

---

# Attività da svolgere

Per la risorsa assegnata ogni gruppo dovrà:

1. Creare il controller REST che implementa l'interfaccia generata da OpenAPI Generator.
2. Annotare il controller con `@RestController`.
3. Implementare tutti gli endpoint CRUD previsti dalla specifica OpenAPI.
4. Utilizzare il Service già realizzato (o svilupparlo se necessario) per la logica applicativa.
5. Utilizzare un'interfaccia `@Mapper` di MapStruct per convertire tra DTO ed Entity.
6. Verificare il corretto funzionamento degli endpoint fino al database.

---

# Architettura da realizzare

L'implementazione dovrà seguire il seguente flusso:

```text
HTTP Request
      │
      ▼
RestController
      │
      ▼
Mapper (DTO → Entity)
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
Database
      │
      ▼
Repository
      │
      ▼
Service
      │
      ▼
Mapper (Entity → DTO)
      │
      ▼
HTTP Response
```

---

# Endpoint da implementare

Implementare tutti gli endpoint CRUD descritti nella propria specifica OpenAPI.

Indicativamente:

- recupero di tutte le risorse;
- recupero di una risorsa tramite ID;
- creazione di una nuova risorsa;
- modifica di una risorsa esistente;
- eliminazione di una risorsa.

Gli endpoint dovranno rispettare esattamente quanto definito nella specifica OpenAPI.

---

# Requisiti

L'implementazione dovrà rispettare le seguenti indicazioni:

- utilizzare esclusivamente i DTO generati da OpenAPI Generator nel livello REST;
- non utilizzare direttamente le Entity nei controller;
- demandare tutta la logica applicativa al Service;
- utilizzare MapStruct per la conversione tra DTO ed Entity;
- evitare conversioni manuali dei campi;
- mantenere separati i livelli Controller, Service e Repository.

---

# Verifica finale

Al termine dell'esercitazione dovrà essere possibile utilizzare Postman (o un altro client HTTP) per invocare tutti gli endpoint della risorsa assegnata e verificarne il corretto funzionamento.

Ogni operazione CRUD dovrà:

- raggiungere il Service;
- interagire con il database;
- restituire il DTO previsto dalla specifica OpenAPI;
- produrre i codici di risposta HTTP corretti.

---

# Consegna

Ogni gruppo dovrà consegnare:

- il `RestController` completo;
- l'interfaccia `Mapper` realizzata con MapStruct;
- eventuali modifiche al Service necessarie;
- il progetto funzionante, con tutti gli endpoint CRUD operativi e testabili tramite Postman;
- valore aggiunto: test di integrazione per il nuovo controller
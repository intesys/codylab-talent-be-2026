# Esercitazione: Esposizione delle API REST per Activities e Trackings

## Obiettivo

In questa esercitazione dovrete completare il flusso completo:

```text
HTTP Request
      ↓
Controller
      ↓
Service
      ↓
Repository
      ↓
Database
```

partendo dai repository già esistenti.

L'obiettivo è esporre due nuove API REST che restituiscono tutti i dati presenti nelle rispettive tabelle.

---

# Situazione iniziale

Sono già disponibili:

- `TrackingRepository`
- `ActivityRepository`

Entrambi espongono il metodo:

```java
findAll()
```

che recupera tutti i record dal database.

Esempio:

```java
public class TrackingRepository {

    public List<Tracking> findAll() {
        ...
    }

}
```

```java
public class ActivityRepository {

    public List<Activity> findAll() {
        ...
    }

}
```

---

# Attività da svolgere

1. Creare TrackingService.findAll()

Creare la classe `TrackingService` e al suo interno il metodo:

```java
public List<Tracking> findAll()
```

2. Analogamente creare ActivityService.findAll()

3. Esporre la creazione dei due service attraverso i rispettivi metodi `@Bean` nella classe di configurazione
(annotata @Configuration).

4. Creare TrackingController che esponga il seguente endpoint:

```http
GET /trackings
```

Il controller dovrà utilizzare il `TrackingService`.

La chiamata:

```http
GET /trackings
```

dovrà restituire:

```json
[
  {
    ...
  },
  {
    ...
  }
]
```

ovvero la lista dei tracking restituita dal service.

5. Analogamente creare ActivityController che esponga il seguente endpoint:

```http
GET /activities
```

Il controller dovrà utilizzare il `ActivityService`.

---

# Architettura finale

## Tracking

```text
GET /trackings
        ↓
TrackingController
        ↓
TrackingService
        ↓
TrackingRepository
        ↓
Database
```

---

## Activity

```text
GET /activities
        ↓
ActivityController
        ↓
ActivityService
        ↓
ActivityRepository
        ↓
Database
```

---

# Test delle API

Per ciascun controller dovrà essere creato un test.

L'obiettivo del test è verificare che la chiamata REST risponda correttamente.

---

# TrackingControllerIntTest

Creare un test che esegua:

```http
GET /trackings
```

e verifichi che la risposta abbia status:

```http
200 OK
```

---

# ActivityControllerIntTest

Creare un test che esegua:

```http
GET /activities
```

e verifichi che la risposta abbia status:

```http
200 OK
```

---

# Suggerimento

Procedere sempre in questo ordine:

1. Service
2. Controller
3. Avvio applicazione
4. Verifica con Postman
5. Scrittura dei test
6. Esecuzione dei test

In caso di problemi, verificare prima che l'endpoint funzioni manualmente e solo successivamente scrivere il test automatico.
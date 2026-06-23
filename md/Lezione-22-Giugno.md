# Tracking Service: PostgreSQL in Main, H2 nei Test

## Obiettivo

In questo progetto vogliamo separare:

- la logica applicativa (`TrackingService`)
- l'accesso ai dati (`TrackingRepository`)
- la configurazione del database (`DataSource`)

In questo modo possiamo utilizzare:

- PostgreSQL durante l'esecuzione normale dell'applicazione
- H2 in memoria durante i test

senza modificare il codice del service.

---

# Architettura

```text
TrackingService
        |
        v
TrackingRepository
        |
        v
    DataSource
        ^
        |
+-------+--------+
|                |
|                |
PostgreSQL     H2
```

---

# Il Service

Il service contiene la logica applicativa.

Non deve sapere quale database viene utilizzato.

```java
public class TrackingService {

    private final TrackingRepository repository;

    public TrackingService(TrackingRepository repository) {
        this.repository = repository;
    }

    public void track(Tracking tracking) {
        // logica di controllo
        repository.save(tracking);
    }
}
```

Il service dipende dal repository.

---

# Il Repository

Il repository contiene la logica di accesso ai dati.

```java
public class TrackingRepository {

    private final DataSource dataSource;

    public TrackingRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(String event) {

        try(Connection connection = dataSource.getConnection()) {

            // INSERT nel database

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```

Il repository non conosce PostgreSQL o H2.

Conosce soltanto l'interfaccia `DataSource`.

---

# Esecuzione normale (Main)

Nel programma principale utilizziamo PostgreSQL.

```java
DataSource dataSource =
        createPostgresDataSource();

TrackingRepository repository =
        new TrackingRepository(dataSource);

TrackingService service =
        new TrackingService(repository);
```

Schema:

```text
TrackingService
        |
        v
TrackingRepository
        |
        v
PostgreSQL DataSource
        |
        v
PostgreSQL
```

I dati vengono salvati nel database reale.

---

# Esecuzione nei Test

Nei test utilizziamo H2.

```java
DataSource dataSource =
        createH2DataSource();

TrackingRepository repository =
        new TrackingRepository(dataSource);

TrackingService service =
        new TrackingService(repository);
```

Schema:

```text
TrackingService
        |
        v
TrackingRepository
        |
        v
H2 DataSource
        |
        v
H2 In Memory
```

Il service non cambia.

Il repository non cambia.

Cambia soltanto il DataSource.

---

# Perché funziona?

Perché TrackingRepository dipende da:

```java
javax.sql.DataSource
```

e non da:

```java
PostgresDataSource
```

oppure

```java
H2DataSource
```

---

# Vantaggio 1: Minore accoppiamento

Se il repository creasse direttamente PostgreSQL:

```java
public class TrackingRepository {

    public TrackingRepository() {

        this.dataSource =
            new PostgresDataSource();

    }
}
```

sarebbe impossibile sostituire il database.

Inoltre ogni modifica richiederebbe modifiche al repository.

---

# Vantaggio 2: Test veloci

H2 viene creato in memoria.

```text
Avvio test
    ↓
Creo database H2
    ↓
Eseguo test
    ↓
Distruggo database
```

Non serve:

- installare PostgreSQL
- avviare Docker
- creare utenti
- creare database

---

# Vantaggio 3: Test isolati

Ogni test parte da una situazione pulita.

Non rischiamo di:

- modificare dati reali
- cancellare dati reali
- dipendere da dati presenti nel database locale

---

# Vantaggio 4: Preparazione a Spring

Più avanti sarà Spring a costruire gli oggetti.

Oggi scriviamo:

```java
TrackingRepository repository =
        new TrackingRepository(dataSource);
```

Con Spring scriveremo semplicemente:

```java
@Repository
public class TrackingRepository {

    private final DataSource dataSource;

    public TrackingRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
```

e sarà Spring a fornire automaticamente il DataSource corretto.

---

# Conclusione

Il vantaggio principale non è usare PostgreSQL o H2.

Il vantaggio è che:

- TrackingService non conosce il database
- TrackingRepository non conosce il database specifico
- il database può essere sostituito senza modificare il codice applicativo

Questo è uno dei primi esempi concreti di:

- Dependency Injection
- Inversion of Control
- Dependency Inversion Principle

che sono alla base di Spring e delle moderne applicazioni Java.
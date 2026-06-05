# CodyLab Talent 2026

# Maven, Classpath, JDBC, HikariCP e PostgreSQL

## Obiettivo

In questo modulo impariamo come costruire un'applicazione Java che accede ad un database PostgreSQL utilizzando:

* Maven
* Classpath
* JDBC
* HikariCP
* Docker Compose
* Repository Pattern

L'obiettivo non è imparare tutte le librerie in profondità, ma comprendere come collaborano tra loro.

---

# Architettura Finale

```text
pom.xml
   ↓
Maven
   ↓
Classpath
   ↓
Driver PostgreSQL
   ↓
HikariCP
   ↓
DataSource
   ↓
Repository
   ↓
JDBC
   ↓
PostgreSQL
   ↓
Docker
```

Questa architettura rappresenta una tipica applicazione Java che accede ad un database relazionale utilizzando JDBC e un connection pool.

---

# 1. Perché esiste Maven?

Prima dell'introduzione di strumenti come Maven, un progetto Java conteneva spesso una cartella simile a questa:

```text
progetto
├── src
├── lib
│   ├── mysql.jar
│   ├── slf4j.jar
│   └── logback.jar
```

Ogni sviluppatore doveva:

* scaricare manualmente i file `.jar`
* aggiornarli manualmente
* copiarli agli altri membri del team

Questo generava problemi come:

```text
Funziona sul mio PC
Non funziona sul tuo
```

---

# 2. Cos'è Maven?

Maven è uno strumento che aiuta a:

* gestire le dipendenze
* compilare il progetto
* eseguire i test
* creare pacchetti distribuibili
* standardizzare la struttura del progetto

---

# 3. Il file pom.xml

Il cuore di un progetto Maven è il file:

```text
pom.xml
```

Esempio:

```xml
<project>
    <groupId>it.intesys.codylab</groupId>
    <artifactId>codylab-talent-2026</artifactId>
    <version>1.0-SNAPSHOT</version>
</project>
```

Questo file descrive:

* il nome del progetto
* la versione
* le dipendenze
* la configurazione della build

---

# 4. Le dipendenze

Supponiamo di voler utilizzare PostgreSQL.

Nel pom.xml aggiungiamo:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.7</version>
</dependency>
```

Quando eseguiamo:

```bash
mvn compile
```

Maven scarica automaticamente il driver PostgreSQL.

---

# 5. Repository Maven

Le librerie vengono salvate localmente in:

```text
~/.m2/repository
```

Ad esempio:

```text
.m2
 └── repository
      └── org
           └── postgresql
                └── postgresql-42.7.7.jar
```

La libreria viene scaricata una sola volta e riutilizzata da tutti i progetti.

---

# 6. Ciclo di vita Maven

Maven definisce un ciclo di vita standard.

Le fasi più importanti sono:

```text
clean
compile
test
package
install
```

## Clean

```bash
mvn clean
```

Cancella i file generati dalla compilazione.

---

## Compile

```bash
mvn compile
```

Compila il codice sorgente.

---

## Test

```bash
mvn test
```

Esegue tutti i test automatici.

---

## Package

```bash
mvn package
```

Esegue automaticamente:

```text
validate
compile
test
package
```

e genera un artefatto distribuibile.

---

# 7. JUnit e Maven

I test si trovano normalmente in:

```text
src/test/java
```

Esempio:

```java
@Test
void deveSommareDueNumeri() {
    assertEquals(5, 2 + 3);
}
```

Quando eseguiamo:

```bash
mvn test
```

Maven individua automaticamente i test e li esegue.

---

# 8. Cos'è il Classpath?

La JVM deve sapere dove cercare le classi.

Esempio:

```java
Logger logger =
        LoggerFactory.getLogger(Main.class);
```

La classe:

```java
LoggerFactory
```

non si trova nel nostro progetto.

Si trova all'interno di:

```text
slf4j-api.jar
```

## Classpath generato da IntelliJ

Quando eseguiamo l'applicazione IntelliJ genera un classpath simile:

```text
-classpath
target/classes;
slf4j-api.jar;
logback.jar;
postgresql.jar
```

La JVM cerca le classi in tutti questi percorsi.

---

# 9. PostgreSQL

PostgreSQL è un database relazionale.

Le informazioni vengono salvate in tabelle.

Esempio:

```sql
CREATE TABLE prodotto (
    id BIGSERIAL PRIMARY KEY,
    descrizione VARCHAR(255),
    prezzo NUMERIC(10,2)
);
```

Ogni riga rappresenta un prodotto.

---

# 10. Docker Compose

Per evitare installazioni manuali utilizziamo Docker.

Docker permette di eseguire PostgreSQL dentro un container.

Esempio:

```yaml
services:

  postgresql:
    image: postgres:17

    environment:
      POSTGRES_DB: codylab
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres

    ports:
      - "5432:5432"
```

Avvio:

```bash
docker compose up
```

---

# 11. JDBC

JDBC (Java Database Connectivity) è l'API standard di Java per comunicare con un database.

Le classi principali sono:

```java
Connection
PreparedStatement
ResultSet
```

## Flusso JDBC

```text
Connection
    ↓
PreparedStatement
    ↓
executeQuery()
    ↓
ResultSet
```

## Esempio

```java
Connection connection =
        dataSource.getConnection();

PreparedStatement statement =
        connection.prepareStatement(
                "SELECT * FROM prodotto");

ResultSet rs =
        statement.executeQuery();
```

---

# 12. PreparedStatement

Mai costruire query concatenando stringhe.

Sbagliato:

```java
String sql =
        "SELECT * FROM prodotto WHERE id = " + id;
```

Corretto:

```java
PreparedStatement statement =
        connection.prepareStatement(
                "SELECT * FROM prodotto WHERE id = ?");
```

Valorizzazione:

```java
statement.setLong(1, id);
```

Vantaggi:

* maggiore sicurezza
* prevenzione SQL Injection
* migliore gestione dei parametri

---

# 13. Try-With-Resources

Le risorse JDBC devono essere chiuse.

Java permette di farlo automaticamente:

```java
try (
    var connection = dataSource.getConnection();
    var statement = connection.prepareStatement(sql)
) {
    ...
}
```

Alla fine del blocco:

```text
Connection chiusa
Statement chiuso
ResultSet chiuso
```

---

# 14. HikariCP

HikariCP è un Connection Pool.

Gestisce le connessioni verso il database.

## Senza Hikari

Per ogni operazione:

```text
Apri connessione
Esegui query
Chiudi connessione
```

Operazione costosa.

## Con Hikari

Le connessioni vengono mantenute aperte.

```text
Pool
 ├── Connessione 1
 ├── Connessione 2
 ├── Connessione 3
 └── Connessione 4
```

Quando servono vengono riutilizzate.

---

# 15. DatabaseConfig

La configurazione del database non deve essere scritta direttamente nel codice.

Per questo motivo utilizziamo:

```java
DatabaseConfig
```

Responsabilità:

* URL JDBC
* username
* password
* dimensione del pool

Esempio:

```java
DatabaseConfig config =
        DatabaseConfig.getConfig();
```

Queste  informazioni solitamente vengono lette dalle variabili ambiente o da dei file di configurazione,
noi per semplicità le abbiamo hardcodate all'interno della classe:

```text
DB_URL
DB_USER
DB_PASSWORD
DB_POOL_MAX
```

---

# 16. HikariDataSourceProvider

Questa classe crea e gestisce il pool di connessioni.

Responsabilità:

```text
Creare un solo HikariDataSource
per tutta l'applicazione
```

Quando viene chiamato:

```java
HikariDataSourceProvider.getDataSource()
```

viene creato:

```java
HikariDataSource
```

contenente il pool di connessioni.

Configurazione:

```java
hikariConfig.setJdbcUrl(...)
hikariConfig.setUsername(...)
hikariConfig.setPassword(...)
```

Dimensione massima del pool:

```java
hikariConfig.setMaximumPoolSize(...)
```

Schema:

```text
Applicazione
      ↓
HikariDataSourceProvider
      ↓
Hikari Pool
      ↓
PostgreSQL
```

---

# 17. Model

Le classi Model rappresentano una riga di una tabella del database.

Esempio:

```java
public class ProdottoModel {

    private Long id;
    private String descrizione;
    private BigDecimal prezzo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
```

Questa classe rappresenta la tabella:

```sql
CREATE TABLE prodotto (
    id BIGSERIAL PRIMARY KEY,
    descrizione VARCHAR(255),
    prezzo NUMERIC(10,2),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```

Allo stesso modo:

```java
LibroModel
```

rappresenta la tabella:

```sql
libro
```

---

# 18. Repository Pattern

Le repository hanno una sola responsabilità:

```text
Accedere ai dati
```

Esempi:

```java
ProdottoJdbcRepository
LibroJdbcRepository
```

Le repository:

* leggono dati
* inseriscono dati
* aggiornano dati
* eliminano dati

Non devono contenere:

* logica di business
* regole applicative
* logica di interfaccia utente

---

# 19. CRUD

CRUD significa:

```text
Create
Read
Update
Delete
```

Nel repository troviamo:

```java
insert()
findAll()
findById()
update()
deleteById()
```

---

# 20. Costruttore della Repository

Esempio:

```java
private final DataSource dataSource;

public ProdottoJdbcRepository(DataSource dataSource) {
    this.dataSource = dataSource;
}
```

La repository riceve il DataSource.

Non conosce PostgreSQL.

Non conosce Hikari.

Sa soltanto che può richiedere una connessione.

Schema:

```text
Repository
     ↓
DataSource
     ↓
Hikari
     ↓
PostgreSQL
```

---

# 21. Optional

Il metodo:

```java
findById(long id)
```

restituisce:

```java
Optional<ProdottoModel>
```

Perché il record potrebbe non esistere.

Esempio:

```java
Optional<ProdottoModel> prodotto =
        repository.findById(1L);
```

---

# 22. Metodo map()

Responsabilità:

```text
ResultSet
      ↓
ProdottoModel
```

Esempio:

Database:

```text
id = 1
descrizione = Mouse
prezzo = 19.99
```

Oggetto Java:

```java
new ProdottoModel()
        .setId(1L)
        .setDescrizione("Mouse")
        .setPrezzo(...)
```

Questo metodo evita di duplicare il codice di conversione.

---

# 23. Metodo findAll()

Scopo:

```text
Leggere tutti i record
```

SQL:

```sql
SELECT id,
       descrizione,
       prezzo,
       created_at,
       updated_at
FROM prodotto
ORDER BY id
```

Apertura delle risorse:

```java
try (
    var connection = dataSource.getConnection();
    var statement = connection.prepareStatement(sql);
    var rs = statement.executeQuery()
)
```

Flusso:

```text
Pool
 ↓
Connection
 ↓
PreparedStatement
 ↓
ResultSet
```

Per ogni riga:

```java
result.add(map(rs));
```

viene creato un oggetto Java.

---

# 24. Metodo findById()

Scopo:

```text
Leggere un solo record
```

SQL:

```sql
SELECT *
FROM prodotto
WHERE id = ?
```

Valorizzazione parametro:

```java
statement.setLong(1, id);
```

Esempio:

```java
findById(5)
```

equivale logicamente a:

```sql
SELECT *
FROM prodotto
WHERE id = 5
```

Restituisce:

```java
Optional<ProdottoModel>
```

perché il record potrebbe non esistere.

---

# 25. Metodo insert()

Scopo:

```text
Inserire un nuovo record
```

SQL:

```sql
INSERT INTO prodotto
(descrizione, prezzo)
VALUES (?, ?)
RETURNING id
```

Valorizzazione:

```java
statement.setString(...)
statement.setBigDecimal(...)
```

Esempio:

Oggetto:

```text
Mouse
19.99
```

Database:

```text
id=1
descrizione=Mouse
prezzo=19.99
```

La clausola:

```sql
RETURNING id
```

permette di ottenere l'id generato dal database.

---

# 26. Metodo update()

Scopo:

```text
Modificare un record esistente
```

SQL:

```sql
UPDATE prodotto
SET descrizione = ?,
    prezzo = ?
WHERE id = ?
```

Esempio:

Prima:

```text
Mouse
19.99
```

Dopo:

```text
Mouse Wireless
29.99
```

---

# 27. Metodo deleteById()

Scopo:

```text
Eliminare un record
```

SQL:

```sql
DELETE
FROM prodotto
WHERE id = ?
```

Esempio:

Prima:

```text
1 Mouse
2 Tastiera
```

Dopo:

```text
2 Tastiera
```

---

# 28. JdbcHikariDemoMain

Questa classe rappresenta il punto di ingresso dell'applicazione.

Contiene il metodo:

```java
public static void main(String[] args)
```

Responsabilità:

1. Creare il DataSource
2. Creare le Repository
3. Eseguire operazioni CRUD
4. Mostrare il risultato in console

Schema:

```text
main()
   ↓
DataSource
   ↓
Repository
   ↓
CRUD
   ↓
Database
```

Esempio:

```java
DataSource ds =
        HikariDataSourceProvider.getDataSource();

ProdottoJdbcRepository repository =
        new ProdottoJdbcRepository(ds);
```

Inserimento:

```java
repository.insert(prodotto);
```

Ricerca:

```java
repository.findById(id);
```

Aggiornamento:

```java
repository.update(prodotto);
```

Cancellazione:

```java
repository.deleteById(id);
```

---

# 29. Flusso completo di una chiamata

Supponiamo di eseguire:

```java
repository.findById(1L);
```

Succede:

```text
Repository
     ↓
DataSource
     ↓
Hikari Pool
     ↓
Connection
     ↓
PreparedStatement
     ↓
PostgreSQL
     ↓
ResultSet
     ↓
ProdottoModel
```

# Esercitazione

Per esercitarvi, vi chiederei di utilizzare tutti i metodi presenti nelle
repository (findAll, findById, insert, update, deleteById) da JdbcHikariDemoMain, 
provando a creare, modificare, leggere ed eliminare dati dal database. L'obiettivo 
non è solo far funzionare il codice, ma capire il flusso completo della chiamata: 
da main(), alla repository, al DataSource, fino a PostgreSQL. Provate a sperimentare, 
aggiungere dati diversi, cercare record inesistenti e osservare cosa succede 
durante ogni operazione.
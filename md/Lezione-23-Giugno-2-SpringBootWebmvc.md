# Lezione: Prime API REST con Spring Boot

## Obiettivi
- aggiungere il modulo `spring-boot-starter-webmvc`
- comprendere cosa cambia nell'applicazione
- creare il primo `@RestController`
- utilizzare Postman per invocare una API
- comprendere il ruolo di `@Configuration` e `@Bean`
- comprendere il concetto di Bean Spring
- collegare Controller → Service → Repository
- capire come gli oggetti Java vengono convertiti automaticamente in JSON

---

# 1. Da applicazione Java a Web Application

## Prima

Avevamo una normale applicazione Java:

```java
public static void main(String[] args) {

    TrackingService service = ...

    service.track("LOGIN");

    System.out.println("Fine programma");
}
```

L'esecuzione era:

```text
Main
 ↓
Esegue istruzioni
 ↓
Termina
```

Il processo **finisce immediatamente**.

---

## Adesso

Aggiungiamo nel pom:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>
```

e una classe principale:

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

---

## Cosa succede?

L'applicazione:

- non termina
- rimane in esecuzione
- apre una porta TCP
- rimane in attesa di richieste HTTP

Schema:

```text
Applicazione avviata
         ↓
Spring Boot parte
         ↓
Tomcat Embedded parte
         ↓
Porta 8080 aperta
         ↓
In attesa di richieste
```

---

# Cosa c'è sotto?

Spring Boot include automaticamente:

- Spring MVC
- Jackson
- Tomcat Embedded

In particolare viene avviato:

```text
Tomcat Embedded
```

che è un server web Java.

Non dobbiamo installare Tomcat separatamente.

È contenuto nell'applicazione.

---

# Primo Controller

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

}
```

---

## Cosa significa?

```java
@RestController
```

dice a Spring:

> Questa classe contiene endpoint HTTP.

---

```java
@GetMapping("/hello")
```

dice:

> Quando arriva una GET su /hello esegui questo metodo.

---

# Flusso della richiesta

```text
Browser/Postman
        |
        v
GET /hello
        |
        v
Tomcat
        |
        v
HelloController
        |
        v
"Hello World!"
```

---

# Test con Browser

Aprire:

```text
http://localhost:8080/hello
```

Risultato:

```text
Hello World!
```

---

# Postman

## Cos'è

Postman è uno strumento per invocare API HTTP.

Permette di:

- fare GET
- fare POST
- fare PUT
- fare DELETE
- vedere header
- vedere body

---

## Installazione

Sito ufficiale:

https://www.postman.com/downloads/

---

## Prima chiamata

Metodo:

```text
GET
```

URL:

```text
http://localhost:8080/hello
```

Premere:

```text
Send
```

Risposta:

```text
Hello World!
```

---

# Introduzione ai Bean

Adesso vogliamo evitare di creare oggetti con:

```java
new TrackingService(...)
```

sparsi nel codice.

Vogliamo che sia Spring a gestirli.

---

# Classe di configurazione

```java
@Configuration
public class ApplicationConfiguration {
}
```

---

## Cosa significa?

Dice a Spring:

> Questa classe contiene la configurazione dell'applicazione.

---

# Primo Bean

```java
@Configuration
public class ApplicationConfiguration {

    @Bean
    public TrackingService trackingService(
            TrackingRepository repository) {

        return new TrackingService(repository);
    }

}
```

---

## Cosa fa @Bean?

Dice a Spring:

> Quando serve un TrackingService usa questo metodo per costruirlo.

---

# Analogia

Senza Spring:

```text
Programmatore
    |
    v
new TrackingService()
```

Con Spring:

```text
Spring Container
    |
    v
TrackingService
```

---

# Repository

Supponiamo di avere:

```java
public class TrackingRepository {

    private final DataSource dataSource;

    public TrackingRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
```

---

# Bean Repository

```java
@Bean
public TrackingRepository trackingRepository(
        DataSource dataSource) {

    return new TrackingRepository(dataSource);
}
```

---

# Catena delle dipendenze

```text
DataSource
    ↓
TrackingRepository
    ↓
TrackingService
```

Spring crea automaticamente tutto nell'ordine corretto.

---

# Controller che usa il Service

```java
@RestController
public class TrackingController {

    private final TrackingService service;

    public TrackingController(TrackingService service) {
        this.service = service;
    }

}
```

---

# Come arriva il Service?

Non c'è nessun:

```java
new TrackingService(...)
```

Spring vede:

```java
TrackingService
```

cerca un Bean compatibile e lo passa automaticamente.

Questo meccanismo si chiama:

```text
Dependency Injection
```

---

# Restituire Oggetti

Supponiamo di avere:

```java
public record Tracking(
        Long id,
        String event) {
}
```

---

Controller:

```java
@GetMapping("/trackings")
public List<Tracking> findAll() {

    return service.findAll();

}
```

---

# Cosa restituisce?

Noi restituiamo:

```java
List<Tracking>
```

non JSON.

---

# Eppure il client riceve JSON

Risposta:

```json
[
  {
    "id": 1,
    "event": "LOGIN"
  },
  {
    "id": 2,
    "event": "LOGOUT"
  }
]
```

---

# Cosa succede?

Spring MVC vede:

```java
@RestController
```

e capisce che il valore restituito deve diventare una risposta HTTP.

---

# Entra in gioco Jackson

Spring Boot include automaticamente:

```text
Jackson
```

tramite:

```text
spring-boot-starter-webmvc
```

---

## Processo

```text
List<Tracking>
        ↓
Jackson
        ↓
JSON
        ↓
HTTP Response
```

---

# La "magia" non è magia

Dietro le quinte avviene:

```java
ObjectMapper.writeValueAsString(...)
```

Jackson converte l'oggetto Java in JSON.

Spring si occupa di chiamarlo automaticamente.

---

# Architettura finale

```text
HTTP Request
      ↓
Tomcat Embedded
      ↓
TrackingController
      ↓
TrackingService
      ↓
TrackingRepository
      ↓
DataSource
      ↓
Database
```

e nel verso opposto:

```text
Oggetti Java
      ↓
Jackson
      ↓
JSON
      ↓
HTTP Response
```

---

# Messaggi chiave da ricordare

1. `spring-boot-starter-webmvc` trasforma una normale applicazione Java in una Web Application.
2. Il processo non termina più perché Tomcat rimane in ascolto sulla porta 8080.
3. `@RestController` espone endpoint HTTP.
4. `@Configuration` contiene la configurazione applicativa.
5. `@Bean` dice a Spring come creare gli oggetti.
6. Spring costruisce e collega automaticamente i Bean.
7. Gli oggetti Java vengono convertiti automaticamente in JSON tramite Jackson.
8. Noi scriviamo Java; Spring si occupa dell'infrastruttura web.

---

# Testare le API REST con Spring Boot

## Obiettivi

- comprendere l'importanza dei test automatici
- distinguere tra test manuali e test automatici
- utilizzare `@WebMvcTest`
- utilizzare `MockMvc`
- verificare status code e contenuto della risposta
- comprendere cosa viene realmente testato
- integrare i test nel normale ciclo di sviluppo

---

# Perché testare?

Fino ad ora abbiamo verificato il funzionamento delle API utilizzando:

- Browser
- Postman

Ad esempio:

```text
GET /hello
```

risponde:

```text
Hello World!
```

Funziona.

Ma come facciamo a garantire che continui a funzionare tra:

- una settimana?
- un mese?
- dopo una modifica?

---

# Il problema dei test manuali

Ogni volta dovremmo:

1. Avviare l'applicazione
2. Aprire Postman
3. Eseguire la chiamata
4. Controllare il risultato

Per poche API può andare bene.

Per decine o centinaia di API diventa impossibile.

---

# Test automatici

Un test automatico è codice che verifica altro codice.

```text
Applicazione
      ↑
      |
    Test
```

I test vengono eseguiti automaticamente e verificano che il comportamento sia quello atteso.

---

# Vantaggi dei test

## Sicurezza nelle modifiche

Possiamo modificare il codice con maggiore tranquillità.

Se rompiamo qualcosa:

```text
Test fallito
```

ce lo segnala immediatamente.

---

## Qualità del software

Riduce il rischio di:

- bug
- regressioni
- comportamenti inattesi

---

# Il nostro primo Controller

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

}
```

---

# Obiettivo del test

Verificare che:

```text
GET /hello
```

restituisca:

```text
Status: 200 OK
Body: Hello World!
```

---

# Introduzione a WebMvcTest

```java
@WebMvcTest(HelloController.class)
class HelloControllerTest {
}
```

---

## Cosa fa?

Carica solamente il layer Web.

Vengono inizializzati:

- DispatcherServlet
- Controller
- Mapping HTTP
- Conversione JSON

Non vengono caricati:

- Database
- Repository
- Service non necessari

---

# Perché è utile?

Il test è:

- veloce
- isolato
- focalizzato sul controller

---

# MockMvc

Nel test utilizziamo:

```java
@Autowired
private MockMvc mvc;
```

---

## Cos'è MockMvc?

MockMvc simula richieste HTTP.

Permette di fare:

```text
GET
POST
PUT
DELETE
```

senza avviare un server reale.

---

# Cosa succede dietro le quinte?

Con Postman avevamo:

```text
Postman
   ↓
Tomcat
   ↓
Controller
```

Con MockMvc:

```text
MockMvc
   ↓
Spring MVC
   ↓
Controller
```

Non serve aprire la porta 8080.

Non serve avviare Tomcat.

---

# Il nostro test

```java
@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnHelloWorld() throws Exception {

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));

    }
}
```

---

# Analisi del test

## Invocazione della API

```java
mvc.perform(get("/hello"))
```

Simula:

```http
GET /hello
```

---

# Verifica dello Status Code

```java
.andExpect(status().isOk())
```

Controlla che la risposta sia:

```http
200 OK
```

---

# Perché è importante?

Una risposta può avere:

```http
200 OK
404 Not Found
500 Internal Server Error
400 Bad Request
```

Il test verifica che venga restituito lo status corretto.

---

# Verifica del contenuto

```java
.andExpect(content().string("Hello World!"))
```

Controlla che il body della risposta sia:

```text
Hello World!
```

---

# Se qualcosa cambia?

Supponiamo di modificare il controller:

```java
@GetMapping("/hello")
public String hello() {
    return "Ciao";
}
```

Il test fallirà.

---

## Risultato

```text
Expected:
Hello World!

Actual:
Ciao
```

Questo ci segnala immediatamente una modifica del comportamento.

---

# Test come rete di sicurezza

Possiamo immaginare i test come una rete.

```text
Nuova modifica
       ↓
Esecuzione test
       ↓
Tutto verde ?
       ↓
Deploy
```

Se qualcosa si rompe:

```text
Test rosso
```

e possiamo intervenire prima di rilasciare il software.

---

# Confronto: Postman vs Test Automatici

| Aspetto | Postman | WebMvcTest |
|----------|----------|------------|
| Verifica manuale | ✔ | ❌ |
| Verifica automatica | ❌ | ✔ |
| Ripetibile | Poco | Molto |
| Eseguibile in CI/CD | ❌ | ✔ |
| Veloce | Medio | Molto veloce |

---

# Dove si inseriscono nel ciclo di sviluppo?

Durante lo sviluppo:

```text
Scrivo codice
      ↓
Scrivo test
      ↓
Eseguo test
      ↓
Correggo eventuali errori
      ↓
Commit su Git
```

---

# Buona pratica

Per ogni endpoint:

```text
GET
POST
PUT
DELETE
```

dovrebbe esistere almeno un test che verifichi:

- status code
- body della risposta
- casi di errore

---

# Messaggi chiave da ricordare

1. I test automatici verificano che il software continui a funzionare nel tempo.
2. `@WebMvcTest` carica solamente il layer web.
3. `MockMvc` permette di simulare richieste HTTP.
4. Possiamo verificare status code e contenuto della risposta.
5. I test sono una rete di sicurezza durante l'evoluzione del software.
6. Un'applicazione professionale contiene sia codice applicativo sia codice di test.
7. Prima si individuano gli errori, meno costano da correggere.
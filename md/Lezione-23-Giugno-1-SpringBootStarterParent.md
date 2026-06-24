# Spring Boot Starter Parent: introduzione all’infrastruttura Spring

## Obiettivo

In questa fase del progetto non stiamo ancora costruendo un’applicazione Spring “completa” (con controller REST attivi e logica esposta via HTTP).

Stiamo però già utilizzando **Spring Boot come infrastruttura**, grazie al:

```xml
spring-boot-starter-parent
```

Questo ci permette di avere un ambiente pronto, coerente e configurato correttamente, anche se il `main` non espone ancora API.

---

# Il problema prima di Spring Boot

Senza Spring Boot, un progetto Java con Maven richiede:

- gestione manuale delle versioni delle librerie
- configurazione dei plugin Maven
- compatibilità tra framework (Spring, Jackson, Tomcat, ecc.)
- setup ripetitivo tra progetti

Esempio:

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>7.x.x</version>
</dependency>
```

Ogni versione va scelta manualmente.

---

# Il ruolo del Spring Boot Starter Parent

Nel `pom.xml` possiamo definire:

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>4.1.0</version>
</parent>
```

## Cosa significa?

Stiamo dicendo a Maven:

> “Questo progetto eredita una configurazione standard Spring Boot già pronta e testata.”

---

# Cosa ci fornisce il parent

## 1. Dependency Management centralizzato

Non dobbiamo più specificare le versioni delle dipendenze Spring.

Esempio:

### Senza parent
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
    <version>4.1.0</version>
</dependency>
```

### Con parent
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>
```

👉 La versione viene gestita automaticamente.

---

## 2. Versioni coerenti tra librerie

Il parent garantisce che:

- Spring Framework
- Hibernate
- Jackson
- Tomcat embedded
- logging (Logback)

siano tra loro compatibili.

---

## 3. Configurazione Maven già pronta

Il parent configura automaticamente:

- compilatore Java
- encoding UTF-8
- plugin di build
- lifecycle Maven

---

## 4. Build coerente tra ambienti

Tutti i progetti Spring Boot basati su parent:

- si comportano allo stesso modo
- si compilano allo stesso modo
- generano artifact coerenti

---

# Cosa NON fa il Spring Boot Starter Parent

È importante chiarirlo:

❌ Non avvia Spring  
❌ Non crea API REST  
❌ Non crea automaticamente il server  
❌ Non rende l’applicazione “attiva”

---

# Cosa abbiamo oggi nel progetto

In questa fase:

- abbiamo ancora un `main()` Java
- non abbiamo controller REST
- non abbiamo server HTTP attivo
- non stiamo ancora esponendo API

MA:

✔ il progetto è già Spring Boot based  
✔ abbiamo già configurazione standardizzata  
✔ abbiamo già dependency management automatico  
✔ abbiamo già infrastruttura pronta per evoluzione futura

---

# Struttura concettuale

```text
Java Main Application
        |
        v
Maven Project
        |
        v
Spring Boot Starter Parent
        |
        v
Dependency Management + Build Config
        |
        v
Progetto pronto per Spring Framework
```

---

# Perché è importante in questa fase

Il valore del parent è che:

> prepara il terreno prima ancora di usare Spring “attivamente”.

Questo evita di:

- configurare tutto a mano dopo
- avere problemi di compatibilità più avanti
- dover rifare il progetto quando si introduce Spring Boot Web

---

# Prossimi  passi

Nelle prossime fasi useremo:

- `spring-boot-starter-webmvc`
- `@SpringBootApplication`
- controller REST
- dependency injection automatica

Il parent ci garantisce che tutto questo funzionerà senza configurazioni manuali complesse.

---

# Messaggio chiave

Il `spring-boot-starter-parent` non è Spring “in esecuzione”.

È la **base infrastrutturale del progetto Spring Boot**, che:

- standardizza Maven
- garantisce compatibilità
- semplifica la gestione delle dipendenze
- prepara il progetto all’uso reale di Spring

---

# Sintesi finale

👉 Arrivati fin qua abbiamo Spring Boot ma non stiamo ancora usando Spring per costruire API  
👉 Stiamo usando Spring Boot per costruire il progetto nel modo corretto  
👉 Il parent è ciò che rende tutto questo possibile senza configurazioni manuali complesse


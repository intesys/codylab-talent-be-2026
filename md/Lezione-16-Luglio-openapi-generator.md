# Lezione – Servizi REST da una specifica OpenAPI

## Obiettivi della lezione

In questa lezione abbiamo visto come automatizzare la creazione di parte del codice Java di un'applicazione Spring Boot partendo dalla specifica OpenAPI scritta in formato **YAML**.

L'obiettivo è evitare di scrivere manualmente le interfacce dei controller REST e i DTO, mantenendo il codice sempre allineato alla documentazione dell'API.

Il flusso di lavoro diventa quindi:

```
Specifica OpenAPI (YAML)
          │
          ▼
openapi-generator-maven-plugin
          │
          ▼
Codice Java generato
- Interfacce dei Controller
- DTO (Model)
```

---

# Il plugin OpenAPI Generator

Per la generazione del codice abbiamo utilizzato il plugin Maven:

```xml
<plugin>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
</plugin>
```

Durante la fase di build il plugin legge il file OpenAPI (`openapi.yml`) e genera automaticamente il codice Java.

Nel nostro progetto il codice generato comprende principalmente:

- le **interfacce dei Controller REST**
- i **DTO (Data Transfer Object)** utilizzati nelle richieste e nelle risposte.

Le classi generate **non devono essere modificate manualmente**, perché ad ogni nuova generazione verrebbero sovrascritte.

---

# Configurazione utilizzata

Di seguito sono riportate le principali opzioni configurate nel plugin.

## generatorName = spring

```xml
<generatorName>spring</generatorName>
```

Indica quale generatore utilizzare.

Nel nostro caso scegliamo **Spring**, che genera codice compatibile con Spring Boot.

Questo permette di ottenere controller, annotazioni e classi perfettamente integrate con l'ecosistema Spring.

---

## modelNameSuffix

```xml
<modelNameSuffix>ApiDTO</modelNameSuffix>
```

Aggiunge un suffisso al nome di tutti i modelli generati.

Ad esempio:

```
Project
```

diventa

```
ProjectApiDTO
```

Questo aiuta a distinguere chiaramente i DTO dalle eventuali Entity JPA o da altri oggetti del dominio.

---

## apiPackage

```xml
<apiPackage>it.intesys.codylab.api</apiPackage>
```

Specifica il package in cui verranno generate le interfacce dei controller REST.

Ad esempio:

```
it.intesys.codylab.api.ProjectApi
```

---

## modelPackage

```xml
<modelPackage>it.intesys.codylab.dto</modelPackage>
```

Definisce il package in cui verranno generati tutti i DTO.

Ad esempio:

```
ProjectDto
CustomerDto
ActivityDto
```

---

## skipIfSpecIsUnchanged = true

```xml
<skipIfSpecIsUnchanged>true</skipIfSpecIsUnchanged>
```

Evita di rigenerare il codice se la specifica OpenAPI non è cambiata.

Questo rende la build più veloce perché il plugin non esegue lavoro inutile.

---

## skipValidateSpec = false

```xml
<skipValidateSpec>false</skipValidateSpec>
```

Indica se validare o meno la specifica OpenAPI prima della generazione.

Con il valore `false` (quello utilizzato durante la lezione) il file YAML viene controllato e, in presenza di errori, la generazione si interrompe.

Questa validazione aiuta a individuare rapidamente errori di sintassi o incoerenze nella specifica.

---

## delegatePattern = false

```xml
<delegatePattern>false</delegatePattern>
```

Disabilita il **Delegate Pattern**.

Con questa configurazione viene generata solamente l'interfaccia del controller.

Sarà poi il nostro controller ad implementare direttamente tale interfaccia.

Se fosse impostato a `true`, verrebbero generate ulteriori classi delegate che separano ulteriormente il codice generato dalla logica applicativa.

La struttura con i delegate complica inutilmente i layer del progetto, per scelta non li usiamo (non danno alcun valore aggiunto).

---

## useSpringBoot3 = true

```xml
<useSpringBoot3>true</useSpringBoot3>
```

Genera codice compatibile con **Spring Boot 3**.

In particolare utilizza le librerie Jakarta (`jakarta.*`) invece delle vecchie librerie `javax.*`, richieste dalle versioni più recenti di Spring Boot.

---

## useTags = true

```xml
<useTags>true</useTags>
```

Le API vengono raggruppate in base ai **tag** definiti nella specifica OpenAPI.

Ad esempio:

```yaml
tags:
  - Projects
```

porterà alla generazione di un'interfaccia dedicata:

```
ProjectsApi
```

Mentre un altro tag produrrà un'altra interfaccia.

Questo permette di mantenere il codice organizzato e suddiviso per funzionalità.

---

## serializableModel = true

```xml
<serializableModel>true</serializableModel>
```

Fa implementare ai DTO l'interfaccia Java:

```java
Serializable
```

In questo modo gli oggetti possono essere serializzati quando necessario (ad esempio per caching, sessioni HTTP o altre esigenze del framework).

---

## requestMappingMode = api_interface

```xml
<requestMappingMode>api_interface</requestMappingMode>
```

Le annotazioni Spring (`@RequestMapping`, `@GetMapping`, `@PostMapping`, ecc.) vengono inserite direttamente sull'interfaccia generata.

Il controller dovrà semplicemente implementare tale interfaccia.

Questo consente di mantenere tutte le informazioni relative alle API direttamente nel codice generato dalla specifica OpenAPI.

---

## interfaceOnly = true

```xml
<interfaceOnly>true</interfaceOnly>
```

Il plugin genera **solo le interfacce** dei controller.

Non vengono create implementazioni vuote.

L'implementazione viene sviluppata manualmente nel progetto, consentendo di separare chiaramente:

- il contratto dell'API (generato automaticamente);
- la logica applicativa (scritta dagli sviluppatori).

---

# Vantaggi dell'approccio

L'utilizzo di OpenAPI Generator offre numerosi vantaggi:

- la documentazione e il codice rimangono sempre sincronizzati;
- si riduce la quantità di codice scritto manualmente;
- si evitano errori nella definizione degli endpoint REST;
- si ottengono DTO coerenti con la specifica;
- eventuali modifiche all'API vengono propagate automaticamente rigenerando il codice.

---

# Risultato finale

Al termine della lezione siamo stati in grado di:

- scrivere una specifica OpenAPI in formato YAML;
- configurare il plugin `openapi-generator-maven-plugin`;
- generare automaticamente il codice Java;
- ottenere le interfacce dei controller REST e i DTO;
- comprendere il significato delle principali opzioni di configurazione utilizzate nel progetto.

---

# Implementazione del Controller REST e introduzione a MapStruct

## Dalle classi generate all'implementazione dei servizi

Prima abbiamo visto come, partendo dalla specifica OpenAPI, sia possibile generare automaticamente:

- i DTO (Data Transfer Object);
- le interfacce dei Controller REST.

Il passo successivo consiste nell'implementare la logica applicativa prevista dagli endpoint.

---

# L'interfaccia generata

Per la risorsa `Project` il plugin ha generato l'interfaccia:

```java
ProjectControllerApi
```

Questa interfaccia contiene già la definizione di tutti gli endpoint descritti nella specifica OpenAPI, ad esempio:

- `getAllProjects()`
- `getProjectById()`
- `createProject()`

L'interfaccia rappresenta quindi il **contratto** dell'API.

---

# L'implementazione di default

Le interfacce generate contengono già un'implementazione di default dei metodi.

Questa implementazione **non contiene alcuna logica applicativa** ma restituisce semplicemente una risposta di errore **HTTP 415 (Unsupported Media Type)**.

Ad esempio, se dopo la generazione avviamo l'applicazione e proviamo a richiamare gli endpoint tramite Postman, otteniamo una risposta simile a:

```
HTTP 415 Unsupported Media Type
```

Questo comportamento è voluto: il generatore crea solamente il contratto dell'API, lasciando allo sviluppatore il compito di implementarne il funzionamento.

---

# Creazione del Controller

Per implementare gli endpoint abbiamo creato la classe:

```java
OpenApiProjectController
```

annotandola con:

```java
@RestController
```

e facendole implementare l'interfaccia generata:

```java
public class OpenApiProjectController
        implements ProjectControllerApi {
}
```

In questo modo Spring registra automaticamente il controller come componente REST.

---

# Generazione dei metodi

Per evitare di scrivere manualmente tutti i metodi dell'interfaccia abbiamo utilizzato la funzionalità dell'IDE:

```
Tasto destro
    → Generate
        → Implement Methods...
```

Selezionando i metodi:

- `getAllProjects`
- `getProjectById`
- `createProject`

L'IDE ha generato automaticamente gli override corrispondenti.

A questo punto il controller è pronto per contenere la logica applicativa.

---

# Il problema dei DTO

Durante l'implementazione emerge però un problema.

I metodi dell'interfaccia generata lavorano con i **DTO** creati automaticamente da OpenAPI Generator.

Ad esempio:

```java
ProjectDto
```

La nostra applicazione, invece, utilizza internamente delle classi completamente diverse, scritte da noi, ad esempio:

```java
Project
```

che rappresentano le Entity del dominio (o del database).

Di conseguenza il controller deve convertire continuamente:

```
DTO  → Entity
```

quando riceve una richiesta

e

```
Entity → DTO
```

quando deve restituire una risposta.

---

# Perché non fare la conversione manualmente?

Una possibile soluzione sarebbe copiare manualmente tutti i campi.

Ad esempio:

```java
Project project = new Project();
project.setId(dto.getId());
project.setName(dto.getName());
...
```

Questa soluzione però presenta diversi svantaggi:

- molto codice ripetitivo;
- elevata possibilità di errori;
- manutenzione più difficile quando i modelli cambiano.

Per questo motivo è preferibile utilizzare una libreria dedicata.

---

# Introduzione a MapStruct

Per automatizzare la conversione tra DTO ed Entity abbiamo introdotto **MapStruct**.

MapStruct è una libreria che genera automaticamente il codice di mapping tra due oggetti Java.

Lo sviluppatore deve semplicemente definire un'interfaccia, ad esempio:

```java
@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDto toDto(Project entity);

    Project toEntity(ProjectDto dto);

}
```

Durante la compilazione Maven, MapStruct genera automaticamente l'implementazione contenente il codice di copia dei campi.

In questo modo il controller può limitarsi ad utilizzare il mapper senza preoccuparsi della conversione.

---

# Il processor di MapStruct

La generazione del codice avviene tramite l'annotation processor:

```xml
<artifactId>mapstruct-processor</artifactId>
```

Durante la compilazione Maven il processor legge le interfacce annotate con `@Mapper` e produce automaticamente le classi di implementazione.

Anche in questo caso il codice generato **non deve essere modificato manualmente**, perché verrà rigenerato ad ogni compilazione.

---

# Vantaggi di MapStruct

L'utilizzo di MapStruct permette di:

- eliminare il codice ripetitivo di conversione;
- mantenere separati DTO ed Entity;
- ridurre gli errori di copia dei dati;
- ottenere ottime prestazioni, poiché il mapping viene generato a compile-time e non tramite reflection;
- semplificare l'implementazione dei controller REST.

---

# Flusso completo

Al termine della lezione il flusso dell'applicazione risulta il seguente:

```text
Richiesta HTTP
       │
       ▼
OpenApiProjectController
       │
       ▼
ProjectDto
       │
       ▼
MapStruct
       │
       ▼
Project (Entity)
       │
       ▼
ProjectService
       │
       ▼
Project (Entity)
       │
       ▼
MapStruct
       │
       ▼
ProjectDto
       │
       ▼
Risposta HTTP
```

In questo modo ogni componente ha una responsabilità ben definita:

- **OpenAPI Generator** genera il contratto dell'API e i DTO;
- **Controller** implementa gli endpoint;
- **MapStruct** converte tra DTO ed Entity;
- **Service** contiene la logica applicativa.
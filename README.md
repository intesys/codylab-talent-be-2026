# CodyLab Talent 2026 - Progetto Java e Spring Boot
Progetto studio java e Spring Boot per CodyLab Talent 2026

## Parte 1 - Java Intro
Eseguire il check-out del progetto https://github.com/intesys/codylab-talent-be-2026.git

Per poter lavorare da IDE con progetto agganciato in https vi occorre il token.
Sulla pagina di github, sul vostro profilo, andate su `Settings > Developer Settings`
e generate un personal access token (classic) dando i permessi `repo` e `workflow`.

### Argomenti
* Java e Javac (JDK e JRE)
* Programma Java => metodo main (`public static void main(String[] args)`)
* In Java tutto è un Oggetto
* Concetto di Classe, cosa definisce
* Interfacce, classi astratte e classi concrete
* private, protected, public
* metodi statici
* Ereditarietà da Object (e java.lang)
* toString, equals e hashCode
* Introduzione principi programmazione

Da studiare
* Principi `SOLID` (https://www.baeldung.com/solid-principles)
    * _S – Single Responsibility Principle (SRP)_
      Ogni classe dovrebbe avere una sola responsabilità, cioè una sola ragione di cambiare.
    * _O – Open/Closed Principle (OCP)_
      Il software dovrebbe essere aperto all'estensione ma chiuso alla modifica.
      Puoi aggiungere nuove funzionalità senza modificare il codice esistente.
    * _L – Liskov Substitution Principle (LSP)_
      Le classi derivate devono poter sostituire le classi base senza alterare il funzionamento del programma.
      In pratica, un oggetto figlio deve comportarsi come il padre.
    * _I – Interface Segregation Principle (ISP)_
      Meglio avere tante interfacce specifiche che una sola generica.
      I client non dovrebbero dipendere da metodi che non usano.
    * _D – Dependency Inversion Principle (DIP)_
      Dipendi da astrazioni, non da classi concrete.
      I moduli di alto livello non dovrebbero dipendere da quelli di basso livello: entrambi dovrebbero dipendere da astrazioni.
* Principi della programmazione ad oggetti:
    * _Incapsulamento_
      Raggruppa dati e comportamenti all’interno di un oggetto.
      Nasconde i dettagli interni e espone solo ciò che serve tramite interfacce pubbliche.
      Dettagli ed esempi: https://www.w3schools.com/java/java_encapsulation.asp
    * _Astrazione_
      Espone solo gli aspetti rilevanti di un oggetto, nascondendo i dettagli complessi.
      Permette di lavorare con concetti generali, non implementazioni specifiche.
      Dettagli ed esempi: https://www.w3schools.com/java/java_abstract.asp
    * _Ereditarietà_
      Una classe può ereditare attributi e metodi da un'altra.
      Favorisce il riuso del codice e la specializzazione.
      Dettagli ed esempi: https://www.w3schools.com/java/java_inheritance.asp
    * _Polimorfismo_
      Un'unica interfaccia può rappresentare comportamenti diversi.
      Permette di usare oggetti di classi diverse in modo uniforme, purché condividano un'interfaccia comune.
      Dettagli ed esempi: https://www.w3schools.com/java/java_polymorphism.asp

**Esercizi**

Fai pull del branch 0-playground.
Spostati su un tuo branch : 0-<<tuonome>>
* es. 3.1

Modifica it/intesys/codylab/MyFirstCodyLabApplication.java, in modo da riuscire a richiamare il metodo  stampa() da  main().
Commit e push.

* es. 3.2

* Nel file it/intesys/codylab/MyFirstCodyLabApplication.java, nel metodo stampa(), sono presenti due righe commentate (15 e 16):

`// Messaggio messaggio = new MessaggioFormaGeometrica(new Cerchio(5.0f));

// Messaggio messaggio = new MessaggioFormaGeometrica(new Cerchio(6.0f));`

Per eseguire l'esercizio:
Commentare la riga `Messaggio messaggio = new MessaggioStatico();`
Scommentare una sola riga alla volta tra la 15 e la 16.
Avviare l'applicazione.

Cosa appare in console:
- Se si scommenta la riga 15 (Cerchio(5.0f)): viene stampato il messaggio della forma geometrica calcolato con raggio 5.
- Se si scommenta la riga 16 (Cerchio(6.0f)): viene stampato il messaggio della forma geometrica calcolato con raggio 6.

Segnatevi cosa appare sulla console.

* es. 3.3

Nel file `it/intesys/codylab/MyFirstCodyLabApplication.java` prova a far stampare 
il messaggio della forma geometrica `Quadrato(5.0f)`.
Commit e push.

* es 3.4

Modifica `it/intesys/codylab/MySecondCodyLabApp.java` (e tutte le classi che ti servono), 
nel metodo `main()`, in modo che riesca a creare un messaggio di tipo
`MessaggioConAutoreImpl` con autore il tuo nome e stampa in console il messaggio 
generato (che deve essere "Benvenuto in CodyLab da parte di " + autore).

Rilascia (commit e push) le tue modifiche.

* es. 3.5

Modifica il programma creando la Classe `Rettangolo` che, come il quadrato estende
`Quadrilatero`, ma necessita di base e altezza per essere creata.
Modifica il programma principale (`main` di 
`it/intesys/codylab/MyFirstCodyLabApplication.java`) in modo che stampi il messaggio 
relativo ad un `Rettangolo(4.0f, 6,0f)`.

Fai commit e push del tuo branch.

* es. 3.6

Crea una Pull Request


### Ultiori argomenti
* variabili e costanti (costruttore, setter e getter)
* final
* Liste, stream, Optional interfacce funzionali
* CRUD in memoria

#### Esercizi

1. Nella classe ProdottiMain, nel metodo catalogoProdotti, prima di creare i prodotti chiamate
prodottoService.getProdotti();
e verificate se la lista restituita è vuota . Scrivete a console se è vuota o se contiene prodotti

2. Provate a implementare la cancellazione: fate una getById e poi usate il metodo remove delle liste

3. Provate a ricreare lo stesso giro su una entità diversa. Non  trovo i campi previsti dalle entità su cui lavoraremo sul FE: Provate a fare la CRUD sull'oggetto Libro: (id), titolo, autore, lingua (enum IT/EN).

Studiate un po' queste pagine:
- Java Encapsulation and Getters and Setters
- Java Inheritance (Subclass and Superclass)
- https://www.w3schools.com/java/java_polymorphism.asp

## Parte 2 - Maven + Layer
Scaricare il branch `2-java-maven`

### Introduzione
Idea chiave da trasmettere

Prima: 
"Funziona sul mio PC"
era normalità.

Con strumenti come Maven:
- build riproducibili 
- dipendenze versionate 
- standardizzazione  
- automazione
diventa possibile:
- lavorare in team
- fare CI/CD
- deploy coerenti
- containerizzazione
- cloud

Una applicazione moderna deve essere:
- automatizzabile
- riproducibile
- configurabile
- portabile

Maven aiuta soprattutto in:
- dependency management
- build automation
- standardizzazione

### Argomenti
* Progetto maven
    * pom.xml
    * struttura cartelle
    * dipendenze
* Repository Maven
    * Maven Central
    * Repository locali e remoti
* Ciclo di vita di un progetto
    * clean
    * compile
    * test
    * package
    * deploy
* I test automatizzati
    * JUnit
    * Mockito
* Basic pom.xml:
```xml
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>it.intesys.codylab</groupId>
  <artifactId>codylab-talent-2026</artifactId>
  <version>1.0-SNAPSHOT</version>
</project>
```
* Esecuzione di un eseguibile da linea di comando
    * java.exe -cp codylab-talent-2026-1.0-SNAPSHOT.jar it.intesys.codylab.MyCodyLabApplication
* Layer architetturali
* Repository (introduzione concetto)

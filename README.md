# CodyLab Talent 2025 - Progetto Java e Spring Boot
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


### extra
* variabili e costanti (costruttore, setter e getter)
* final
* Liste, stream, interfacce funzionali


# CodyLab Talent 2025 - Progetto Java e Spring Boot
Progetto studio java e Spring Boot per CodyLab Talent 2026

## Classe eseguibile
La classe eseguibile del progetto è `it.intesys.codylab.MyFirstCodyLabApplication`.

Questa classe contiene il metodo `main` e rappresenta il punto di avvio dell'applicazione.

## Primo esercizio
Nel file `it/intesys/codylab/MyFirstCodyLabApplication.java`, nel metodo `stampa()`, sono presenti due righe commentate (15 e 16):

- `// Messaggio messaggio = new MessaggioFormaGeometrica(new Cerchio(5.0f));`
- `// Messaggio messaggio = new MessaggioFormaGeometrica(new Cerchio(6.0f));`

Per eseguire l'esercizio:

1. Commentare la riga `Messaggio messaggio = new MessaggioStatico();`
2. Scommentare **una sola riga alla volta** tra la 15 e la 16.
3. Avviare l'applicazione.

Cosa appare in console:

- Se si scommenta la riga 15 (`Cerchio(5.0f)`): viene stampato il messaggio della forma geometrica calcolato con raggio 5.
- Se si scommenta la riga 16 (`Cerchio(6.0f)`): viene stampato il messaggio della forma geometrica calcolato con raggio 6.

I due casi producono output diversi perché cambiano i valori della forma in base al raggio scelto.

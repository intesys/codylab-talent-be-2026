# Esercitazione su integration test con database h2

## Obiettivo
L'obiettivo di questa esercitazione è quello di creare uan batteria di test in grado di verificare
il comportamento atteso delle classi repository che interagiscono con un database H2 in memoria.

Il punto di attenzione è anche sul fatto che l'applicazione "vera" gira collegandosi a PostgreSQL mentre
per i test si utilizza un database h2 embeddable e questo consente di effettuare la batteria di test
anche in pipeline automatizzate.
Questa è la forza di aver scritto il software con mattoncini componibili.

### Esercizi
Nei test riferire sempre un datasource di test che si collega ad h2. h2 quando viene avviato deve 
inizializzarsi con lo script in resources/sql/esercizio-1.sql

1. Creare un test che verifichi che TrackingRepository.findAll restituisca tutti i record presenti nella tabella tracking. Per fare questo inserire prima dei record di test nella tabella tracking 
e poi verificare che il numero di record restituito sia corretto.

2. Creare un test che verifichi che TrackingRepository.findById restituisca il record corretto per un certo id tra
quelli creati con lo script di inizializzazione. 

3. Creare un test che verifichi che TrackingRepository.insert inserisca correttamente un nuovo record nella tabella tracking. 
Dopo l'inserimento verificare che il numero di record sia aumentato di uno e che il record inserito sia presente
(recuperare l'id ottenuto dall'insert e usarlo per fare la findById).

4. Creare un test che verifichi che TrackingRepository.update modifichi correttamente un record esistente nella tabella tracking.
Si può fare la insert e poi la update o aggiornare un record già presente nello script di inizializzazione. 
dopo la update verificare che il record sia stato modificato correttamente.

5. Creare un test che verifichi che TrackingRepository.deleteById elimini correttamente un record esistente nella tabella tracking.
Si può fare la insert e poi la delete o eliminare un record già presente nello script di inizializzazione.

6. Fare un test sul comportamento di TrackingRepository.findById quando viene passato un id che non esiste. 

7. Fare un test sul comportamento di TrackingRepository.update quando si cerca di aggiornare un record che non esiste.

Effettuare la stessa batteria di test su ActivityRepository e ProjectRepository.



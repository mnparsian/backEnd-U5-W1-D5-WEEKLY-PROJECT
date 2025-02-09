In questa struttura di codice non ho utilizzato Configuration poiché JPA Repository gestisce automaticamente i Bean necessari.
Inoltre, il programma funziona in modo dinamico e non utilizza dati statici.
Ho aggiunto un DataLoader che genera dati di test all'interno dell'applicazione.
Infine, ho implementato uno Scheduler che verifica automaticamente a mezzanotte 
e libera tutte le postazioni prenotate, rendendole disponibili.

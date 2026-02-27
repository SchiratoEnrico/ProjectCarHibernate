Per il database (siamo in postgres) utilizzaimo questa convenzione:
  - si utilizza snake_case
  - tutto minuscolo
  - nomi tabelle plurale
  - nome colonne singolare

Abbiamo sviluppato dei controller per ogni tabella, permettono operazioni di add, update, delete, findById, findAll()

Per i veicoli abbiamo implementato un find con dei filtri.

Per fare ciò abbiamo deciso di far estendere alle nostre repository JpaSpecificationExecutor<Veicolo>.
Abbiamo quindi creato una classe VeicoloSpecs per creare le specification veicolo. Valgono per tutti i tipi, macchina moto e bici.

Abbiamo quindi creato un oggetto VeicoloFilter per mappare i nostri filtri. Le specification vengono create a partire da questo oggetto.
Per comodità nel nostro VeicoloFilter sono contenuti i campi utilizzando gli id (la tabella veicolo nel database ha gli id).
Quindi, per avere in fase di test la possibilità di inserire "rosso" piuttosto che l'effettivo id del colore rosso, abbiamo creato un oggetto VeicoloFilterRequest, che appunto
contiene tutti i campi con il loro valore effettivo. Per convertire questa richiesta nel nostro filtro effettivo abbiamo creato una classe apposita FilterTranslator, che passa da una richiesta con i valori 
ad un filtro che contiene gli id di questi valori.

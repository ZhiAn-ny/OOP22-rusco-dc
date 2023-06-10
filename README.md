# OOP22-rusco-dc

## Guida Utente
Il gioco inizia subito con una schermata di inizio, molto autoesplicativa, generalmente gestita con il mouse (ma è sufficente premere invio). Passando quindi subito al gioco in sè, questo è invece gestito prevalmentemente da tastiera. 
In particolare:
- WASD : classico movimento dei giochi da tastiera (quindi W per andare in alto, S in basso, A per sinistra e D per destra)
- ESC : serve per annullare una interazione giocatore-gioco complessa (sia una interazione di attacco, di inventario, ecc)
- F : comando di interazione. Crea un'area grigia attorno all'eroe su cui posizionare il cursore. In questa situazione, premere C permette di stamapre a video delle informazioni relative a quella cella (o un messaggio di errore se il cursore è oltre all'area di interazione o se si chiede l'informazione di una cella vuota). Ha senso interagire con porte, scale, chest (scatoloni), drop (sacchi spazzatura) e con i mostri (Rusco non ha la vista aguzza, quindi per capire come sta il suo avversario deve aspettare che si avvicini!)
- 1 2 3 4 5 : comandi che creano una situazione di attacco (in particolare 1 è l'attacco base, mentre gli altri sono abilità speciali). Quando premuti generano l'area range, l'area splash e il cursore. Affinchè un comando di attacco vada a buon termine bisogna premere C (Conferma) quando il cursore si trova sopra a una cella grigia (che è l'area di range), mentre durante la fase di preparazione quella arancione mostra chi è compreso nell'attacco.
    Notare che se il cursore esce dall'area grigia, quella arancione non viene più aggiornata.
    Di base, è da considerare che gli attacchi sono perforanti (se due mostri sono in linea ma entrambi sono compresi nell'area arancione allora entrambi subiranno lo stesso effetto), e non è compreso il "fuoco amico" (Rusco è immune ai suoi attacchi, poi ovviamente se si hanno dei mostri che lo possono bersagliare e il comando viene confermato con il mirino sopra Rusco, i danni ricevuti non sono quelli del suo attacco ma quelli dei mostri!)
    Notare come il comando 4 in realtà è un comando di "recupera salute", quindi non fa altro che consumare AP per guadagnare HP
    Per più informazioni aprire il menù di inventario
- I : apertura dell'inventario. La griglia di gioco viene rimpiazziata da una serie di oggetti che permettono di modificare le statistiche, che vengono riportate in una label a fianco.
    Per sapere più informazioni su un oggetto è sufficente posizionare il cursore sopra quell'oggetto e quindi ripremere I - Informazioni: verranno stampate a video delle informazioni.
    Per poterne usufruire è sufficente premere C e, se l'oggetto era consumabile, questo sparisce dall'inventario e modifica la stat secondo quanto descritto, mentre se era equipaggiabile allora questo viene equipaggiato. Se si equipaggia un pezzo di armatura A dopo aver già equipaggiato un pezzo di armatura B, e sono entrambi dello stesso tipo (es due elmetti), allora Rusco equipaggia A, ma dis-equipaggia B, rimettendolo nell'inventario e modificando le stats di conseguenza.
    Nel caso non si desideri avere l'inventario affollato di oggetti, è possibile scartarli con lo BACKSPACE.
    Nel caso in cui l'inventario sia vuoto, viene comunque stampato l'inventario con a fianco le stat di Rusco, ma alla prossima pressione di pulsante verrà chiuso. Se si desidera chiuderlo anticipatamente, è sufficente usare ESC
- Qualsiasi altro pulsante di tastiera non citato : se Rusco stava aspettando una nuova interazione, verrà intepretato come comando di "passa turno", mentre se stava elaborando un attacco, una interazione o stava consultando l'inventario allora semplicemente la pressione del pulsante viene ignorata

Interazioni non prettamente di tastiera sono:
- Il bottone di ritorno al menù principale
- Un bottone flag che assicura di non premere accidentalmente il bottone precedente se non desiderato
In caso di game over (o di game win), si apre invece una schermata di game over che permette di tornare al menù principale. 

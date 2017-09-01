GraphViewer
Möjlighet att välja mellan läsning av textfil
Kunna välja mellan enkel (en läsning per anrop) och repeterande läsning, där läsintervallet för repeterande läsning ska gå att välja, t.ex. genom att ange antal millisekunder
Vid läsning av textfil ska namn på textfil kunna väljas i en ”browser”, och hela textfilen läses på en gång, d.v.s. så många rader som finns tillgängliga
Får inte ”låsa” textfilen, så konflikt med FileSaver  uppstår
Inställbar ”fast” skalning på både vertikal och horisontell axel för varje kanal
Kunna välja ”Autoscale” istället för ”fast” skalning på varje axel
Horisontella axeln ska visa antingen antal sampel eller tid (kunna välja)
Kunna välja vilken kanal som ”styr” värdena på vertikal och horisontell axel
Kunna välja vilka kanaler som visas som grafer (= kunna släcka/tända grafer)
Grafytan ska vara vit
Färgen på respektive graf ska gå att ändra, där utgångsfärgen är samma som MS Excel färglägger vid 8 grafer
Angivet namn på kanalen ska skrivas ut med färgförklaring till respektive graf
Zoomningsmöjligheter (zoom in/ut i en punkt som sätts med markören, zoom in i en ruta som ritas med markören samt återställning till normalbild)
En flyttbar mätlinjal bestående av en horisontell och en vertikal linje som ska kunna förflyttas manuellt över graferna, där koordinaterna för mätlinjalernas skärningspunkt anges för vald kanal
Vi bestämde ju att det räcker med att din GraphViewer för PC respektive Android-app kan läsa från textfilen som finns beskriven i projektuppgiften (bifogar ett exempel på en sådan textfil i detta mail). Databasfunktion (kursiv stil) är inte ett krav att ta med. Att läsa direkt från datalogger bestämde vi att inte vara ett krav (därmed behöver du inte fundera på kommando för programmering av dataloggern). Viktigt är att läsningen från textfilen fungerar även när filen uppdaterats, d.v.s. läs antingen när den uppdaterats eller läs regelbundet efter valfritt tidsintervall, se projektbeskrivning.
 
Resultatet är alltså dels en GraphViewer för PC skriven i Java, dels en GraphViewer för Android Smartphones som kan läsa och plotta grafer från textfil, se bifogad fil. Dessa applikationer måste vara installerbara och fungera med beskrivna funktioner.
 
Det innebär att du behöver inte testa mot en datalogger, utan kan klara testerna mot textfilen så länge formatet på textfilen behålls.
 
Mvh
Car-Axel Olsson

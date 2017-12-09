Hej!
 
Jag har gått igenom versionen som du skickade 2017-12-04. Vad jag kan se har följande punkter åtgärdats:

(4a) Sampel kan nu väljas och fungerar för "SinglePlot", men räknaren verkar inte nollställas då ny textfil väljs. Om första textfilen innehåller exempelvis 10 rader kommer första plotten att ges sampelvärdena 0-9, medan andra inläsningen kommer att börja på 10 istället för 0.
(4b) Tidangivelse verkar fungera för "SinglePlot".
 
Anmärkning: Något av alternativen måste vara förvalt och mitt förslag är att "Sampel" är förvalt. Som det är nu kan båda alternativen väljas samtidigt. Endast ett av alternativen får kunna väljas, d.v.s. väljs annat alternativ måste föregående deaktiveras (släckas).

Punkt (2) har förändrats något, men är fortfarande ofärdig och/eller följer inte uppgiftsformuleringen. Jag gör därför ett ytterligare förtydligande här hur man kan lösa uppgiften:

När tryckknappen "SinglePlot" aktiveras (trycks in) ska samtliga rader i den valda textfilen plottas direkt, inte som nu enbart en rad per knapptryckning.

När switch-knappen "AutoPlot" aktiveras (trycks in=ON) ska exempelvis en sekvens exekveras enligt följande:
(I) Vald textfil i "Read" läses
(II) Samtliga rader i vald textfil ska plottas
(III) Pause (väntan) under angivet läsintervall X millisekunder
(IV) ClearScreen
(V) Kontrollera om switch-knappen "AutoPlot" är aktiverad (=ON) och i så fall gå till (I), annars om switch-knappen är deaktiverad (=OFF) lämna sekvensen och vänta på knapptryckning
Observera att bäst är om textfilen vid "AutoPlot" är den som väljs i browsern (nu vid tryck på knappen "Read"). En alternativ lösning är att hårdkoda läsning, vid "AutoPlot", av en textfil som kallas "data.txt" och som ligger direkt under C:\
 
Övrigt
När "ClearScreen" gjorts kan ny textfil läsas in och plottas utan att applikationen hänger sig, men detta fungerar enbart för "SinglePlot".  Vid "AutoPlot" hänger applikationen sig som tidigare, d.v.s. läser inte textfilen på nytt även om en ny väljs.

Grafens färger stämmer endast vid första läsningen av textfil, därefter kommer andra färger att återges när kanaler bockas bort. Testa exempelvis att enbart ha en kanal vald och jämför första inläsningen med andra, tredje, osv.; det kommer att dyka upp olika färger för varje rad i textfilen, för en och samma kanal.

Jag vill att du verkligen kontrollerar att dina åtgärder fungerar enligt uppgiftbeskrivningen före du skickar nästa uppdatering till mig. Det tar ganska lång tid för mig att gå igenom, verifiera applikationen och kommentera när det är så mycket av huvuddelarna som inte fungerar. Jag måste göra många fler tester för att kontrollera p.g.a. detta. Allt tar mycket längre tid. Jag har redan reducerat uppgiften genom att ta bort flera punkter som du inte behöver utföra för godkänt, men punkten (2) utgör själva kärnan i applikationen och måste fungera för att uppgiften ska kunna godkännas. Jag har själv testat att implementera punkt (2) för att kontrollera om något skulle vara problem, men jag ser inget som skulle vara svårt eller omöjligt att utföra. Mitt råd är därför att noga sätta dig in i vad som faktisk bör göras och strukturerat skapa en kod, som du testar stegvis. Applikationen måste vara i ett utförande som fungerar tillfredsställande och inte hamnar i tillstånd där den hänger sig eller uppvisar oförutsedda saker. Gör du ordentliga tester och ser till att allt är ok är chansen också mycket större att applikationen kan bli godkänd.
 
Med vänliga hälsningar


Carl-Axel

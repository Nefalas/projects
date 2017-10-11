## Intro

Det här är en liten samling av små projekt jag har hållit på med. Tyvärr har
jag inte mycket att visa eftersom det mesta intressanta jag gjort var för olika
företag och jag har inte tillstånd att visa det. Jag har också den väldigt
dåliga vanan att påbörja ett nytt projekt innan jag ens blivit klar med det
förra när det gäller personliga projekt, vilket gör att jag aldrig blir klar med
dem...
Om ni vill se mer specifika saker kan vi kanske komma på något annat, som till
exempel en uppgift som jag får lösa.

***

**Jag har inkluderat fyra projekt:**

 * #### KthPlanner Backend (Java)

Ett REST API som samlar data från KTH, KTH Social och KTH Canvas genom att
härma en webbläsare (HTMLUnit). KTH har ett väldigt dåligt API och Canvas måste
man betala för, så detta var en enklare lösning.

Programmet kan hantera 'sessions' för olika användare och håller dem automatiskt
inloggade under 10 minuter efter sista användning.

Jag håller också på att utveckla en app i ReactNative som använder detta API.


 * #### MathGame (Java)

Ett litet spel (som vill gärna få mer levels) som jag gjorde för en övning på
KTH. Målet är att går runt och plocka siffror som sedan används för att lösa
matematiska problem. Meningen med detta var att barn skulle kunna lära sig på
ett roligt sätt.

Idén var att man skulle kunna skaffa olika 'powers' genom att
lära sig nya matematiska operationer, till exempel (+) gör att man kan hoppa
rutor för att hamna inom väggar, men det blev aldrig klart för att jag började
med ett annat projekt...


 * #### CryptoChat (NodeJS)

Detta är en krypterad chattapp som använder websockets och AES 256 bitars
kryptering. Det finns möjlighet att lägga till verifierade användare, vilket
innebär att ett lösenord krävs för att välja ett visst användarnamn.

Utvecklingen pågår fortfarande. Min bästa vän har precis flyttat till Kina och
detta ska vara ett enkelt sätt att kommunicera med honom (plus att han är lite
paranoid och gillar kryptering).

 * #### LogicBuilder (JavaScript)

En simulator för elektroniska komponenter som körs i webbläsaren. Som det mesta
är det inte färdigt... Idén var att man skulle kunna skapa egna komponenter med
grundkomponenter, som till exempel en OR gate med transistorer, som sedan läggs
till i toolboxen för att kunna återanvända dem senare.

Meningen med detta projekt var att bygga ett verktyg för att lära sig elektronik
och logik, utan att riskera att man förstör hårdvaran. Idén var att man skulle
kunna få information i realtid när man bygger, som till exempel råd, varningar
och förklaring till varför det inte fungerar. Man ska också kunna bygga en krets
och sedan automatiskt hitta hårdvaran till billigaste pris.

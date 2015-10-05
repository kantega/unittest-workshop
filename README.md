# unittest-workshop #

## Kom i gang ##

## Oppgave 1 ##

En indisk guru har på oppdrag fra kunden gjennomført review av koden du forvalter. I rapporten han har levert, påpeker han at deler av koden er vanskelig å forstå.
Testklassen GrepTest lider av “primitive assertions”. Det vil si at den baserer seg på mer primitive elementer enn oppførselen den sjekker.
Jo nærmere opp til naturlig språk man formulerer seg, jo lettere vil det være å forstå hva som skjer. Guruen anbefaler at du bruker metoden assertThat istedenfor assertTrue og at du bruker en Matcher fra utilityklassen CoreMatchers, for eksempel containsString.

## Oppgave 2 ##

Guruen er også kritisk til klassen ConfigurationTest. Testen prøver å gjøre for mange ting på én gang, den har såkalt «split personality».  Det gjør den uoversiktlig.
Del opp testen i tre forskjellige tester:

a)	En test som tester defaultverdier
b)	En test som tester eksplisitte options
c)	En test som tester konfigurasjonsfeil

## Oppgave 3 ##

Du har fått i oppgave å ferdigstille klassen Dictionary. Som den gode utvikler du er, så starter du med å se på testklassen DictionaryTest. Det er laget en test som kjører grønt. Men når du ser nærmere på testen, ser du at den stinker av «conditional logic». Denne testen burde feile så lenge Dictionary-klassen ikke er ferdigstilt.

a)	Skriv først om testen slik at den feiler
b)	Fullfør Dictionary -klassen
c)	Sjekk at testen nå kjører grønt


## Oppgave 4 ##

DateUtil.spec.js kryr av «magic numbers».

Erstatt magiske numre med konstanter/variable slik at koden blir lettere å forstå.

## Oppgave 5 ##

CommentView.js blander struktur og stilsetting. Erstatt bruken av <small> med en <span> med et css-element (bruk for eksempel den eksisterende class selector commentFooter).
CommentView.spec.js vil nå feile. En «hyperassertion» er en test som er så samvittighetsfull at den sjekker hver eneste detalj om oppførselsen til koden den tester. Enhver endring av koden kan føre til at testen feiler selv om endringen i dette tilfellet ikke endrer funksjonaliteten sett fra brukerens ståsted.
Skriv om testen slik at den tester det som er vesentlig, for eksempel at det vises hvem som har kommentert og hvor lenge siden det ble kommentert.

## Oppgave 6 ##

En juniorutvikler i teamet ditt har misforstått hvordan objekter skal persisteres i databasen. Han trodde det var applikasjonskoden som hadde ansvar for å generere primærnøkler til objektene. Dette har ført til mye krøll i koden og i databasen, og han ber deg om hjelp.
Du blir overrasket over dette. Du har trodd at man ville få en exception hvis man prøvde å inserte et objekt med id-feltet satt av applikasjonen. Klassen CompanyRepositoryTest inneholder metoden insertCompanyWithApplicationProvidedIdShallFail og denne kjører grønt.
Ser du problemet? Det er en «never-failing test».

a)	Skriv om testen slik at den feiler
b)	Endre repository-klassen (bruk for eksempel en if-test)
c)	Sjekk at testen nå kjører grønt


## Oppgave 7 ##

Kundesenteret har rapportert om en alvorlig feil i systemet som vurderer lånesøknader. For alle søkere blir det beregnet en kredittscore basert på mange kriterier. Eksempelvis skal en søker som er i et langvarig ansettelesforhold få økt kredittscore, mens en søker uten fast arbeid skal få redusert score. Stikkprøver viser at søkere uten fast arbeid får uforholdsmessig høy score.
Teamet som forvalter scoringmodulen påstår at det ikke er noe som tyder på feil der. De tror problemet kan skyldes en feil i repositorymodulen som du er ansvarlig for.
Du går inn i klassen ApplicantRepositoryTest og ser til din forskrekkelse at det gjøres veldig få assertions i testen, et klassisk tilfelle av «lowered expectations». Du bestemmer deg for å begynne med å forbedre testen, og håper det gir nye spor til å avdekke feilen.


## Oppgave 8 ##

Du har irritert deg over at det tar lang tid å bygge applikasjonen du forvalter. En grunn til dette er at noen enhetstester tar lang tid å kjøre. En av disse testene er IdGeneratorTest. Den er en såkalt «sleeping snail».
Skriv om testen slik at den ikke benytter Thread.sleep. Pass på at du unngår at testen feiler hvis den kjører på en treg maskin.

Hint: Bruk en CountDownLatch.


## Oppgave 9 ##

Du har behov for å lage en ny metode findBySurName i klassen StudentRepository.
Start med å beskrive hvilken oppførsel metoden skal ha ved å lage en eller flere testmetoder. Formuler forbetingelser (given) og bakbetingelser (then) for hver test.

## Oppgave 10 ##

Du har fått i oppgave å ta over forvaltningen av et informasjonssystem for ansatte som arbeider i felt. Systemet skal blant annet sørge for å holde rede på hvilke ansatte som må bekrefte å ha lest og forstått distribuerte dokumenter.
Du starter med å se på test-koden siden tester bør dokumentere hva produksjonskoden er ment å gjøre. Du blir skuffet når du ser på klassen ConfirmationServiceTest. Den var ikke lett å forstå. Det er så mange detaljer («incidental details») at det er vanskelig å se hva som er essensielt.
Det er på tide å refaktorisere koden.

a)	Flytt initialisering av objekter til en setup-metode
b)	Strukturer testen etter BDD-mønsteret og lag en hjelpemetode for hvert steg

1.	givenDocumentToBeDistributedToEmployees
2.	whenRequiringConfirmationForDocument
3.	thenCreatePendingConfirmationForAllEmployeesInDistributionGroup

c)	Metoden som sjekker bakbetingelsen sjekker flere ting. Mange foretrekker at en test bare tester én ting. Del opp testen i ulike tester slik at hver test bare sjekker én ting, for eksempel:

1.	at det persisteres like mange confirmation-objekter som det er ansatte som skal bekrefte dokumentet
2.	at hvert confirmation-objekt har status «pending»
3.	at hvert confirmation-objekt gjelder dokumentet som ble fordelt
4.	at confirmation-objektene gjelder de ansatte i distribusjonsgruppen


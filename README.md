# Setup Guide
For at andre skal kunne se din hjemmeside på internettet skal den hostes et eller andet sted. Man kan hoste den på sin egen computer dog betyder at når din computer er slukket så er hjemmesiden nede. Derfor bruger man oftest en third party host til at hoste sin hjemmeside. Vi har brugt Heroku til at hoste vores hjemmeside og vil forklare hvordan man selv kan gøre det.

## Hvordan hoster man programmet på Heroku?
1. Gå til Herokus hjemmeside [her](https//:www.heroku.com).
2. Opret en bruger og log in.
3. Gå til [dashboard](https://dashboard.heroku.com/apps).
4. Klik på "new" og "Create new app".
5. Giv appen et navn og vælg en region.
6. Push projektet til Heroku via Git.\

Den nemmeste måde at deploye sit program på Heroku er at tilslutte sit github repository. På tidspunktet af dette skrives er det ikke muligt at deploye via github så vi bruger vi Heroku Git. Se Herokus guide på at deploye via Git [her](https://devcenter.heroku.com/articles/git)

Nu eksisterer projektet på vores Heroku app, men der er ikke nogen database tilknyttet så du kan hverken se nogen data eller indsætte dem. Selve hjemmesiden er hostet på Heroku, men vores database skal også hostes et sted og det sted er ClearDB.

7. Gå tilbage til dashboardet og klik på "resources".
8. Klikl på "addons" søgefeltet og indtast `ClearDB` og vælg den som addon.
9. Vælg "Ignite" planen da den er gratis (hvis du gider at bruge penge er det op til dig selv hvilken plan du vælger) og klik "submit order".
10. Klik så på "settings" og "reveal config vars".
11. Opret tre vars kaldet `url`, `pass`, `user`.
12. I `user` var'en indsæt den del af `CLEARDB_DATABASE_URL` var'en der kommer efter "mysql://" og før ":". Dette er dit brugernavn til databasen. Her er et eksempel: `mysql://b3edsagf1f:`
13. I `pass` var'en indsættes den del af `CLEARDB_DATABASE_URL` der kommer efter ":" og før "@". Dette er koden til databasen. Her er et eksempel: `:52fadac@eu`
14. I `url` var'en indsættes hele af `CLEARDB_DATABASE_URL` borset fra `?reconnect=true` og i starten af var'en indsættes `jdbc:` Dette er url'en til din database. Her er et eksempel: `jdbc:mysql://b3edsagf1f:52fadac@eu-cdbr-west-02.cleardb.net/heroku_46464`.
15. Åbn MySQL Workbendch og opret en database connection.
16. I `Hostname:` indsæt `@eu-cdbr-west-02.cleardb.net` delen af `CLEARDB_DATABASE_URL` var'en.
17. I `Username` indsættes `user` var'en.
18. I `Password:` indsættes `pass` var'en.
19. I `schema` indsættes `heroku_46464` delen af `CLEARDB_DATABASE_URL` var'en.
20. Åbn connection'en og kør CreateDatabase SQL script'et på repositoriet. Udskift `use rentalcarflow;` med navnet på dit schema.
21. Kør CreateTestCases script'et (dette er valgfrit).
22. Gå tilbage til dashboardet og klik "open app".

Hjemmesiden er nu klar.

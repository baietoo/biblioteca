# biblioteca
PAO - proiect biblioteca

# Cerinte proiect
Fiecare student va lucra la un proiect individual Condiția de punctare a proiectelor:
- să nu prezinte erori de compilare 
- să se implementeze cerințele date
## Definirea sistemului
Să se creeze o lista pe baza temei alese cu cel puțin 10 acțiuni/interogări care se pot face în cadrul sistemului și o lista cu cel puțin 8 tipuri de obiecte.
### Actiuni
| Tip Obiect | Actiune |     |
|------------|---------|-----|
| Sectiune   |         |     |
| Carte      |         |     |
| Autor      |         |     |
| Cititor    |         |     |
| Imprumut   |         |     |
| Intarzieri |         |     |
| Intarzieri |         |     |
| Intarzieri |         |     |
| Intarzieri |         |     |
| Intarzieri |         |     |



# Implementare
## Aplicația va conține: 
- clase simple cu atribute private / protected și metode de acces 
- cel puțin 2 colecții diferite capabile să gestioneze obiectele definite anterior (eg: List, Set, Map, etc.)
    - ```
        ArrayList<Carte>
        ArrayList<Cititor> // sorted
      ``` 
    - dintre care cel puțin una sa fie sortata
      - se vor folosi array-uri uni-/bidimensionale în cazul în care nu se parcurg colectiile pana la data checkpoint-ului. 
- utilizare moștenire pentru crearea de clase adiționale și utilizarea lor în cadrul colecțiilor
  - interfatare sau clasa abstracta pentru a defini Carte
- cel puțin o clasă serviciu care sa expună operațiile sistemului
  - #TODO: clasa statica? am nevoie de mai multe de detalii aici
- o clasa Main din care sunt făcute apeluri către servicii

### Aplicatia trebuie sa asigure persistenta datelor(fisier sau baza de date):
- Se vor realiza fișiere de tip CSV pentru cel puțin 4 dintre clasele definite în prima etapa. Fiecare coloana din fișier este separata de virgula. Exemplu:nume,prenume,varsta 
- Se vor realiza servicii singleton generice pentru scrierea și citirea din fișiere; 
- Se pot inlocui serviciile care lucreaza cu fisiere cu servicii care asigura persistenta in baza de date folosind JDBC
- Se vor realiza servicii care sa expună operații de tip create, read, update si delete pentru cel puțin 4 dintre clasele definite.
- La pornirea programului se vor încărca datele din fișiere utilizând serviciile create;

### Toate operatiunile din aplicatie vor fi auditate folosind un serviciu de audit
- Se va realiza un serviciu care sa scrie într-un fișier de tip CSV sau in baza de date de fiecare data când este executată una dintre acțiunile descrise în prima etapa. Structura fișierului/ tabelului: nume_actiune, timestamp
- > OBS: Aplicatia va expune un meniu in consola astfel incat operatiunile posibile pot fi selectate  prin introducerea de la tastatura a cuvintelor cheie

Teme Sugerate
- 1)catalog (student, materie, profesor) 
- 2)biblioteca (sectiuni, carti, autori, cititori)
- 3)programare cabinet medical (client, medic, programare)
- 4)gestiune stocuri magazin (categorii, produse, distribuitori) 
- 5)aplicatie bancara (conturi,extras de cont, tranzactii, carduri, servicii) 
- 6)platfora e-learning(cursuri, utilizatori, cursanti, quizuri) 
- 7)sistem licitatii (licitatii, bids, produse, utilizatori) 
- 8)platforma food delivery(localuri, comenzi, soferi, useri) 
- 9)platforma imprumuturi carti -tip bookster (companii afiliate, utilizatori, carti) 
- 10)platforma e-ticketing (evenimente, locatii, clienti)
- 
# Predare proiect
Proiectul va fi sustinut sub forma de prezentare In ultimul laborator.
Codul sursa va fi incarcat public pe un system de versionare, iar link-ul va fi trimis pe email.
> Termen limita: 24 Mai 2023
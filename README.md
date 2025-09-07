Wersja developerska - prace trwają. 
Ponieważ mój kod zasadniczo został w firmach, gdzie pracowałem, jest to rekonstrukcja z pamięci. Mogą być błędy. 
Z grubsza jest tak: 
* Aplikacja buduje się bez warningów, pom.xml raczej poprawny.
* Wdraża się poprwnie bez problemów na TomcatEE lokalnie, gdzie jest poprawnie zainicjowana baza postgres.
* Nie wdraża sie na dockerze, bo trzeba dopiąć skrypt SQL inicjujący bazę: tabela User itd. 
* SecurityConfiguration nie jest skonfigurowane, na ten moment.

Inspirowałem się tym: https://www.youtube.com/watch?v=6r-MpAWVw6c 

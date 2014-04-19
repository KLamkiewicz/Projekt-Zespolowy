Projekt-Zespolowy
=================


####Technologie Programistyczne
* Framework – Libgdx
* Język – JAVA 7
* IDE – Eclipse 
* Notepad++

####Technologie graficzne : 
*	Autodesk 3DsMax 2014
*	Blender
*	Sculptris 
*	Chaos Group Vray / Corona
*	Krita / Adobe Photoshop

####Technologie pomocnicze
*	Github – system kontroli wersji
*	HTML , javascript, Bootstrap
*	Trello.com
*	MS Office
*	Dropbox

####Platforma Docelowa – Desktop (priorytet):
*	Windows XP – 8.1 
*	Linux 
*	Mac OS X

####Platformy Dodatkowe - Mobile:
*	Android 4.0 – Tablety 
*	HTML 5 – przeglądarki internetowe


###Założenia projektu : 


  Gra ma być domyślnie uruchamiana w wersji pełno ekranowej.  Zakładamy możliwość rozruchu w wersji okienkowej. Chcemy, aby aplikacja spełniała dzisiejsze standardy Full HD oraz Responsive Design. Nie chcemy się ograniczać do jednej rozdzielczości ekranu, co wykluczyłoby port gry na urządzenia mobilne i zdecydowanie pogorszyło wrażenia z rozrywki na platformie PC z monitorami nieobsługującymi 1080p. Oczywiście będzie pewien próg tolerancji i na urządzeniach z pewną przekątną ekranu czy rozdzielczością grać się nie będzie dało, wymagania minimalne aplikacji zostaną przedstawione w późniejszej fazie rozwoju.  
  Na komputerach osobistych przewidujemy sterowanie wyłącznie za pomocą klawiatury, na tabletach poprzez dotyk graficznych przycisków w lewym i prawym dolnym rogu ekranu.  
Projekt będzie posiadał swoją stronę internetową, która będzie zawierać dokumentacje , materiały reklamowe gry, odnośniki do pobrania różnych wersji, oraz wersje demonstracyjną gry w html 5. 




###Zespół

Imie | Nazwisko | Rola
--- | --- | ---
*Agnieszka* | *Rzeszuto* | **Web Developer, Level Designer, Documentation writer, Game Tester**
*Sabina* | *Litke* | **Web Developer , Level Designer , Documentation writer, Game Tester**
*Jarosław* | *Cimoch* | **3D Artist, Second, Main Developer**
*Krzysztof* | *Lamkiewicz* | **Main Developer**




                                Dokumentacja gry – etap pierwszy
                                „Tiny Time”*
*tytuł gry roboczy

1. Zasady gry:

Wzorem dla gry „Tiny Time” jest gra platformowa „Rayman”. Gracz wciela się w postać (?), której zadaniem jest przejść przez wszystkie krainy, gdzie czeka go zwycięstwo i nagroda. Podczas tej wędrówki musi pokonać różne przeszkody, którymi są zróżnicowanie terenu oraz stwory. Trudność w ich pokonaniu zależna jest od etapu gry. Można je próbować ominąć lub zaatakować.  W trakcie całej przygody zbierane są monety(?), które dają określone bonusy. Dodatkowo w każdej krainie gracz spotka postać, która przydzieli nam zadanie do wykonania na danej planszy.


2. Podział gry: (punkt zostanie uzupełniany na kolejnych etapach pracy)

Gra dzieli się na rundy, które przenoszą bohatera do innej krainy:

- I poziom – to lodowa kraina, który zakończy łamigłówka „Krzyżówka z sumami”.
Polega ona na wpisaniu w każde pole diagramu takiej cyfry, aby:
* suma cyfr w każdym rzędzie poziomym była równa liczbie znajdującej się przed tym rzędem (nad ukośną kreską w przekreślonym polu);
* suma cyfr w każdej kolumnie pionowej była równa liczbie znajdującej się nad tą kolumną (pod ukośną kreską w przekreślonym polu).
Wszystkie cyfry w danym rzędzie lub kolumnie (tworzące podaną sumę) powinny być różne i żadna nie może być zerem. 


W tej rundzie gracz porusza się po kostkach lodu. Jego celem jest uwolnienie jednorożca, które nastąpi po rozwiązaniu łamigłówki. Życie może stracić w wyniku spadnięcia z lodu, lub w momencie gdy zabraknie czasu. Dodatkowy czas można uzyskać zbierając klepsydrę, natomiast życie można zdobyć znajdując serce. Gra zaczyna się z 3 życiami oraz z 3 minutami czasu.

-II poziom- w krainie wulkanów, który zakończy łamigłówka „A, B, C” polegająca na:
Diagram należy podzielić wzdłuż oznaczonych linii na części - każda o innym kształcie i wielkości - tak, aby w każdej części znalazło się po jednym i tylko jednym A, B i C.
Podobne jak w rundzie I, gracz dąży do uwolnienia jednorożca. Tym razem poruszając się po fragmentach ziemi musi uważać na wypływającą lawę. Bonusy podobnie jak w rundzie I. 
 

3. Sterowanie postacią:

•	PC
            - chód w prawo – strzałka w prawo
            - chód w lewo – strzałka w lewo
            - skok – strzałka w górę
            - wysoki skok – spacja 
            - kucanie/unik – strzałka w dół
            - atak – (?)



•	tablet
- sterowanie postacią poprzez dotyk graficznych przycisków w lewym i prawym dolnym rogu ekranu.


4. Tworzenie nowej rozgrywki:

Chcąc rozpocząć nową grę, po jej uruchomieniu należy podać swój login. Po czym od razu można przystąpić do rozgrywki. Jeśli gracz chce przerwać grę może ją zapisać, po czym po podaniu wcześniej wybranego loginu, ponownie zagrać od miejsca zapisu gry, zachowując wszystkie dane. W ten sposób z gry może korzystać kilku graczy, każdy  z odrębnej rozgrywki.

5. Uruchomienie gry:

Po uruchomieniu gry ukazuje nam się menu gry, które pozwala rozpocząć nową rozgrywkę, usunąć  lub wczytać rozpoczętą. Możemy także zmienić ustawienia gry np. wł/wył dźwięku.

6. Założenia projektu : (zostaną zmienione oraz wzbogacone o detale na kolejnych etapach projektu)
Gra ma być domyślnie uruchamiana w wersji pełno ekranowej. Zakładamy możliwość rozruchu w wersji okienkowej. Chcemy, aby aplikacja spełniała dzisiejsze standardy Full HD oraz Responsive Design. Nie chcemy się ograniczać do jednej rozdzielczości ekranu, co wykluczyłoby port gry na urządzenia mobilne i zdecydowanie pogorszyło wrażenia z rozrywki na platformie PC z monitorami nieobsługującymi 1080p. Oczywiście będzie pewien próg tolerancji i na urządzeniach z pewną przekątną ekranu czy rozdzielczością, grać  nie będzie można, wymagania minimalne aplikacji zostaną przedstawione w późniejszej fazie rozwoju.

7. Informacje dla użytkownika:
Projekt będzie posiadał swoją stronę internetową, która będzie zawierać instrukcję, dokumentację , materiały reklamowe gry, odnośniki do pobrania różnych wersji, oraz wersje demonstracyjną gry w html 5. 


Jak uruchomic
=====

Eclipse -> Run -> Run configurations -> Arguments -> VM Arguments -> -Xms512M -> Apply

Dociagniecie assetow https://www.dropbox.com/home/PROJEKT%20ZESPOLOWY%20-%20planowanie/assets-raw wrzucic do Game-desktop assets-raw , w przypadku błedów w kompilacji odświeżyć Game-android

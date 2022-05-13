# Büfé termékek nyilvántartó/vásárló program

### A program célja/lényege

A program ugye, ahogy a nevében is szerepel gazdaság témakörön belül van, egy termékeket nyilvántartó program.
Két fajta felhasználási módja is van, az egyikben dolgozóként kezelhetjük a termékeket, míg a másikban vásárlóként rakhatjuk össze a saját kosarunkat és vásárolhatjuk meg a kívánt árukat.

## Osztályok

### Good

A termékek generálásához szükséges getter, setter, illetve constructor metódusokat tartalmazza.

Az osztály tagváltozói:

* String name - a termék neve
* int price - a termék ára (forintban!)
* int id - a termék azonosítója (9! számjegyből álló számsor)
* String category - a termék kategóriája (pl. rágcsálnivaló)

### Main

A main-en belül található a menü választási opció. Két lehetőség közül választhat a felhasználó. Beléphet az alkalmazotti felületre vagy a vásárlóira.
Vásárlóként lehetősége van a termékek listázásra, azok kosárhoz adására, kosárból való elvételére, kosár megtekintésére, illetve ha végzett a vásárlással, akkor fizetésre.
Az adatbázisban termék hozzáadására, törlésére, illetve módosítására azonban csak dolgozóként van jogosultsága. Az ehhez való hozzáféréshez szüksége lesz a felhasználonak a megfelelő felhasználónévre és jelszóra!

```bash
username: admin
password: 6vasNzQ3569z
```

### Methods

A mainben kiválasztott menüpontok ebből az osztályból hívják meg a metódusaikat, az átláthatóság kedvéért került ez mind egy külön osztályba.

## Xml fájl és annak osztályai, metódusai

* goods - a fájl gyökéreleme ezen belül vannak a termékek
  * good - egy termék adatait tartalmazza
    * name - az adott termék nevét tartalmazza
    * price - az adott termék árát tartalmazza
    * id - az adott termék azonosítóját tartalmazza
    * category - az adott termék kategóriáját tartalmazza

### xmlReader

Az előbb említett tagokat olvassa be az xml fájlból majd azokat a megfelelő típusúvá alakítás után a "goods" nevű Good objektumokből álló ArrayListbe pakolja, mindegyik tagot hozzáadva az adott termékhez/Good-hoz.

### xmlSaver

A program minden egyes futás végén elmenti az ArrayListben történt módosításokat és ezek alapján írja át az xml fájl tartalmát, ezzel az esetleges módosítások maradandóak lesznek. Ezt mind a createChildElement metódus segítségével teszi, ott hozza létre a termékekből az xml fájl elemeit.

## Metódusok

!A Methods class metódusainak részletes leírása!

### listProducts

Ez az adminmenü, illetve a vásárlói menü első választható opciójához tartozó metódus, ahol végigmegy a program a beolvasott ArrayList-en majd külön külön kiírja a termékek összes adatát.

### addNewProduct

Ez az adminmenü második választható opciójához tartozó metódus, ahol a program először négy különálló metódus segítségével bekéri az új termék adatait és a dolgozónak lehetősége van egy teljesen új terméket adni a listához, majd az xml fájlhoz is.

### modifyProduct

Ez az adminmenü harmadik választható opciójához tartozó metódus, ahol a program először bekéri a módosítani kívánt termék nevét, végig megy a listán, ha nincs ilyen nevű termék azt jelzi. Ha van, akkor belép egy switch-case-be, ahol lehetőségünk van kiválasztani, hogy a termék mely tagváltozóját szeretnénk módosítani. Itt is ahogy a menükben is csak a választható pontok közül lehet választani különben a program hibát jelez. Ezeket a módosításokat is természetesen menti a program.

### deleteProduct

Ez az adminmenü negyedik választható opciójához tartozó metódus, ahol a program először bekéri a törölni kívánt termék nevét, végig megy a listán, ha nincs ilyen nevű termék azt jelzi. Ha van akkor azt a terméket név és index alapján eltávolítja az ArrayListből, majd mentés során az xml fájlból is.

### dataOfProduct

Ez az adminmenü ötödik választható opciójához tartozó metódus, ahol a felhasználó lekérheti név alapján egy bizonyos termék adatait. A program itt is végig megy a listán és az alapján írja ki a képernyőre a megadott név adatait, ha az megegyezik az ArrayList aktuális elemének nevével. Ha nincs ilyen nevű termék az adatbázisban arról is jelzést kapunk.

### inputPrice

A bekérő metódusok egyike, ahol a felhasználó megadhatja a termék árát, amit addig kell ismételnie még nem számot ad meg, természetesen itt is hibajelzés történik. Az értékét returnöli adatmegadásnál.

### inputId

A működése teljesen hasonló az inputPrice metódushoz azzal a különbséggel, hogy a metódus itt az id hosszát is ellenőrzi, hiszen mindenképpen 9 számjegyből kell annak állnia, ellenkező esetben hibát jelez. Az értékét returnöli adatmegadásnál.

### inputName

Ez már String bekérő metódus, itt tudjuk megadni a visszatérítendő nevet, amit a program leellenőriz, hogy foglalt-e vagy üres-e, ezekben az esetekben hibát jelez.

### inputCategory

Ez az adatmegadó metódus is hasonlóan működik mint az inputName, azzal a különséggel, hogy itt nem ellenőrzi le a program, hogy foglalt-e már, mivel több termékhez is tartozhat ugyanaz a kategória.


### addToCart

Ez a vásárlói menü második metódusa ahol a vásárló megadhatja a választott termék nevét és az alapján egy ciklus segítségével, ha megegyezik a megadott név valamelyik létező termék nevével, akkor azt hozzáadja a kosárhoz majd returnnel kilép a metódusból (itt is). Ha nincs ilyen nevű termék, hibajelzés érkezik.

### removeFromCart

Ez a vásárlói menü harmadik metódusa ahol a vásárló megadhatja, hogy mely terméket szeretné eltávolítani a kosárból, megadott név alapján a végig megy a program a listán és a megfelelő nevű terméket törli a kosárból, ha nincs ilyen termék akkor azt jelzi a program, illetve, ha üres kosárból törölnénk akkor is hibaüzenetet kapunk vissza.

### listCart

Ez a vásárlói menü negyedik metódusa, ahol a vásárló egy listán végigmenő ciklus segítségével lekéri a kosarában található termékek nevét és árát, ha a kosár üres, akkor a program itt is jelez.

### pay

Ez a vásárlói menü ötödik metódusa, a vásárló itt tud fizetni, megkapja mennyibe kerülnek a kosarában talalható termékek és választhat, hogy fizet és befejezi a vásárlást, ezzel a kosár is kiürül, vagy visszalép és folytatja még esetleges termékek eltávolításával vagy hozzáadásával.

## Megjegyzések

* A metódusok többségénel megjelenik megadott paraméterként vagy a cart lista vagy a goods lista, a metódusok ezeket használják fel a parancsok végrehajtására, illetve az elemek vizsgálatára.
* Bekerült a programba továbbá még két új hiba, amik csupán azért kerültek be, hogy néhány típus hibának nevet adhassak.
* A programban törekedtem az érthető és legoptimálisabb kódsorok megírására, az adatvalidációra, hibajelzésre, illetve az általam talált összes hiba lekezelésére és egy felhasználóbarát program elkészítésére.

Cilj ovog projekta je da omoguci osnovni uvid u Java UI testiranje sa Selenium i TestNG bibliotekama. <br>
Ovo je **Maven** projekat i trebalo bi ga "importovati" u razvojno okruznje kao takvog.<br>
Sajt koji cemo koristit za rad  je: "https://www.saucedemo.com/" <br>



**Nameštanje sistema**

**Java** <br>
Pre samog početka potrebno je skinuti Java 11 biblioteku.<br> 
To cemo uradi tako što će mo otici na lokaciju [Java JDK Archiva](https://jdk.java.net/archive/) zatim 
naći 11.0.2(build 11.0.2+9) i kliknuti na Windows 64-bit zip link. Nakon toga otvoriće se prozor 
gde cemo sacuvati ovaj fajl. <br>
Posto smo sačuvali fajl potreno ga je otpakovati - desnim klikom miša na fajl i izabrati opciju **Extreact All...** zatim u novom prozoru
kliknuti na **Extract** dugme u donjem desnom uglu. <br>
Nakon toga potrebno je u System varaibles **JAVA_HOME** staviti putanju do otpakovanog foldera, to radimo tako što odemo
u **System Properties > Environment Variables** i u System variables kliknemo na New.. dugme i dodamo novu varijablu JAVA HOME, 
a vrednost varijable je putanja do naseg otpakovanog fajla. <br>
<br>
JAVA_HOME:

![img_3.png](read_me_data/img_3.png)

Zatim u System variables prozor moramo uvrstiti našu JAVA_HOME varijablu u Path varijablu, <br>
to radimo tako što kliknemo na Path varijablu, zatim kliknemo na dugme New... i dodamo **%JAVA_HOME%\bin** koa novu vrednost.
<br>
<br>

PATH:

![img_2.png](read_me_data/img_2.png)

Da bi smo proverili da li je sve u redu možem otici u Command prompt i ukucati komandu **java -version** i ako nam se prikaže
korektna verzija jave znači da smo sve dobro podesili.
<br>
<br>

**Maven** <br>
Isto što smo uradili sa Javom moramo sada uraditi i sa Maven bibliotekom: <br>
Otićićemo na sledecu lokaciju [Apache Maven](https://maven.apache.org/download.cgi) i kliknuti na **Binary zip archive** i 
sačuvati fajl na proizvoljnoj lokaciji.<br>
Takodje moramo otpakovati ovaj skinuti maven folder i zatim isto tako napraviti M2_HOME varijablu u System variables sa lokacijom
ka nasem otpakovanom maven folderu, a zatim u Path varijablu dodati **%M2_HOME%\bin**
<br>
<br>
M2_HOME
<br>
MAVEN_HOME
<br>

![img.png](read_me_data/img.png)

<br>

%M2_HOME%\bin
<br>
%MAVEN_HOME%\bin
<br>

![img_1.png](read_me_data/img_1.png)

Time smo namestili nase razvojno okruzenje.



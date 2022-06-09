**RESPECTO A PLAYER Y SUS TEST**
****
Se creó el enum SubirCon y se da como parámetro a Player con dos opciones posibles:STARS y WINS,
que permiten elegir el criterio para subir de norma en el siguiente norma check.
Por defecto, tiene como valor inicial STARS debido a que para el primer norma check no se tiene 
un valor de wins que permitan subir. A este parámetro se le  los métodos:
getStar_or_wins(),subir_wins(),subir_star(), estos dos últimos permiten cambiar la forma de subir de norma.

Además, se creó el parámetro wins, inicializado en cero, que lleva la cuenta de estas. A este se le asociaron
dos métodos:getWins(),increaseWinsBy(final int amount). Como las wins no disminuyen
no se creó un método para esta funcionalidad.

En los test de player se probaron todos los métodos creados. Con esto se prueba la funcionalidad de
esta clase.



****
**RESPECTO A PANEL**
****
Se decidió crear una clase abstracta "AbstracPanel" que implementa la interfaz InterPanel. En esta
interfaz se indican los métodos que absolutamente todos los tipos de paneles tienen que tener, y en 
la clase abstracta estos son implementados. Los tipos de paneles son clases por si solos y heredan la
clase abstracta y, por lo tanto, estos métodos.

Un método muy importante es public void activate(final  Player player), el cual es sobreescrito
en cada panel, y en este serán llamados los métodos que se deben aplicar al jugador player al caer
en este panel. Estos métodos siempre serán privados, ya que activarlos es la única forma de aplicarlos
a un jugador. Para activarlo se va a revisar que el jugador se encuentre en la lista que indica los
jugadores en el panel.

A la vez, para cada tipo de panel se sobreescribe el método equals y hashCode(). 

Respecto a DrawPanel no tiene métodos que creen su funcionalidad, debido a que se necesita tener cartas.
Por esto, solo se sobreescribieron los métodos descritos anteriormente.

EncounterPanel y BossPanel:
Cada uno tiene un parámetro que se llamará Rival, lo que es ligeramente diferente para estas dos clases
pero su funcionamiento es igual.
Por defecto este rival será null, y al momento de que un jugador active el panel se generará aleatoriamente
el enemigo. Este permanecerá vivo hasta que su vida sea 0 y se llame el método public Boolean rival_dead(), que
indica si el enemigo está muerto y en caso de estarlo asigna nuevamente al parámetro Rival el valor null
hasta que el siguiente jugador active el panel y vuelva a generar aleatoriamente el enemigo.
Debido a que no se ha implementado la batalla, probablemente esté impleto este método.

Para HomePanel:
En este método se agrega un nuevo parámetro owner de tipo Player que debe ser dado como parámento al 
constructor y corresponde al dueño del panel.

Para este panel al activarlo se tenían dos funcionalidades. La primera corresponde a hacer un normaCheck.
Para hacer esto se decidieron implementar 3 métodos, cada uno con una responsabilidad propia.

normaCheckStar Y normaCheckWins revisan si el jugador cumple las condiciones para subir de norma
con criterios de star y wins respectivamente. makeNormaCheck deriva a alguno de los dos métodos anteriores
a un jugador según la forma que haya decidido apra subir de nivel.

Para la segunda funcionalidad, se crea el método applyHealTo que permite al jugador recuperar un punto de salud.

Estos 4 métodos son privados, y llamados por el método público activate.

Los otros tipos de paneles no serán explicados, ya que son mucho más sencillas sus implementaciones.
****
**RESPECTO A TEST PANEL**
****
Para el test de panel, se decidió separarlos en varias clases. Hay una clase por cada tipo de panel 
que prueba las especificaciones de ese panel, entre ellas la creación del constructor, la activación,
y equals. En algunos tipos se prueban diferente cosas.

También se tiene InteractionPanelsTest, que revisa pruebas donde participan más de un tipo de panel,
como los paneles siguiente y métodos equals. 

Se decidió hacer el diseño de los test de esta forma porque resulta mucho más legible y modificable, ya
que al tener todo en la misma clase se volvía largo y dificil de entender cada test.


****
**RESPECTO A LOS ENEMIGOS**
****
Para ambos tipos de enemigos, Boss y Wild, se siguió la misma lógica.
Se creó un enum asociado a cada tipo, llamados BossType y Wild_type, esto debido a que 
los enemigos posibles son limitados y, además, tienen características específicas y fijas. 
Con esto, en los enum se crearon cada enemigo con parámetros name,maxHp,atk,def,evd. Se crearon
métodos también en este enum para poder extraer estos parámetros, los cuales son usados en boss y
wild.

Para estos dos métodos se tienen varios parámetros, y el constructor recibe un único parámetro
correspondiente a BossType o Wild_type, de los cuales debido al enum y los parámetros predeterminados 
se obtienen los parámetros name,maxHp,atk,def,evd. Además de esto, se crean currentHp y stars, los cuales
son valores que según las batallas y sus resultados se irán modificando.

El motivo por el cual se decidió hacer esta estructura es que con el enum se evitó hacer subclases
para cada tipo de enemigo, debido a que todos tienen el mismo comportamiento. Además, es mucho mas fácil 
agregar a nuevos enemigos, ya que es agregarlo en el enum con sus respectivas características.

****
**RESPECTO A LOS TEST DE ENEMIGOS**
****
Para testear los enemigos se revisó que el constructor estuviera bien hecho para cada uno de los tipos
de enemigos, también que las stars cumplieran lo pedido, pudiendo aumentarse o disminuirse y no ser 
negativas, también se revisó HP para no superar el máximo y equals.  Con esto, todas las funcionalidades
fueron comprobadas y en próximos usos de estos (como, por ejemplo, en panel) no deben ser comprobados.






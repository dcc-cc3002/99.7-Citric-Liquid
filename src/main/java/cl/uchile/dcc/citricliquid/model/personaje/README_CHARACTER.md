**EXPLICACIÓN PERSONAJES COMO CONTEXTO EN STATE PATTERN**
---

Para poder implementar el patrón de diseño STATE PATTERN, se tomó como contexto a los personajes.
Por esto, se debieron hacer modificaciones en relación con lo que se tenía en primera instancia en la 
tarea 1.

----

**Modificaciones en AbstractCharacter:**

Como todos los personajes extienden de AbstractCharacter, acá se crearon los métodos comunes tanto para players 
como para boss unit o wild unit. Se agregó una nueva variable a la clase: "*protected State state;*". Esto es porque cada
personaje tiene que tener asociado un estado. Se le da una inicialización en el estado Inactive.

Con este parámetro se crean los métodos getState(); y setState(State state);. 

También se crean los métodos que permiten transicionar entre estados, los cuales TODOS son de la forma:
public void toEstado1() {state.toEstado1}, es decir, es el estado que se encarga de redirigir a otros estados. Esto es 
porque no todas las transiciones están permitidas y en la clase State y sus hijos se puede manejar esto.

Acá se encuentran únicamente las transiciones que tanto player como los boss y wild pueden realizar. Player puede estar
en muchos más estados que los enemigos.

A la vez, se definen algunas funciones nuevas que también llaman a state.función, esto es porque muchas van a ser 
reimplementadas según el estado en el que se encuentre, y de esta forma se puede utilizar la herencia para hacer un código
mucho más legible. Estas funciones son las relativas a las batallas.

----

**Modificaciones en Player**

En la clase Player también se realizaron algunas modificaciones extra, ya va a heredar todas las hechas en la clase 
abstracta.

Se crea una nueva variable *private int chapters;* inicializada en 0 en la cual se va a guardar la información del 
chapter en que nos encontramos. Va a ser responsabilidad del controlador el administrar los capítulos. Con esta variable 
se generaron los métodos getChapters() y setChapters(int charp).

Se definieron los métodos de transición de estados que solo puede hacer un personaje de tipo Player. Entre estos, se
encuentran varios que requieren un input o que posiblemente podrían requerirlo, como los estados: MoveState, 
DetenidoState, CounterAttackedState y AttackedState.

Para pasar a los estados StartTurn, RecoveryState,ActiveState, EffectPanelState, que son aquellos en los que no 
se requiere ningún input, se envía una señal a un handler para ejecutar la función runContextAction() que a la vez
va a llamar a runAction() del respectivo estado. Estas funciones realizan una acción y derivan al siguiente estado, 
según corresponda, porque en todos estos casos solo va a haber una transición posible. Para utilizar esto, se crea
el método addStateRunContextChange(PropertyChangeListener listener), que nos permite añadir un "oyente" atento 
a si existe una notificación.

También hacemos que se envíe una señal si el player realiza una norma clear. Para poder escuchar estas señales, se creó
el método addNormaChangeNotification(PropertyChangeListener listener). Lo que se hace con cada señal es explicado en 
otro archivo.


----
**Modificaciones en Enemies(Wild y boss)**
Para estos personajes, en la clase abstracta AbstractEnemies de la cual heredan ambos tipos de enemigos, se reimplementó
un método toAttackedState(), el cual al azar va a elegir si el personaje desea defender o esquivar al momento de entrar en 
batalla, ya que no se espera tener un input de su parte.


----
SOBRE EL PATRÓN DE DISEÑO
----
Para hacer la tarea se decidió utilizar el patrón de diseño State. En este caso, el contexto corresponde a los personajes, 
y como pueden ser players o unidades se considera de forma general como del tipo AbstractCharacter, aunque hay ciertos 
estados en los que solo se puede estar si se es Player. Respecto a State y las transiciones y flujo se tiene un archivo 
README en la carpeta states, en la cual se explican estos, transiciones y lógicas.

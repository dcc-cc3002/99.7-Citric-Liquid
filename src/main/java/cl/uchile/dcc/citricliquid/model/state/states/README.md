**README PARA LOS ESTADOS**
Este archivo es para explicar y justificar los estados y sus métodos.
---------------
**Estados relativos al turno:**


Se comienza siempre en **Inactive**: estado que indica que el jugador no está en su turno y no tiene acciones
por realizar. Para salir de este estado, es necesario manualmente sacar al character a otro estado,
existiendo solo dos posibilidades: Pasar a Active, en caso de que sea su turno o pasar a AttackedState
en caso de que haya sido atacado.

Cuando se inicie el turno del jugador, se "Activa", por lo que se tiene una única transición
que va a **Active**. cuando se llama a ToActiveState(), y el personaje está en estado Active, se llama al método 
runAction() que deriva a los dos posibles estados según el hp del jugar. Estos se explican a continuación:

**RecoveryState**: en caso de tener hp=0 se llega a este estado. Dentro de este, se encuetra el método
runAction() que decide cual será el siguiente estado. Esto se hace tirando un dado y obteniendo el 
chapter en el que el jugador se encuentra. Si la suma de estos dos valores es > 7, encontonces se recupepra
y el persona obtiene su hp máximo y pasa al estado StartTurn. En caso de que no se cumpla la condición, 
vuelve a Inactive con hp = 0 termiando su turno.

**StartTurn**: en caso de tener hp>0. Al pasar a este estado se va a ejecutar la función runAction(), la cual
le dará al jugador una cantidad de estrellas dependiente del chapter en que el jugador se encuentre y a continuación
cambiará el estado a MoveState.

**MoveState** Cuando el jugador se encuentra en este estado, es que puede avanzar en el panel. En la 
clase de este estado se define la función mover_un_panel(), la cual permite moverse un panel en
el tablero, preguntando si es que hay más de una posible opción. Como para esta tarea no se debe 
implementar los inputs, se dejó comentado una forma tentativa de hacer esto en el método. Para avanzar, 
se va a hacer manualmente. De este estado, solo puede pasar a DetenidoState. Esto cuando se detiene,
ya sea porque encontró otro jugador y decidió detenerse o porque sus movimientos indicados por el 
dado han terminado.

De este estado no se manda automáticamente al siguiente, sino que se tiene que invocar manualmente al método 
player.toDetenidoState. Esto es porque el estado provee un método para moverse solo un panel y si no hay varios 
caminos. En caso de haber varios caminos, por el momento se tendreá que hacer manualmente el movimiento desde
el contorlador para elegirlo, pero eso va a pasar a ser carga del estado cuando se sepa el input. 

Por lo tanto, cuando se haya dejado al jugador en el panel de destino, se deberá invocar player.toDetenidoState
de forma manual para indicar que puede continuar.

**DetenidoState** : Hay 3 motivos por los cuales se pasa a este estado:
1. El jugador se detiene anticipadamente por encontrar su propio home panel. Pasa a EffectPanelState.
2. El jugador se detuvo anticipadamente por encontrarse con otro jugador y decide iniciar una batalla,
donde va a pasar a AttackState (Será necesario un input para decidir si desea pelear).
3. El jugador termina todos sus movimientos sin aticipación, pasa EffectPanelState.

De estas tres posibilidades se pueden notar las dos transiciones posibles: AttackState y EffectPanelState.

**EffectPanelState**: En este estado se aplica el efecto del panel en el que se encuentre. Para esto, se llama
al método runAction(), que realiza esto. En caso de no ser paneles de batalla, estos mismos envian de vualta al 
jugador a Inactive. En caso de ser BossPanel o EncounterPanel, se va a iniciar una battalla enviando al player a
attackState.


Con esto, se describieron todos los posibles estados relativos al turno. Es importante notar que únicamente los Players 
pueden estar en estos estados y no los enemigos (Boss o wild), por lo tanto, es posible hacer cast a player para llamar
métodos si un AbstractCharacter se encuentra en uno de estos estados, ya que sabemos con total certeza que es un player.

-------------
Estados relativos a las batallas: Atacante
-------------
Las batallas se inician bajo dos condiciones:
1. Un jugador pasa de estado Detenido a Attack e inicia batalla contra otro jugador.
2. Un jugador pasa de EffectPanel a Attack e inicia batalla con un boss o wild unit.

Va a ser el controlador quien sepa quien es el enemigo del jugador, y lo que pasa con esto
se verá posteriormente. A continuación se describe que pasa con el jugador que inicia la batalla,
sin importar quien es el enemigo,

**Attack**: Estado que indica que el jugador está atacando. En esta clase se define una función muy importante: 
battle(@NotNull AbstractCharacter atacado). Esta función será llamada desde el controlador, ya que este sabe quien
es el atacado. Lo que hace la función battle es:
1. Calcula el ataque enviado, tirando el dado.
2. Avisa al atacado que ha sido atacado. Por lo tanto, lo envía al estado AttackedState (Que será explicado después).
3. El atacado recibirá el ataque.
4. Si el atacado está en KO, entonces se realiza la transferencia de estrellas del perdedor al ganador, el ganador
aumenta su contador de wins y ambos pasan al estado **Inactive**.
5. Si el atacado tiene hp>0, entonces va a pasar al estado **CounterAttackState** y el atacante a **CounterAttacked**


**CounterAttacked**: En caso de estar vivo el atacado, el jugador que estuvo en el estado attack va a pasar a
este nuevo estado que indica que se está siendo contraatacado. Este es un estado en que únicamente pueden tener 
los jugadores y no Boss o wild, así que será requerido un input para elegir si esquivar o defender. En el caso de 
batallas en paneles, se hizo un método al azar para que el jugador elija, pero es tentativo y deberá ser reemplazado
con el input, solo es para hacer test. Para jugador vs jugador, se tiene que ingresar manualmente la decisión.
Existen dos posibles transiciones, la cual dependerá del input. Estas son a los siguientes estados:

**CounterEsquivar**: Si decide esquivar. En esta clase se sobreescribe el método attacked(int base_atk), que recibe como
parámetro el ataque enviado por el oponente. En esta, se va a calcular el daño que será recibido como está dado en el 
enunciado, recibiendo todo o nada según una condición. Finalmente, envía al player a Inactive. 

**CounterDefender**: si decide defender. Se sobreescribe el método attacked(int base_atk) para que el daño recibido sea 
como es indicado en el enunciado, disminuyéndolo según ciertas condiciones. Finalmente, envía al player a Inactive.

De estos dos estados, existe una única transición posible que es a Inactive, ya que se termina el turno de este jugador.

-------------

Estados relativos a las batallas: Atacado
-------------
En caso de que un character(Wild, Boss o player) haya pasado de estado Inactive a Attacked sucede lo siguiente:
1. El personaje será enviado al estado **AttackedState**. En este, se tiene que decidir si se desea Defender o esquivar,
lo cual debe venir como un input en caso de ser un Player, o se decide al azar en caso de ser un Boss o Wild.
Esto se hace redefiniendo la clase toAttackedState() de la interfaz GameCharacter.
En caso de ser un player, se deberá ingresar manualmente la siguiente transición, a los estados AttackedDefender o
AttackedEsquivar. 
Para el caso de los Boss o Wild, en la función toAttackedState() en la clase abstracta AbstractEnemies 
de las cuales heredan ambos tipos, se envía de forma aleatoria al estado de defender o atacar.
2. Se pasa a AttackDefender o AttackEsquivar según la decisión.

**AttackDefender** funciona similar a *CounterDefender*, redefine el método attacked(int base_atk), pero tiene dos posibles
transiciones: a CounterAttack, en caso de sobrevivir al ataque recibido o a Inactive en caso de no sobrevivir.

**CounterAttack**:Estado que se entra cuando se contraataca. Puede tenerlo cualquier tipo de personaje, pero el 
que ataque será siempre un tipo Player. Se define la función battle(@NotNull AbstractCharacter atacado), donde se va
a administrar el ataque de la siguiente forma:
1. Calcula el ataque enviado, tirando el dado.
2. Avisa al atacado que ha sido contraatacado. Por lo tanto, lo envía al estado CounterAttacked.
3. El atacado recibirá el ataque.
4. Si el atacado está en K.O, entonces se realiza la transferencia de estrellas y el aumento de wins (solo en caso)
que el atacante sea un Player. El atacante pasa a Inactive.

En este caso, solo existe una posible transición que es al estado Inactive, pues el turno ha terminado.

----
SOBRE ABSTRACTSTATE
----

AbstractState corresponde a la clase padre de todos los estados. Absolutamente, todos van a extender de este.
Tiene una única variable correspondiente al contexto, que es del tipo AbstractCharacter. Todos los personajes
tendrán siempre un estado, pero los Player pueden tener estados de turno a diferencia de los enemigos.


Acá se van a tener definidas todas las transiciones posibles, que tendrán un nombre genérico toEstadoDestino().
Estas transiciones van a mostrar en pantalla el mensaje "TRANSICIÓN NO PERMITIDA" y no van a hacer nada, es decir, 
el estado desde cuál se llama será conservado. En los distintos estados hijos se reimplementan, de tan forma que si la 
transición si esté permitida esta sea realizada correctamente.

Se tiene la función attacked(int base_atk), que es reimplementada en los 4 estados de manejo de ataque: defender y 
esquivar para un ataque y un contraataque. Esta función maneja el daño recibido según la decisión del character, y 
retorna el daño efectivo.

El método battle(@NotNull AbstractCharacter atacado) va a ser reimplementado en los estados Attack y CounterAttack, 
esta es una función muy importante porque maneja la mayor parte de la lógica de una batalla. Se encarga de calcular el 
ataque del atacado, y avisar al atacado que ha sido atacado. En caso de uno haber dejado K.O al otro, esta función hace
el traspaso de stars y wins, si corresponde.

La función runAction() lleva acciones al ser ejecutada en algunos estados, como en StartTurn. En general, hace una 
derivación básica de estados o ejecuta acciones monótonas que no requiere input del character bajo ninguna circunstancia.


Finalmente, se tiene la función mover_un_panel(AbstracPanel actual), la cual será reimplementada de forma util solo en el 
estado MoveState para avanzar en el tablero. En si, esta función permite avanzar un único panel, y comentado se encuentr
un código tentativo para hacer un input en caso de haber más de una opción para el siguiente movimiento. Va a ser el 
controlador quien se encargará de saber cuando el player ha terminado su movimiento y desea parar al siguiente estado, que
es detenid0.


----------------------
SOBRE PATRÓN DE DISEÑO EN GENERAL: STATE PATTERN
-----------------------
Lo descrito anteriormente es para tener un patrón de diseño State. En este, se le asocia a un contexto (AbstractCharacter)
un estado(State) y el estado tiene asociado su contexto. Las modificaciones hechas en AbstractCharacter para adaptarse a 
este patrón se encuentran en un README en la carpeta personajes. 

Se decidió seguir este patrón de diseño para conocer el flujo del juego ya que era factible debido a que los estados 
por definir no eran demasiados. Este patrón nos evita hace if else innecesarios, y hace el código mucho más entendible y 
manejable.

La idea es que el controlador pueda utilizar que conoce los estados de cada personaje para poder crear un juego 
adecuadamente. 



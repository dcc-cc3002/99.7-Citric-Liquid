**EXPLICACIÓN CONTROLLER**
---
A continuación se explicará el controlador del juego. Su objetivo es permitir que el usuario interactúe 
con el modelo y debe llevar la lógica de juego. Para esto, se crearon las siguientes variables:

*List<AbstracPanel> paneles* : Corresponde a un ArrayList<> que contiene todos los paneles del tablero.

*List<Player> players*: corresponde a un ArrayList<> que contiene a los 4 jugadores manejados por un 
usuario. Es importante que sea este tipo de lista porque debe tener nombre, donde la posición depende
del orden del que han sido creados los personajes

*Player winner*: Corresponde al jugador que ha ganado. Inicialmente, es null, será agregado cuando el 
primer jugador llegue a ser norma check.

*int actualTurn*: indica al dueño del turno. Va entre 0 y 3, y corresponde al jugador en esa posición
en la lista.

*int chapter*: Corresponde al capítulo en que nos encontramos. 

*boolean boss*; Es un boleano que indica si el boss ha sido agregado o no. Inicialmente, es false,
porque al comenzar el juego solo hay wild unit. Cuando un jugador llegue a norma 4, este será agregado
y su valor va a cambiar a true, para que no vuelva a aparecer otro en toda la partida.

-------
**Métodos relativos a las variables**
Se crearon métodos para obtener/ setear /incrementar las variables descritas
anteriormente, si esto es necesario. 

Son particularmente importante los métodos addPanel(AbstracPanel panel), y addPlayers(Player newPlayer),
los que permiten agregar paneles y players a sus respectivas listas. En general, no se van a llamar estos
métodos solos, sino que desde otros que crean estos dos tipos de objetos.

Se tiene el método void nextTurn(), que se encarga de actualizar el turno según sea necesario, también
aumente el charper tanto para el controlador como para cada player.

También se tienen métodos específicos para las siguientes funcionalidades:
1. Obtener el capítulo actual del juego.
2. Obtener el jugador que es dueño del turno.
3. Terminar el turno del jugador actual (nextTurn())


-------
**Métodos relativos a los paneles**
Tal como se indica por enunciado, se crearon métodos para las siguientes funcionalidades:
1. Crear paneles.
2. Asignarle a cada panel uno o más paneles siguientes.
3. Obtener todos los paneles del tablero.

Pero las siguientes funcionalidades se realizan indirectamente:
1. Definir el home panel de un jugador: Esto se hace de forma indirecta porque, según como están creados
los homePanels, se da el jugador que el dueño de este para crearlo y no de forma separada.
2. Realizar un norma check y norma clear cuando un jugador cae en un home panel: Esto se realiza cuando se
cae en un home panel y automáticamente será llamado al método activate, que justamente se encarga de hacer el
normaCheck y norma clear, por lo que en un juego esto no será llamado manualmente.

También se crea el método setEnemyToBossPanel(AbstractEnemies enemy), que permite poner en todos los boss panel 
del tablero el mismo enemigo, que se usará cuando el boss sea creado.

------
**Métodos relativos para characters**
Se crean métodos para las funcionalidades:
1. Crear jugadores, wild units y boss units.
2. Ubicar un jugador en un panel.
3. Definir el objetivo para aumentar de norma para un jugador.

Y también para las siguientes funcionalidades, que son implementadas utilizando el patrón de diseño 
observer:
1. Saber cuando un jugador gana llegando a la norma máxima checkNorma6().

------
**Métodos relativos al movimiento**

Para permitir que un player se mueva por el tablero, se creó el método:
*move(Player player, int moves, boolean battle, boolean home)*, que dado un player y una cantidad
de movimientos, se indica con valores boleanos si:
1. battle: Se desea detener en caso toparse con otro jugador y tener la posibilidad de iniciar una batalla.
Asociado a esto, se crea el método askToStopforBattle(player), que revisa si en el panel actual del juador 
se encuentra otro y, por lo tanto, existe la posibilidad de comenzar una batalla. El jugador es enviado al estado
DetenidoState.
2. home: Es true si se desea detener anticipadamente al encontrar su propio HomePanel. En relación, se crea el 
método askToStopforHomePanel(Player player), que revisa si el panel en el que en ese momento se encuentra el jugador
es su propio home panel. El jugador es enviado al estado DetenidoState.
3. Encontrar más de un camino posible: Si en algún momento entre los movimientos que desea ejecutar el jugador se 
encuentra con más de un posible camino, su movimiento se detiene. Se va a retornar la cantidad efectiva de movimientos 
realizados. El jugador va a permanecer en su estado (MoveState), y se debería pedir un input al usuario para elegir el 
camino, pero como esto no ha sido hecho en esta tarea se debe avanzar manualmente al jugador en estos casos, lo que se 
hace en los test en controller->move->moveWithOptions, donde se simulan movimientos con más de un posible camino. Para 
detectar estos casos, se crea el método askforWay(player) que indica si se debe preguntar por un camino específico.

Siempre se retorna el número de movimientos efectivos realizados.


---------------------
**Métodos creados para testear**
En el controlador también se encuentran métodos creados con la intención de hacer más sencillo el testear.
Estos son los siguientes:

1. *setWay(Player player, int i)*: permite indicar el camino que el player debe seguir en caso de haber más de uno.
Como los next panels se encuentran ordenados, podemos saber con certeza a cual panel se está enviando el jugador. Este
método debería ser implementado con un input.
2. *void battle(Player atacante, Player atacado)*: Este método nos permite simular una batalla player vs player.
Fue creado de tal forma que permitiera que los jugadores elijan defender o esquivar aleatoriamente para los test, ya que
esto debería ser dado con un input.
3. *Player possibleBattle(Player player)*: método que dado un player, va a devolver otro que se encuentre en el mismo 
panel para poder iniciar una batalla contra él. 


-----------
**SOBRE OBSERVER**

Se usó el patrón de diseño para las siguientes funcionalidades:
1. Saber cuando un jugador gana, es decir, se llega a norma 6.
2. Saber cuando debe aparecer el boos, es decir, se llega a norma 4.
3. Al cambiar de estado a uno que se pueda llamar la método runAction(), para hacerlo
automáticamente y no tener que llamar manualmente este método.

Esto se hizo creando una clase llamada NormaObserver, que nos permite observar la norma clear, es decir, cuando 
un jugador ha subido de norma. Esto se implementa en el controlador de la siguiente forma:
1. si un jugador llega a norma 4, entonces se llama al método activeBoss() que, si no ha aparecido el boss antes,
crea uno aleatoriamente y pone en todos los bossPanel, también cambia la variable boss a true, para que no aparezca 
otro cuando los demás jugadores lleguen a esta norma.
2. Si un jugador llega a norma 6, se llama al método checkNorma6(), que lo que hace es revisar que jugador tiene norma 6
y lo setea como ganador, terminando así la partida.

También se usa el método observer para vigilar el estado de los jugadores, en ya que en caso de ser Active, RecoveryState,
StartTurn o EffectPanelState, se debe ejecutar inmediatamente el método runAction(), ya que no existe la posibilidad de
esperar algún tipo de input por parte del usuario, sino que la acción depende de variables concretas. Por esto, se tiene 
la clase StateRunContextChange() que vigila el estado. 

*¿Por qué no se ejecutan acciones en los otros estados?*
Los motivos son dos, dependiendo del estado:

Los siguiente estados pueden recibir un input: MoveState, DetenidoState, AttackedState, CounterAttackedState.

Los siguientes estados hay una función que lleva su lógica: AttackedDefender, AttackedEsquivar, CounterDefender,
CounterEsquivar, Attack, CounterAttack.

Tampoco se puede observar el movimiento por los siguientes motivos:
1. Detenerse por batalla: No se implementó observer porque el decidir detenerse por una batalla es opcional.
Esto quiere decir que dada la posibilidad, el jugador puede elegir si detenerse o no. Se decidió preferible 
manejar, por ahora, esto desde la función move del controlador, mientras que no se tengan inputs.
2. Batallas con boss o wild: No se tiene el método porque es el método Activates de los paneles que llevan la lógica
de la batalla.


-----------------
**TEST DE JUEGO COMPLETO**
---------------------------
En la carpet Main se encuentra la clase main. En esta, se implementa un ejemplo de juego completo,
en la cual se puede ver como se utiliza el controlador para simular completamente una partida, creando los 
paneles, jugadores, etc. Solo que esta está hecha de tal forma que nunca sea necesario usar inputs, pues se tiene
solo un camino posible y la función move es estática.

Como este es un ejemplo de juego, no hay test para la clase, por lo que el coverage 0 es esperable.

----------------------------
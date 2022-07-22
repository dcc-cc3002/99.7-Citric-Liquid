**EXPLICACIÓN PANELES COMO CONTEXTO EN STATE PATTERN**
---
Para implementar el patrón state, se hizo un pequeño cambio en los paneles,
específicamente en la clase AbstractPanelEnemies, en el método activate.

En este método, se impementa una batalla de un player contra un wild unit o boss unit.
Lo que hace este método es llamar a los métodos "player.toAttack()" y "player.battle(rival)",
donde se llevan a cabo una secuencia de acciones (Explicado en readme en carpeta states),
y termina dejando a ambos personajes en estado inactive.

Con esto, siempre que un player esté en este panel y lo active, se realiza la batalla inmediatamente. 
Por facilidad en los test, se utiliza un método para que, en caso de existir un contraataque, el player
decida defender o esquivar aleatoriamente. Esto debe ser modificado para recibir un input.

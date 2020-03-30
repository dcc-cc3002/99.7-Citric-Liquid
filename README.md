<!-- 1.0.3-b1 -->
# 99.7-Citric-Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by 
[Fruitbat Factory](https://fruitbatfactory.com).

## Game's entities

### Characters

A *character* is the entity that represents the player.

During the game each player controls a different character, with a maximum of 4 players 
and a minimum of 2 for the game to be playable.

#### Basic fields

Each character has a **name**, **hit points**, **attack**, **defense** and **evasion**.
When a character's hit points reach 0, the character enters a KO status and can't perform
any action until it gets out of that state.

#### Dice

Most player actions are decided by rolling a dice with 6 faces (a D6 if you prefer), this
is implemented by using Java's integrated random number generators.
Characters have a ``setSeed(long)`` method to give a deterministic behaviour to the 
dice for testing purposes.
The ``roll()`` method is defined to simulate the rolling of a D6.

#### Panel activation

Characters also have the ability to activate panels (this event is trigged when a player 
steps on a panel, but that should be implemented by the person extending this code), 
each panel have a different effect on the player as noted on the [Panels](#panels) 
section.

#### Battle

When 2 characters meet a battle event may be triggered (again, this event should be 
implemented).
A battle consist on an attack from each player to the other.
The player who triggered the battle goes first.
The attack power 
When a player attacks the receiver has the option to defend or evade

### Panels
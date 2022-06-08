Author: Sebastián Monteiro
# Citriq-Liquid
This file contains the basics to understand this code.
First of all.The class design.
We have two main Interfaces:
1-.CharacterInterface.
The CharacterInferface has a child named Character, which manages the methods for the players and the NPC'S.
The Player has a concrete class and is a son of Character
The Entity Abstract class is an abstract class that handles the boss and wild units abstract sub-classes.
Every boss/wild unit has a concrete class.


2-. Panel Inteface handles the panel abstract class. Every type of panel is a son of Panel.

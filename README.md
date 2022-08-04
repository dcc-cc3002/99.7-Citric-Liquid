<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by 
[Fruitbat Factory](https://fruitbatfactory.com).

The implementation of this game consists principally on a state pattern, in which the mediator checks through 
the conditions in the phases, to then make the controller implement them in the game.

**To Run the game:**
For the moment, the game hasn't been implemented in its totality, so, the way to test the methods would be by running
the following tests: *ControllerTest*, *PanelTest*, *BossUnitTest*, *PlayerTest*, *WildUnitTest* and *PhaseTest*.

*MediatorTest* has been implemented, one of the tests wasn't passed, but there's a possibility of an error in the
test's definition.

The battle process hasn't been completely implemented 
(the first answer to the initial attack has been implemented.)
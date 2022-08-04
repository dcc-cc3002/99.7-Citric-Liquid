package cl.uchile.dcc.citricliquid.phases;

import cl.uchile.dcc.citricliquid.controller.GameController;

public class StartPhase extends Phase{

        public StartPhase() {
                this.canStart = true;
                this.canMove = false;
                this.canFight = false;
                this.stayHome = false;
                this.recover = false;
                this.waitHome = false;
                this.waitFight = false;
                this.waitPath = false;
                this.canEnd = false;
                this.battle = false;
        }


        @Override
        public String toString() {
                return "StartPhase";
        }

        @Override
        public void setController(GameController controller) {
                super.setController(controller);
        }

        @Override
        public void changePhase(Phase phase) {
                super.changePhase(phase);
        }
}

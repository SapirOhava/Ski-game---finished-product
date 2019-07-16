package game.CompetitorsState;
/**

 */
import game.entities.sportsman.WinterSportsman;
import java.util.Random;

public class Active implements IState {

    WinterSportsman winterSportsman;


    public Active( WinterSportsman winterSportsman) {

        this.winterSportsman = winterSportsman;

    }


    @Override
    public String StateString() {
        return "";
    }

    @Override
    public String getName() {
        return "active";
    }

    @Override
    public void action() {
        if(winterSportsman.getLocation().getX() >= winterSportsman.finish.getX()){

            this.winterSportsman.SetState(winterSportsman.getcompletedState());

        }
        if(winterSportsman.isGoingToBeInjured && ((Injured)winterSportsman.getinjuredState()).getInjuryLocation() <= winterSportsman.getLocation().getX()) {
            winterSportsman.SetState(winterSportsman.getinjuredState());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            winterSportsman.isGoingToBeInjured = false;
        }

    }
}


package game.CompetitorsState;
/**

 */
import game.entities.sportsman.WinterSportsman;

public class Disabled implements IState {

    WinterSportsman winterSportsman;

    public  Disabled(WinterSportsman winterSportsman){
        this.winterSportsman = winterSportsman;
    }

    @Override
    public String StateString() {
        return "Failed";
    }

    @Override
    public String getName() {
        return "disabled";
    }

    @Override
    public void action() {

    }
}

package game.CompetitorsState;
/**

 */
import game.entities.sportsman.WinterSportsman;

public class Completed implements IState {

    WinterSportsman winterSportsman;

    public  Completed(WinterSportsman winterSportsman){
        this.winterSportsman = winterSportsman;
    }
    @Override
    public String StateString() {
        return "i completed the race !";
    }

    @Override
    public String getName() {
        return "completed";
    }

    @Override
    public void action() {


    }
}

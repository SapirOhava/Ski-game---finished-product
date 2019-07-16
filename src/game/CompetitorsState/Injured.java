package game.CompetitorsState;
/**

 */
import game.competition.WinterCompetition;
import game.entities.sportsman.WinterSportsman;

import java.util.Random;

public class Injured implements IState {

    int injuryLocation;
    WinterSportsman winterSportsman;

    public Injured(WinterSportsman winterSportsman , int injuryLocation ){
        this.winterSportsman = winterSportsman;
        this.injuryLocation = injuryLocation;
    }

    @Override
    public String StateString() {
        return "injured at : "+injuryLocation;
    }

    @Override
    public String getName() {
        return "injured";
    }

    public void setInjuryLocation(int injuryLocation){
        this.injuryLocation = injuryLocation;
    }

    public int getInjuryLocation() {
        return injuryLocation;
    }

    @Override
    public void action() {

        Random rand = new Random();

        int n = rand.nextInt(50);


        if(n <= 25)
            winterSportsman.SetState(winterSportsman.getdisabledState());

        if(n>25)
            winterSportsman.SetState(winterSportsman.getActiveState());

        }


    }




package game.CompetitorsState;
/**

 */
import game.competition.Competition;
import game.competition.WinterCompetition;

public interface IState {


        public String StateString();
        public String getName();
        public void action();
    }

package game.competition;
/**

 */
import game.CompetitorsState.IState;
import game.ObserverAndObservable.OurObservable;
import game.arena.IArena;
import game.arena.WinterArena;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;


public class WinterCompetition extends Competition {
    private final Discipline discipline;
    private final League league;
    private final Gender gender;

    //changed from WinterArena arena to IArena arena
    public WinterCompetition(IArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) {
        super(arena, maxCompetitors);
        this.discipline = discipline;
        this.league = league;
        this.gender = gender;
    }

    protected boolean isValidCompetitor(Competitor competitor){
        if(competitor instanceof WinterSportsman){
            WinterSportsman winterSportsman = (WinterSportsman) competitor;
            return discipline.equals(winterSportsman.getDiscipline()) &&
                    league.isInLeague(winterSportsman.getAge()) &&
                    gender.equals(winterSportsman.getGender());
        }
        return false;
    }
    
    public Discipline getDiscipline() {
    	return discipline;
    }
    
    public League getLeague() {
    	return league;
    }
    
    public Gender getGender() {
    	return gender;
    }


}

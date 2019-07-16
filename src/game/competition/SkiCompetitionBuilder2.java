package game.competition;
/**

 */
import game.arena.ArenaFactory;
import game.arena.IArena;
import game.competition.builder.IBuilder;
import game.entities.sportsman.Skier;
import game.enums.*;

public class SkiCompetitionBuilder2 implements IBuilder {

    private  Discipline discipline;
    private  League league;
    private  Gender gender;
    private IArena arena;
    private int maxCompetitors;


    public SkiCompetitionBuilder2(int maxCompetitors) {
        this.maxCompetitors = maxCompetitors;
        discipline = Discipline.DOWNHILL;
        league = League.JUNIOR;
        gender = Gender.FEMALE;
        arena = new ArenaFactory().makeArena("winter",700, SnowSurface.POWDER, WeatherCondition.CLOUDY);
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setArena(IArena arena) {
        this.arena = arena;
    }

    public void setMaxCompetitors(int maxCompetitors) {
        this.maxCompetitors = maxCompetitors;
    }

    @Override
    public SkiCompetition build() {
        SkiCompetition result = new SkiCompetition(arena,maxCompetitors,discipline,league,gender);
        for(int i = 0 ; i < maxCompetitors; i++){

            result.addCompetitor(new Skier( i+1,"Anna", 15 , Gender.FEMALE ,1 ,5 ,Discipline.DOWNHILL ,"Pink" ));

        }
        return result;
    }
}

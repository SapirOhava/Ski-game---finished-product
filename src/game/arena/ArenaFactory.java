package game.arena;
/**

 */
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class ArenaFactory {

    public IArena makeArena(String newArena , double length , SnowSurface snowSurface , WeatherCondition weatherCondition){

        if(newArena.equals("summer"))
            return new SummerArena(length , snowSurface , weatherCondition);
        if(newArena.equals("winter"))
            return new WinterArena(length , snowSurface , weatherCondition);
        else
            return null;
    }
}

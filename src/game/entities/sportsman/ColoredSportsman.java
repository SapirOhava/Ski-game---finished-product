package game.entities.sportsman;
/**

 */
public class ColoredSportsman extends WSDecorator {


    public ColoredSportsman(IWinterSportsman winterSportsman , String color){
    	super(winterSportsman);
    	System.out.println("  "+ winterSportsman.getColor()+ " "+ color + "****");
        
        this.setColor(color);


    }



}

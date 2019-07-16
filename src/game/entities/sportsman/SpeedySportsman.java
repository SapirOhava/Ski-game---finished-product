package game.entities.sportsman;
/**

 */
public class SpeedySportsman extends WSDecorator {


    public SpeedySportsman(IWinterSportsman winterSportsman , double acceleration){

        super(winterSportsman);
    
       
        	
            this.winterSportsman.setAcceleration( winterSportsman.getAcceleration()+acceleration);
            

        


    }
}

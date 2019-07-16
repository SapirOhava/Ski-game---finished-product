package game.entities.sportsman;
/**

 */
public abstract class WSDecorator implements IWinterSportsman {


    IWinterSportsman winterSportsman;


    public WSDecorator(IWinterSportsman winterSportsman ){

        this.winterSportsman = winterSportsman;


    }


    @Override
    public void setColor(String color) {

        this.winterSportsman.setColor(color);
    }

    @Override
    public void setAcceleration(double acceleration) {

        this.winterSportsman.setAcceleration( acceleration);
        

    }
    

    public double getAcceleration(){return winterSportsman.getAcceleration();}
    public String getColor(){return winterSportsman.getColor();}



}

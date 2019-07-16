package game.entities;
/**

 */
import game.CompetitorsState.Disabled;
import game.CompetitorsState.IState;
import game.ObserverAndObservable.OurObserver;
import game.competition.Competitor;
import utilities.Point;

import java.util.ArrayList;

/**
 * Created by itzhak on 07-Mar-19.
 */
public class MobileEntity extends Entity implements IMobileEntity{
    public ArrayList<OurObserver> guiObserver;
    public ArrayList<OurObserver> CompetitionObserver;
    protected final double maxSpeed;
    private double acceleration;
    protected double speed;

    /**
     * Ctor for a mobile entity in the game
     * @param initialSpeed initial speed of the entity
     * @param acceleration entity acceleration
     * @param maxSpeed entity maximum speed
     */
    public MobileEntity( double initialSpeed,double acceleration, double maxSpeed){
        guiObserver = new ArrayList<>();
        CompetitionObserver = new ArrayList<>();
        this.setSpeed(initialSpeed);
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
    }

    //region IMobileEntity Implementation

    /**
     * @see IMobileEntity#move(double)
     */
    @Override
    public void move(double friction) {
        this.setSpeed(Math.min(this.maxSpeed,this.speed + this.getAcceleration()* (1-friction)));
        Point newLocation = this.getLocation().offset(this.speed,0);
        this.setLocation(newLocation);
    }
    //endregion

    //region Setters

    /**
     * Note: speed can theoretically be negative
     * @param speed the current speed of the entity
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    //endregion

    //region Getters

    /**
     * @return the acceleration of the entity
     */
    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getSpeed() {
    	return speed;
    }
    
    public double getMaxSpeed() {
    	return maxSpeed;
    }


    public void addGuiObserver(OurObserver o) {
        guiObserver.add(o);
    }

    public void addCompetitionObserver(OurObserver o){
        CompetitionObserver.add(o);
    }


    public void deleteGuiObserver(OurObserver o) {

        guiObserver.remove(o);

    }
    public void deleteCompetitionObserver(OurObserver o) {

        CompetitionObserver.remove(o);

    }

    public void notifyGuiObservers() {

        for(OurObserver o: guiObserver){
            o.update(this);
        }
    }

    public void notifyGuiObservers(IState state) {

        for(OurObserver o: guiObserver){
            o.update(this , state);
        }
    }


    public void notifyCompetitionObservers() {
        for(OurObserver o: CompetitionObserver){
            o.update(this);
        }
    }


}

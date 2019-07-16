package game.entities.sportsman;
/**

 */
import game.CompetitorsState.*;
import game.arena.IArena;
import game.competition.Competition;
import game.competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.Point;

import java.util.Random;


public abstract class WinterSportsman extends Sportsman implements Competitor, Cloneable , IWinterSportsman {



    private Discipline discipline;
    public Point finish;
    public IArena arena;
    public boolean isGoingToBeInjured = false;
    public IState active;
    public IState disabled;
    public IState injured;
    public IState completed;

    public IState currentState;


    public WinterSportsman(int id, String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline, String color ) {
        super(id, name, age, gender, acceleration, maxSpeed, color);
        this.discipline = discipline;
        active = new Active(this);
        disabled = new Disabled(this);
        injured = new Injured(this, 0);
        completed = new Completed(this);
        currentState = active;
    }


    public void destiny(int arenaLength) {
        Random rand = new Random();

        int n = rand.nextInt(3);

        switch (n) {
            case 0:
                return;
            case 1:
                SetState(this.getdisabledState());
            case 2:
                Random randLocation = new Random();
                int n1 = randLocation.nextInt(arenaLength);
                ((Injured)this.getinjuredState()).setInjuryLocation(n1);
                isGoingToBeInjured = true;
        }
    }

    public void action(){
        this.currentState.action();
    }

    public void SetState(IState CurrentState) {

        this.currentState = CurrentState;


    }

    public IState getActiveState(){return this.active;}
    public IState getdisabledState(){return this.disabled;}
    public IState getinjuredState(){return this.injured;}
    public IState getcompletedState(){return this.completed;}

    public IState getState(){ return this.currentState; }

    public WinterSportsman clone() {

        Object clone = null;
        try{
            clone = super.clone();
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return (WinterSportsman)clone;
    }


    @Override
    public void initRace() {
        this.setLocation(new Point(0, this.getLocation().getY()));
    }

    @Override
    public void initRace(Point p, Point f, IArena arena) {
        this.setLocation(p);
        this.finish = f;
        this.arena = arena;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + getName();
    }

    //region Getters & setters
    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    public double getAcceleration() {
        return super.getAcceleration() + League.calcAccelerationBonus(this.getAge());
    }
    //endregion

    public void setDiscipline(Discipline newDiscipline) {
        this.discipline = newDiscipline;
    }

    private boolean competitionInProgress() {
        boolean res = getLocation().getX() < finish.getX();
        Point p = getLocation();
        if (!res) setLocation(new Point(finish.getX(), p.getY()));
        return res;
    }

    public void move(double friction) {
        this.setSpeed(Math.min(this.maxSpeed,this.speed + this.getAcceleration()* (1-friction)));

        Point newLocation = this.getLocation().offset(this.maxSpeed, 0);
        this.setLocation(newLocation);

        this.currentState.action();
        notifyGuiObservers();

    }

    @Override
    public void run() {

        while ( competitionInProgress() && !(currentState instanceof Disabled)){

                move(arena.getFriction());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();

            }

        }

        if(!(currentState instanceof Disabled)){

            notifyCompetitionObservers();
            notifyGuiObservers(currentState);
        }
        else {
            notifyGuiObservers(currentState);
        }
    }
}

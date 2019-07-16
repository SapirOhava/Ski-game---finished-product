package game.competition;
/**

 */
import game.CompetitorsState.*;
import game.ObserverAndObservable.OurObservable;
import game.ObserverAndObservable.OurObserver;
import game.arena.IArena;
import game.entities.sportsman.WinterSportsman;
import utilities.Point;
import utilities.ValidationUtils;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CopyOnWriteArrayList;


public abstract class Competition implements OurObserver//implements Observer
{
    private IArena arena;
    private final int maxCompetitors;
    private final CopyOnWriteArrayList<Competitor> finishedCompetitors;
    private final CopyOnWriteArrayList<Competitor> activeCompetitors;
    private final CopyOnWriteArrayList<Competitor> DisabledCompetitors;

    private double y;

    public Competition(IArena arena , int maxCompetitors) {
        this.maxCompetitors = maxCompetitors;

        finishedCompetitors = new CopyOnWriteArrayList<>();
        activeCompetitors = new CopyOnWriteArrayList<>();
        DisabledCompetitors = new CopyOnWriteArrayList<>();
        this.arena = arena;
        y=0;
    }

    protected abstract boolean isValidCompetitor(Competitor competitor);

    public void addCompetitor(Competitor competitor){
        ValidationUtils.assertNotNull(competitor);
        if(maxCompetitors <= activeCompetitors.size()){
            throw new IllegalStateException("WinterArena is full max = "+ maxCompetitors);
        }
        if(isValidCompetitor(competitor)){
        	Point s = new Point(0, y);
        	Point f = new Point(arena.getLength(), y);
            competitor.initRace(s,f,arena);
            //destiny(competitor);
            ((WinterSportsman)competitor).destiny((int)this.arena.getLength());
            activeCompetitors.add(competitor);
            if(competitor instanceof OurObservable){
                ((OurObservable)competitor).addCompetitionObserver(this);
            }

            y += 75;
        }
        else{
            throw new IllegalArgumentException("Invalid competitor "+ competitor);
        }
    }




    @Deprecated
    public void playTurn(){
        ArrayList<Competitor> tmp = new ArrayList<>(activeCompetitors);
        for(Competitor competitor: tmp){
            if(!arena.isFinished(competitor)){
                competitor.move(arena.getFriction());
                if(arena.isFinished(competitor)){
                    finishedCompetitors.add(competitor);
                    activeCompetitors.remove(competitor);
                }
            }
        }
    }
    
	public void startCompetition() throws InterruptedException {
		ExecutorService e = Executors.newFixedThreadPool(activeCompetitors.size());
		for (Competitor c : activeCompetitors) {
			e.execute(c);
		}
		e.shutdown();

	}


	public synchronized void update(OurObservable o) {
		finishedCompetitors.add((Competitor) o);
		activeCompetitors.remove((Competitor) o);

	}

   @Override
    public void update(OurObservable o, IState state) {

    }

	
    public boolean hasActiveCompetitors(){
        return activeCompetitors.size() > 0;
    }

    public synchronized CopyOnWriteArrayList<Competitor> getFinishedCompetitors() {
        return finishedCompetitors;
    }
    
    public synchronized CopyOnWriteArrayList<Competitor> getActiveCompetitors() {
        return activeCompetitors;
    }

    public synchronized CopyOnWriteArrayList<Competitor> getDisabledCompetitors() {
        return DisabledCompetitors;
    }
    public IArena getArena() {
        return arena;
    }

    public int getMaxCompetitors() {
        return maxCompetitors;
    }
}

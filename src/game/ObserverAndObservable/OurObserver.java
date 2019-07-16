package game.ObserverAndObservable;
/**

 */
import game.CompetitorsState.IState;

public interface OurObserver {

    public void update(OurObservable o);
    public void update(OurObservable o , IState state);

}

package game.ObserverAndObservable;
/**

 */
public abstract class OurObservable {

    public abstract void addGuiObserver(OurObserver o);
    public abstract void deleteGuiObserver(OurObserver o);
    public abstract void notifyGuiObservers();
    public abstract void addCompetitionObserver(OurObserver o);
    public abstract void deleteCompetitionObserver(OurObserver o);
    public abstract void notifyCompetitionObservers();
}

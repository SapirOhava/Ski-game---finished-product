package gui;
/**
 *  adva amor - 311410922
 *  sapir ohava - 301726865
 */
import game.CompetitorsState.Disabled;
import game.CompetitorsState.IState;
import game.ObserverAndObservable.OurObservable;
import game.ObserverAndObservable.OurObserver;
import game.arena.ArenaFactory;
import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.*;
import game.entities.sportsman.*;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

import java.awt.Dimension;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ArenaPanel extends JPanel implements  OurObserver {

    private JLabel background;
    private int arenaLength = 700;
    private int arenaWidth = 1000;
    private int maxCompetitors = 10;
    private String surface = null;
    private String weather = null;
    private String competition = null;
    private String discipline = null;
    private String league = null;
    private String gender = null;
    private int competitorsNumber = 0;
    private ImageIcon competitorsImages[] = null;
    private static ArrayList<WinterSportsman> competitors;
    ///////// changed from private WinterArena arena to IArena;
    public IArena arena = null;
    public WinterCompetition winterCompetition = null;
    public CompetitionFrame competitionFrame = null;

    private boolean competitionStarted = false;
    private boolean competitionFinished = false;
    private InfoTable infoTable = null;
    private  CopyCompetitorFrame copyCompetitorFrame=null;
    private ChangCompetitorFrame changCompetitorFrame=null;



    public void initArena(){
        this.removeAll();
        setPreferredSize(new Dimension(arenaWidth,arenaLength+80));
        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("icons/"+weather+".jpg").getImage().getScaledInstance(arenaWidth,arenaLength+80, Image.SCALE_DEFAULT));
        JLabel picLabel1 = new JLabel(imageIcon1);
        picLabel1.setLocation(0, 0);
        picLabel1.setSize(arenaWidth,arenaLength+80);
        add(picLabel1);
        JLabel back = new JLabel();
        back.setSize(imageIcon1.getIconWidth() , imageIcon1.getIconWidth());

        for (int i=0; i<competitorsNumber; i++){
            JLabel picLabel2 = new JLabel(competitorsImages[i]);
            picLabel2.setLocation((int) competitors.get(i).getLocation().getY()+5, (int) competitors.get(i).getLocation().getX());
            picLabel2.setSize(70, 70);
            picLabel1.add(picLabel2);
            back.add(picLabel2);
        }

        picLabel1.add(back);
        background = back;


    }

    public void UpdateGuiObserver() {
        background.removeAll();
        for (int i=0; i<competitorsNumber; i++){
            JLabel picLabel2 = new JLabel(competitorsImages[i]);
            picLabel2.setLocation((int) competitors.get(i).getLocation().getY()+5, (int) competitors.get(i).getLocation().getX());
            picLabel2.setSize(70, 70);
            background.add(picLabel2);
        }
        background.repaint();
    }
    public  ArenaPanel()  {
        setLayout(null);
        initArena();
    }
    
    
    
    public void buildArena(String arenaType ,String surface, String weather){
        this.surface = surface;
        this.weather = weather;
     
        competitors = new ArrayList<>();
        competitorsImages = new ImageIcon[maxCompetitors];
        winterCompetition = null;
        competition = null;
        maxCompetitors=10;
        this.arenaWidth = 1000;
        
        SnowSurface snowSurf;
        WeatherCondition weatherCond;
        
        if (surface.equals("Powder"))
        	snowSurf = SnowSurface.POWDER;
        else if(surface.equals("Crud"))
        	snowSurf = SnowSurface.CRUD;
        else
        	snowSurf = SnowSurface.ICE;

        if (weather.equals("Sunny"))
        	weatherCond = WeatherCondition.SUNNY;
        else if (weather.equals("Cloudy"))
        	weatherCond = WeatherCondition.CLOUDY;
        else
        	weatherCond = WeatherCondition.STORMY;

        ArenaFactory arenaFactory = new ArenaFactory();
        arena = arenaFactory.makeArena(arenaType , arenaLength , snowSurf , weatherCond );
        competitionFrame.updateFrame();
    }
    
    
    
    public void createCompetition(String competition, String discipline, String league, String gender) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	this.competition = competition;
    	this.discipline = discipline;
    	this.league = league;
    	this.gender = gender;
    	
        competitionStarted = competitionFinished = false; 
        competitorsNumber = 0;
        
        int newWidth = (maxCompetitors)*75 + 5;
        
        if (newWidth>1000)
            this.arenaWidth = newWidth;
        else
            this.arenaWidth = 1000;
                    
        competitors = new ArrayList<>();
        competitorsImages = new ImageIcon[maxCompetitors];    
        
        Discipline disc;
        League leag;
        Gender gen;
        discipline =discipline.toLowerCase();
        if (discipline.equals("slalom"))
        	disc = Discipline.SLALOM;
        else if (discipline.equals("giant-slalom"))
        	disc = Discipline.GIANT_SLALOM;
        else if(discipline.equals("downhill"))
        	disc = Discipline.DOWNHILL;
        else 
        	disc = Discipline.FREESTYLE;
        
        league = league.toLowerCase();
        if (league.equals("junior"))
        	leag = League.JUNIOR;
        else if(league.equals("adult"))
        	leag = League.ADULT;
        else
        	leag = League.SENIOR;

        gender = gender.toLowerCase();
        if(gender.equals("male"))
        	gen = Gender.MALE;
        else
        	gen = Gender.FEMALE;
        
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c = cl.loadClass("game.competition."+competition+"Competition");
        Constructor con = c.getConstructor(IArena.class,int.class,Discipline.class,League.class,Gender.class);

        winterCompetition = (WinterCompetition) con.newInstance(arena,maxCompetitors,disc,leag,gen);
        competitionFrame.updateFrame();
    }


    
    public void addCompetitor(int id , String name, double age, double maxSpeed, double acceleration , String color) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	WinterSportsman ws = null;
        for(int i=0;i<competitors.size();i++){
    	if ( id == ((WinterSportsman)winterCompetition.getActiveCompetitors().get(i)).getId()){
            JOptionPane.showMessageDialog(this, "this id already exist , try again!");
            return;
        }
        }
    	
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c = cl.loadClass("game.entities.sportsman."+competition+"er");
        Constructor con = c.getConstructor(int.class ,String.class, double.class, Gender.class, double.class, double.class, Discipline.class , String.class );
        
        ws = (WinterSportsman) con.newInstance(id ,name,age,winterCompetition.getGender(),acceleration,maxSpeed,winterCompetition.getDiscipline() , color);

    	
        try {

        	winterCompetition.addCompetitor(ws);
            ws.addGuiObserver(this);
            competitors.add(ws);
            competitorsImages[competitorsNumber] = new ImageIcon(new ImageIcon("icons/"+ competition + color+ ".png" ).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
            competitorsNumber++;
            competitionFrame.updateFrame();

        }
        catch(IllegalArgumentException e) {
        	JOptionPane.showMessageDialog(this, "Competitor does not fit to competition! Choose another competitor."); 

        }

    }

    public void CopyCompetitorButton(WinterSportsman newCopiedCompetitor) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

        for(int i=0;i<competitors.size();i++){
            if ( newCopiedCompetitor.getId() == ((WinterSportsman)winterCompetition.getActiveCompetitors().get(i)).getId()){
                JOptionPane.showMessageDialog(this, "this id already exist , try again!");
                return;
            }
        }
        try {
            newCopiedCompetitor.addGuiObserver(this);
            winterCompetition.addCompetitor(newCopiedCompetitor);
            competitors.add(newCopiedCompetitor);
            competitorsImages[competitorsNumber] = new ImageIcon(new ImageIcon("icons/"+competition+ newCopiedCompetitor.getColor()+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
            competitorsNumber++;
            competitionFrame.updateFrame();
        }
        catch(IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Competitor does not fit to competition! Choose another competitor.");
            
        }

    }
    
    public void ChangCompetitorButton(int number,WinterSportsman newCopiedCompetitor) {
    	

        
    	competitorsImages[number] = new ImageIcon(new ImageIcon("icons/"+competition+ newCopiedCompetitor.getColor()+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
            
            competitionFrame.updateFrame();
        
        

    }

    
    public void startRace(){
        competitionStarted = true;
             
        try {                    
            //new Thread(this).start();
            winterCompetition.startCompetition();
        } catch (InterruptedException ex) {
        	ex.printStackTrace();
        }
    }

    public void CopyCompetitor(){
        if (copyCompetitorFrame != null)
            copyCompetitorFrame.dispose();

        copyCompetitorFrame = new  CopyCompetitorFrame(winterCompetition , competitorsNumber , this);

    }
    public void ChangeCompetitor(){
        if (changCompetitorFrame != null)
        	changCompetitorFrame.dispose();

        changCompetitorFrame = new ChangCompetitorFrame (winterCompetition , competitorsNumber , this);

    }
    
    
    public void showInfo(){
        if (infoTable != null)
            infoTable.dispose();
                    
        infoTable = new InfoTable(winterCompetition,competitorsNumber);

    }
    
    
    public void setArenaLength(int arenaLength){
        this.arenaLength = arenaLength;
    }
    
    public int getArenaLength(){
        return arenaLength;
    }
          
    
    public void setArenaWidth(int arenaWidth){
        this.arenaWidth = arenaWidth;
    }
    
    public int getArenaWidth(){
        return arenaWidth; 
    }
    
    
    public String getWeather(){
        return this.weather;
    }
    
    public String getSurface(){
        return this.surface;
    }   
    
    public String getDiscipline() {
    	return discipline;
    }
    
    public String getLeague() {
    	return league;
    }
    
    public String getGender() {
    	return gender;
    }
    
    public void setMaxCompetitors(int maxCompetitors){
        this.maxCompetitors = maxCompetitors;
    }
    
    public int getMaxCompetitors(){
        return this.maxCompetitors;
    }
    
    public boolean noArena(){
        return arena == null;
    }
    
    public boolean fullArena(){
        return competitorsNumber == maxCompetitors;
    }
    
    
    public boolean noCompetitors(){
        return competitorsNumber == 0;
    }
    

    public void setCompetitionFrame(CompetitionFrame competitionFrame){
        this.competitionFrame = competitionFrame;
    }
  
    
    public boolean isCompetitionStarted(){
        return this.competitionStarted;
    }
    
    public boolean isCompetitionFinished(){
        return this.competitionFinished;
    }
    public void setCompetitionStarted(boolean S){
        competitionStarted=S;
    }
    public void setCompetitionFinished(boolean S){
        competitionFinished=S;
    }
    public void setCompetitorsNumber(int number){
        competitorsNumber=number;
    }

     
    public String getCompetition() {
    	return competition;
    }
    


    public void setWinterCompetition(WinterCompetition winterCompetition) {
        this.winterCompetition = winterCompetition;
        this.arena = winterCompetition.getArena();
    }
    public WinterCompetition getWinterCompetition(){return winterCompetition;}



    @Override
    public synchronized void update(OurObservable o){
        UpdateGuiObserver();

    }

    @Override
    public synchronized void update(OurObservable o, IState state) {

        if (state instanceof Disabled) {
            this.winterCompetition.getDisabledCompetitors().add((Competitor) o);
            this.winterCompetition.getActiveCompetitors().remove(o);
        }
        if (infoTable != null && infoTable.isShowing()) {
            infoTable.dispose();
            infoTable = new InfoTable(winterCompetition, competitorsNumber);
            }

    }
}

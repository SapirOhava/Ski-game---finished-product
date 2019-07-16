package gui;
/**
 *  adva amor - 311410922
 *  sapir ohava - 301726865
 */
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import game.arena.WinterArena;
import game.competition.*;
import game.entities.sportsman.Skier;


public class ControlsPanel extends JPanel implements ActionListener{
    private final JTextField tfArenaLength;
    private final JTextField tfMaxCompetitors;
    private final JTextField tfCompetitorName;
    private final JTextField tfMaxSpeed;
    private final JTextField tfAcceleration;
    private final JTextField tfAge;
    private final JTextField tfid;
    private final JTextField tfDefaultCompetitionNumber;

    private final JComboBox<String> cmbArenaSurface;
    private final JComboBox<String> cmbArenaWeather;
    private final JComboBox<String> cmbCompetition;
    private final JComboBox<String> cmbDiscipline;
    private final JComboBox<String> cmbLeague;
    private final JComboBox<String> cmbGender;
    private final JComboBox<String> cmbArenaType;
    private final JComboBox<String> cmbColor;
    private ArenaPanel arenaPanel = null;


    /**
     * constructor
     * @param arenaPanel - the panel that is on the left side of this panel . at the main panel that holds both of them
     *                     this is the way  we can make changes on the arena panel , because now this class knows the
     *                     arena panel and hold it , as its field.
     *                     in this constructor we built all the buttons, text fields , and labels that are on the right side of the frame
     */

    public ControlsPanel(ArenaPanel arenaPanel) {
        this.arenaPanel = arenaPanel;
        setLayout(null);
        setPreferredSize(new Dimension(165,arenaPanel.getArenaLength()));


        JLabel l1 = new JLabel("<HTML><font color='blue'><U>BUILD ARENA</U></font></HTML>");
        add(l1);
        l1.setLocation(10,0);
        l1.setSize(145, 20);

        JLabel l2 = new JLabel("Arena length");
        l2.setLocation(10,20);
        l2.setSize(145, 15);
        add(l2);

        tfArenaLength = new JTextField(""+arenaPanel.getArenaLength());
        tfArenaLength.setLocation(10,35);
        tfArenaLength.setSize(145, 20);
        add(tfArenaLength);


        JLabel l4 = new JLabel("Snow surface");
        l4.setLocation(10,55);
        l4.setSize(170, 15);
        add(l4);

        cmbArenaSurface = new JComboBox<>();
        cmbArenaSurface.addItem("Powder");
        cmbArenaSurface.addItem("Crud");
        cmbArenaSurface.addItem("Ice");

        if (arenaPanel.getSurface() != null)
            //ASK WAY SHE DOING THIS
            cmbArenaSurface.setSelectedItem(arenaPanel.getSurface());
        else
            cmbArenaSurface.setSelectedItem("Powder");

        add(cmbArenaSurface);
        cmbArenaSurface.setLocation(10,70);
        cmbArenaSurface.setSize(145,20);

        JLabel l76 = new JLabel("Arena Type");
        l76.setLocation(10,90);
        l76.setSize(145, 15);
        add(l76);

        cmbArenaType = new JComboBox<>();
        cmbArenaType.addItem("summer");
        cmbArenaType.addItem("winter");
        add(cmbArenaType);
        cmbArenaType.setLocation(10,105);
        cmbArenaType.setSize(145,20);

        JLabel l5 = new JLabel("Weather condition");
        l5.setLocation(10,125);
        l5.setSize(170, 15);
        add(l5);

        cmbArenaWeather = new JComboBox<>();
        cmbArenaWeather.addItem("Sunny");
        cmbArenaWeather.addItem("Cloudy");
        cmbArenaWeather.addItem("Stormy");

        if (arenaPanel.getWeather() != null)
            cmbArenaWeather.setSelectedItem(arenaPanel.getWeather());
        else
            cmbArenaWeather.setSelectedItem("Sunny");

        add(cmbArenaWeather);
        cmbArenaWeather.setLocation(10,140);
        cmbArenaWeather.setSize(145,20);


        JButton buildArenaBut = new JButton("Build arena");
        buildArenaBut.setLocation(10,162);
        buildArenaBut.setSize(145, 20);
        ///ASK WHAT THIS
        buildArenaBut.addActionListener(this);
        add(buildArenaBut);

        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setLocation(0,187);
        sep.setSize(170, 10);
        add(sep);


        //-------------------------------------------------------------------

        JLabel l6 = new JLabel("<HTML><font color='blue'><U>CREATE COMPETITION</U></font></HTML>");
        add(l6);
        l6.setLocation(10,185);
        l6.setSize(145, 20);

        JLabel l7 = new JLabel("Choose competition");
        l7.setLocation(10,205);
        l7.setSize(170, 15);
        add(l7);

        cmbCompetition = new JComboBox<>();
        cmbCompetition.addItem("Ski");
        cmbCompetition.addItem("Snowboard");
        if (arenaPanel.getCompetition() != null)
            cmbCompetition.setSelectedItem(arenaPanel.getCompetition());
        else
            cmbCompetition.setSelectedItem("Ski");
        add(cmbCompetition);
        cmbCompetition.setLocation(10,220);
        cmbCompetition.setSize(145,20);


        JLabel l8 = new JLabel("Max competitors number");
        l8.setLocation(10,240);
        l8.setSize(170, 15);
        add(l8);

        tfMaxCompetitors = new JTextField(""+arenaPanel.getMaxCompetitors());
        tfMaxCompetitors.setLocation(10,255);
        tfMaxCompetitors.setSize(145, 20);
        add(tfMaxCompetitors);


        JLabel l9 = new JLabel("Discipline");
        l9.setLocation(10,275);
        l9.setSize(170, 15);
        add(l9);

        cmbDiscipline = new JComboBox<>();
        cmbDiscipline.addItem("Slalom");
        cmbDiscipline.addItem("Giant-Slalom");
        cmbDiscipline.addItem("Downhill");
        cmbDiscipline.addItem("Freestyle");
        if (arenaPanel.getCompetition() != null)
            cmbDiscipline.setSelectedItem(arenaPanel.getDiscipline());
        else
            cmbDiscipline.setSelectedItem("Slalom");
        add(cmbDiscipline);
        cmbDiscipline.setLocation(10,290);
        cmbDiscipline.setSize(145,20);


        JLabel l10 = new JLabel("League");
        l10.setLocation(10,310);
        l10.setSize(170, 15);
        add(l10);

        cmbLeague = new JComboBox<>();
        cmbLeague.addItem("Junior");
        cmbLeague.addItem("Adult");
        cmbLeague.addItem("Senior");
        if (arenaPanel.getCompetition() != null)
            cmbLeague.setSelectedItem(arenaPanel.getLeague());
        else
            cmbLeague.setSelectedItem("Junior");
        add(cmbLeague);
        cmbLeague.setLocation(10,325);
        cmbLeague.setSize(145,20);


        JLabel l11 = new JLabel("Gender");
        l11.setLocation(10,345);
        l11.setSize(170, 15);
        add(l11);

        cmbGender = new JComboBox<>();
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");
        if (arenaPanel.getCompetition() != null)
            cmbGender.setSelectedItem(arenaPanel.getGender());
        else
            cmbGender.setSelectedItem("Male");
        add(cmbGender);
        cmbGender.setLocation(10,360);
        cmbGender.setSize(145,20);

        JButton createCompetitionBut = new JButton("Create competition");
        createCompetitionBut.setLocation(10,382);
        createCompetitionBut.setSize(145, 20);
        createCompetitionBut.addActionListener(this);
        add(createCompetitionBut);

        JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
        sep2.setLocation(0,407);
        sep2.setSize(170, 10);
        add(sep2);

        //-------------------------------------------------------------------------------

        JLabel l12 = new JLabel("<HTML><font color='blue'><U>ADD COMPETITOR</U></font></HTML>");
        add(l12);
        l12.setLocation(10,408);
        l12.setSize(145, 20);

        JLabel lid = new JLabel("id");
        lid.setLocation(10,428);
        lid.setSize(150, 15);
        add(lid);

        tfid = new JTextField("");
        tfid.setLocation(20,428);
        tfid.setSize(145, 17);
        add(tfid);


        JLabel l13 = new JLabel("Name");
        l13.setLocation(10,447);
        l13.setSize(100, 15);
        add(l13);

        tfCompetitorName = new JTextField("");
        tfCompetitorName.setLocation(45,447);
        tfCompetitorName.setSize(120, 17);
        add(tfCompetitorName);

        JLabel l14 = new JLabel("Age");
        l14.setLocation(10,466);
        l14.setSize(150, 15);
        add(l14);

        tfAge = new JTextField("");
        tfAge.setLocation(45,466);
        tfAge.setSize(145, 17);
        add(tfAge);

        JLabel l15 = new JLabel("Max speed");
        l15.setLocation(10,484);
        l15.setSize(150, 15);
        add(l15);

        tfMaxSpeed = new JTextField("");
        tfMaxSpeed.setLocation(80,484);
        tfMaxSpeed.setSize(85, 17);
        add(tfMaxSpeed);

        JLabel l16 = new JLabel("Acceleration");
        l16.setLocation(10,503);
        l16.setSize(150, 15);
        add(l16);

        tfAcceleration = new JTextField("");
        tfAcceleration.setLocation(90,503);
        tfAcceleration.setSize(73, 20);
        add(tfAcceleration);

        JLabel lcolor = new JLabel("color");
        lcolor.setLocation(10,522);
        lcolor.setSize(150, 15);
        add(lcolor);

        cmbColor = new JComboBox<>();
        cmbColor.addItem("Red");
        cmbColor.addItem("Purpule");
        cmbColor.addItem("Yellow");
        cmbColor.addItem("Pink");
        cmbColor.addItem("Blue");
        cmbColor.addItem("Green");


        add(cmbColor);
        cmbColor.setLocation(10,535);
        cmbColor.setSize(145,20);

        JButton addCompetitorBut = new JButton("Add competitor");
        addCompetitorBut.setLocation(10,557);
        addCompetitorBut.setSize(145, 20);
        addCompetitorBut.addActionListener(this);
        add(addCompetitorBut);

        JButton CopyCompetitorBut = new JButton("Copy competitor");
        CopyCompetitorBut.setLocation(10,579);
        CopyCompetitorBut.setSize(145, 20);
        CopyCompetitorBut.addActionListener(this);
        add(CopyCompetitorBut);
        
        JButton changCompetitorBut = new JButton("Chang competitor");
        changCompetitorBut.setLocation(10,601);
        changCompetitorBut.setSize(145, 20);
        changCompetitorBut.addActionListener(this);
        add(changCompetitorBut);
        
        

        JSeparator sep4 = new JSeparator(SwingConstants.HORIZONTAL);
        sep4.setLocation(0,622);
        sep4.setSize(170, 10);
        add(sep4);

        JLabel l17 = new JLabel("<HTML><font color='blue'><U>Enter competitors number</U></font></HTML>");
        l17.setLocation(10,624);
        l17.setSize(150, 15);
        add(l17);

        tfDefaultCompetitionNumber = new JTextField("");
        tfDefaultCompetitionNumber.setLocation(7,639);
        tfDefaultCompetitionNumber.setSize(155, 17);
        add( tfDefaultCompetitionNumber);

        JButton BuildDefaultCompetition = new JButton("Default Competition");
        BuildDefaultCompetition.setLocation(7,656);
        BuildDefaultCompetition.setSize(155, 20);
        BuildDefaultCompetition.addActionListener(this);
        add(BuildDefaultCompetition);



        JSeparator sep3 = new JSeparator(SwingConstants.HORIZONTAL);
        sep3.setLocation(0,679);
        sep3.setSize(170, 10);
        add(sep3);

        //---------------------------------------------


        JButton startCompetitionBut = new JButton("Start competition");
        startCompetitionBut.setLocation(10,685);
        startCompetitionBut.setSize(145, 20);
        startCompetitionBut.addActionListener(this);
        add(startCompetitionBut);

        JButton printInfoBut = new JButton("Show info");
        printInfoBut.setLocation(10,705);
        printInfoBut.setSize(145, 20);
        printInfoBut.addActionListener(this);
        add(printInfoBut);
    }


    /**
     * this function sets and Initializing new entries for the Arena in the arena panel
     * @param length - the new arena length
     * @param arenaType - the new arena type
     * @param surface - the new arena surface
     * @param weather - the new arena weather
     */

    void buildArena(int length, String arenaType ,String surface, String weather ){
        arenaPanel.setArenaLength(length);
        arenaPanel.buildArena(arenaType,surface,weather);
    }


    /**
     * built competition in the arena panel
     * @param maxCompetitors - in the new competition
     * @param competition - the type of the new competition
     * @param discipline - the discipline of the new competition
     * @param league - the league of the new competition
     * @param gender - the gender of the new competition
     */
    void createCompetition(int maxCompetitors,String competition, String discipline, String league, String gender){
        arenaPanel.setMaxCompetitors(maxCompetitors);

        try {
            arenaPanel.createCompetition(competition,discipline,
                    league,gender);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }



    void addCompetitor(int id , String name, double age, double maxSpeed, double acceleration , String color){
        try {
            arenaPanel.addCompetitor(id , name, age, maxSpeed, acceleration , color);
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalArgumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "Build arena":
                if(arenaPanel.getWinterCompetition()!=null && arenaPanel.getWinterCompetition().getActiveCompetitors().size()==0){
                    arenaPanel.setCompetitionStarted(false);
                    arenaPanel.setCompetitionFinished(false);
                    arenaPanel.setCompetitorsNumber(0);
                }
                int length = 0;
                if (arenaPanel.isCompetitionStarted() && !arenaPanel.isCompetitionFinished()){
                    JOptionPane.showMessageDialog(arenaPanel, "Competition started! Please wait.");
                    return;
                }
                try{
                    length = Integer.parseInt(tfArenaLength.getText());
                    if (length<700 || length>900) throw new Exception();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                    return;
                }

                String typeArena = (String)cmbArenaType.getSelectedItem();

                buildArena(length,typeArena,(String)cmbArenaSurface.getSelectedItem(),(String)cmbArenaWeather.getSelectedItem());
                break;

            case "Create competition":

                if (arenaPanel.isCompetitionStarted() && !arenaPanel.isCompetitionFinished()){
                    JOptionPane.showMessageDialog(arenaPanel, "Competition started! Please wait.");
                    return;
                }

                if (arenaPanel.noArena()){
                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena first!");
                    return;
                }

                int maxCompetitors = 1;
                try{
                     maxCompetitors = Integer.parseInt(tfMaxCompetitors.getText());
                    if (maxCompetitors<=0 || maxCompetitors > 20) throw new Exception();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                    return;
                }
                createCompetition(maxCompetitors,(String)cmbCompetition.getSelectedItem(),(String)cmbDiscipline.getSelectedItem(),
                        (String)cmbLeague.getSelectedItem(),(String)cmbGender.getSelectedItem());

                break;

            case "Add competitor":
                if (arenaPanel.isCompetitionFinished()){
                    JOptionPane.showMessageDialog(arenaPanel, "Competition finished! Please create new competition.");
                    return;
                }
                if (arenaPanel.isCompetitionStarted()){
                    JOptionPane.showMessageDialog(arenaPanel, "Competition started! No competitors can be added.");
                    return;
                }
                if (arenaPanel.noArena()){
                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena first!");
                    return;
                }
                if (arenaPanel.getCompetition() == null) {
                    JOptionPane.showMessageDialog(arenaPanel, "Please create competition first!");
                    return;
                }
                if (arenaPanel.fullArena()){
                    JOptionPane.showMessageDialog(arenaPanel, "No more competitors can be added!");
                    return;
                }
                int id;
                String name;
                double age;
                double maxSpeed;
                double acceleration;
                String color;
                try {
                    id = Integer.parseInt(tfid.getText());
                    name = tfCompetitorName.getText();
                    age = Double.parseDouble(tfAge.getText());
                    maxSpeed = Double.parseDouble(tfMaxSpeed.getText());
                    acceleration = Double.parseDouble(tfAcceleration.getText());
                    color = (String)cmbColor.getSelectedItem();
                    if (name.isEmpty() || maxSpeed <=0 || acceleration <=0 || age<=0) throw new Exception();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                    return;
                }

                addCompetitor(id,name,age,maxSpeed,acceleration,color);
                break;

            case "Copy competitor":
                if (arenaPanel.isCompetitionStarted() && !arenaPanel.isCompetitionFinished()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Competition started! Please wait.");
                    return;
                }

                if (arenaPanel.noArena() || arenaPanel.getCompetition()==null ){
                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena, create competition and add competitors!");
                    return;
                }

                arenaPanel.CopyCompetitor();
                break;
            case "Chang competitor":
                if (arenaPanel.isCompetitionStarted() && !arenaPanel.isCompetitionFinished()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Competition started! Please wait.");
                    return;
                }

                if (arenaPanel.noArena() || arenaPanel.getCompetition()==null ){
                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena, create competition and add competitors!");
                    return;
                }

                arenaPanel.ChangeCompetitor();
                break;

            case "Default Competition":
                if(arenaPanel.getWinterCompetition()!=null && arenaPanel.getWinterCompetition().getActiveCompetitors().size()==0){
                    arenaPanel.setCompetitionStarted(false);
                    arenaPanel.setCompetitionFinished(false);
                    arenaPanel.setCompetitorsNumber(0);
                }
                try {
                    SkiCompetition comp = new SkiCompetitionBuilder2(Integer.parseInt(tfDefaultCompetitionNumber.getText())).build() ;
                    WinterArena arena = comp.getArena();

                    buildArena((int) (arena.getLength()),
                            "winter",
                            arena.getSurface().toString().toLowerCase(),
                            arena.getCondition().toString().toLowerCase()
                    );

                    createCompetition(comp.getMaxCompetitors(), "Ski", comp.getDiscipline().toString(),
                            comp.getLeague().toString(), comp.getGender().toString());


                    for (Competitor c : comp.getActiveCompetitors()) {
                        if (c instanceof Skier) {
                            Skier s = (Skier) c;
                            addCompetitor(s.getId(), s.getName(), s.getAge(), s.getMaxSpeed(), s.getAcceleration(), s.getColor());
                        }
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(arenaPanel , ex.getStackTrace());//"Invalid input values! Please try again."
                }
                break;




            case "Start competition":

                if (arenaPanel.noArena() || arenaPanel.getCompetition()==null || arenaPanel.noCompetitors()){
                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena, create competition and add competitors!");
                    return;
                }
                if (arenaPanel.isCompetitionFinished()){
                    JOptionPane.showMessageDialog(arenaPanel, "Competition finished! Please create a new competition and add competitors.");
                    return;
                }
                if (arenaPanel.isCompetitionStarted()){
                    JOptionPane.showMessageDialog(arenaPanel, "Competition already started!");
                    return;
                }

                arenaPanel.startRace();
                break;

            case "Show info":

                if (arenaPanel.noArena() || arenaPanel.getCompetition()==null || arenaPanel.noCompetitors()){
                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena, create competition and add competitors!");
                    return;
                }

                arenaPanel.showInfo();
                break;
        }
    }



}
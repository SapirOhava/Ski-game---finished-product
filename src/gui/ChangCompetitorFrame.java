
package gui;
/**
 *  adva amor - 311410922
 *  sapir ohava - 301726865
 */

import javax.swing.*;

import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.ColoredSportsman;
import game.entities.sportsman.IWinterSportsman;
import game.entities.sportsman.SpeedySportsman;
import game.entities.sportsman.Sportsman;
import game.entities.sportsman.WinterSportsman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;


public class ChangCompetitorFrame extends JFrame  {

    JLabel NewIdLabel ;
    JTextField NewACTF;
    Competition competition ;
    ArenaPanel arenaPanel;


    public ChangCompetitorFrame(Competition competition , int racersNumber , ArenaPanel arenaPanel ){
        super("ChangCompetitorFrame");
        this.competition = competition;
        this.arenaPanel=arenaPanel;

        String[] columnNames = {"Id","Name",
                "Speed",
                "Max speed",
                "Age","Gender","Acceleration",
                "Color"};

        String[][] data = new String[racersNumber][8];
        int i=0;

        for (Competitor c: competition.getActiveCompetitors()){
            data[i][0] = String.valueOf(((Sportsman) c).getId());
            data[i][1]= ((Sportsman) c).getName();
            data[i][2] = ""+c.getSpeed();
            data[i][3] = ""+c.getMaxSpeed();
            data[i][4] = ""+String.valueOf(((Sportsman) c).getAge());
            data[i][5] = ""+((Sportsman) c).getGender();
            data[i][6]=""+String.valueOf(((Sportsman) c).getAcceleration());
            data[i][7] = ""+((Sportsman) c).getColor();
            i++;


        }



        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tabPan = new JPanel();
        tabPan.setLayout(new BoxLayout(tabPan, BoxLayout.Y_AXIS));
        tabPan.add(scrollPane);

        NewIdLabel = new JLabel("enter new Accelation :");
        NewIdLabel.setSize(60, 15);
        tabPan.add(NewIdLabel);

        NewACTF = new JTextField();
        NewACTF.setSize(60, 15);
        tabPan.add(NewACTF);

        JLabel lcolor = new JLabel("color");
        lcolor.setLocation(10,603);
        lcolor.setSize(150, 15);
        tabPan.add(lcolor);

        final JComboBox<String> cmbColor;
        cmbColor = new JComboBox<>();
        cmbColor.addItem("Red");
        cmbColor.addItem("Purpule");
        cmbColor.addItem("Yellow");
        cmbColor.addItem("Pink");
        cmbColor.addItem("Blue");
        cmbColor.addItem("Green");


        tabPan.add(cmbColor);
        cmbColor.setLocation(10,618);
        cmbColor.setSize(145,20);


        JButton changcompetitorBut = new JButton("Chang comptitor");

        tabPan.add(changcompetitorBut);

        changcompetitorBut.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	try {
                	IWinterSportsman wm =  new SpeedySportsman( new ColoredSportsman( (WinterSportsman)competition.getActiveCompetitors().get(table.getSelectedRow()),(String)cmbColor.getSelectedItem()) ,Double.parseDouble(NewACTF.getText()));
                    arenaPanel.ChangCompetitorButton(table.getSelectedRow(),(WinterSportsman)competition.getActiveCompetitors().get(table.getSelectedRow()));
                   
            	}
            	 catch (Exception ex) {
                     JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                 }

            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(tabPan);
        pack();
        setVisible(true);
    }


}

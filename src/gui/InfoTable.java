
package gui;
/**
 *  adva amor - 311410922
 *  sapir ohava - 301726865
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.Sportsman;
import game.entities.sportsman.WinterSportsman;


public class InfoTable extends JFrame{
    
    public  InfoTable(Competition competition, int racersNumber){
        super("Competitors information");
        String[] columnNames = {"Name",
                             "Speed",
                             "Max speed",
                             "Location",
                             "Finished" ,
                              "state" ,
                             "State String" };
        
        String[][] data = new String[racersNumber][7];
        int i = 0;
     
        for (Competitor c: competition.getFinishedCompetitors()){
            data[i][0] = ((Sportsman) c).getName();
            data[i][1] = ""+c.getSpeed();
            data[i][2] = ""+c.getMaxSpeed();
            data[i][3] = ""+c.getLocation().getX();
            data[i][4] = "Yes";
            data[i][5] = ((WinterSportsman) c).getState().getName();
            data[i][6] = ((WinterSportsman) c).getState().StateString();
            i++;
        }

        for (Competitor c: competition.getActiveCompetitors()){
            data[i][0] = ((Sportsman) c).getName();
            data[i][1] = ""+c.getSpeed();
            data[i][2] = ""+c.getMaxSpeed();
            data[i][3] = ""+c.getLocation().getX();
            data[i][4] = "No";
            data[i][5] = ((WinterSportsman) c).getState().getName();
            data[i][6] = ((WinterSportsman) c).getState().StateString();
            i++;
        }

           int t=0;
        for ( int m= i+competition.getDisabledCompetitors().size()-1 ; m>=i ; m--){
            Competitor c = competition.getDisabledCompetitors().get(t);
            data[m][0] = ((Sportsman) c).getName();
            data[m][1] = ""+c.getSpeed();
            data[m][2] = ""+c.getMaxSpeed();
            data[m][3] = ""+c.getLocation().getX();
            data[m][4] = "No";
            data[m][5] = ((WinterSportsman) c).getState().getName();
            data[m][6] = ((WinterSportsman) c).getState().StateString();
            t++;

        }


        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tabPan = new JPanel();
        tabPan.add(scrollPane);                   

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(tabPan);
        pack();
        setVisible(true); 
    }
    
}

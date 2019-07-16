package gui;
/**
 *  adva amor - 311410922
 *  sapir ohava - 301726865
 */

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public final class CompetitionFrame extends JFrame {

    /**
     * arena panel field -  object of the calss that maintains the left panel
     */
    private ArenaPanel arenaPanel = null;
    //private static final long serialVersionUID = 1L;

    /**
     * constructor
     */
    public CompetitionFrame() {
        super("Competition");     
        updateFrame();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    /**
     *
     * @return the current main panel of the competition frame that conatain the
     * arena panel and the control panel and between them a separator
     */

    public JPanel getMyContentPane(){ 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); 
        if (arenaPanel==null) {
            arenaPanel = new ArenaPanel();
            arenaPanel.setCompetitionFrame(this); 
        }
        else arenaPanel.initArena();
        mainPanel.add(arenaPanel,BorderLayout.WEST); 
        mainPanel.add(new JSeparator(SwingConstants.VERTICAL),BorderLayout.CENTER);
        mainPanel.add(new ControlsPanel(arenaPanel),BorderLayout.EAST);
        return mainPanel;
    }


    /**
     * update the competitions frame , every time that we change or update the arena panel
     * it will construct from the start the main panel ( that holds the arena panel and the control panel )
     * and print it to the screen
     */

    public void updateFrame(){
        this.setContentPane(getMyContentPane());
        this.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true); 
    }

    /**
     * the main function - built competition frame object
     * @param args
     */
    public static void main(String[] args) {
    	CompetitionFrame competitionFrame = new CompetitionFrame();
	}

   
}

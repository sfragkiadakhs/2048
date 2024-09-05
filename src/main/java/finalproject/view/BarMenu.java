package finalproject.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * A JMenuBar for the game
 */
public class BarMenu extends JMenuBar{
    ViewListener listener;
    JMenu menu,history,help;
    JMenuItem restart,showHistory,hideHistory,about;
    public BarMenu(ViewListener listener){
        this.listener=listener;

        menu=new JMenu("options");
        restart = new JMenuItem("new Game");
        //history=new JMenu("history");
        showHistory = new JMenuItem("show");
        //hideHistory= new JMenuItem("hide");
        help = new JMenu("help");
        about= new JMenuItem("about");
        
        restart.setActionCommand("restart");
        //showHistory.setActionCommand("showHistory");
        //hideHistory.setActionCommand("hideHistory");
        about.setActionCommand("about");
        restart.addActionListener(listener);
        showHistory.addActionListener(listener);
        about.addActionListener(listener);

        menu.add(restart);
        //history.add(showHistory);
        //history.add(hideHistory);
        help.add(about);
        add(menu);
        //add(history);
        add(help);
        
    }
    /**
     * @return the JMenuItem called restart
     */
    public JMenuItem getRestart(){
        return restart;
    }
   
}

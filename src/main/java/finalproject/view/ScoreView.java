package finalproject.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A pannel with the name of the player and details about the scores 
 */
public class ScoreView extends JPanel{
    private static final Color backGroundColor = new Color(250, 248, 239);
    private JLabel score,bestScore;
    private JLabel title ;
    private JPanel scoresPanel  ,scorePanel,  bestPanel;
    public ScoreView(String playerName,ViewListener listener){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(backGroundColor);
        setAlignmentX(CENTER_ALIGNMENT);
        initComponents(playerName);
    }

    private void initComponents(String playerName){
        title = new JLabel("  Hello "+playerName+"!!");
        add(title);

        scoresPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
        scoresPanel.setBackground(backGroundColor);

        scorePanel = new someScorePanel("SCORE");                         // custom made panel to hold the score
        bestPanel  = new someScorePanel("BEST");                          // custom made panel to hold the bestScore
        scoresPanel.add( scorePanel );  
        scoresPanel.add( bestPanel  ); 
        add(scoresPanel);
    }

    public JLabel getScore(){
        return score;
    }

    public JLabel getBestScore(){
        return bestScore;
    }

    

    public synchronized void setScore(int score){
            this.score.setText(String.valueOf(score)) ;   
        
    }
    public synchronized void setBestScore(int bestScore){
            this.bestScore.setText(String.valueOf(bestScore));
        
    }

    private class someScorePanel extends JPanel {
		private static final long serialVersionUID = -8332252455499210670L;

		someScorePanel(String name) {
			super(new FlowLayout(FlowLayout.CENTER, 20, 20));
			setBackground(new Color(187, 173, 160));

			JLabel scoreHeader = new JLabel(name);
			scoreHeader.setAlignmentX(CENTER_ALIGNMENT);
			scoreHeader.setForeground(new Color(238, 228, 218));

			JLabel scoreValue = new JLabel("4096");
			scoreValue.setAlignmentX(CENTER_ALIGNMENT);
			scoreValue.setForeground(Color.WHITE);

			if (name.equals("SCORE")) score = scoreValue;
			else if (name.equals("BEST")) bestScore = scoreValue;

			Box box = new Box(BoxLayout.Y_AXIS);
			box.add(Box.createVerticalStrut(8));
			box.add(scoreHeader);
			box.add(scoreValue);
			box.add(Box.createVerticalStrut(8));
			add(box);
			setPreferredSize(new Dimension(100, 115));
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			//CustomPainter.paintComponentRounded(this, g, 3);
		}
    }
}
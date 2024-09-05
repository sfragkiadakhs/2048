package finalproject.view;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import finalproject.controller.Controller;
import finalproject.controller.Direction;
public class ViewListener  extends KeyAdapter implements MouseListener, ActionListener, ItemListener, WindowListener{
    
    private Controller controller;
    private Point drag_start;
    private static final List<Integer> UP = Arrays.asList(KeyEvent.VK_UP, KeyEvent.VK_W);
    private static final List<Integer> RIGHT = Arrays.asList(KeyEvent.VK_RIGHT, KeyEvent.VK_D);
    private static final List<Integer> DOWN = Arrays.asList(KeyEvent.VK_DOWN, KeyEvent.VK_S);
    private static final List<Integer> LEFT = Arrays.asList(KeyEvent.VK_LEFT, KeyEvent.VK_A);
    private int nextBoardWidth;
    private int nextBoardHeight;
    /**
     * Constructs this ViewListener and directs it to send all pertinent user input to the controller provided.
     * @param controller The controller that this ViewListener will send all pertinent user actions to.
     * @throws NullPointerException If controller == null.
     */
    public ViewListener(Controller controller) throws NullPointerException {
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!e.isAltDown() && !e.isControlDown() && !e.isShiftDown()) {
            if (UP.contains(e.getKeyCode())) controller.makeMove(Direction.UP);
            else if (RIGHT.contains(e.getKeyCode())) controller.makeMove(Direction.RIGHT);
            else if (DOWN.contains(e.getKeyCode())) controller.makeMove(Direction.DOWN);
            else if (LEFT.contains(e.getKeyCode())) controller.makeMove(Direction.LEFT);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) drag_start = e.getLocationOnScreen();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Point drag_end = e.getLocationOnScreen();
            if (drag_end.distance(drag_start) > 20) {
            int hor = drag_end.x - drag_start.x;
            int vert = drag_end.y - drag_start.y;
            if (Math.abs(hor) >= Math.abs(vert)) { // horizontal movement larger or equal to vertical movement
                controller.makeMove(hor > 0 ? Direction.RIGHT : Direction.LEFT);
            } else { // horizontal movement less than vertical movement
                controller.makeMove(vert > 0 ? Direction.DOWN : Direction.UP);
            }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd != null && cmd.equalsIgnoreCase("restart")){
                nextBoardWidth = controller.getModel().getWidth();
                nextBoardHeight= controller.getModel().getHeight();
                controller.restartGame(nextBoardWidth, nextBoardHeight);
        }else if (cmd != null && cmd.equalsIgnoreCase("about")){
            JOptionPane.showMessageDialog(null,"<html>HOW TO PLAY: Use your arrow keys or WASD keys to move the tiles. When two tiles with the same number touch, they merge into one!  <p> Project xeimerinou examinou 2048,Fragkiadakhs Stylianos tp4691</p></html>","About",JOptionPane.OK_OPTION);
        }
    
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {	controller.endGame();
    }
    @Override
    public void windowClosed(WindowEvent e) {	controller.endGame();
}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
    @Override
    public void itemStateChanged(ItemEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}   
}
package finalproject.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JLabel;

/**
 * Controls the look of the tiles.
 */
public class ViewTile extends JLabel{
    private static Dimension prefSize = new Dimension(117, 117);
    private static Dimension actSize = new Dimension(prefSize.width, prefSize.height);

    public ViewTile(int val,Point p) throws IllegalArgumentException {
        super(val==0 ? "" : String.valueOf(val),CENTER);
        if(p.x <0|| p.y<0) throw new IllegalArgumentException("Invalid tile location!");
        setLocation(p);
        setOpaque(true);  //paint every pixel within its bounds
        setSize(actSize);
        setStyle(val);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }
    /**
     * Returns the preferred size of ViewTile.
     * @return The preferred size of ViewTile.
     */
    public static Dimension getPrefSize() {
        return new Dimension(prefSize.width, prefSize.height);
    }
    
    /**
     * Sets the actual width of ViewTile.
     * @param width The value the width of ViewTile will be set to.
     */
    public static void setActualWidth(int width) {
        actSize.width = width;
    }
    
    /**
     * Sets the actual height of ViewTile.
     * @param height The value the height of ViewTile will be set to.
     */
    public static void setActualHeight(int height) {
        actSize.height = height;
    }
    /**
     * Returns the actual height of the ViewTile.
     * @return The actual height of the ViewTile.
     */
    public static int getActualHeight() {
        return actSize.height;
    }
    /**
     * Returns the actual width of the ViewTile.
     * @return The actual width of the ViewTile.
     */
    public static int getActualWidth() {
    return actSize.width;
    }
    /**
     * Moves this tile by the given amounts.
     * @param dx The amount to move this tile in the x direction (horizontal shift).
     * @param dy The amount to move this tile in the y direction (vertical shift).
     */
    public void moveTileBy(int dx, int dy) {
        setLocation(getLocation().x + dx, getLocation().y + dy);
    }

    /**
     * Changes this ViewTiles value to the one provided.
     * @param val The value that this ViewTiles value will be updated to.
     */
    public void setTileVal(int val) {
        setText(val == 0 ? "" : String.valueOf(val));
        setStyle(val);
    }

    private void setStyle(int val) {

        switch (val) {
            case 0:
                setBackground(new Color(238, 228, 218, 89));
                setForeground(new Color(119, 110, 101));
                break;
            case 2:
                setBackground(new Color(238, 228, 218));
                setForeground(new Color(119, 110, 101));
                break;
            case 4:
                setBackground(new Color(237, 224, 200));
                setForeground(new Color(119, 110, 101));
                break;
            case 8:
                setBackground(new Color(242, 177, 121));
                setForeground(new Color(249, 246, 242));
                break;
            case 16:
                setBackground(new Color(245, 149, 99));
                setForeground(new Color(249, 246, 242));
                break;
            case 32:
                setBackground(new Color(246, 124, 95));
                setForeground(new Color(249, 246, 242));
                break;
            case 64:
                setBackground(new Color(246, 94, 59));
                setForeground(new Color(249, 246, 242));
                break;
            case 128:
                setBackground(new Color(237, 207, 114));
                setForeground(new Color(249, 246, 242));
                break;
            case 256:
                setBackground(new Color(237, 204, 97));
                setForeground(new Color(249, 246, 242));
                break;
            case 512:
                setBackground(new Color(237, 200, 80));
                setForeground(new Color(249, 246, 242));
                break;
            case 1024:
                setBackground(new Color(237, 197, 63));
                setForeground(new Color(249, 246, 242));
                break;
            case 2048:
                setBackground(new Color(237, 194, 46));
                setForeground(new Color(249, 246, 242));
                break;
            default:
                setBackground(new Color(60, 58, 50));
                setForeground(new Color(249, 246, 242));
                break;
        }
    }
    
    /**
     * Repaints this component.
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	//CustomPainter.paintComponentRounded(this, g, 3);
    }
}

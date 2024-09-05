package finalproject.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * a JDialog to enter the players name 
 */
public class NameDialog extends JDialog implements ActionListener {
    private String[] data;
    private JTextField descBox;
    private JButton btnOk;

    public NameDialog(JFrame parent) {
        super(parent,"Enter name",true);
        Point loc = parent.getLocation();
        setLocation(loc.x+80,loc.y+80);
        data = new String[1];
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2,2,2);

        JLabel descLabel = new JLabel("Name:");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(descLabel,gbc);

        descBox = new JTextField(30);
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(descBox,gbc);

        JLabel spacer = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(spacer,gbc);
        btnOk = new JButton("Ok");
        btnOk.addActionListener(this);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnOk,gbc);
        getContentPane().add(panel);
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == btnOk){
            data[0]= descBox.getText();
        }
        dispose();
    }
    
    public String[] run(){
        this.setVisible(true);
        return data;
    }
    
}

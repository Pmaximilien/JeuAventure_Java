import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
//import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003)
 */
public class UserInterface implements ActionListener
{
    private GameEngine engine;
    private JFrame myFrame;
    private JTextField entryField;
    private JTextArea log;
    private JLabel image;
    private JButton exit,eat,help,status,back;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface(GameEngine gameEngine)
    {
        engine = gameEngine;
        createGUI();
    }

    /**
     * Print out some text into the text area.
     */
    public void print(String text)
    {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(String text)
    {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Show an image file in the interface.
     */
    public void showImage(String imageName)
    {
        URL imageURL = this.getClass().getClassLoader().getResource(imageName);
        if(imageURL == null)
            System.out.println("image not found");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            image.setIcon(icon);
            myFrame.pack();
        }
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        myFrame    = new JFrame("Zork");
        entryField = new JTextField(34);
        exit       = new JButton("quit");
        eat        = new JButton("eat");
        help       = new JButton("help");
        status     = new JButton("status");
        back       = new JButton("back");
        log        = new JTextArea();
        log.setEditable(false);
        
       
        
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel panel = new JPanel();
        JPanel panelCommande = new JPanel();
        
        image = new JLabel();

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entryField, BorderLayout.SOUTH);
        panelCommande.add(exit);
        panelCommande.add(eat);
        panelCommande.add(help);
        panelCommande.add(status);
        panelCommande.add(back);
       
        exit.setText("Exit");
        exit.setSize(100,50);
        exit.setActionCommand("quit");
        exit.addActionListener(this);
 
        eat.setSize(100,50);
        eat.setActionCommand("eat");
        eat.addActionListener(this);
       
        help.setSize(100,50);
        help.setActionCommand("help");
        help.addActionListener(this);
       
        status.setSize(100,50);
        status.setActionCommand("status");
        status.addActionListener(this);
        
        back.setSize(100,50);
        back.setActionCommand("back");
        back.addActionListener(this);
        
        myFrame.getContentPane().add(panel, BorderLayout.CENTER);
        myFrame.getContentPane().add(panelCommande, BorderLayout.SOUTH);
        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        

        entryField.addActionListener(this);

        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
    }

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed(ActionEvent e) 
    {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
    	if(e.getSource() == entryField){
    		processCommand();
    	}
    	else{
    		String input = e.getActionCommand();
    		engine.interpretCommand(input);
    	}
    }

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        @SuppressWarnings("unused")
		boolean finished = false;
        String input = entryField.getText();
        entryField.setText("");

        engine.interpretCommand(input);
    }
}

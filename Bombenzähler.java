import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class Bombenzähler {
    public JFrame Fenster;
    protected JButton Knopf;
    int currentBombs = 0;
    private static final Bombenzähler refrence = new Bombenzähler();


    private Bombenzähler()
    {
        Fenster= new JFrame();
        Knopf = new JButton(String.valueOf(currentBombs));
        Fenster.setDefaultCloseOperation(3);
        Fenster.setLayout(new GridLayout(1,1));
        Fenster.add(Knopf);
        Fenster.setVisible(true);
        Fenster.setLocation(1400,400);
        Fenster.pack();
    System.out.println("1");

    }
    public void setCurrentBombs(int cb){
        currentBombs=cb;
        System.out.println(currentBombs);
    }

    public void BombenzahlVerringern()
    {
        Knopf = new JButton(String.valueOf(currentBombs));
        currentBombs --;
        Knopf.repaint();

        System.out.println("2");
    }

    public static Bombenzähler getRefrence(){
        return refrence;
    }
}

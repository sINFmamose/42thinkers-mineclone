

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Algorithmen extends JFrame implements MouseListener,KeyListener  {     //Fenster und Screen // mit Mouselistener
    private  static int width =500; //breite 500  statisch festgelegt
    private static int height = 500; // höhe mit 500 statisch festgelegt
    private Screen screen;  // Intitialisierung des screens
    private Welt welt;  // Intialisierung einer neuen Welt
    private Font font;
    private int insetLeft; // wegen den rändern klickt man ansonsten nicht richtig
    private int insetTop;
    private int counter;
    private Uhr uhr2 = new Uhr();


    public Algorithmen (){
        super("Minesweeper"); //(Titelzeile)
        welt = new Welt() ;// neue Welt erzeugen
        setResizable(false); //Nicht veränderbar in der Grösse
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Beenden beim X im rechten oberen eck
        addMouseListener(this); //  damit der Mouselistener überhaupt auf das Feld hört // Implementierung
        addKeyListener(this);
        screen = new Screen() ;  // Aufruf neuer Screen
        add(screen);  // füge Screen in das Frame hinzu
        pack(); //Die Methode  pack() bewirkt, dass das Frame die minimale Grösse bei optimaler Anordnung der in ihm enthaltenen Komponenten bekommt.
        insetLeft = getInsets().left;
        insetTop= getInsets().top;
        //System.out.println(getInsets().left); //Insets (rand) des feldes
        setSize(width+insetLeft+getInsets().right,height+insetTop+getInsets().bottom); //grösse des Fensters + Die Ränder links und rechts bzw oben und unten
        setLocationRelativeTo(null); // in welcher relation die Location steht
        setVisible(true); // Fenster soll sichtbar sein
        font=new Font("SansSerif",0,50);    // Mit welcher Schriftart gezeichnet wird ( nicht fett und grösse soll 12 sein)

        uhr2.setVisible(false);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        counter ++;
        int b;
        b = counter;

        if ((b == 1)&&(welt.isBombe((e.getX()-insetLeft),(e.getY()-insetTop)))){
           welt.nichtoeffnen((e.getX()-insetLeft),(e.getY()-insetTop));
            welt.reset();
            screen.repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) { // wenn die maus losgelassen haben nach klick



        if(e.getButton()==1) welt.clickedLeft(e.getX()-insetLeft,e.getY()-insetTop);// wo geklickt wurde diese koords werden übergeben
        if(e.getButton() == 3) welt.clickedRight(e.getX() - insetLeft, e.getY() - insetTop);

        screen.repaint();  // der Screen soll neu gemalt werden


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (welt.isDead() && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
            System.out.println("43");
            welt.restart();
            uhr2.restart();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            welt.reset();
            screen.repaint();
            System.out.println("42");
            uhr2.restart();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {


    }



    public class Screen extends JPanel{ // innere Klasse erbt von Jpanel
        @Override
        public void paintComponent (Graphics g){ //damit wird das panel repainet

            g.setFont(font);
            welt.draw (g); // neue welt wird gezeichnet
            // g.setColor(Color.red); // Farbe auf rot setzen
            // g.fillRect(0,0,50,50); // rechteck reinmalen
        }
    }


    public static int getScreenWidth() { // ansonsten müsste man die Methode overriden  So kann mann die breite returnen ohne override
        return width;
    }


    public static int getScreenHeight() {
        return height;
    }

}

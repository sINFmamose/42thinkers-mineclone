import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Level {

    private JTable table1;
    //aka Tile
    //private BufferedImage image; // Initialisierung des Bufferedimages
    private BufferedImage normal;
    private BufferedImage openedImage; // zum öffnen der Kacheln
    private BufferedImage flagImage;
    private BufferedImage bombImage;
    private BufferedImage einsImage;
    private BufferedImage zweiImage;
    private BufferedImage dreiImage;
    private BufferedImage vierImage;
    private BufferedImage fuenfImage;
    private BufferedImage sechsImage;
    private BufferedImage siebenImage;
    private BufferedImage achtImage;
    private int i;
    private int x; // wohin die Kachel gemalt werden soll
    private int y;
    private boolean bomb;  // booleanwert ob bombe oder nicht
    private boolean opened;
    private boolean flag;
    private int amountOfNearBombs;




    private static int width = Algorithmen.getScreenWidth()/Welt.getWidth(); // Gesammtgrösse / einzelne Kachelgrösse =>
    // 10 Tiles brauchts um ins Fenster zu passen
    private static int height = Algorithmen.getScreenHeight()/Welt.getHeight();

    public Level(int x , int y,BufferedImage normal,BufferedImage bomb,BufferedImage openedImage,
                 BufferedImage flag,BufferedImage eins,BufferedImage zwei,BufferedImage drei,BufferedImage vier,
                 BufferedImage fuenf,BufferedImage sechs,BufferedImage sieben,BufferedImage acht){ //Positionen werden übergeben
        this.x = x;
        this.y = y;
        this.normal = normal;
        this.bombImage = bomb;
        this.openedImage=openedImage;
        this.flagImage =flag;
        this.einsImage=eins;
        this.zweiImage = zwei;
        this.dreiImage=drei;
        this.vierImage =vier;
        this.fuenfImage=fuenf;
        this.sechsImage=sechs;
        this.siebenImage =sieben;
        this.achtImage=acht;


        //opened = true;
    }

    public void setNormal(BufferedImage normal) {       // setter für normalimage
        this.normal = normal;
    }

    public void setOpenedImage(BufferedImage openedImage) {     // setter für das openedimage
        this.openedImage = openedImage;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
  /* public void setImage(BufferedImage image) {     //setzen des Images
        this.image = image;
    }*/

    public void setBomb(boolean bomb) {         //settermethode für Bomb
        this.bomb = bomb;
    }


    public boolean isBomb() {               // wenn bombe sag auch dass bombe ist
        return bomb;
    }
    public void placeFlag(){
        if (flag)
        {
            flag = false;
        }
        else {
            if (!opened) flag = true;
        }
    }
    public void reset(){
        flag = false;
        bomb = false;
        opened = false;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setAmountOfNearBombs(int amountOfNearBombs) {
        this.amountOfNearBombs = amountOfNearBombs;
    }
    public int getAmountOfNearBombs(){
        return amountOfNearBombs;
    }
    public boolean canOpen()                    //boolean ob öffnen kann aber nur, wenn nicht schon offen/ keine Bombe und Zähler >=0
    {
        return !opened&&!bomb&&amountOfNearBombs >= 0;
    }


    public void draw(Graphics g) {  // um etwas in der Kachel zu malen

        if (!opened) {


            if (!flag)
                g.drawImage(normal, x * width, y * height, null);// wenn es nicht auf ist soll das normale gezeichnet werden oder keine Flagge da ist
            else g.drawImage(flagImage, x * width, y * height, null);
        }
        else
        {
            if (bomb)  g.drawImage(bombImage,x*width,y*height,null);      // bei ner bombe wird die gemalt
            else{
                g.drawImage(openedImage,x*width,y*height,null);
                if (amountOfNearBombs>0)

                {
                    i=amountOfNearBombs;
                    switch (i){
                        case 1: g.drawImage(einsImage, x * width, y * height, null);
                            break;
                        case 2: g.drawImage(zweiImage, x * width, y * height, null);
                            break;
                        case 3: g.drawImage(dreiImage, x * width, y * height, null);
                            break;
                        case 4: g.drawImage(vierImage, x * width, y * height, null);
                            break;
                        case 5: g.drawImage(fuenfImage, x * width, y * height, null);
                            break;
                        case 6: g.drawImage(sechsImage, x * width, y * height, null);
                            break;
                        case 7: g.drawImage(siebenImage, x * width, y * height, null);
                            break;
                        default: g.drawImage(achtImage, x * width, y * height, null);

                    }

                    //  g.setColor(Color.white);
                    //  g.drawString(""+amountOfNearBombs,x*width+5,y*height+height-4); // wegen anzeigefehlern muss hier noch etwas nachgeholfen werden
                }
            }
        }
        // g.setColor(Color.red); // farbe auf Rot setzen
        // g.fillRect(x*width,y*height,width,height); // positionen der Kacheln
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}




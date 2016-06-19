import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * Created by ${perdu} on ${16.06.16}.
 */
public class Welt {
    private static final int width = 20; // Wieviele Kacheln in X richtung
    private static final int height = 20;// wieviele Kacheln in y-richtung
    private boolean finish;
    private boolean dead;
    private final Random random;      // Initialisierung eines neuen Random objekts
    private final Level[][] level; // 2 dim level array
    private boolean bombe;
    private boolean offen;
   // private int clicks = 0;
   // private int zeit = 0;
   // private long startTime = 0;         // wird für timer benötigt
   // private long endTime = 0;
    //private long duration = 0;
   private Uhr uhr2 = new Uhr();

    public Welt()  {
        { // intitialisierung
            random = new Random();    // neues Random
            uhr2.setVisible(false);
            level = new Level[width][height];
            // Level wird mit den dems von width und height gezeichnet
            for (int x = 0; x < width; x++) { // wir gehen duch alle Tiles/level durch
                for (int y = 0; y < height; y++) {
                    BufferedImage bomb = Imageloader.scale(Imageloader.loadImage("pics/bombe.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage flag = Imageloader.scale(Imageloader.loadImage("pics/Flaggenfeld.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage normal = Imageloader.scale(Imageloader.loadImage("pics/Feld.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage pressed = Imageloader.scale(Imageloader.loadImage("pics/gedrueckt.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage eins = Imageloader.scale(Imageloader.loadImage("pics/1.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage zwei = Imageloader.scale(Imageloader.loadImage("pics/2.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage drei = Imageloader.scale(Imageloader.loadImage("pics/3.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage vier = Imageloader.scale(Imageloader.loadImage("pics/4.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage fuenf = Imageloader.scale(Imageloader.loadImage("pics/5.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage sechs = Imageloader.scale(Imageloader.loadImage("pics/6.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage sieben = Imageloader.scale(Imageloader.loadImage("pics/7.jpg"), Level.getWidth(), Level.getHeight());
                    BufferedImage acht = Imageloader.scale(Imageloader.loadImage("pics/8.jpg"), Level.getWidth(), Level.getHeight());
                    level[x][y] = new Level(x, y, normal, bomb, pressed, flag, eins, zwei, drei, vier, fuenf, sechs, sieben, acht); // jedes tile soll ein neues Tile sein mit .. x/y koordinate
                    // und obs normal ist oder bombe,pressed oder flag oder ne zahl
                    level[x][y].setNormal(normal);      // automatisch auf normal gezeichnet
                }
            }
            placeBombs(); //Aufruf von placebombs welches die methode placebomb aufruft
            setNumbers();
           // getStartTime();
        }
        reset();
    }

    private void placeBombs() {
        int amountOfBombs = 40;
        for (int i = 0; i < amountOfBombs; i++) {
            placeBomb();
        }
    }

    private void placeBomb() {
        int x = random.nextInt(width);          // zufälliges Plazieren der Bomben
        int y = random.nextInt(height);

        if (!level[x][y].isBomb()) {         //Abfrage ob Bombe oder nicht, wenn keine Bombe
            level[x][y].setBomb();

        } else placeBomb();               //ansonsten erneuter aufruf
    }


    public boolean isBombe(int x, int y) {      // Methode zum abfragen ob erster Klick bombe
        int tileX = x / Level.getWidth();      // Welche Kachelposi
        int tileY = y / Level.getHeight();

        if (level[tileX][tileY].isBomb()){


            return true;}
        return true;
    }



    private void setNumbers() { // methode um die Zahlen die gezeichnet werden zu setzen
        for (int x = 0; x < width; x++) { // wir gehen duch alle Tiles/level durch
            for (int y = 0; y < height; y++) {
                int mx = x - 1;                // Bombenzählalgorithmus
                int gx = x + 1;
                int my = y - 1;
                int gy = y + 1;

                int amountOfBombs = 0;
                if (mx >= 0 && my >= 0 && level[mx][my].isBomb()) amountOfBombs++;
                if (mx >= 0 && level[mx][y].isBomb()) amountOfBombs++;
                if (mx >= 0 && gy < height && level[mx][gy].isBomb()) amountOfBombs++;

                if (my >= 0 && level[x][my].isBomb()) amountOfBombs++;
                if (gy < height && level[x][gy].isBomb()) amountOfBombs++;

                if (gx < width && my >= 0 && level[gx][my].isBomb()) amountOfBombs++;
                if (gx < width && level[gx][y].isBomb()) amountOfBombs++;
                if (gx < width && gy < height && level[gx][gy].isBomb()) amountOfBombs++;

                level[x][y].setAmountOfNearBombs(amountOfBombs);

            }
        }
    }

    public void clickedLeft(int x, int y) {
        {

             if (!dead && !finish) {

                int tileX = x / Level.getWidth();      // Welche Kachelposi
                int tileY = y / Level.getHeight();

                if (!level[tileX][tileY].isFlag()) {
                    level[tileX][tileY].setOpened();


                    if (level[tileX][tileY].isBomb()) dead = true;

                    else {
                        if (level[tileX][tileY].getAmountOfNearBombs() == 0) { //alle Felder öffnen die keine Bomben haben und min 1 feld von einer Bombe weg sind
                            open(tileX, tileY);
                        }
                    }
                    checkFinish();
                }
            }
        }
    }

    public void clickedRight(int x, int y) {
        if (!dead && !finish) {
            int tileX = x / Level.getWidth();      // Welche Kachelposi
            int tileY = y / Level.getHeight();

            level[tileX][tileY].placeFlag();

            checkFinish();
        }
    }
    public boolean nichtoeffnen(int x, int y){
       if (level[x][y].canOpen()){
            return false;
        }
        return false;
    }

    public void open(int x, int y) {
        level[x][y].setOpened();
        if (level[x][y].getAmountOfNearBombs() == 0) {


            int mx = x - 1;
            int gx = x + 1;
            int my = y - 1;
            int gy = y + 1;


            if (mx >= 0 && my >= 0 && level[mx][my].canOpen()) open(mx, my);
            if (mx >= 0 && level[mx][y].canOpen()) open(mx, y);
            if (mx >= 0 && gy < height && level[mx][gy].canOpen()) open(mx, gy);

            if (my >= 0 && level[x][my].canOpen()) open(x, my);
            if (gy < height && level[x][gy].canOpen()) open(x, gy);

            if (gx < width && my >= 0 && level[gx][my].canOpen()) open(gx, my);
            if (gx < width && level[gx][y].canOpen()) open(gx, y);
            if (gx < width && gy < height && level[gx][gy].canOpen()) open(gx, gy);
        }
    }

    private void checkFinish() {
        outer:              // Sprungmarke
        for (int x = 0; x < width; x++) { // wir gehen duch alle Tiles/level durch
            for (int y = 0; y < height; y++) {
                if (!(level[x][y].isOpened() || level[x][y].isBomb() && level[x][y].isFlag())) {
                    finish = false;
                    break outer;
                }
            }
        }
    }


    public void draw(Graphics g) {  // um etwas in der Welt zu malen
        for (int x = 0; x < width; x++) { // wir gehen duch alle Tiles/level durch
            for (int y = 0; y < height; y++) {
                level[x][y].draw(g);        // hier wird eine neue Kachel gezeichnet bzw Fenster wird rot
            }
        }
        if (dead) {
           Klang.HINTERGRUND.gestoppt();
            Klang.TOT.play(40);

            g.setColor(Color.red);
            g.drawString("you loose\n", 200, 300);

            uhr2.stop();
            if (uhr2.stop){
                System.out.println("Zeit"+ uhr2.getCl()); // da passt noch was nicht
            }

            //g.drawString("Zeit:"+getDuration(),200,400);   // Brauchen wir jetzt nicht mehr, da wir nen Timer haben
        } else if (finish) {

            Klang.HINTERGRUND.gestoppt();
            Klang.HURRA.play(40);
            g.setColor(Color.red);
            g.drawString("you won\n", 200, 300);
           // g.drawString("Zeit:"+getDuration(),200,400);

        }

    }

    public boolean isDead() {
        return dead;
    }

    public void reset()
    {
        for(int x = 0;x < width;x++)
        {
            for(int y = 0;y < height;y++)
            {
                level[x] [y].reset();
            }
        }
        Klang.TOT.gestoppt();
        Klang.HURRA.gestoppt();
        Klang.HINTERGRUND.gestoppt();

        dead = false;
        finish = false;

        placeBombs();
        setNumbers();
       Klang.HINTERGRUND.playloop();

        //getStartTime();
    }
  /*  public final long getDuration() {          // Timermethode
        long start = getStartTime();
        long ende = getEndTime();
        long duration =  ((ende - start)/1000);
        return duration;
    }
    public long getStartTime() {
        startTime = System.nanoTime();
        return startTime;
    }
    public long getEndTime() {
        long endTime = System.nanoTime();
        return endTime;
    }*/


    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }


    public void restart(){
        main main = new main();
    }
}





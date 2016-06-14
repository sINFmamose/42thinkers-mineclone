import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * Created by perdu on 10.06.16.
 */
public class Welt  {
    private static int width = 20; // Wieviele Kacheln in X richtung
    private static int height = 20;// wieviele Kacheln in y-richtung
    private final int AmountOfBombs = 40; // Bomben
    private boolean finish;
    private boolean dead;
    private Random random;      // Initialisierung eines neuen Random objekts
    private Level[][] level; // 2 dim level array
    private int clicks = 0;
    private BufferedImage bomb = Imageloader.scale(Imageloader.loadImage("pics/bombe.jpg"), Level.getWidth(), Level.getHeight());     // besser hier die images laden als in jeder kachel einzeln
    private BufferedImage flag = Imageloader.scale(Imageloader.loadImage("pics/Flaggenfeld.jpg"), Level.getWidth(), Level.getHeight()); //Scalliertes laden
    private BufferedImage normal = Imageloader.scale(Imageloader.loadImage("pics/Feld.jpg"), Level.getWidth(), Level.getHeight());
    private BufferedImage pressed = Imageloader.scale(Imageloader.loadImage("pics/gedrueckt.jpg"), Level.getWidth(), Level.getHeight());
    private BufferedImage eins = Imageloader.scale(Imageloader.loadImage("pics/1.jpg"), Level.getWidth(), Level.getHeight());
    private BufferedImage zwei = Imageloader.scale(Imageloader.loadImage("pics/2.jpg"), Level.getWidth(), Level.getHeight());
    private BufferedImage drei = Imageloader.scale(Imageloader.loadImage("pics/3.jpg"), Level.getWidth(), Level.getHeight());
    private BufferedImage vier = Imageloader.scale(Imageloader.loadImage("pics/4.jpg"), Level.getWidth(), Level.getHeight());
    private BufferedImage fuenf = Imageloader.scale(Imageloader.loadImage("pics/5.jpg"), Level.getWidth(), Level.getHeight());
    private BufferedImage sechs = Imageloader.scale(Imageloader.loadImage("pics/6.jpg"), Level.getWidth(), Level.getHeight());
    private BufferedImage sieben = Imageloader.scale(Imageloader.loadImage("pics/7.jpg"), Level.getWidth(), Level.getHeight());
    private BufferedImage acht = Imageloader.scale(Imageloader.loadImage("pics/8.jpg"), Level.getWidth(), Level.getHeight());


    private long startTime = 0;         // wird für timer benötigt
    private long endTime = 0;
    private long duration = 0;


    public Welt() {
        { // intitialisierung
            random = new Random();    // neues Random
            level = new Level[width][height];
            // Level wird mit den dems von width und height gezeichnet
            for (int x = 0; x < width; x++) { // wir gehen duch alle Tiles/level durch
                for (int y = 0; y < height; y++) {
                    level[x][y] = new Level(x, y, normal, bomb, pressed, flag,eins,zwei,drei,vier,fuenf,sechs,sieben,acht); // jedes tile soll ein neues Tile sein mit .. x/y koordinate
                    // und obs normal ist oder bombe,pressed oder flag oder ne zahl
                    level[x][y].setNormal(normal);      // automatisch auf normal gezeichnet
                }
            }
            placeBombs(); //Aufruf von placebombs welches die methode placebomb aufruft
            setNumbers();
            getStartTime();
        }
        reset();
    }

    private void placeBombs() {
        for (int i = 0; i < AmountOfBombs; i++) {
            placeBomb();
        }
    }

    private void placeBomb() {
        int x = random.nextInt(width);          // zufälliges Plazieren der Bomben
        int y = random.nextInt(height);

        if (!level[x][y].isBomb()) {         //Abfrage ob Bombe oder nicht, wenn keine Bombe
            level[x][y].setBomb(true);

        } else placeBomb();               //ansonsten erneuter aufruf
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
                    level[tileX][tileY].setOpened(true);


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

    private void open(int x, int y) {
        level[x][y].setOpened(true);
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
            g.setColor(Color.red);
            g.drawString("you loose\n", 200, 300);
            g.drawString("Zeit:"+getDuration(),200,400);
        } else if (finish) {
            g.setColor(Color.red);
            g.drawString("you won\n", 200, 300);
            g.drawString("Zeit:"+getDuration(),200,400);
        }
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

        dead = false;
        finish = false;

        placeBombs();
        setNumbers();
        getStartTime();
    }
    public final long getDuration() {          // Timermethode
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
    }




    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}





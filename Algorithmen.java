import java.util.Random;


/**
 * Created by perdu on 20.05.16.
 */
public class Algorithmen
{



    private void PlaziereBomben (int x ,int y )
    {
        Random rand = new Random();

        minenLand = new int[Level.breite][Level.hoehe]; // minenland wird durch die Dimensionen  des Lvls bestimmt

        int zähler = 0;
        int xPunkt;
        int yPunkt;
        while(zähler<Level.Bomben){ //performanter als die andere Lösung
            xPunkt = rand.nextInt(Level.breite);
            yPunkt = rand.nextInt(Level.hoehe);
            if ((minenLand[xPunkt][yPunkt] != 10)) {
                    if (minenland[xPunkt][yPunkt]!=22)
                    {
                        minenLand[xPunkt][yPunkt] = 10; // 10 ist eine Bombe
                        zähler++;
                    }
            }
        }


       //Minenzähler im Umfeld
        for (int i = 0; i < Level.breite; i++) {
            for (int j = 0; j < Level.hoehe; j++) {
                if (minenLand[i][j]==10 ) {            //  Abfrage ob Mine da ist
                    for (int k = -1; k <= 1 ; k++) {
                        for (int l = -1; l <= 1; l++) {

                            try {
                                if (minenLand[i+k][j+l]!=-1) {
                                    minenLand[i+k][j+l] += 1;
                                }
                            }
                            catch (Exception e) {
                                // Do nothing
                            }
                        }
                    }
                }
            }
        }
    }
}

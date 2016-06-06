import java.util.Random;


/**
 * Created by perdu on 20.05.16.
 */
public class Algorithmen
{
int derzeitigesLevel =0;  // immo nur ein helper


    private void PlaziereBomben (int x ,int y )
    {
        Random rand = new Random();
        int b;
        int c;
        int d;
        b = Level.breite[derzeitigesLevel];
        c = Level.hoehe[derzeitigesLevel];
        d = Level.Bomben[derzeitigesLevel];

         int [][]minenLand = new int[b][c]; // minenland wird durch die Dimensionen  des Lvls bestimmt

        int zähler = 0;
        int xPunkt;
        int yPunkt;
        while(zähler<d){ //performanter als die andere Lösung
            xPunkt = rand.nextInt(b);
            yPunkt = rand.nextInt(c);
            if ((minenLand[xPunkt][yPunkt] != 10)) {
                    if (minenLand[xPunkt][yPunkt]!=22)
                    {
                        minenLand[xPunkt][yPunkt] = 10; // 10 ist eine Bombe
                        zähler++;
                    }
            }
        }


       //Minenzähler im Umfeld
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < c; j++) {
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

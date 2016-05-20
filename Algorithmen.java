import java.util.Random;

/**
 * Created by perdu on 20.05.16.
 */
public class Algorithmen
{

    private void PlaziereBomben (int breite,int hoehe)
    {
        Random rand = new Random();

        minenLand = new int[breite][hoehe]; // minenland wird durch die Dimensionen  des Lvls bestimmt
        for (int i = 0 ; i< breite; i++)
        {
            for (int j = 0; j< hoehe; j++)
            {
                minenLand[i][j] = 0 ;
            }
        }

        int zähler = 0;
        int xPunkt;
        int yPunkt;
        while(zähler<Bomben){ //performanter als die andere Lösung
            xPunkt = rand.nextInt(breite);
            yPunkt = rand.nextInt(hoehe);
            if ((minenLand[breite][hoehe] != 10) &!=22) // da passt noch was nicht! Abfrage ob keine 10 oder keine 22
            {
                minenLand[breite][hoehe]= 10; // 10 ist eine Bombe
                zähler++;

            }
        }
       //Minenzähler im Umfeld
        for (int i = 0; i < breite; i++) {
            for (int j = 0; j < hoehe; j++) {
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

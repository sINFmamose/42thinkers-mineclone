/**
 * Created by perdu on 19.05.16.
 */

public class Dimension
        extends java.awt.geom.Dimension2D
       implements java.io.Serializable

public class Level {

    final static int Bomben = 20;
    final static int hoehe = 10;
    final static int breite = 10;

    protected int [][] level1 = new int [9][9];
    {
        level1[0][] = {22,22,22,22,22,22,22,22,22,22};
        level1[1][] = {22,0,0,0,0,0,0,0,0,22};
        level1[2][] = {22,0,0,0,0,0,0,0,0,22};
        level1[3][] = {22,0,0,0,0,0,0,0,0,22};
        level1[4][] = {22,0,0,0,0,0,0,0,0,22};
        level1[5][] = {22,0,0,0,0,0,0,0,0,22};
        level1[6][] = {22,0,0,0,0,0,0,0,0,22};
        level1[7][] = {22,0,0,0,0,0,0,0,0,22};
        level1[8][] = {22,0,0,0,0,0,0,0,0,22};
        level1[9][] = {22,22,22,22,22,22,22,22,22,22};

    // Dimensionen die dem Header des Spielers übergeben werden
        class Dimension{int width; int height;}

        int width = 8;
        int height = 8;

        }


}

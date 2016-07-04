import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;




import static org.junit.Assert.*; // Static import method .../Assert.assertTrue(org.junit)	JUnit4(junit-4.12)

/**
 * Created Eduardo Alezard on 21.06.2016.
 */
public class TestMine {

    // tests if 'World' works correctly

    private Welt testWelt;
    private Level[][] testLevel;

    private Level testGanzLevel;

    private static final int width = 20;
    private static final int height = 20;
    private Level testL; // for isOpened

    @Before
    public void setupTest() {

   /*     testLevel = new Level[4][];
        //[][]
        //[][]
        // created four lines of the field world for the tests
        testLevel[0] = new Level[]{new Level(0, 0, null, null, null, null, null, null, null, null, null, null, null, null),
                new Level(0, 1, null, null, null, null, null, null, null, null, null, null, null, null)};
        testLevel[1] = new Level[]{new Level(1, 0, null, null, null, null, null, null, null, null, null, null, null, null),
                new Level(1, 1, null, null, null, null, null, null, null, null, null, null, null, null)};
        testLevel[2] = new Level[]{new Level(2, 0, null, null, null, null, null, null, null, null, null, null, null, null),
                new Level(2, 1, null, null, null, null, null, null, null, null, null, null, null, null)};
        testLevel[3] = new Level[]{new Level(3, 0, null, null, null, null, null, null, null, null, null, null, null, null),
                new Level(3, 1, null, null, null, null, null, null, null, null, null, null, null, null)};*/



        // Eine eins zu eins Welt, 20*20, wie die Welt, die das Programm erzeugt
        testLevel = new Level[width][height];

        for (int x = 0; x < width; x ++){
            for (int y = 0; y < height; y++){
                testLevel[x][y] = new Level(x, y, null, null, null, null, null, null, null, null, null, null, null, null);
                //System.out.println("Das " + (x+1) + ". Level: " + testLevel[x][y]);
            }
        }

        // Fehler korrigiert: der Konstruktor der Welt benötigt keine argumente(schmeißt eine Compilerwarnung)

                this.testWelt = new Welt(/*testLevel*/);
    }

    @Test
    public void testPlaceBombs_shouldBeDeadAfterClick() {
        System.out.println("Testing Welt ... expected: 'isDead' must be 'true' if tile has a bomb and left clicked");

        //Bombe in (0,0) und (1,1)
        testLevel[0][0].setBomb();
        testLevel[1][1].setBomb();

        testWelt.clickedLeft(1, 1); //gut!
        //testWelt.clickedRight(1, 1); // if clickedRight should fail, gut!

        //testWelt.clickedLeft(0, 1); // should fail if bomb = false! bombs are only in (0, 0) and (1, 1) <-- Problem, die Skalierung schikt immer zu (0, 0) und nicht (0, 1)

        assertTrue("Test bomb + left mouse button = dead; test failed", testWelt.isDead());
        testGanzLevel.setAmountOfNearBombs(2); // es fehlt was noch
        testWelt.clickedRight(1,0);
        //assertTrue(testGanzLevel.isOpened());
        //assertTrue("Test failed!", testL.isOpened());

        //assertFalse("The tile has not bomb", testGanzLevel.isBomb());

    }

    @Test
    public void testPlaceBombs_shouldBeAliveAfterClick() {
        System.out.println("Testing no bombs in tile that dead false ...");

        // Bombe in (0,0) und (1,1)
        testLevel[0][0].setBomb(); // Debug for not dead
        testLevel[1][1].setBomb();

        testWelt.clickedLeft(2, 1); // no bomb then isDead = false ; It doesn't work! <-- Problem 'keine Bombe' ob links/rechts klicken dead = false
        //testWelt.clickedRight(0, 0); // gut

        assertFalse("If bomb false or right clicked should be not dead", testWelt.isDead());
    }


    @Test
    public void testPlaceBombs_and_Flags_shouldBeAliveAfterRightClick() {
        System.out.println("Testing Welt ...'isFlag' must be 'true' and dead false");

        //Bombe in (0,0) und (1,1)
        testLevel[0][0].setBomb();
        testLevel[1][1].setBomb();


        testWelt.clickedRight(0, 0); // 1) flag in, dead false
        testWelt.clickedRight(0, 0); // 2) flag out, dead false
        //testWelt.clickedLeft(0, 0);  // 3) after flag in, alive, 1) and 3); after flag out, dead true --> assertFalse --> failed, 1), 2) and 3)

        assertFalse("test failed. Check the settings, one or two right means dead = false, two right and one left means dead = true!", testWelt.isDead());
        //assertTrue("Should appear if once or twice clickRight", testWelt.isDead()); // It works just with 3) or 1) - 3)
    }


    @Ignore
    @Test
    public void testShouldBeOpenAfterLeftClick() {
        System.out.println("Testing open after left click a tile");
        testLevel[2][0].setBomb();
        testWelt.clickedLeft(2,1);

        assertTrue("Test is isOpened failed", testL.isOpened());


    }

    @Test
    public void testChekFinish() {
        System.out.print("Testing checkFinish ...");

    }


}
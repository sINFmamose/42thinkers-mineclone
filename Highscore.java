
import javax.swing.*;
import java.io.*;
/**
 * Created by perdu on 05.06.16.
 */
public class Highscore {

    private JPanel panel1;

        public static void main(String[] args)throws IOException {
            Reader reader = null;

            try {
                reader = new FileReader("bin/highscore.txt");

                while ( reader.ready() ) {
                    System.out.println(reader.read());
                }
            } catch (IOException e) {
                System.err.println("Fehler beim Lesen der Datei!");
            } finally {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
}

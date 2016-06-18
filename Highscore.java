
import javax.swing.*;
import java.io.*;
/**
 * Created by ${perdu} on ${16.06.16}.
 */
public class Highscore extends JFrame {

    private JPanel panel1;

        public static void main(String[] args)throws IOException {
            Reader reader = null;

            try {
                System.getProperty("file.separator");
                reader = new FileReader("bin.highscore.txt");

                while ( reader.ready() ) {
                    System.out.println(reader.read());
                }
            } catch (IOException e) {
                System.err.println("Fehler beim Lesen der Datei!");
            } finally {
                try {
                    assert reader != null;
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
}

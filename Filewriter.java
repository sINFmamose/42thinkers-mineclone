import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by perdu on 14.06.16.
 */
public class Filewriter {

    FileWriter writer;
    File file;
    public void schreiben(){
        // File anlegen
        file = new File("saves.txt");
        try {
            // new FileWriter(file ,true) - falls die Datei bereits existiert
            // werden die Bytes an das Ende der Datei geschrieben

            // new FileWriter(file) - falls die Datei bereits existiert
            // wird diese überschrieben
            writer = new FileWriter(file ,true);
            // Text wird in den Stream geschrieben
            writer.write("xx"); /// Da müssen wir die Savevariablen eintragen

            // Platformunabhängiger Zeilenumbruch wird in den Stream geschrieben
            writer.write(System.getProperty("line.separator"));

            // Text wird in den Stream geschrieben
            writer.write("xxxx");


            // Schreibt den Stream in die Datei
            // Sollte immer am Ende ausgeführt werden, sodass der Stream
            // leer ist und alles in der Datei steht.
            writer.flush();

            // Schließt den Stream
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void lesen(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(
                    "saves.txt"));  // Liest aus der saves was ein
            String zeile;
            try {
                while ((zeile = br.readLine()) != null) {
                    System.out.println(zeile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    }



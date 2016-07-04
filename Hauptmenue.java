

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.awt.GridBagLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


/**
 * Created by ${perdu} on ${16.06.16}.
 */
public class Hauptmenue  /*extends Application */{
    private GridLayout hauptmenuLayout;
    private JButton creditsButton;
    private JButton spielBeendenButton;
    private JButton spielStartenButton;
    private JButton highscoreButton;
    private JButton spielLadenButton;
    private JButton tutorialButton;
/*
    //als Vorbereitung für JavaFX gedacht, im Garbage collector wohl gut aufgehoben
    private Stage MenuStage;
    private Button creditsKnopf;
    private Button endeKnopf;
    private Button startKnopf;
    private Button ladenKnopf;
    private Button tutorialKnopf;
    private GridPane Raster;
*/




 public Hauptmenue(){
       //weil das mit FX unverstänlicher Code wird, mit AWT... Entschluss gefasst am 4.07 um 21 uhr... nach erfolgreichem Erfahrungssammeln mit Jfx

       //Erstellen eines awt-Fensters mit Raster-Layout 1 auf 6 für die Buttons
       JFrame Fenster = new JFrame("Menu");
       //damit das Kreuzchen das Fenster Schließt...
       Fenster.setDefaultCloseOperation(3);
     //layout erzeugen...
       hauptmenuLayout = new GridLayout(6,1);
        Fenster.setLayout(hauptmenuLayout);

       spielStartenButton = new JButton ("neues Spiel");
       creditsButton = new JButton("credits");
       spielLadenButton = new JButton("Spiel laden(in Vorbereitung)");
       highscoreButton = new JButton ("Highscore (in Vorbereitung)");
       tutorialButton = new JButton ("Tutorial anzeigen");
       spielBeendenButton = new JButton("Spiel Beenden");


       //Buttons zum Fenster hinzufügen
       Fenster.add(spielStartenButton);
       Fenster.add(creditsButton);
       Fenster.add(tutorialButton);
       Fenster.add(spielLadenButton);
       Fenster.add(highscoreButton);
       Fenster.add(spielBeendenButton);

       // den Buttons ihre Aufgaben zuweisen...
       //erstellen der Benötigten ActionListener... damit beim Knöpfedrücken auch was passiert ;)
       ActionListener creditsListener = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Fenster.setVisible(false);
               Fenster.dispose();
               JFrame CreditsFenster = new JFrame("Credits");
               CreditsFenster.setDefaultCloseOperation(3);
               JTextArea CreditsText = new JTextArea("Mineclone TEAM: \n" +
                       " Lead Progammmer: Georg Goldes \n" +
                       "advanced GUI creator: zdravko Buljan\n"+
                       "Lead Tester: Eduardo Alezard Ostermann\n"+
                        "alpha GUI creator: Markus Moses");
               CreditsFenster.setLayout(new GridLayout(1,1));
               CreditsFenster.add(CreditsText);
               CreditsFenster.pack();
               CreditsFenster.setVisible(true);

           }

       };

       ActionListener notWorkingYetListener = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Fenster.setVisible(false);
               Fenster.dispose();
               JFrame gehtNichtFenster = new JFrame();
               JButton gehtNichtKnopf = new JButton ("die gewählte Aktion gibt es leider nocht nicht");
               gehtNichtKnopf.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       gehtNichtFenster.setVisible(false);
                       gehtNichtFenster.dispose();
                   }
               });
               gehtNichtFenster.setDefaultCloseOperation(3);
               gehtNichtFenster.setLayout(new GridLayout(1,1));
               gehtNichtFenster.add(gehtNichtKnopf);
               gehtNichtFenster.pack();
               gehtNichtFenster.setVisible(true);
           }
       };

       ActionListener tutorialListnener = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Fenster.setVisible(false);
               Fenster.dispose();
               JFrame tutorialFenster = new JFrame("Tutorial");
               tutorialFenster.setDefaultCloseOperation(3);
               JTextArea CreditsText = new JTextArea("Tutorial für den Mine_Clone:\n" +
                       "\n" +
                       "Ziel des Spieles ist es, genau wie beim Spiele-Klassiker\"Mine Sweeper\",  das Minenfeld aufzudecken, ohne eine der Minen auszulösen. Zu diesem Zweck können Felder, bei denen der Verdacht besteht, dass sie eine Mine enthalten, mit Flaggen markiert werden.\n" +
                       "Die Zahl in einem Feld gibt an, wie viele Minen sich in den angrenzenden Feldern befinden, sodass eine Lösung des Rätsels mit etwas Glück möglich ist.\n" +
                       "\n" +
                       "Stueuerung:\n" +
                       "linke Maustaste: Feld aufdecken.\n" +
                       "rechte Maustaste: Feld als mine Markieren.\n" +
                       "ENTER-taste: Spiel neu Starten.\n" +
                       "click auf das Kreuz am Spiel-Fenster: Spiel beenden.\n" +
                       "\n" +
                       "zur Regelung der Spiellautstärke benutzen Sie bitte die Einstellungen ihres Betriebssystems.\n");
               tutorialFenster.setLayout(new GridLayout(1,1));
               tutorialFenster.add(CreditsText);
               tutorialFenster.pack();
               tutorialFenster.setVisible(true);

           }
       };

       ActionListener spielStartListener = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               //nicht edel oder schön, sollte aber hoffentlich irgendwie gehen... die alte main-class Kopiert...

               /*@TODO: zum laufen Bringen... also ein neues Spiel aufrfen, irgenwdwie...
               *
                */
                class run extends Application{
                   Algorithmen algorithmen = new Algorithmen();
                   @Override
                   public void start(Stage primaryStage) throws Exception {
                       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Startbildschirm.fxml"));
                       Parent view = fxmlLoader.load();
                       run.Controller controller = fxmlLoader.getController();
                       controller.setStage(primaryStage);

                       primaryStage.setTitle("Algorithmen");
                       primaryStage.setScene(new Scene(view));
                       primaryStage.show();
                   }



                    class Controller {
                       // reference to (primary) stage; required to move on to next view
                       private Stage stage;

                       public void setStage(Stage stage) {
                           this.stage = stage;
                       }

                       public void reactToButtonClick(javafx.event.ActionEvent actionEvent) {
                           try {
// generate login controller from FXML;
                               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Algorithmen.fxml"));
                               Parent view = fxmlLoader.load();
                               stage.setScene(new Scene(view));
                               stage.show();
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                       }
                   }
               }
               run laufen = new run();

           }
       };

     ActionListener EndeListener = new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             Fenster.setVisible(false);
             Fenster.dispose();
         }
     };


       // den Buttons die Jeweiligen ActionListener zuweisen. poc...

       creditsButton.addActionListener(creditsListener);
       spielLadenButton.addActionListener(notWorkingYetListener);
       highscoreButton.addActionListener(notWorkingYetListener);
       tutorialButton.addActionListener(tutorialListnener);
        spielBeendenButton.addActionListener(EndeListener);


       //packen
       Fenster.pack();
       //sichtbar machen
       Fenster.setVisible(true);


     /*
     //Escape-Taste als Escape nutzen... noch in Vorbereitung...

       Fenster.addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {
               if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
               {
                   //code to execute if escape is typed
                   Fenster.dispose();
               }
           }

           @Override
           public void keyPressed(KeyEvent e) {
               if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
               {
                   //code to execute if escape is pressed
                   Fenster.dispose();
               }

           }

           @Override
           public void keyReleased(KeyEvent e) {
               if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
               {
                   //code to execute if escape is released
                   Fenster.dispose();
               }

           }
       });
*/

   }


}

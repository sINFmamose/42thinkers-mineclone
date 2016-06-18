import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Created by ${perdu} on ${16.06.16}.
 */
public class Startbildschirm extends JFrame {


    private JButton klickmich1;


    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hauptmenue.fxml"));
        Parent view = (Parent) fxmlLoader.load();
        main.Controller controller = fxmlLoader.<main.Controller>getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Algorithmen");
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }

    public Startbildschirm() {
        klickmich1 = new JButton("click mich!");
        this.getContentPane().add(klickmich1);
       Stage stage = null;





        // Initiales Laden der Klänge
        Klang.init();
        Klang.INTRO.play(30);

        klickmich1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() ==(klickmich1))
                    try {
// generate login controller from FXML;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Algorithmen.fxml"));
                        Parent view = (Parent) fxmlLoader.load();
                        assert stage != null;
                        stage.setScene(new Scene(view));
                        stage.show();
                    } catch (Exception x) {
                        x.printStackTrace();
                    }
            }
        });

       }

    private JTextField willkommenZuMinecloneTextField;


    private void createUIComponents() {


        // TODO: place custom component creation code here
    }
    /*
    public static void main(String[] args) {
        new Startbildschirm();
    }*/
}

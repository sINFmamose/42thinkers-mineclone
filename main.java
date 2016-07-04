import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;



/**
 * Created by ${perdu} on ${16.06.16}.
 */
public class main /*extends Application */ {



    public static void main(String[] args) {


       //Algorithmen algorithmen = new Algorithmen();
       //Startbildschirm startbildschirm = new Startbildschirm();
         Hauptmenue hauptmenue = new Hauptmenue();

        /*EventQueue.invokeLater(() -> {          //mit ner Lambda-expression ersetzt
            Uhr clock = new Uhr();
            clock.start();

        });*/
    }

/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Startbildschirm.fxml"));
        Parent view = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Algorithmen");
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }


*/
    public class Controller {
        // reference to (primary) stage; required to move on to next view
        private Stage stage;

        public void setStage(Stage stage) {
            this.stage = stage;
        }

        public void reactToButtonClick(ActionEvent actionEvent) {
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
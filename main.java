import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by perdu on 20.05.16.
 */
public class main extends Application {

    public static void main(String[] args) {

        Algorithmen algorithmen = new Algorithmen();

        // Hauptmenue hauptmenue = new Hauptmenue();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hauptmenue.form"));
        Parent view = (Parent) fxmlLoader.load();
        Controller controller = fxmlLoader.<Controller>getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Algorithmen");
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }

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
                Parent view = (Parent) fxmlLoader.load();
                stage.setScene(new Scene(view));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
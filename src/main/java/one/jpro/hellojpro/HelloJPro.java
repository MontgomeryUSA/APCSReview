package one.jpro.hellojpro;

import java.net.URL;

import com.jpro.webapi.JProApplication;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HelloJPro extends JProApplication {
    
    @Override
    public void start(Stage stage) {
        System.out.println("JPro Application Started"); // Debugging

        Scene scene = getHomePageScene();
        stage.setScene(scene);
        stage.show();
    }

    

    public static void main(String[] args) {
        launch(args);
    }
}

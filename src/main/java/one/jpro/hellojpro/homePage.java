package one.jpro.hellojpro;

import java.net.URL;

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

public class homePage {
    test testClassInstance;
public Scene getHomePageScene(test test) {
        testClassInstance = test;
        BorderPane root = new BorderPane();
        root.setPrefSize(1920, 1080);


        VBox mainContent = new VBox();
        mainContent.setAlignment(Pos.TOP_CENTER);

        // Load and set background image
        BackgroundImage bgImage = loadBackground("/images/2HomeButtonColored.png");
        if (bgImage != null) {
            mainContent.setBackground(new Background(bgImage));
        }

        // Sidebar & Buttons
        VBox sideBar = new VBox(15);
        sideBar.setAlignment(Pos.CENTER);

        // Create buttons
        Button button1 = createImageButton("/images/2HomeButtonColored.png", 370, 87, "Home Button");
        Button button2 = createHoverButton("/images/1LearnButtonGrey.png", "/images/2LearnButtonColored.png", 370, 87, "Learn Button");
        Button button3 = createHoverButton("/images/1MockTestButtonGrey.png", "/images/2MockTestButtonColored.png", 370, 87, "Mock Test Button");
        Button button4 = createHoverButton("/images/1ReviewGameButtonGrey.png", "/images/2ReviewGameColored.png", 370, 87, "Review Game Button");

        button2.setOnAction(this::handleLearnButtonClick);
        button3.setOnAction(this::handleMockTestButtonClick);
        button4.setOnAction(this::handleReviewGameButtonClick);

        VBox buttonContainer = new VBox(10, button1, button2, button3, button4);
        buttonContainer.setAlignment(Pos.CENTER);

        // Sidebar Background
        BackgroundImage sidebarBg = loadBackground("/images/MenuBGE.png");
        if (sidebarBg != null) {
            sideBar.setBackground(new Background(sidebarBg));
        }

        sideBar.setPrefWidth(450);
        sideBar.getChildren().add(buttonContainer);

        root.setLeft(sideBar);
        root.setCenter(mainContent);

        return new Scene(root);
    }

    // Helper: Create a hover button
    private Button createHoverButton(String defaultPath, String hoverPath, int width, int height, String name) {
        Button button = new Button();
        ImageView defaultImage = loadImageView(defaultPath, width, height);
        ImageView hoverImage = loadImageView(hoverPath, width, height);

        if (defaultImage != null && hoverImage != null) {
            button.setGraphic(defaultImage);
        }

        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setOnMouseEntered(e -> {
            button.setGraphic(hoverImage);
            System.out.println(name + " - Hovered");
        });
        button.setOnMouseExited(e -> button.setGraphic(defaultImage));

        button.setOnAction(e -> {
            System.out.println(name + " - Clicked");
        });

        return button;
    }

    // Helper: Create an image button
    private Button createImageButton(String path, int width, int height, String name) {
        Button button = new Button();
        ImageView imageView = loadImageView(path, width, height);

        if (imageView != null) {
            button.setGraphic(imageView);
        }

        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setOnAction(e -> System.out.println(name + " - Clicked"));

        return button;
    }

    // Helper: Load ImageView properly for JPro
    private ImageView loadImageView(String path, int width, int height) {
        Image img = loadImage(path);
        if (img != null) {
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(width);
            imgView.setFitHeight(height);
            return imgView;
        }
        return null;
    }

    // Helper: Load Image correctly for JPro
    private Image loadImage(String path) {
        try {
            URL imageUrl = getClass().getResource(path);
            if (imageUrl == null) {
                System.err.println("ERROR: Image not found at path: " + path);
                return null;
            }
            return new Image(imageUrl.toExternalForm(), true);
        } catch (Exception e) {
            System.err.println("ERROR: Failed to load image: " + path);
            return null;
        }
    }
    
    

    // Helper: Load Background Image
    private BackgroundImage loadBackground(String path) {
        Image img = loadImage(path);
        if (img != null) {
            return new BackgroundImage(
                    img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );
        }
        return null;
    }

    // Event handlers
    private void handleLearnButtonClick(ActionEvent event) {
        System.out.println("Learn button clicked!");
    }

    private void handleMockTestButtonClick(ActionEvent event) {
        System.out.println("Mock test button clicked!");
    }

    private void handleReviewGameButtonClick(ActionEvent event) {
        System.out.println("Review game button clicked!");
    }
}

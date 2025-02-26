package one.jpro.hellojpro;

import java.net.URL;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MockTestButtons {
    test testClassInstance;
    Scene mockTest;
    public Scene getMockTestButtons(test testClassInstance){
        this.testClassInstance = testClassInstance;
        makeMockTestButtons();
        return mockTest;
    }
    private void makeMockTestButtons(){
        BorderPane root = new BorderPane();
        root.setPrefSize(1920, 1080);


        //========= MAIN BUTTON LAYOUT ============
        VBox mainLayout = new VBox(20); // 20px spacing
        mainLayout.setPrefSize(1470, 1080);
        mainLayout.setAlignment(Pos.CENTER);

        Button topButton = createHoverButtonWithAction("/images/3FullMockGrey.png","/images/3FullMockColored.png", 1209, 112,11, "FullMock");

        GridPane row1 = createRow(3, new ButtonData("/images/3Unit1Grey.png",1,"/images/3Unit1Colored.png"),new ButtonData("/images/3Unit2Grey.png",2,"/images/3Unit2Colored.png"),new ButtonData("/images/3Unit3Grey.png",3,"/images/3Unit3Colored.png"));
        GridPane row2 = createRow(2, new ButtonData("/images/3Unit4Grey.png",4,"/images/3Unit4Colored.png"),new ButtonData("/images/3Unit5Grey.png",5,"/images/3Unit5Colored.png"));
        GridPane row3 = createRow(3, new ButtonData("/images/3Unit6Grey.png",6,"/images/3Unit6Colored.png"),new ButtonData("/images/3Unit7Grey.png",7,"/images/3Unit7Colored.png"),new ButtonData("/images/3Unit8Grey.png",8,"/images/3Unit8Colored.png"));
        GridPane row4 = createRow(2, new ButtonData("/images/3Unit9Grey.png",9,"/images/3Unit9Colored.png"),new ButtonData("/images/3Unit10Grey.png",10,"/images/3Unit10Colored.png"));

        Button bottomButton = createHoverButtonWithAction("/images/3FRQMockGrey.png", "/images/3FRQMockColored.png", 1219, 116, 12, "3FRQ Mock");


        mainLayout.getChildren().addAll(topButton, row1, row2, row3, row4, bottomButton);

        //============= SIDE BAR THINGS AND BUTTONS ==================
        VBox sideBar = new VBox(15);
        sideBar.setAlignment(Pos.CENTER);

        // Create buttons
        Button button1 = createHoverButton("/images/1HomeButtonGrey.png","/images/2HomeButtonColored.png" ,370, 87, "Home Button");
        Button button2 = createHoverButton("/images/1LearnButtonGrey.png", "/images/2LearnButtonColored.png", 370, 87, "Learn Button");
        Button button3 = createImageButton("/images/1MockTestButtonGrey.png",  370, 87, "Mock Test Button");
        Button button4 = createHoverButton("/images/1ReviewGameButtonGrey.png", "/images/2ReviewGameColored.png", 370, 87, "Review Game Button");


        VBox buttonContainer = new VBox(10, button1, button2, button3, button4);
        buttonContainer.setAlignment(Pos.CENTER);

        // Sidebar Background
        System.out.println("🔍 Attempting to load sidebar background: /images/MenuBGE.png");
        BackgroundImage sidebarBg = loadBackground("/images/MenuBGE.png");
        if (sidebarBg != null) {
            System.out.println("✅ Sidebar background loaded successfully!");
            sideBar.setBackground(new Background(sidebarBg));
        } else {
            System.err.println("❌ Failed to load sidebar background!");
        }

        sideBar.setPrefWidth(450);
        sideBar.getChildren().add(buttonContainer);

        root.setLeft(sideBar);
        root.setCenter(mainLayout);
        mockTest = new Scene(root, 1920,1080);
    }




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
        System.out.println("🔍 Attempting to load ImageView for: " + path);
        Image img = loadImage(path);
        if (img != null) {
            System.out.println("✅ ImageView successfully loaded for: " + path);
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(width);
            imgView.setFitHeight(height);
            return imgView;
        }
        System.err.println("❌ Failed to load ImageView for: " + path);
        return null;
    }

    // Helper: Load Image correctly for JPro
    private Image loadImage(String path) {
        System.out.println("🟠 Attempting to load image: " + path);
        
        URL imageUrl = getClass().getResource(path);
        if (imageUrl == null) {
            System.err.println("❌ ERROR: Image not found at path: " + path);
            return null;
        }
        
        System.out.println("✅ Image found! Loading: " + imageUrl.toExternalForm());
        return new Image(imageUrl.toExternalForm(), false); // Prevent caching issues
    }

    // Helper: Load Background Image
    private BackgroundImage loadBackground(String path) {
        System.out.println("🟡 loadBackground() called with path: " + path);
        Image img = loadImage(path);

        if (img != null) {
            System.out.println("✅ Background image loaded successfully: " + path);
            return new BackgroundImage(
                    img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );
        } else {
            System.err.println("❌ Background image failed to load: " + path);
        }
        return null;
    }


    private Button createHoverButtonWithAction(String defaultPath, String hoverPath, int width, int height, int actionId, String name) {
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
        button.setOnMousePressed(e -> {
            button.setGraphic(hoverImage);
            button.setScaleX(0.95);
            button.setScaleY(0.95);
        });
        button.setOnMouseReleased(e -> {
            button.setGraphic(hoverImage);
            button.setScaleX(1.0);
            button.setScaleY(1.0);
        });
    
        button.setOnAction(e -> {
            System.out.println(name + " - Clicked");
            testClassInstance.setSceneForQuizzes(actionId);
        });
    
        return button;
    }
    
    private GridPane createRow(int numButtons, ButtonData... buttonData) {
        GridPane row = new GridPane();
        row.setHgap(20);
        row.setAlignment(Pos.CENTER);
    
        for (int i = 0; i < buttonData.length; i++) {
            Button btn = createHoverButtonWithAction(buttonData[i].imagePath, buttonData[i].HoverImagePath, 356, 108, buttonData[i].actionId, "Button " + (buttonData[i].actionId));
            row.add(btn, i, 0);
        }
    
        return row;
    }
    


    private static class ButtonData {
        String imagePath;
        int actionId;
        String HoverImagePath;

        public ButtonData(String imagePath, int actionId, String HoverImagePath) {
            this.imagePath = imagePath;
            this.actionId = actionId;
            this.HoverImagePath= HoverImagePath;;
        }
    }
}

package one.jpro.hellojpro;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileNotFoundException;

public class LearnButtons {
    test testClassInstance;
    Scene mockTest;
    public Scene getLearnButtons(test testClassInstance){
        this.testClassInstance = testClassInstance;
        makeLearnButtons();
        return mockTest;
    }
    private void makeLearnButtons(){
        BorderPane root = new BorderPane();
        root.setPrefSize(1920, 1080);


        //========= MAIN BUTTON LAYOUT ============
        VBox mainLayout = new VBox(20); // 20px spacing
        mainLayout.setPrefSize(1470, 1080);
        mainLayout.setAlignment(Pos.CENTER);


        GridPane row1 = createRow(3, new ButtonData("/3Unit1Colored.png",1,"/3Unit1Grey.png"),new ButtonData("/3Unit2Colored.png",2,"/3Unit2Grey.png"),new ButtonData("/3Unit3Colored.png",3,"/3Unit3Grey.png"));
        GridPane row2 = createRow(2, new ButtonData("/3Unit4Colored.png",4,"/3Unit4Grey.png"),new ButtonData("/3Unit5Colored.png",5,"/3Unit5Grey.png"));
        GridPane row3 = createRow(3, new ButtonData("/3Unit6Colored.png",6,"/3Unit6Grey.png"),new ButtonData("/3Unit7Colored.png",7,"/3Unit7Grey.png"),new ButtonData("/3Unit8Colored.png",8,"/3Unit8Grey.png"));
        GridPane row4 = createRow(2, new ButtonData("/3Unit9Colored.png",9,"/3Unit9Grey.png"),new ButtonData("/3Unit10Colored.png",10,"/3Unit10Grey.png"));

        mainLayout.getChildren().addAll( row1, row2, row3, row4);

        //============= SIDE BAR THINGS AND BUTTONS ==================
        VBox sideBar = new VBox(15);  // 15px spacing
        sideBar.setAlignment(Pos.CENTER);

        // Button 1
        Button button1 = new Button();
        ImageView Button1Pic = new ImageView("/1HomeButtonGrey.png");
        ImageView Button1PicH = new ImageView("/2HomeButtonColored.png");
        Button1Pic.setFitWidth(370);
        Button1Pic.setFitHeight(87);
        button1.setGraphic(Button1Pic);
        button1.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button1.setOnMouseEntered(e -> button1.setGraphic(Button1PicH));
        button1.setOnMouseExited(e -> button1.setGraphic(Button1Pic));
        button1.setOnMousePressed(e -> {
            button1.setGraphic(Button1PicH);
            button1.setScaleX(0.95);
            button1.setScaleY(0.95);
        });
        button1.setOnMouseReleased(e -> {
            button1.setGraphic(Button1PicH);
            button1.setScaleX(1.0);
            button1.setScaleY(1.0);
        });
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    testClassInstance.changeScene(0);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Button2
        Button button2 = new Button();
        ImageView Button2Pic = new ImageView("/2LearnButtonColored.png");
        ImageView Button2PicH = new ImageView("/2LearnButtonColored.png");
        Button2Pic.setFitWidth(370);
        Button2Pic.setFitHeight(87);
        button2.setGraphic(Button2Pic);
        button2.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button2.setOnMouseEntered(e -> button2.setGraphic(Button2PicH));
        button2.setOnMouseExited(e -> button2.setGraphic(Button2Pic));
        button2.setOnMousePressed(e -> {
            button2.setGraphic(Button2PicH);
            button2.setScaleX(0.95);
            button2.setScaleY(0.95);
        });
        button2.setOnMouseReleased(e -> {
            button2.setGraphic(Button2PicH);
            button2.setScaleX(1.0);
            button2.setScaleY(1.0);
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    testClassInstance.changeScene(1);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Button3
        Button button3 = new Button();
        ImageView button3Pic = new ImageView("/1MockTestButtonGrey.png");
        ImageView button3PicH = new ImageView("/2MockTestButtonColored.png");
        button3Pic.setFitWidth(370);
        button3Pic.setFitHeight(87);
        button3.setGraphic(button3Pic);
        button3.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button3.setOnMouseEntered(e -> button3.setGraphic(button3PicH));
        button3.setOnMouseExited(e -> button3.setGraphic(button3Pic));
        button3.setOnMousePressed(e -> {
            button3.setGraphic(button3PicH);
            button3.setScaleX(0.95);
            button3.setScaleY(0.95);
        });
        button3.setOnMouseReleased(e -> {
            button3.setGraphic(button3PicH);
            button3.setScaleX(1.0);
            button3.setScaleY(1.0);
        });
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    testClassInstance.changeScene(2);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //Button4
        Button button4 = new Button();
        ImageView Button4Pic = new ImageView("/1ReviewGameButtonGrey.png");
        ImageView Button4PicH = new ImageView("/2ReviewGameColored.png");
        Button4Pic.setFitWidth(370);
        Button4Pic.setFitHeight(87);
        button4.setGraphic(Button4Pic);
        button4.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button4.setOnMouseEntered(e -> button4.setGraphic(Button4PicH));
        button4.setOnMouseExited(e -> button4.setGraphic(Button4Pic));
        button4.setOnMousePressed(e -> {
            button4.setGraphic(Button4PicH);
            button4.setScaleX(0.95);
            button4.setScaleY(0.95);
        });
        button4.setOnMouseReleased(e -> {
            button4.setGraphic(Button4PicH);
            button4.setScaleX(1.0);
            button4.setScaleY(1.0);
        });
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    testClassInstance.changeScene(3);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Button Adding to SideBar + Formatting
        VBox buttonContainer = new VBox(10, button1, button2, button3, button4);
        buttonContainer.setAlignment(Pos.CENTER);

        Image sideBarImage = new Image("/MenuBGE.png");
        BackgroundImage bgI = new BackgroundImage(sideBarImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false,true,false));
        sideBar.setBackground(new Background(bgI));

        sideBar.setPrefWidth(450);
        sideBar.getChildren().add(buttonContainer);

        root.setLeft(sideBar);
        root.setCenter(mainLayout);
        mockTest = new Scene(root, 1920,1080);
    }


    private Button createImageButton(String hoverImagePath, double width, double height, int actionId, String imagePath) {
        Button button = new Button();
        ImageView imageView1 = new ImageView(new Image(imagePath));
        ImageView imageViewH = new ImageView(new Image(hoverImagePath));
        imageView1.setFitWidth(width);
        imageView1.setFitHeight(height);
        button.setGraphic(imageView1);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setOnMouseEntered(e -> button.setGraphic(imageViewH));
        button.setOnMouseExited(e -> button.setGraphic(imageView1));
        button.setOnMousePressed(e -> {
            button.setGraphic(imageViewH);
            button.setScaleX(0.95);
            button.setScaleY(0.95);
        });
        button.setOnMouseReleased(e -> {
            button.setGraphic(imageViewH);
            button.setScaleX(1.0);
            button.setScaleY(1.0);
        });

        button.setOnAction(e -> testClassInstance.setSceneForVideo(actionId));

        return button;
    }

    private GridPane createRow(int numButtons, ButtonData... buttonData) {
        GridPane row = new GridPane();
        row.setHgap(20);
        row.setAlignment(Pos.CENTER);

        for (int i = 0; i < buttonData.length; i++) {
            Button btn = createImageButton(buttonData[i].imagePath, 356, 108, buttonData[i].actionId,buttonData[i].HoverImagePath);
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

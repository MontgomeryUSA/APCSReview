package one.jpro.hellojpro;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class snakeScene {
    static int speed = 7;
    static int foodColor = 0;
    static final int width = 48;
    static final int height = 35;
    static int foodX = 0;
    static int foodY = 0;
    static int cornerSize = 30;
    static List<Corner> snake = new ArrayList<>();
    static Dir direction = Dir.left;
    static boolean gameOver = false;
    static Random rand = new Random();
    static Stage mainStage;
    private static AnimationTimer gameTimer;
    private static Scene gameScene;
    private QuizData qd;
    private static ArrayList<Question> allQuestions;
    public enum Dir { left, right, up, down }
    private test testClassInstance;
    private Label notificationLabel;
    private Pane inputBlocker;
    private StackPane overlayPane;
    public static class Corner {
        int x, y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public void stopTimer(){
        gameTimer.stop();
    }
    public Scene getGameScene(test test, Stage m)
    {
        testClassInstance = test;
        mainStage = m;
        setUpGameScene();
        return gameScene;
    }
    private void setUpGameScene(){
        resetGame();
        qd = new QuizData();
        allQuestions = qd.getQuestions(11);
        Canvas canvas = new Canvas(width * cornerSize, height * cornerSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        HBox root;
        VBox gameSection = new VBox(10, canvas);
        gameSection.setAlignment(Pos.CENTER);
        VBox sideBar = setUpSideBar();
        root = new HBox(0,sideBar,gameSection);
        gameScene = new Scene(root, 1920, 1080);

        gameTimer = new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick(gc);
                    return;
                }

                if (now - lastTick > 1000000000 / speed) {
                    lastTick = now;
                    tick(gc);
                }
            }
        };

        gameTimer.start();

        gameScene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.W && direction != Dir.down) {
                direction = Dir.up;
            }
            if (key.getCode() == KeyCode.A && direction != Dir.right) {
                direction = Dir.left;
            }
            if (key.getCode() == KeyCode.S && direction != Dir.up) {
                direction = Dir.down;
            }
            if (key.getCode() == KeyCode.D && direction != Dir.left) {
                direction = Dir.right;
            }
        });

        mainStage.setScene(gameScene);

    }

    public static void tick(GraphicsContext gc) {
        if (gameOver) {
            gameTimer.stop(); // Stop ticking when the game ends
            showQuizWindow();
            return;
        }

        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction) {
            case up -> snake.get(0).y--;
            case down -> snake.get(0).y++;
            case left -> snake.get(0).x--;
            case right -> snake.get(0).x++;
        }

        if (snake.get(0).x < 0 || snake.get(0).y < 0 || snake.get(0).x >= width || snake.get(0).y >= height) {
            gameOver = true;
        }

        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
            }
        }

        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Corner(-1, -1));
            speed ++;
            newFood();
        }

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * cornerSize, height * cornerSize);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (snake.size() - 3), 10, 30);

        gc.setFill(Color.RED);
        gc.fillOval(foodX * cornerSize, foodY * cornerSize, cornerSize, cornerSize);

        for (Corner c : snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * cornerSize, c.y * cornerSize, cornerSize - 1, cornerSize - 1);
        }
    }

    public static void showQuizWindow() {
        Random rand = new Random();
        int r = rand.nextInt(0, allQuestions.size());
        Question q = allQuestions.get(r);
        VBox mainLayout = new VBox(30);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPrefSize(1920, 1080);

        Label questionLabel = new Label(q.getQuestion());
        questionLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(1400);

        TextArea pseudoCodeArea = new TextArea(q.getPseudoCode());
        pseudoCodeArea.setEditable(false);
        pseudoCodeArea.setWrapText(true);
        pseudoCodeArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px;");
        pseudoCodeArea.setMaxSize(1200, 200);

        if (q.getPseudoCode().isEmpty()) {
            pseudoCodeArea.setVisible(false);
            pseudoCodeArea.setManaged(false);
        }

        ToggleGroup answerGroup = new ToggleGroup();
        VBox answerBox = new VBox(15);
        answerBox.setAlignment(Pos.CENTER);

        ArrayList<String> answers = new ArrayList<>(Arrays.asList(q.getAnswers()));
        int correctIndex = q.getCorrectA();
        for (String answer : answers) {
            RadioButton radioButton = new RadioButton(answer);
            radioButton.setToggleGroup(answerGroup);
            radioButton.setStyle("-fx-font-size: 22px;");
            radioButton.setWrapText(true);
            radioButton.setMaxWidth(1100);
            answerBox.getChildren().add(radioButton);
        }
        Button submitButton = new Button();
        ImageView Button2Pic = new ImageView("/images/4SubmitGrey.png");
        ImageView Button2PicH = new ImageView("/images/4SubmitRandow.png");
        Button2Pic.setFitWidth(356);
        Button2Pic.setFitHeight(108);
        submitButton.setGraphic(Button2Pic);
        submitButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        submitButton.setOnMouseEntered(e -> submitButton.setGraphic(Button2PicH));
        submitButton.setOnMouseExited(e -> submitButton.setGraphic(Button2Pic));
        submitButton.setOnMousePressed(e -> {
            submitButton.setGraphic(Button2PicH);
            submitButton.setScaleX(0.95);
            submitButton.setScaleY(0.95);
        });
        submitButton.setOnMouseReleased(e -> {
            submitButton.setGraphic(Button2PicH);
            submitButton.setScaleX(1.0);
            submitButton.setScaleY(1.0);
        });
        submitButton.setOnAction(e -> {
            RadioButton selected = (RadioButton) answerGroup.getSelectedToggle();

            if (selected == null) {
                showAlert("No Answer Selected! Please select an answer before submitting.");
                return;
            }

            if (selected.getText().equals(answers.get(correctIndex))) {
                showAlert("Correct! Returning to the game.");
                resetGame();
            } else {
                showAlert("Incorrect! Try again.");
            }
        });

        mainLayout.getChildren().addAll(questionLabel,pseudoCodeArea, answerBox, submitButton);
        Scene quizScene = new Scene(mainLayout);

        Platform.runLater(() -> mainStage.setScene(quizScene));
    }

    private static void showAlert(String message) {
        Stage alertStage = new Stage();
        VBox alertBox = new VBox(10);
        alertBox.setAlignment(Pos.CENTER);

        Label label = new Label(message);
        Button okButton = new Button("OK");
        if(message.equalsIgnoreCase("Incorrect! Try again.")){
            okButton.setOnAction(e -> {
                alertStage.close();
            });
        }
        else{
            okButton.setOnAction(e -> {
                alertStage.close();
                returnToGameScene();
            });

        }


        alertBox.getChildren().addAll(label, okButton);

        Scene alertScene = new Scene(alertBox, 300, 150);
        alertStage.setScene(alertScene);
        alertStage.show();
    }
    private void initializeOverlay() {
    if (notificationLabel == null) {
        notificationLabel = new Label();
        notificationLabel.setStyle(
            "-fx-background-color: rgba(50, 50, 50, 0.9); " +
            "-fx-text-fill: white; " +
            "-fx-padding: 20px; " +
            "-fx-font-size: 22px; " +
            "-fx-font-weight: bold; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px;"
        );
        notificationLabel.setVisible(false);

        inputBlocker = new Pane();
        inputBlocker.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        inputBlocker.setVisible(false);
        
        overlayPane = new StackPane(notificationLabel);
        overlayPane.setAlignment(Pos.CENTER);
    }
}
private  void showNotification(String message) {
    Platform.runLater(() -> {
        notificationLabel.setText(message);
        notificationLabel.setVisible(true);
        inputBlocker.setVisible(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            notificationLabel.setVisible(false);
            inputBlocker.setVisible(false);

            if (!message.equalsIgnoreCase("Incorrect! Try again.")) {
                returnToGameScene();
            }
        });
        pause.play();
    });
}
    private static void returnToGameScene(){
        mainStage.setScene(gameScene);
        gameTimer.start();
        resetGame();

    }

    private static void resetGame() {
        gameOver = false;
        snake.clear();
        direction = Dir.left;
        speed = 7;
        snake.add(new Corner(width / 2, height / 2));
        snake.add(new Corner(width / 2, height / 2));
        snake.add(new Corner(width / 2, height / 2));
        newFood();
    }

    public static void newFood() {
        start:
        while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);
            for (Corner c : snake) {
                if (c.x == foodX && c.y == foodY) {
                    continue start;
                }
            }
            break;
        }
    }
    private VBox setUpSideBar(){
        VBox sideBar = new VBox(15);
        sideBar.setAlignment(Pos.CENTER);

        // Create buttons
        Button button1 = createHoverButton("/images/1HomeButtonGrey.png","/images/2HomeButtonColored.png" ,370, 87, "Home Button");
        Button button2 = createHoverButton("/images/1LearnButtonGrey.png", "/images/2LearnButtonColored.png", 370, 87, "Learn Button");
        Button button3 = createImageButton("/images/2MockTestButtonColored.png",  370, 87, "Mock Test Button");
        Button button4 = createHoverButton("/images/1ReviewGameButtonGrey.png", "/images/2ReviewGameColored.png", 370, 87, "Review Game Button");
        button1.setOnAction((ActionEvent actionEvent) -> {
            try {
                testClassInstance.changeScene(0);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        button2.setOnAction((ActionEvent actionEvent) -> {
            try {
                testClassInstance.changeScene(1);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        button4.setOnAction((ActionEvent actionEvent) -> {
            try {
                testClassInstance.changeScene(3);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        VBox buttonContainer = new VBox(10, button1, button2, button3, button4);
        buttonContainer.setAlignment(Pos.CENTER);

        // Sidebar Background
        System.out.println("Attempting to load sidebar background: /images/MenuBGE.png");
        BackgroundImage sidebarBg = loadBackground("/images/MenuBGE.png");
        if (sidebarBg != null) {
            System.out.println("✅ Sidebar background loaded successfully!");
            sideBar.setBackground(new Background(sidebarBg));
        } else {
            System.err.println("Failed to load sidebar background!");
        }

        sideBar.setPrefWidth(450);
        sideBar.getChildren().add(buttonContainer);
        return sideBar;
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
}

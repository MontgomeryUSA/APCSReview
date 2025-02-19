package one.jpro.hellojpro;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        ImageView Button2Pic = new ImageView("/4SubmitGrey.png");
        ImageView Button2PicH = new ImageView("/4SubmitRandow.png");
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
        ImageView Button2Pic = new ImageView("/1LearnButtonGrey.png");
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
        ImageView Button4Pic = new ImageView("/2ReviewGameColored.png");
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
                System.out.println("You're here already blud");
            }
        });

        //Button Adding to SideBar + Formatting
        VBox buttonContainer = new VBox(10, button1, button2, button3, button4);
        buttonContainer.setAlignment(Pos.CENTER);

        Image sideBarImage = new Image("/MenuBGE.png");
        BackgroundImage bgI = new BackgroundImage(sideBarImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false,true,false));
        sideBar.setBackground(new Background(bgI));

        sideBar.setPrefWidth(450);
        sideBar.getChildren().add(buttonContainer);

        return sideBar;
    }
}

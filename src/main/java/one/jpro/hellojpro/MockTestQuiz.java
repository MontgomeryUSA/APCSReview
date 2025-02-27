package one.jpro.hellojpro;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MockTestQuiz {
    private test testClassInstance;
    private Scene quizScene;
    private int questionNumber = 0;
    private int numberOfQuestions= 8;
    private ArrayList<Question> questions;
    private VBox mainLayout;
    private int attemptsLeft = 2;
    private int answersCorrect = 0;
    private Label notificationLabel;
    private Pane inputBlocker;
    private StackPane overlayPane;
    public Scene getQuizScreenAndQuestions(test test, int i) {
        testClassInstance = test;
        questions = getQuestions(i);
        setUpQuizScreenAndQuestions();
        return quizScene;
    }

    private ArrayList<Question> getQuestions(int i) {
        QuizData quizData = new QuizData();
        if(i == 11){
            numberOfQuestions = 55;
        }
        return quizData.getQuestions(i);
    }

    private void setUpQuizScreenAndQuestions() {
        mainLayout = new VBox(30);
        mainLayout.setPadding(new Insets(40));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPrefSize(1920, 1080);

    
        // Initialize overlay to block input (START HIDDEN)
        inputBlocker = new Pane();
        inputBlocker.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        inputBlocker.setVisible(false); // Ensure it starts hidden
    
        // Notification label setup
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
    
        // StackPane for overlay
        overlayPane = new StackPane(mainLayout, inputBlocker, notificationLabel);
        
        loadNextQuestion();
        quizScene = new Scene(overlayPane, 1920, 1080);
    }
    

    private void loadNextQuestion() {
        mainLayout.getChildren().retainAll(inputBlocker,notificationLabel);
        attemptsLeft = 2;

        if (questionNumber >= numberOfQuestions) {
            Label finishedLabel = new Label("Quiz Finished! You got a score of: " + answersCorrect + "/" + numberOfQuestions);
            finishedLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: green;");


            //todo replace with proper asset when that is made
            Button homeButton = new Button("Return to Quiz");
            homeButton.setStyle("-fx-font-size: 22px; -fx-background-color: lightcoral; -fx-border-radius: 10;");
            homeButton.setOnAction(e -> {
                try {
                    testClassInstance.changeScene(2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            VBox finalScreen = new VBox(30, finishedLabel, homeButton);
            finalScreen.setAlignment(Pos.CENTER);

            mainLayout.getChildren().add(finalScreen);
            return;
        }

        Question displayingQ = getRandomQuestion();
        if (displayingQ == null) return;

        Label questionLabel = new Label(displayingQ.getQuestion());
        questionLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(1400);

        TextArea pseudoCodeArea = new TextArea(displayingQ.getPseudoCode());
        pseudoCodeArea.setEditable(false);
        pseudoCodeArea.setWrapText(true);
        pseudoCodeArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px;");
        pseudoCodeArea.setMaxSize(1200, 200);

        if (displayingQ.getPseudoCode().isEmpty()) {
            pseudoCodeArea.setVisible(false);
            pseudoCodeArea.setManaged(false);
        }

        ToggleGroup answerGroup = new ToggleGroup();
        VBox answerBox = new VBox(15);
        answerBox.setAlignment(Pos.CENTER);

        ArrayList<String> answers = new ArrayList<>(Arrays.asList(displayingQ.getAnswers()));
        for (String answer : answers) {
            RadioButton radioButton = new RadioButton(answer);
            radioButton.setToggleGroup(answerGroup);
            radioButton.setStyle("-fx-font-size: 22px;");
            radioButton.setWrapText(true);
            radioButton.setMaxWidth(1100);
            answerBox.getChildren().add(radioButton);
        }

        Button submitButton = createHoverButton("/images/4SubmitGrey.png", "/images/4SubmitRandow.png", 356, 108, "Submit Button");
       
        submitButton.setOnAction(e -> handleAnswer(answerGroup, answers, displayingQ.getCorrectA()));

        VBox answerContainer = new VBox(20, answerBox, submitButton);
        answerContainer.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(questionLabel, pseudoCodeArea, answerContainer);
    }

    private void handleAnswer(ToggleGroup group, ArrayList<String> answers, int correctIndex) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();

        if (selected == null) {
            showNotification("No Answer Selected! Please select an answer before submitting.");
            return;
        }

        String selectedAnswer = selected.getText();
        String correctAnswer = answers.get(correctIndex);

        if (selectedAnswer.equals(correctAnswer)) {
            showNotification("Correct! Great job! Moving to the next question.");
            answersCorrect++;
            questionNumber++;
            loadNextQuestion();
        } else {
            if (attemptsLeft > 1) {
                attemptsLeft--;
                showNotification("Incorrect! Try again! You have " + attemptsLeft + " attempt(s) left.");
            } else {
                showNotification("Incorrect! Out of attempts! Moving to the next question.");
                questionNumber++;
                loadNextQuestion();
            }
        }
    }

    private Question getRandomQuestion() {
        if (questions.isEmpty()) {
            System.out.println("No questions available.");
            return null;
        }
        return questions.remove(new Random().nextInt(questions.size()));
    }
    private void showNotification(String message) {
        Platform.runLater(() -> {
            System.out.println("Showing notification: " + message);
            
            notificationLabel.setText(message);
            notificationLabel.setVisible(true);
            
            // Show overlay ONLY when notification is visible
            inputBlocker.setVisible(true);
    
            // Pause for 2 seconds, then hide everything
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                System.out.println("Hiding notification");
                notificationLabel.setVisible(false);
                
                // Ensure the inputBlocker disappears AFTER the notification
                inputBlocker.setVisible(false);
            });
            pause.play();
        });
    }
    

    private ImageView loadImageView(String path, int width, int height) {
        System.out.println("Attempting to load ImageView for: " + path);
        Image img = loadImage(path);
        if (img != null) {
            System.out.println("ImageView successfully loaded for: " + path);
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(width);
            imgView.setFitHeight(height);
            return imgView;
        }
        System.err.println("Failed to load ImageView for: " + path);
        return null;
    }

    // Helper: Load Image correctly for JPro
    private Image loadImage(String path) {
        System.out.println("Attempting to load image: " + path);
        
        URL imageUrl = getClass().getResource(path);
        if (imageUrl == null) {
            System.err.println("ERROR: Image not found at path: " + path);
            return null;
        }
        
        System.out.println("Image found! Loading: " + imageUrl.toExternalForm());
        return new Image(imageUrl.toExternalForm(), false); // Prevent caching issues
    }

    // Helper: Load Background Image

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
}

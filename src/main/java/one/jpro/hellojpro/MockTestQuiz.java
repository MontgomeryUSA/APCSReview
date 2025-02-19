package one.jpro.hellojpro;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MockTestQuiz {
    private test testClassInstance;
    private Scene quizScene;
    private int questionNumber = 0;
    private int numberOfQuestions= 8;
    private ArrayList<Question> questions;
    private VBox mainLayout;
    private int attemptsLeft = 2;
    private int answersCorrect = 0;

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

        loadNextQuestion();

        quizScene = new Scene(mainLayout, 1920, 1080);
    }

    private void loadNextQuestion() {
        mainLayout.getChildren().clear();
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
        submitButton.setOnAction(e -> handleAnswer(answerGroup, answers, displayingQ.getCorrectA()));

        VBox answerContainer = new VBox(20, answerBox, submitButton);
        answerContainer.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(questionLabel, pseudoCodeArea, answerContainer);
    }

    private void handleAnswer(ToggleGroup group, ArrayList<String> answers, int correctIndex) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();

        if (selected == null) {
            showAlert("No Answer Selected! Please select an answer before submitting.");
            return;
        }

        String selectedAnswer = selected.getText();
        String correctAnswer = answers.get(correctIndex);

        if (selectedAnswer.equals(correctAnswer)) {
            showAlert("Correct! Great job! Moving to the next question.");
            answersCorrect++;
            questionNumber++;
            loadNextQuestion();
        } else {
            if (attemptsLeft > 1) {
                attemptsLeft--;
                showAlert("Incorrect! Try again! You have " + attemptsLeft + " attempt(s) left.");
            } else {
                showAlert("Incorrect! Out of attempts! Moving to the next question.");
                questionNumber++;
                loadNextQuestion();
            }
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Question getRandomQuestion() {
        if (questions.isEmpty()) {
            System.out.println("No questions available.");
            return null;
        }
        return questions.remove(new Random().nextInt(questions.size()));
    }
}

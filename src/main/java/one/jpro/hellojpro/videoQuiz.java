package one.jpro.hellojpro;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class videoQuiz {
    private Timer myTimer;
    private WebView webview;
    private boolean isPaused = true;
    private ArrayList<Integer> timeIntervals = new ArrayList<>();
    private int currentIntervalIndex = 0;
    private Scene videoScene;
    private int elapsedTime = 0;
    private int attemptCount = 0;
    private Stage mainStage;
    private int currentQuestionIndex = 0;
    private test testClassInstance;
    private ArrayList<Question> questions;
    private ArrayList<Question> videoQuestions;

    private ArrayList<Question> getQuestions(int i) {
        QuizData quizData = new QuizData();
        return quizData.getQuestions(i);
    }

    public Scene getVideoScene(test test, int i, Stage mainStage) {
        this.mainStage = mainStage;
        this.testClassInstance = test;
        makeVideoScene(i);
        return videoScene;
    }
    private void setupQuestions(int i){
        videoQuestions = new ArrayList<>();
        switch(i){
            case 1->{

                videoQuestions.add(questions.get(2));
                videoQuestions.add(questions.get(6));
                videoQuestions.add(questions.get(7));

            }
            case 2->{
                videoQuestions.add(questions.get(3));
                videoQuestions.add(questions.get(1));
                videoQuestions.add(questions.get(2));
            }
            case 3->{
                //Index 1, 39 seconds
                //Index 2, 28 seconds
                videoQuestions.add(questions.get(2));
                videoQuestions.add(questions.get(1));

            }
            case 4->{
                //Index 0, 11 seconds
                //Index 1, 24 seconds
                //Index 5, 50 seconds
                //Index 7, 71 seconds
                videoQuestions.add(questions.get(0));
                videoQuestions.add(questions.get(1));
                videoQuestions.add(questions.get(5));
                videoQuestions.add(questions.get(7));
            }
            case 5->{
                //Index 7, 37 seconds
                //Index 1, 60 seconds
                //Index 2, 77 seconds
                //Index 6, 96 seconds
                videoQuestions.add(questions.get(7));
                videoQuestions.add(questions.get(1));
                videoQuestions.add(questions.get(2));
                videoQuestions.add(questions.get(6));
            }
            case 6->{
                //Index 2, 16 seconds
                //Index 4, 41 seconds
                videoQuestions.add(questions.get(2));
                videoQuestions.add(questions.get(4));

            }
            case 7->{
                //Index 0, 48 seconds
                //Index 4, 16 seconds
                videoQuestions.add(questions.get(4));
                videoQuestions.add(questions.get(0));
            }
            case 8->{
                //Index 0, 62 seconds
                //Index 1, 19 seconds
                //Index 2, 26 seconds

                videoQuestions.add(questions.get(1));
                videoQuestions.add(questions.get(2));
                videoQuestions.add(questions.get(0));

            }
            case 9->{
                //Index 1, 5 seconds
                //Index 3, 49 seconds
                //Index 6, 65 seconds
                //Index 2, 104 seconds
                videoQuestions.add(questions.get(1));
                videoQuestions.add(questions.get(3));
                videoQuestions.add(questions.get(6));
                videoQuestions.add(questions.get(2));
            }
            case 10->{
                //Index 2, 21 seconds
                //Index 1, 42 seconds
                //Index 5, 52 seconds
                //Index 3, 63 seconds
                videoQuestions.add(questions.get(2));
                videoQuestions.add(questions.get(1));
                videoQuestions.add(questions.get(5));
                videoQuestions.add(questions.get(3));
            }
        }

    }

    public void makeVideoScene(int i) {
        questions = getQuestions(i);
        setupQuestions(i);
        makeTimeIntervals(i);
        String videoURL = getVideoUrl(i);

        webview = new WebView();
        webview.getEngine().setJavaScriptEnabled(true);
        webview.getEngine().load(videoURL);

        Button playButton = new Button();
        playButton.setOnAction(e -> playVideo());
        ImageView Button2Pic = new ImageView("/4PlayGrey.png");
        ImageView Button2PicH = new ImageView("/4PlayRainbow.png");
        Button2Pic.setFitWidth(356);
        Button2Pic.setFitHeight(108);
        playButton.setGraphic(Button2Pic);
        playButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        playButton.setOnMouseEntered(e -> playButton.setGraphic(Button2PicH));
        playButton.setOnMouseExited(e -> playButton.setGraphic(Button2Pic));
        playButton.setOnMousePressed(e -> {
            playButton.setGraphic(Button2PicH);
            playButton.setScaleX(0.95);
            playButton.setScaleY(0.95);
        });
        playButton.setOnMouseReleased(e -> {
            playButton.setGraphic(Button2PicH);
            playButton.setScaleX(1.0);
            playButton.setScaleY(1.0);
        });
        Button returnButton = new Button();
        returnButton.setOnAction(e -> {
            try {
                testClassInstance.changeScene(1);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            endVideo();
        });
        ImageView Button3Pic = new ImageView("/4ReviewGrey.png");
        ImageView Button3PicH = new ImageView("/4ReviewRainbow.png");
        Button3Pic.setFitWidth(356);
        Button3Pic.setFitHeight(108);
        returnButton.setGraphic(Button3Pic);
        returnButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        returnButton.setOnMouseEntered(e -> returnButton.setGraphic(Button3PicH));
        returnButton.setOnMouseExited(e -> returnButton.setGraphic(Button3Pic));
        returnButton.setOnMousePressed(e -> {
            returnButton.setGraphic(Button3PicH);
            returnButton.setScaleX(0.95);
            returnButton.setScaleY(0.95);
        });
        returnButton.setOnMouseReleased(e -> {
            returnButton.setGraphic(Button3PicH);
            returnButton.setScaleX(1.0);
            returnButton.setScaleY(1.0);
        });

        StackPane videoStackPane = new StackPane(webview);
        videoStackPane.setPrefSize(1400, 800);
        videoStackPane.setMaxSize(1400, 800);

        VBox mainLayout = new VBox(20, videoStackPane, playButton,returnButton); //todo replace play button with graphic to match the rest of the stuff
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        videoScene = new Scene(mainLayout, 1920, 1080);
    }
    private void endVideo(){
        webview.getEngine().executeScript("document.querySelector('button.ytp-play-button').click()");
        myTimer.cancel();
    }
    private void showQuizWindow() {
        if (myTimer != null) {
            myTimer.cancel();
            myTimer = null;
        }

        String currentQuestion = videoQuestions.get(currentQuestionIndex).getQuestion();
        VBox mainLayout = new VBox(30);
        mainLayout.setPadding(new Insets(40));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPrefSize(1920, 1080);

        Label questionLabel = new Label(currentQuestion);
        questionLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(1400);

        TextArea pseudoCodeArea = new TextArea(videoQuestions.get(currentQuestionIndex).getPseudoCode());
        pseudoCodeArea.setEditable(false);
        pseudoCodeArea.setWrapText(true);
        pseudoCodeArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px;");
        pseudoCodeArea.setMaxSize(1200, 200);

        if (videoQuestions.get(currentQuestionIndex).getPseudoCode().isEmpty()) {
            pseudoCodeArea.setVisible(false);
            pseudoCodeArea.setManaged(false);
        }

        ToggleGroup answerGroup = new ToggleGroup();
        VBox answerBox = new VBox(15);
        answerBox.setAlignment(Pos.CENTER);

        ArrayList<String> answers = new ArrayList<>(Arrays.asList(videoQuestions.get(currentQuestionIndex).getAnswers()));
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
        submitButton.setOnAction(e -> handleAnswer(answerGroup, answers, videoQuestions.get(currentQuestionIndex).getCorrectA()));

        VBox answerContainer = new VBox(20, answerBox, submitButton);
        answerContainer.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(questionLabel, pseudoCodeArea, answerContainer);
        Scene quizScene = new Scene(mainLayout, 1920, 1080);

        Platform.runLater(() -> mainStage.setScene(quizScene));
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
            showAlert("Correct! Let’s continue learning.");
            attemptCount = 0;
            currentQuestionIndex++;
            Platform.runLater(() -> {
                mainStage.setScene(videoScene);
                webview.getEngine().executeScript("document.querySelector('button.ytp-play-button').click()");
                startTimer(timeIntervals.get(currentIntervalIndex));
            });
        } else {
            if (attemptCount < 2) {
                attemptCount++;
                showAlert("Incorrect! Try again! You have " + (3 - attemptCount) + " attempt(s) left.");
            } else {
                showAlert("Incorrect! Out of attempts! Returning to the lesson.");
                attemptCount = 0;
                currentQuestionIndex++;
                Platform.runLater(() -> {
                    mainStage.setScene(videoScene);
                    webview.getEngine().executeScript("document.querySelector('button.ytp-play-button').click()");
                    startTimer(timeIntervals.get(currentIntervalIndex));
                });
            }
        }
    }

    private String getVideoUrl(int i) {
        //todo update with actual video urls
        return switch (i) {
            case 1 -> "https://www.youtube.com/embed/yuCJ_1AVEK0?si=hOv1qJ9dadmGFJ1T";
            case 2 -> "https://www.youtube.com/embed/STE0dixYf80?si=nZAdMidGdWR86sCN";
            case 3 -> "https://www.youtube.com/embed/DvqopqfZ76o?si=eroK4zNu6yCJSQKE";
            case 4 -> "https://www.youtube.com/embed/SlnfxGur3fc?si=bJYRjQL36Odl43iT";
            case 5 -> "https://www.youtube.com/embed/_RsP-LJZ90Y?si=kTGAvvBYsw1Lf0tV";
            case 6 -> "https://www.youtube.com/embed/xoY2dpDggvI?si=UogX-tghbPqdc-wa";
            case 7 -> "https://www.youtube.com/embed/QObblWdtG8o?si=YXCUAJxYqFjFSgwC";
            case 8 -> "https://www.youtube.com/embed/zWKuhN2nlfU?si=xjp0hyKvr8JNcqwu";
            case 9 -> "https://www.youtube.com/embed/Gt_og7w6isA?si=LG9VIvVU8-8bhsh2";
            case 10 -> "https://www.youtube.com/embed/H3h4CnLIVhQ?si=2vlV8wXDOLFNmcUk";

            default -> "";
        };
    }

    private void makeTimeIntervals(int i) {
        //todo update this with the rest of the time intervals, depending on the videos
        switch(i){
            case 1 -> {
                timeIntervals.add(60);
                timeIntervals.add(28);
                timeIntervals.add(18);

            }
            case 2 ->{
                timeIntervals.add(25);
                timeIntervals.add(15);
                timeIntervals.add(35);
            }
            case 3 -> {
                timeIntervals.add(28);
                timeIntervals.add(11);

            }
            case 4 ->{
                timeIntervals.add(11);
                timeIntervals.add(13);
                timeIntervals.add(26);
                timeIntervals.add(21);
            }
            case 5 -> {
                timeIntervals.add(37);
                timeIntervals.add(23);
                timeIntervals.add(17);
                timeIntervals.add(19);

            }
            case 6 ->{
                timeIntervals.add(16);
                timeIntervals.add(25);
            }
            case 7 -> {
                timeIntervals.add(16);
                timeIntervals.add(32);

            }
            case 8 ->{
                timeIntervals.add(19);
                timeIntervals.add(7);
                timeIntervals.add(36);
            }
            case 9 -> {
                timeIntervals.add(5);
                timeIntervals.add(44);
                timeIntervals.add(16);
                timeIntervals.add(39);
            }
            case 10 ->{
                timeIntervals.add(21);
                timeIntervals.add(21);
                timeIntervals.add(10);
                timeIntervals.add(11);
            }



        }

    }

    private void playVideo() {
        if (isPaused) {
            webview.getEngine().executeScript("document.querySelector('button.ytp-play-button').click()");
            isPaused = false;
            startTimer(timeIntervals.get(currentIntervalIndex));
        }
    }

    private void startTimer(int interval) {
        if (myTimer != null) {
            myTimer.cancel();
        }

        elapsedTime = 0;

        myTimer = new Timer();
        myTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                elapsedTime++;
                if (elapsedTime >= interval) {
                    pauseVideo();
                    currentIntervalIndex++;
                    if (currentIntervalIndex < timeIntervals.size()) {
                        startTimer(timeIntervals.get(currentIntervalIndex));
                    }
                }
            }
        }, 1000, 1000);
    }

    private void pauseVideo() {
        Platform.runLater(() -> {
            webview.getEngine().executeScript("document.querySelector('button.ytp-play-button').click()");
            isPaused = true;

            if (myTimer != null) {
                myTimer.cancel();
                myTimer = null;
            }

            if (currentQuestionIndex < videoQuestions.size()) {
                showQuizWindow();
            } else {
                Platform.runLater(() -> {
                    mainStage.setScene(videoScene);
                    webview.getEngine().executeScript("document.querySelector('button.ytp-play-button').click()");
                    startTimer(timeIntervals.get(currentIntervalIndex));
                });
            }
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
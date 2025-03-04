package one.jpro.hellojpro;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.jpro.webapi.HTMLView;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class videoQuiz {
    private Timer myTimer;
    private boolean isPaused = true;
    private int currentIntervalIndex = 0;
    private int elapsedTime = 0;
    private Stage mainStage;
    private Scene videoScene;
    private test testClassInstance;
    private HTMLView htmlView;
    private List<Integer> timeIntervals = new ArrayList<>();
    private List<Question> videoQuestions = new ArrayList<>();
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int attemptCount = 0;
    private ArrayList<Question> getQuestions(int i) {
        QuizData quizData = new QuizData();
        return quizData.getQuestions(i);
    }
    
    public Scene getVideoScene(test thisClassInstance, int i, Stage mainStage) {
        this.mainStage = mainStage;
        this.testClassInstance = thisClassInstance;
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
        String videoURL = getEmbedUrl(i);

        // HTML with embedded JavaScript
        String htmlContent = """
            <html>
                <body style="margin: 0; padding: 0; text-align: center;">
                    <iframe id="yt-player" width="1400" height="800" 
                        src="%s" 
                        frameborder="0" allowfullscreen>
                    </iframe>
                    <script>
                        function sendCommand(command) {
                            let iframe = document.getElementById('yt-player');
                            if (iframe && iframe.contentWindow) {
                                iframe.contentWindow.postMessage(
                                    '{"event":"command","func":"' + command + '","args":""}', '*'
                                );
                            }
                        }
                    </script>
                </body>
            </html>
            """.formatted(videoURL);

        htmlView = new HTMLView();
        htmlView.setContent(htmlContent); // Load the HTML

        // Play button
        Button playButton = createStyledButton("/images/4PlayGrey.png", "/images/4PlayRainbow.png", this::playVideo);
        
        // Pause button
        Button pauseButton = createStyledButton("/images/4PauseGrey.png", "/images/4PauseRainbow.png", this::pauseVideo);

        // Return button
        Button returnButton = createStyledButton("/images/4ReviewGrey.png", "/images/4ReviewRainbow.png", () -> {
            try {
                testClassInstance.changeScene(1);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            endVideo();
        });

        StackPane videoStackPane = new StackPane(htmlView);
        videoStackPane.setPrefSize(1400, 800);

        VBox mainLayout = new VBox(20, videoStackPane, playButton, pauseButton, returnButton);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        videoScene = new Scene(mainLayout, 1920, 1080);

        startTimer(timeIntervals.get(0));
    }
    private void endVideo() {
        if (myTimer != null) {
            myTimer.cancel();
        }
    }

    private void sendJavaScriptCommand(String command) {
        htmlView.setContent(htmlView.getContent().replace("</script>",
            "sendCommand('" + command + "');</script>"));
    }

    private void playVideo() {
        if (isPaused) {
            sendJavaScriptCommand("playVideo");
            isPaused = false;
        }
    }

    private void pauseVideo() {
        if (!isPaused) {
            sendJavaScriptCommand("pauseVideo");
            isPaused = true;
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
                    Platform.runLater(() -> {
                        pauseVideo();
                        showQuizWindow();
                    });
                    cancel();
                }
            }
        }, 1000, 1000);
    }
    private String getEmbedUrl(int i) {
        return switch (i) {
            case 1 -> "https://www.youtube.com/embed/yuCJ_1AVEK0?enablejsapi=1";
            case 2 -> "https://www.youtube.com/embed/STE0dixYf80?enablejsapi=1";
            case 3 -> "https://www.youtube.com/embed/DvqopqfZ76o?enablejsapi=1";
            case 4 -> "https://www.youtube.com/embed/SlnfxGur3fc?enablejsapi=1";
            case 5 -> "https://www.youtube.com/embed/_RsP-LJZ90Y?enablejsapi=1";
            case 6 -> "https://www.youtube.com/embed/xoY2dpDggvI?enablejsapi=1";
            case 7 -> "https://www.youtube.com/embed/QObblWdtG8o?enablejsapi=1";
            case 8 -> "https://www.youtube.com/embed/zWKuhN2nlfU?enablejsapi=1";
            case 9 -> "https://www.youtube.com/embed/Gt_og7w6isA?enablejsapi=1";
            case 10 -> "https://www.youtube.com/embed/H3h4CnLIVhQ?enablejsapi=1";
            default -> "";
        };
    }

    private void makeTimeIntervals(int i) {
        switch (i) {
            case 1 -> timeIntervals.addAll(Arrays.asList(60, 28, 18));
            case 2 -> timeIntervals.addAll(Arrays.asList(25, 15, 35));
            case 3 -> timeIntervals.addAll(Arrays.asList(28, 11));
            case 4 -> timeIntervals.addAll(Arrays.asList(11, 13, 26, 21));
            case 5 -> timeIntervals.addAll(Arrays.asList(37, 23, 17, 19));
            case 6 -> timeIntervals.addAll(Arrays.asList(16, 25));
            case 7 -> timeIntervals.addAll(Arrays.asList(16, 32));
            case 8 -> timeIntervals.addAll(Arrays.asList(19, 7, 36));
            case 9 -> timeIntervals.addAll(Arrays.asList(5, 44, 16, 39));
            case 10 -> timeIntervals.addAll(Arrays.asList(21, 21, 10, 11));
        }
    }

    private void showQuizWindow() {
        if (currentQuestionIndex >= videoQuestions.size()) {
            return;
        }

        String currentQuestion = videoQuestions.get(currentQuestionIndex).getQuestion();
        VBox mainLayout = new VBox(30);
        mainLayout.setPadding(new Insets(40));
        mainLayout.setAlignment(Pos.CENTER);

        Label questionLabel = new Label(currentQuestion);
        questionLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        ToggleGroup answerGroup = new ToggleGroup();
        VBox answerBox = new VBox(15);
        answerBox.setAlignment(Pos.CENTER);

        List<String> answers = Arrays.asList(videoQuestions.get(currentQuestionIndex).getAnswers());
        for (String answer : answers) {
            javafx.scene.control.RadioButton radioButton = new javafx.scene.control.RadioButton(answer);
            radioButton.setToggleGroup(answerGroup);
            radioButton.setStyle("-fx-font-size: 22px;");
            answerBox.getChildren().add(radioButton);
        }

        Button submitButton = createStyledButton("/images/4SubmitGrey.png", "/images/4SubmitRainbow.png", () -> handleAnswer(answerGroup, answers, videoQuestions.get(currentQuestionIndex).getCorrectA()));

        VBox answerContainer = new VBox(20, answerBox, submitButton);
        answerContainer.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(questionLabel, answerContainer);
        Scene quizScene = new Scene(mainLayout, 1920, 1080);

        Platform.runLater(() -> mainStage.setScene(quizScene));
    }

    private void handleAnswer(ToggleGroup group, List<String> answers, int correctIndex) {
        javafx.scene.control.RadioButton selected = (javafx.scene.control.RadioButton) group.getSelectedToggle();
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
                playVideo();
                if (currentIntervalIndex < timeIntervals.size() - 1) {
                    currentIntervalIndex++;
                    startTimer(timeIntervals.get(currentIntervalIndex));
                }
            });
        } else {
            attemptCount++;
            showAlert("Incorrect! Try again! Attempts left: " + (3 - attemptCount));
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private Button createStyledButton(String imagePath, String hoverPath, Runnable action) {
        Button button = new Button();
        ImageView image = new ImageView(imagePath);
        ImageView hoverImage = new ImageView(hoverPath);

        image.setFitWidth(356);
        image.setFitHeight(108);
        button.setGraphic(image);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        
        button.setOnMouseEntered(e -> button.setGraphic(hoverImage));
        button.setOnMouseExited(e -> button.setGraphic(image));
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
        button.setOnAction(e -> action.run());

        return button;
    }


}
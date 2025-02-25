package one.jpro.hellojpro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class FRQStuff {

    private int currentQuestion;
    private final int totalQuestions = 2;
    private Label questionLabel;
    private Grader grader;
    private FRQCodeBase codeBase;
    private test testClassInstance;
    private Scene FRQScene;
    public Scene getFRQScene(test test){
        testClassInstance = test;
        getQ();
        makeFRQScene();
        return FRQScene;
    }
    private void getQ(){
        Random rand = new Random();
        currentQuestion = rand.nextInt(1,totalQuestions+1);
    }
    private void makeFRQScene() {
        grader = new Grader();
        codeBase = new FRQCodeBase();

        VBox textMoverV = new VBox(30);
        HBox textMoverH = new HBox(30);
        textMoverV.setPadding(new Insets(50));
        textMoverH.setPadding(new Insets(100));

        TextArea codeEditor = new TextArea();
        codeEditor.setPromptText("Write your Java code here...");
        codeEditor.setPrefSize(450, 350);


        Button runButton = new Button();
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefSize(450, 350);
        textMoverH.setAlignment(Pos.CENTER);
        textMoverV.setAlignment(Pos.CENTER);

        TextArea Question = new TextArea();
        Question.setEditable(false);
        if(currentQuestion == 1){
            Question.setText("Write the eatFood method which takes in the integer foodNum and eats the foodNum the food, increasing the totalFats, totalProtiens, and totalCarbs accordingly and using the numCarbs numProtiens and numFats.\n" +
                    "\n" +
                    "For example if totalFats=0,totatCarbs=0,totatProtiens=0, and foodNum=3 the following may happen;\n" +
                    "numCarbs(3) returns 10\n" +
                    "numFats(3) returns 21\n" +
                    "numProtiens(3) returns 12\n" +
                    "Then\n" +
                    "totalCarbs is set to 10\n" +
                    "totalFat is set to 21\n" +
                    "totalProtiens is set to 12\n" +
                    "\n" +
                    "if totalFats=64,totatCarbs=92,totatProtiens=41, and foodNum=5 the following may happen;\n" +
                    "numCarbs(5) returns 19\n" +
                    "numFats(5) returns 5\n" +
                    "numProtiens(5) returns 42\n" +
                    "Then\n" +
                    "totalCarbs is set to 83\n" +
                    "totalFat is set to 113\n" +
                    "totalProtiens is set to 83\n" +
                    "(b)\n" +
                    "Write the eatUntilTarget method which takes a list of foods, the targetFats, targetCarbs, and targetProtiens as inputs,eats each food in the list until all targets are reach using the eatFood() method implement in part (a) and returns the number of foods needed(in order) to reach all those targets. If the targets cannot be reached then return -1.\n" +
                    "\n" +
                    "For example, if foodList={2,1,4,5}, totalCarbs=30, totalFats=20, totalProtiens=40, targetCarbs=43,targetFats=32,targetProteins=33\n" +
                    "The following may happen\n" +
                    "Food 2 is eaten\n" +
                    "totalCarbs now equals=43\n" +
                    "totalFats now equals=31\n" +
                    "totalProtiens now equals=42\n" +
                    "\n" +
                    "Food 1 is eaten\n" +
                    "totalCarbs now equals=43\n" +
                    "totalFats now equals=36\n" +
                    "totalProtiens now equals=54\n" +
                    "\n" +
                    "2 is returned\n" +
                    "\n" +
                    "if foodList={2,1,3,6}, totalCarbs=30, totalFats=20, totalProteins=40, targetCarbs=43,targetFats=32,targetProiens=330\n" +
                    "The following may happen\n" +
                    "Food 2 is eaten\n" +
                    "totalCarbs now equals=43\n" +
                    "totalFats now equals=31\n" +
                    "totalProtiens now equals=42\n" +
                    "\n" +
                    "Food 1 is eaten\n" +
                    "totalCarbs now equals=43\n" +
                    "totalFats now equals=36\n" +
                    "totalProtiens now equals=54\n" +
                    "\n" +
                    "Food 3 is eaten\n" +
                    "totalCarbs now equals=162\n" +
                    "totalFats now equals=106\n" +
                    "totalProtiens now equals=134\n" +
                    "\n" +
                    "Food 6 is eaten\n" +
                    "totalCarbs now equals=202\n" +
                    "totalFats now equals=146\n" +
                    "totalProtiens now equals=329\n" +
                    "\n" +
                    "-1 is returned because protein target is never reached\n");
        } else if (currentQuestion ==2) {
            Question.setText(" This question involves an automated email system, which is represented by the mailingList class. You will write the complete mailingList class which contains a constructor and two methods.\n" +
                    "The mailingList class constructor has two parameters. The first parameter is a String which contains each student’s first name, last name, and student ID. The second parameter is an Integer which how many digits of the ID each email will include.\n" +
                    "The first name, last name, and student ID of each student will be delimited by a forward slash(“/”) and each student will be delimited by a dash(“-”). \n" +
                    "For example “a/b/123-c/d/456”\n" +
                    "The numberOfPeople method returns the number of people in the mailing list. In the previous example, it would print 2, as there are 2 people on the mailing list.\n" +
                    "\n" +
                    "\n" +
                    "The convertToEmail method returns a String containing the email addresses of the students separated by commas (,) or returns null if the message if there are no students. Each email is composed of a student's FirstName + “.” + LastName + “.” + The first n digits of the Student Defined by the constructor parameter + “@k12.friscoisd.org”.\n" +
                    "\n" +
                    "\n" +
                    "For, example\n" +
                    "A string \n" +
                    "“Naail/Khan/101010-Sid/Barla/3404325-Julian/Montgomery/9562”\n" +
                    "Would turn into the mailing list\n" +
                    "“Naail.Khan.101@k12.friscoisd.org,Sid.Barla.340@k12.friscoisd.org,Juilan.Montgomery.956@k12.friscoisd.org”\n" +
                    "If the number of digits per email is 3\n" +
                    "And\n" +
                    "“Naail.Khan.1010@k12.friscoisd.org,Sid.Barla.3404@k12.friscoisd.org,Juilan.Montgomery.9562@k12.friscoisd.org”\n" +
                    "If the number of digits per email is 4\n");

        }

        Question.setPrefSize(150, 250);
        //=============== Pseudo Code ===================
        TextArea baseCode = new TextArea();
        baseCode.setEditable(false);
        if(currentQuestion == 1){
            baseCode.setText("public class CalorieCounter{\n" +
                    "\tint totalCarbs;\n" +
                    "int totalFats;\n" +
                    "int totalProteins;\n" +
                    "/* Precondition:\n" +
                    "totalCarbs,totalFats,totalProtiens are all initialized and>=0*/\n" +
                    "/* Finds the number of carbs in the foodNumth food */\n" +
                    "public int numCarbs(int foodNum){\n" +
                    "/*implementation not shown*/\n" +
                    "}\n" +
                    "/* Finds the number of Proteins in the foodNumth food */\n" +
                    "public int numProteins(int foodNum){\n" +
                    "/*implementation not shown*/\n" +
                    "}\n" +
                    "/* Finds the number of fats in the foodNumth food */\n" +
                    "public int numFats(int foodNum){\n" +
                    "/*implementation not shown*/\n" +
                    "}\n" +
                    "/* Eats the foodNumth food and takes in its nutrients\n" +
                    "as described in part (a)\n" +
                    "Post-Condition:totalCarbs,totalFats,totalProtiens are all >=0\n" +
                    "*/\n" +
                    "public void eatFood(int foodNum){\n" +
                    "/*to be implemented in part (a)*/\n" +
                    "}\n" +
                    "/* Eats each food in foodList and takes in its nutrients(using the eatFood method) until target fats, carbs and proteins are reached\n" +
                    "as described in part (b)\n" +
                    "Post-Condition:totalCarbs,totalFats,totalProtiens are all >=0\n" +
                    "*/\n" +
                    "public int eatUntilTarget(int[] foodList, targetFats, targetCarbs, targetFats){\n" +
                    "/*to be implemented in part b*/\n" +
                    "}\n" +
                    "}\n");
        } else if (currentQuestion == 2) {
            baseCode.setText("None Provided");
        }

        baseCode.setPrefSize(150, 250);

        ImageView Button2Pic = new ImageView("/4RunGrey.png");
        ImageView Button2PicH = new ImageView("/4RunRainbow.png");
        Button2Pic.setFitWidth(142);
        Button2Pic.setFitHeight(43);
        Button2PicH.setFitWidth(142);
        Button2PicH.setFitHeight(43);
        runButton.setGraphic(Button2Pic);
        runButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        runButton.setOnMouseEntered(e -> runButton.setGraphic(Button2PicH));
        runButton.setOnMouseExited(e -> runButton.setGraphic(Button2Pic));
        runButton.setOnMousePressed(e -> {
            runButton.setGraphic(Button2PicH);
            runButton.setScaleX(0.95);
            runButton.setScaleY(0.95);
        });
        runButton.setOnMouseReleased(e -> {
            runButton.setGraphic(Button2PicH);
            runButton.setScaleX(1.0);
            runButton.setScaleY(1.0);
        });

        runButton.setOnAction(e -> {
                    codeBase.setCurrentQuestion(currentQuestion);
                    String userCode = codeEditor.getText();
                    String fullCode;
                    if (currentQuestion == 1) {
                        // Wrap user code inside the provided class scaffolding
                        fullCode = codeBase.getFRQCProvidedCode() + "\n" + userCode + "\n" + codeBase.getClassClosingBracket();
                    } else if (currentQuestion == 2) {
                        // Assume user wrote a complete class for Question 2
                        fullCode = userCode;
                    } else {
                        outputArea.setText("Invalid question setup.");
                        return;
                    }

                    CompilerTest compilerTest = new CompilerTest(codeBase);
                    try {
                        String programOutput = compilerTest.CompileAndRunTest(fullCode);
                        if (programOutput.startsWith("Compilation Error:")) {
                            outputArea.setText("Compilation Error:\n" + programOutput + "\nCode Submitted:\n" + fullCode);
                            return;
                        }
                        String feedback = grader.grade(userCode, programOutput, currentQuestion);
                        outputArea.setText(feedback);
                    } catch (Exception ex) {
                        outputArea.setText("Error during execution: " + ex.getMessage());
                    }
               });
        textMoverV.getChildren().addAll(Question, baseCode, textMoverH);
        textMoverH.getChildren().addAll(codeEditor, runButton, outputArea);

        VBox sideBar = setUpSideBar();
        HBox root = new HBox(0,sideBar, textMoverV);


        FRQScene = new Scene(root,1920,1080);
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
        ImageView button3Pic = new ImageView("/2MockTestButtonColored.png");
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
        BackgroundImage bgI = new BackgroundImage(sideBarImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false,true,false));
        sideBar.setBackground(new Background(bgI));

        sideBar.setPrefWidth(450);
        sideBar.getChildren().add(buttonContainer);

        return sideBar;
    }

}
package one.jpro.hellojpro;

import java.io.FileNotFoundException;

import com.jpro.webapi.JProApplication;

import javafx.stage.Stage;

public class test extends JProApplication {

    private Stage mainStage;
    private snakeScene gameInstance;
    private test thisClassInstance;
    private homePage homePageInstance;
    private MockTestButtons mockTestInstance;
    private MockTestQuiz mockTestQuizInstance;
    private LearnButtons learnButtonsInstance;
    //private videoQuiz videoQuizInstance;
    private FRQStuff frqStuffInstance;
    //todo replace when quiz class is setup right, use as a backend way to handle different quizzes and class updates, keep UI seperated into UI classes
    @Override
    public void start(Stage primaryStage) {
        thisClassInstance = this;
        this.mainStage = primaryStage;
        homePageInstance = new homePage();
        mainStage.setScene(homePageInstance.getHomePageScene(thisClassInstance));
        mainStage.show();
        mainStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void changeScene(int i) throws FileNotFoundException {
        switch (i){
            case 0 -> {
                homePageInstance = new homePage();
                mainStage.setScene(homePageInstance.getHomePageScene(thisClassInstance));
                if(gameInstance !=null){
                    gameInstance.stopTimer();
                }
                removeInstance(i);
            }
            case 1 -> {
                learnButtonsInstance = new LearnButtons();
                mainStage.setScene(learnButtonsInstance.getLearnButtons(thisClassInstance));
                if(gameInstance !=null){
                    gameInstance.stopTimer();
                }
                removeInstance(i);
            }
            case 2 -> {
                mockTestInstance = new MockTestButtons();
                mainStage.setScene(mockTestInstance.getMockTestButtons(thisClassInstance));
                if(gameInstance !=null){
                    gameInstance.stopTimer();
                }
                removeInstance(i);
            }
            case 3 -> {

                gameInstance = new snakeScene();
                mainStage.setScene(gameInstance.getGameScene(thisClassInstance,mainStage));
                removeInstance(i);
            }
        }
    }
    public void removeInstance(int i){
        switch(i){
            case 0 ->{
                learnButtonsInstance = null;
                gameInstance = null;
                mockTestInstance = null;
                frqStuffInstance = null;
                //videoQuizInstance = null;
            }
            case 1 ->{
                homePageInstance = null;
                mockTestInstance = null;
                gameInstance = null;
                frqStuffInstance = null;
                //videoQuizInstance = null;
            }
            case 2 ->{
                learnButtonsInstance = null;
                gameInstance = null;
                homePageInstance = null;
                frqStuffInstance = null;
                //videoQuizInstance = null;
            }
            case 3 ->{
                homePageInstance = null;
                mockTestInstance = null;
                frqStuffInstance = null;
                //videoQuizInstance = null;
                learnButtonsInstance = null;
            }
            case 5->{
                homePageInstance = null;
                mockTestInstance = null;
                learnButtonsInstance = null;
                gameInstance = null;
                frqStuffInstance = null;
                //videoQuizInstance = null;
            }

        }
    }
    public void setSceneForQuizzes(int i){
        switch (i){
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11 ->{
                mockTestQuizInstance = new MockTestQuiz();
                mainStage.setScene(mockTestQuizInstance.getQuizScreenAndQuestions(thisClassInstance,i));
            }
            case 12 ->{
                frqStuffInstance = new FRQStuff();
                mainStage.setScene(frqStuffInstance.getFRQScene(thisClassInstance));
            }


        }
    }
    //public void setSceneForVideo(int i) {
        //switch (i) {
            //case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> {
                //videoQuizInstance = new videoQuiz();
                //mainStage.setScene(videoQuizInstance.getVideoScene(thisClassInstance, i, mainStage));
            //}
        //}
    //}
}
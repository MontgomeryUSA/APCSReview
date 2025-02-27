package one.jpro.hellojpro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FRQCodeBase {
    private int currentQuestion = 1;

    public void setCurrentQuestion(int questionNumber) {
        this.currentQuestion = questionNumber;
    }

    public int getCurrentQuestion() {
        return this.currentQuestion;
    }

    public String getFRQCProvidedCode() {
        switch (currentQuestion) {
            case 1:
                return getCalorieCounterCode();
            case 2:
                return ""; 
            default:
                return "// No provided code for this question.\n";
        }
    }

    public String getClassClosingBracket() {
        switch (currentQuestion) {
            case 1:
                return getCalorieCounterClosing();
            case 2:
                return getMailingListClosing();
            default:
                return "// No closing bracket for this question.\n";
        }
    }

    public boolean userWroteFullClass(String userCode) {
        Pattern classPattern = Pattern.compile("(?i)\\bpublic\\s+class\\s+mailingList\\b");
        Matcher matcher = classPattern.matcher(userCode);
        return matcher.find();
    }

    private String getCalorieCounterCode() {
        return """
            public class CalorieCounter {
                private int totalCarbs = 0;
                private int totalFats = 0;
                private int totalProteins = 0;

                private int[][] foodData = {
                    {0, 0, 0},  
                    {10, 5, 8}, 
                    {13, 11, 12}, 
                    {34, 20, 15}, 
                    {40, 25, 30}, 
                    {19, 5, 42}, 
                    {40, 40, 100} 
                };

                public int numCarbs(int foodNum) {
                    return foodData[foodNum][0];
                }

                public int numProteins(int foodNum) {
                    return foodData[foodNum][2];
                }

                public int numFats(int foodNum) {
                    return foodData[foodNum][1];
                }
            """;
    }

    private String getCalorieCounterClosing() {
        return """
                public static void main(String[] args) {
                    CalorieCounter counter = new CalorieCounter();
                    int[] testFoods = {2, 1, 4};
                    int result = counter.eatUntilTarget(testFoods, 30, 40, 20);
                    System.out.println("Result: " + result);
                }
            }
            """;
    }

    private String getMailingListClosing() {
        return """
            class TestMailingList {  
                public static void main(String[] args) {
                    mailingList list1 = new mailingList("a/b/123-c/d/4567", 2);
                    System.out.println("Result: " + list1.numberOfPeople()); 
                    System.out.println("Result: " + list1.convertToEmail()); 

                    mailingList list2 = new mailingList("g/h/987654321", 7);
                    System.out.println("Result: " + list2.numberOfPeople()); 
                    System.out.println("Result: " + list2.convertToEmail()); 

                    mailingList list3 = new mailingList("", 3);
                    System.out.println("Result: " + list3.numberOfPeople()); 
                    System.out.println("Result: " + list3.convertToEmail()); 
                }
            }
            """;
    }
}

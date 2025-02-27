package one.jpro.hellojpro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grader {
    public String grade(String userCode, String programOutput, int i) {
        switch (i) {
            case 1 -> {
                int score = 9;
                StringBuilder feedback = new StringBuilder();
                if (!(userCode.contains("numCarbs(") && userCode.contains("numProteins(") && userCode.contains("numFats("))) {
                    score -= 2;
                    feedback.append("- Your code must call numCarbs, numProteins, and numFats.\n");
                }

                if (!(userCode.contains("totalCarbs +=") && userCode.contains("totalFats +=") && userCode.contains("totalProteins +="))) {
                    score -= 2;
                    feedback.append("- Your eatFood method must correctly update total macros.\n");
                }

                Pattern loopPattern = Pattern.compile("public\\s+int\\s+eatUntilTarget\\s*\\(.*\\)\\s*\\{[^}]*\\b(for|while)\\b", Pattern.DOTALL);
                if (!loopPattern.matcher(userCode).find()) {
                    score -= 1;
                    feedback.append("- Your eatUntilTarget method must loop through foodList.\n");
                }

                Pattern eatFoodPattern = Pattern.compile("\\b(for|while)\\s*\\(.*\\)\\s*\\{[^}]*\\b(eatFood)\\s*\\(", Pattern.DOTALL);
                if (!eatFoodPattern.matcher(userCode).find()) {
                    score -= 1;
                    feedback.append("- Your eatUntilTarget method should call eatFood inside the loop.\n");
                }

                Pattern resultPattern = Pattern.compile("Result:\\s*(-?\\d+)");
                Matcher resultMatcher = resultPattern.matcher(programOutput);
                if (resultMatcher.find()) {
                    int result = Integer.parseInt(resultMatcher.group(1));
                    if (result <= 0) {
                        score -= 1;
                        feedback.append("- Your eatUntilTarget method returned incorrect results.\n");
                    }
                } else {
                    score -= 1;
                    feedback.append("- Your program output is invalid.\n");
                }

                if (!userCode.contains("return -1;")) {
                    score -= 1;
                    feedback.append("- Your method should return -1 when targets are unreachable.\n");
                }

                feedback.append("\nFinal Score: ").append(score).append("/9\n");
                if (score == 9) {
                    feedback.append("Perfect! Your solution is correct.\n");
                } else {
                    feedback.append("Review the comments above and try again.\n");
                }

                return feedback.toString();
            }
            case 2 -> {
                int score = 9;
                StringBuilder feedback = new StringBuilder();

                String normalizedCode = userCode.toLowerCase();
                if (!normalizedCode.contains("public class mailinglist")) {
                    score -= 1;
                    feedback.append("- Your class must be named 'mailingList'.\n");
                }

                if (!userCode.contains("private String studentData") || !userCode.contains("private int emailDigits")) {
                    score -= 1;
                    feedback.append("- Your instance variables must be declared correctly.\n");
                }

                if (!userCode.contains("public mailingList(String") || !userCode.contains("int")) {
                    score -= 1;
                    feedback.append("- Your constructor header must be 'public mailingList(String, int)'.\n");
                }

                if (!userCode.contains("public int numberOfPeople()") || !userCode.contains("public String convertToEmail()")) {
                    score -= 1;
                    feedback.append("- Your method headers must be correct.\n");
                }
                Pattern emailPattern = Pattern.compile("[a-zA-Z]+\\.[a-zA-Z]+\\.[0-9]+@k12\\.friscoisd\\.org");
                if (!emailPattern.matcher(programOutput).find()) {
                    score -= 1;
                    feedback.append("- Your convertToEmail method must correctly format emails.\n");
                }

                feedback.append("\nFinal Score: ").append(score).append("/9\n");
                if (score == 9) {
                    feedback.append("Perfect! Your solution is correct.\n");
                } else {
                    feedback.append("Review the comments above and try again.\n");
                }

                return feedback.toString();
            }
            default -> {
                return "Invalid question number.";
            }
        }
    }
}
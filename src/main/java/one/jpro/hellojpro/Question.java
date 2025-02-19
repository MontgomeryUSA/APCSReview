package one.jpro.hellojpro;

public class Question {
    String[] Answers;
    String question;
    String PseudoCode;
    int correctA;
    public Question(String question, String PseudoCode,String[] answers, int correctA){
        this.Answers = answers;
        this.question = question;
        this.PseudoCode = PseudoCode;
        this.correctA = correctA;
    }
    public int getCorrectA() {

        return correctA;
    }
    public String getQuestion() {
        return question;
    }
    public String[] getAnswers() {
        return Answers;
    }
    public String getPseudoCode() {
        return PseudoCode;
    }
}

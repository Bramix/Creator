package application.struct;


public class Answer {
    private String answer;
    private String text;

    public Answer(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }
    
    public Answer() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}

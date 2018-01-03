package application.design.Controller;

import application.Memory;
import application.design.WindowsController;
import application.struct.Answer;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class ConfController {
    @FXML
    TextArea answer, text;

    private Answer answ = new Answer();

    @FXML
    private void create(){
        if (!answer.getText().isEmpty() && !text.getText().isEmpty()) {
            answ.setAnswer(answer.getText());
            answ.setText(text.getText());
            Memory.answers.add(answ);
            Memory.variants.add(answer.getText() + " - " + text.getText());
            WindowsController.closeStage(answer);
        }
    }
    public void initialize() {
    }
}

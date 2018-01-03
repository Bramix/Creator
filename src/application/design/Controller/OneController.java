package application.design.Controller;

import application.Memory;
import application.design.WindowsController;
import application.struct.Answer;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class OneController {
	@FXML
	CheckBox checkBox;
	@FXML
	TextArea answer;
	private Answer answ = new Answer();

	@FXML
	private  void  create(){
		if (!answer.getText().isEmpty()) {
			answ.setText(answer.getText());
			if (checkBox.isSelected())
				answ.setAnswer("+");
			else
				answ.setAnswer("-");
			Memory.answers.add(answ);
			Memory.variants.add(answ.getText());
			WindowsController.closeStage(answer);
		}
	}
	public void initialize() {
	}
}

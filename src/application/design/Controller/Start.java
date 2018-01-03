package application.design.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import application.Memory;
import application.design.PathWindows;
import application.design.WindowsController;
import application.design.component.WindowsWorker;
import application.struct.Test;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class Start {
	private String json = "", str;
	private String endOne = "!----@@@@@----!\n";
	private String endTwo = "\n!----@@@END@@@----!";
	private int index; 
	@FXML
	private ImageView creator;
	@FXML
    ListView list;
	
	@FXML
	private void newQwestion() {
		Memory.variants.clear();
		Memory.answers.clear();
		WindowsWorker windowsLogin = new WindowsWorker(PathWindows.Create.getPath(), false);
		new WindowsController().closeStage(list);
		windowsLogin.setResizable(true);
		windowsLogin.show();
	       
	}
	@FXML
	private void openFile() throws IOException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = 
		new FileChooser.ExtensionFilter("HTML files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(WindowsController.getStage(creator));
		if (file != null) {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			while ((str = in.readLine()) != null) {
					json += str;
				}
				in.close();
		  }
		  Gson GSON = new GsonBuilder().setPrettyPrinting().create();
		  String[] mas = json.split("!-");
		  Memory.test = GSON.fromJson(mas[0], Test.class);
		  Memory.names.setAll(Memory.test.getQuestionsNames());
		}
		
	
	@FXML
	private void saveFile() throws IOException {
		String gson = new Gson().toJson(Memory.test) + endOne;		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Document");
		FileChooser.ExtensionFilter extFilter = 
		new FileChooser.ExtensionFilter("HTML files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(WindowsController.getStage(creator));
			if (file != null) {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
	            bw.write(gson);
	            bw.write(Memory.imagePaths + endTwo);
	            bw.flush();
			}
		}
	
	@FXML
	private void openQwest(MouseEvent mouseEvent){
		if(mouseEvent.getClickCount() == 2) {
			Memory.indx = list.getSelectionModel().getSelectedIndex();
			WindowsController.closeStage(creator);			
			WindowsWorker windowsLogin = new WindowsWorker(PathWindows.Create.getPath(), false);
			windowsLogin.show();
		}
		
	}
	@FXML
	private void delete() {
		index = list.getSelectionModel().getSelectedIndex();
		Memory.names.remove(index);
		Memory.test.remove(index);
		list.setItems(Memory.names);
	}
	
	public void initialize() {
		//list.setStyle("-fx-border-color: orange;");
		list.setItems(Memory.names);
	}
}
  


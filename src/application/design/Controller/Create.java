package application.design.Controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.xml.bind.SchemaOutputResolver;

import com.sun.javafx.tk.Toolkit;

import application.Memory;
import application.design.PathWindows;
import application.design.WindowsController;
import application.design.component.WindowsWorker;
import application.struct.Answer;
import application.struct.Question;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;


public class Create implements Initializable {
	private Image newImage;
	private Boolean can = false;
	private int index;
	private Question question;
	private Boolean image = false;
	private String type = "", typeBox, path = "", imagePath = null;
	private Answer answer;
	@FXML
	private Button titledButton;
	@FXML
	private CheckBox checkBox;
	@FXML
	private ComboBox comboBox;
	@FXML
	private TextArea name, about;
	@FXML
	public ListView list;
	@FXML
	private TextField cost;
	@FXML
	private AnchorPane  vision;
	@FXML
	private ImageView plus, minus, download;

	@FXML
	private void check() {

		if (checkBox.isSelected())
			download.setVisible(true);

		else {
			download.setVisible(false);
			titledButton.setVisible(false);
		}
	}
	@FXML
	private void reload() {

			if (Memory.variants.size() == 0) {
				typeBox = (String) comboBox.getValue();
				switch(typeBox) {
					case "Одна відповідь":
						type = "One";
						break;
					case "Відповідність":
						type = "Сonformity";
						break;
					default:
						type = "Checkbox";
				}
			}
			else {
				comboBox.setDisable(true);
				comboBox.setValue(typeBox);
			}

	}

	@FXML
	private void createVariant()  {
			if (type == "Сonformity")
				new WindowsWorker(PathWindows.Conformity.getPath(), true).show();
			else
				new WindowsWorker(PathWindows.One.getPath(), true).show();
	}
	
	@FXML
	private void delete() {
		Memory.answers.remove(list.getSelectionModel().getSelectedIndex());
		Memory.variants.remove(list.getSelectionModel().getSelectedIndex());
		list.setItems(Memory.variants);
		if (Memory.variants.size() == 0) {
			comboBox.setDisable(false);
		}
	}
	@FXML
	private void goBack() {
		WindowsController.closeStage(list);
		WindowsWorker windowsLogin = new WindowsWorker(PathWindows.Start.getPath(), false);
		windowsLogin.show();
	}
	@FXML
	private void downloadImage() throws IOException {
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().addAll(extFilterJPG);
        File file = fileChooser.showOpenDialog(null);
        BufferedImage bufferedImage = ImageIO.read(file);
        newImage = SwingFXUtils.toFXImage(bufferedImage, null);
        titledButton.setVisible(true);
    	FileInputStream imageInFile = new FileInputStream(file.getAbsolutePath());
    	byte imageData[] = new byte[(int) file.length()];
    	imageInFile.read(imageData);
    	imagePath = "img" + Integer.toString(Memory.next);
    	path = Base64.getEncoder().encodeToString(imageData);
    	image = true;
    	imageInFile.close();
	}

	@FXML
	private void showImage() {
		new WindowsWorker(newImage).show();
	}

	@FXML
	private void finish() {
		question = new Question();
		if (can) {
			Memory.names.remove(Memory.indx);
			Memory.test.remove(Memory.indx);
		}
		if (image){
			Memory.imagePaths += imagePath + "%" + path;
			Memory.next ++;
		}
		
		question.setAll(imagePath, about.getText(), name.getText(), Double.parseDouble(cost.getText()), type,
		false, Memory.answers, Memory.answers.size(), image);

		Memory.test.add(question);
		Memory.names.add(name.getText());
		WindowsController.closeStage(list);
		Memory.indx = 5000;
		new WindowsWorker(PathWindows.Start.getPath(), false).show();

	}
	private void load(){
			name.setText(Memory.test.get(Memory.indx).getName());
			about.setText(Memory.test.get(Memory.indx).getAbout());
			cost.setText(Double.toString(Memory.test.get(Memory.indx).getCost()));
			Memory.answers = Memory.test.get(Memory.indx).getAnswers();
			Memory.variants = FXCollections.observableArrayList(Memory.test.get(Memory.indx).getAllTexts());
			list.setItems(Memory.variants);
			checkBox.setSelected(Memory.test.get(Memory.indx).isImage());
			can = true;
			type = Memory.test.get(Memory.indx).getType();
				switch(type) {
					case "One":
		              typeBox = "Одна відповідь";
		              break;
		            case "Сonformity":
		              typeBox = "Відповідність";
		              break;
		            default:
		              typeBox = "Декілька відповідей";
				}
			comboBox.setValue(typeBox);
			imagePath = Memory.test.get(Memory.indx).getImagePath();
			System.out.println(imagePath);
			if(imagePath != null){
				checkBox.setSelected(true);
			}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list.setItems(Memory.variants);
		comboBox.getItems().add("Відповідність");
		comboBox.getItems().add("Одна відповідь");
		comboBox.getItems().add("Декілька відповідей");
		if(Memory.indx != 5000)
			load();

	}
}

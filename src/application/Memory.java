package application;

import java.util.ArrayList;

import application.struct.Answer;
import application.struct.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class Memory {
	public static ObservableList<String> names = FXCollections.observableArrayList();
	public static ObservableList<String> variants = FXCollections.observableArrayList();
	public static ArrayList<Answer> answers = new ArrayList();
	public static Test test = new Test();
	public static int indx = 5000;
	public static int next = 1; 
	public static String imagePaths = "";
	 

}

package application.struct;

import java.util.ArrayList;
import java.util.Random;

import com.sun.prism.Image;

import application.Memory;

public class Question {
	private ArrayList<Answer> answers = new ArrayList<>();
	private double cost;
	private int count;
	private boolean image;
	private String name;
	private String about;
	private boolean solve = false;
    private String imagePath;
    private String type;

    public Question() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(ArrayList <Answer> answers) {
        this.answers = answers;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isSolve() {
        return solve;
    }

    public void setSolve(boolean solve) {
        this.solve = solve;
    }
    public void setAll (String imagePath, String about, String name, double cost, String type, boolean solve, ArrayList<Answer> answers,int count,boolean image){
    	this.imagePath = imagePath;
		this.about = about;
		this.name = name;
		this.cost = cost;
		this.type = type;
		this.solve = solve;
		this.answers = answers;
		this.count = count;
		this.image = image;
    }


    public String[] getAllAnswer() {
        String[] res = new String[count];
        int i = 0;
        for(Answer answer: answers) {
            res[i] = answer.getAnswer();
            i++;
        }
        return res;
    }
    public String[] getAllTexts() {
        String[] res = new String[count];
        int i = 0;
        for(Answer answer: answers) {
            res[i] = answer.getText();
            i++;
        }
        return res;
    }



}

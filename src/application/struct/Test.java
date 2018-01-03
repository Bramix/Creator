package application.struct;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Test extends ArrayList<Question> {


    public List<String> getQuestionsNames() {
        ArrayList<String> result = new ArrayList<>() ;
        for (Question question: this) {
            String item = question.getName();
            if (question.isSolve()) item = "✔ " + item;
            result.add(item);
        }

        return result;
    }

    public double getMaxCost() {
        double res = 0;
        for(Question question: this) {
            if(question.getType().equals("One"))
                res += question.getCost();
            else if(question.getType().equals("Checkbox")) {
                int k = 0;
                for(int i = 0; i < question.getCount(); i++) {
                    if(question.getAnswers().get(i).getAnswer().equals("+"))
                        k++;
                }
                res += k * question.getCost();
            }
            else
                res += question.getCount() * question.getCost();
        }
        return res;
    }


    public void sortQuestion() {
        ArrayList<Question> buf = new ArrayList();
        buf.addAll(this);
        this.clear();
        Random random = new Random();
        random.nextInt();
        int size = buf.size();
        for (int i = 0; i < size; i++) {
            int index;
            do {
                int randomNum = random.nextInt();
                index = Math.abs(randomNum - 1) % buf.size();
            } while(index < 0 && index > buf.size() - 1);

            this.add(buf.get(index));
            buf.remove(index);
            random.nextInt();
        }
    }

}
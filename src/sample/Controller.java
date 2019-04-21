package sample;

//Afsana Chowdhury
//ID: 1731163642



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Controller {

    @FXML
    private Label questionLabel;
    @FXML
    private ToggleGroup optionsGroup;
    @FXML
    private Button submitButton;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb4;
    int currentQuestion=0;
    @FXML
    int score =0;

    @FXML
    public void initialize(){
        List<Question> questions = QuestionLoader.loadQuestions("QuestionDB.csv");
        Collections.shuffle(questions);

        questionLabel.setText(((Question)questions.get(currentQuestion)).getQuestion());
        String option1 = (String)questions.get(currentQuestion).getOptions().get(0);
        String option2 = (String)questions.get(currentQuestion).getOptions().get(1);
        String option3 = (String)questions.get(currentQuestion).getOptions().get(2);
        String option4 = (String)questions.get(currentQuestion).getOptions().get(3);

        rb1.setText(option1);
        rb2.setText(option2);
        rb3.setText(option3);
        rb4.setText(option4);
        submitButton.setText("Next");


        submitButton.setOnAction(event -> getNextQuestion(questions));

    }

    private void getNextQuestion(List<Question> questions) {
        if (currentQuestion<questions.size()) {
            String selectedRB = ((RadioButton)optionsGroup.getSelectedToggle()).getText();
            String correctAnswer = (String)questions.get(currentQuestion).getCorrectAns();
            if (selectedRB.equals(correctAnswer) ){
                score++;
                System.out.println("Correct Answer");
            }
            else {
                score--;
                System.out.println("Wrong Answer");
            }
            currentQuestion++;
            if(currentQuestion<questions.size()){
                questionLabel.setText(((Question)questions.get(currentQuestion)).getQuestion());
                String option1 = (String)questions.get(currentQuestion).getOptions().get(0);
                String option2 = (String)questions.get(currentQuestion).getOptions().get(1);
                String option3 = (String)questions.get(currentQuestion).getOptions().get(2);
                String option4 = (String)questions.get(currentQuestion).getOptions().get(3);

                rb1.setText(option1);
                rb2.setText(option2);
                rb3.setText(option3);
                rb4.setText(option4);
            }
            else{
                questionLabel.setText("Your score is: " +score);
                rb1.setText("");
                rb2.setText("");
                rb3.setText("");
                rb4.setText("");
                submitButton.setText("Restart");
            }
        }
        else {
            currentQuestion = 0;
            score = 0;
            getNextQuestion(questions);
            submitButton.setText("Next");
        }

    }

}

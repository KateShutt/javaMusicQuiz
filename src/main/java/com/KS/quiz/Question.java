package com.KS.quiz;

public class Question {

    private String term;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Option correctOption;
    private Category category;

    public Question(String term, String optionA, String optionB, String optionC, String optionD, String correctOption, Category category){
        this.term = term;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = Option.valueOf(correctOption.toUpperCase().trim());
        this.category = category;
    }

    public boolean isCorrect(Option userAnswer){
        return userAnswer == this.correctOption;
    }

    public String getTerm(){
        return this.term;
    }

    public String getOptionA(){
        return this.optionA;
    }

    public String getOptionB(){
        return this.optionB;
    }

    public String getOptionC(){
        return this.optionC;
    }

    public String getOptionD(){
        return this.optionD;
    }

    public Option getCorrectOption() {
        return this.correctOption;
    }

    public Category getCategory() {
        return this.category;
    }
}

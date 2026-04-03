package com.KS.quiz;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class CliApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int score = 0;

        int numOfQuestions;

        System.out.println("**********************");
        System.out.println("MUSIC TERMINOLOGY QUIZ");
        System.out.println("**********************");

        Category chosenCategory = chooseCategory(scanner);

        System.out.println("chosen category is " + chosenCategory);

        List<Question> questions =  loadQuestions(chosenCategory);

        if (questions.isEmpty()){
            System.out.println("no questions were loaded");
            return;
        }


        numOfQuestions = questions.size();
//        System.out.println("num of questions is " + numOfQuestions);


        Random random = new Random();

            while(!questions.isEmpty()){
                // move all question logic in here so that we keep asking questions till we have asked them all

                //System.out.println("loop is working");



                int randomNum = random.nextInt(0, questions.size());

                //System.out.println("random num is " + randomNum);

                Question q = questions.get(randomNum);

                //checking to see how many questions are in the list before removing question

                //System.out.println(questions.size()+ " questions to choose from");

                questions.remove(randomNum);

                // testing to see if question is removed from the arraylist
                //System.out.println(questions.size()+ " questions left to ask");

                Option selectedOption = askQuestion(q,scanner);





                boolean isCorrectAnswer = q.isCorrect(selectedOption);

                if(isCorrectAnswer){
                    System.out.println("********");
                    System.out.println("CORRECT!");
                    System.out.println("********");
                    System.out.println();
                    score++;
                } else {
                    System.out.println("********");
                    System.out.println("NOT QUITE!");
                    System.out.println("********");
                    System.out.println("The correct answer was " + q.getCorrectOption());
                    System.out.println();
                }

            }



        System.out.println("********");
        System.out.println("END OF QUIZ!");
        System.out.printf("You scored %d out of %d\n", score, numOfQuestions);
        System.out.println("********");






    scanner.close();

    }

    public static List<Question> loadQuestions(Category chosenCategory) {

        List<Question> questions = new ArrayList<>();

        String line;

        InputStream inputStream = CliApp.class
                .getClassLoader()
                .getResourceAsStream("questions.csv");

        if (inputStream == null) {
            System.out.println("File not found!");
            return new ArrayList<>();
        }


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            // checks the file path is valid
            //System.out.println(CliApp.class.getClassLoader().getResource("questions.csv"));


            // loads questions.csv from the resources folder and saves as inputStream


            // allows java to read the input stream line by line


            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                String[] parts = line.split(",");
                //System.out.println(parts[0]);

                // checks that the question has the correct number of parts
                if (parts.length != 7) {
                    System.out.println("Invalid line (wrong no of fields) in line " + line);
                    continue;
                }

                Category category;

                try {
                    category = Category.valueOf(parts[6].toUpperCase().trim());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid category: " + parts[6] + " in line " + line);
                    continue;
                }

                if(category == chosenCategory){
                    Question question = new Question(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], category);

                    questions.add(question);
                }



            }
//            To check that questions have successfully loaded
//            System.out.println("Total questions loaded: " + questions.size());

            if (questions.isEmpty()) {
                System.out.println("No questions were loaded.");
                return new ArrayList<>();
            }

            return questions;

        } catch (Exception e) {
            System.out.println("An error occurred while rUNNING THE QUIZ.");

            // debug info (can remove later)
            System.err.println("DEBUG: " + e.getMessage());

            return new ArrayList<>();

        }


    }

    public static Option askQuestion(Question q, Scanner scanner ){

        Option selectedOption;

        while(true){
            System.out.printf("What does %s mean?\n", q.getTerm());
            System.out.println();
            System.out.printf("A: %s\n", q.getOptionA());
            System.out.printf("B: %s\n", q.getOptionB());
            System.out.printf("C: %s\n", q.getOptionC());
            System.out.printf("D: %s\n", q.getOptionD());
            System.out.println();





            System.out.print("Type your answer: ");
            String selection = scanner.nextLine().toUpperCase().trim();
//            System.out.println(selection);

            // a variable that can point to one of the enums A,B,C or D


            try{
                // try to match selection to one of the available enums
                selectedOption = Option.valueOf(selection);
                return selectedOption;
                //we have got a valid selection. Break out of while loop
            } catch(IllegalArgumentException e){
                //if can't find a match, throw an error
                System.out.println("******************************************");
                System.out.println("Invalid input. Please enter A, B, C, or D.");
                System.out.println("******************************************");

            }
        }

    }

    public static Category chooseCategory(Scanner scanner){

        System.out.println("1: TEMPO");
        System.out.println("2. DYNAMICS");
        System.out.println();
        System.out.print("Choose a category for the quiz: ");

        String chosenCategory = scanner.nextLine().trim();

        while(true){
            switch(chosenCategory){
                case "1": return Category.TEMPO;
                case "2": return Category.DYNAMICS;
                default:
                    System.out.println("Invalid input. Please enter 1 or 2");
            }
        }


    }




}





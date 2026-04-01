package com.KS.quiz;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CliApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            // checks the file path is valid
            //System.out.println(CliApp.class.getClassLoader().getResource("questions.csv"));


            // loads questions.csv from the resources folder and saves as inputStream
            InputStream inputStream = CliApp.class
                    .getClassLoader()
                    .getResourceAsStream("questions.csv");

            if (inputStream == null) {
                System.out.println("File not found!");
                return;
            }

            // allows java to read the input stream line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            List<Question> questions = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                String[] parts = line.split(",");
                //System.out.println(parts[0]);

                // checks that the question has the correct number of parts
                if (parts.length != 7) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }
                Question question = new Question(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);

                questions.add(question);

            }
//            To check that questions have successfully loaded
//            System.out.println("Total questions loaded: " + questions.size());

            if (questions.isEmpty()) {
                System.out.println("No questions were loaded.");
                return;
            }

            Question q = questions.get(0);

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
                String selection = scanner.nextLine();
//            System.out.println(selection);

                // a variable that can point to one of the enums A,B,C or D


                try{
                    // try to match selection to one of the available enums
                    selectedOption = Option.valueOf(selection);
                    break;
                    //we have got a valid selection. Break out of while loop
                } catch(IllegalArgumentException e){
                    //if can't find a match, throw an error
                    System.out.println("******************************************");
                    System.out.println("Invalid input. Please enter A, B, C, or D.");
                    System.out.println("******************************************");

                }
            }

            boolean isCorrectAnswer = q.isCorrect(selectedOption);

            if(isCorrectAnswer){
                System.out.println("********");
                System.out.println("CORRECT!");
                System.out.println("********");
            } else {
                System.out.println("NOT QUITE!");
            }


        } catch (Exception e) {
            System.out.println("An error occurred while rUNNING THE QUIZ.");

            // debug info (can remove later)
            System.err.println("DEBUG: " + e.getMessage());
        }


    scanner.close();

    }

}





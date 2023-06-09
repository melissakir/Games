import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class NumberGuessGame implements Game {
    public int score = 0;
    static Random random = new Random();
    private static int level = 1;

    @Override
    public String getGameName(){
        return "NumerGuessGame";
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> pastGuesses = new ArrayList<>();

        int guess = 0, tries = 0, number = 0;


        System.out.println(
                "Welcome to the Random Number guessing game. Please specify your range below (seperate two numbers with a dash)! You cannot use negitive numbers.");
        int min = 0;
        int max = 0;
        try {
            String[] range = (scanner.nextLine()).split("-");
            number = getRandomNumber(Integer.valueOf(range[0]), Integer.valueOf(range[1]));
            min = Integer.valueOf(range[0]) * -level;
            max = Integer.valueOf(range[1]) * level;
        } catch (Exception e) {
            System.out.println("Please enter a range seperated by a dash");
            String[] range = (scanner.nextLine()).split("-");
            number = getRandomNumber(Integer.valueOf(range[0]), Integer.valueOf(range[1]));
        }
        boolean continueGame = true;

        while (continueGame = true) {

            System.out.println(
                    "Guess the number:");

            guess = errorCheck(scanner);

            // guess = scanner.nextInt();
            if (guess > max) {
                System.out.println("This number is out of range.");
            } else if (pastGuesses.contains(guess)) {
                System.out.println("You already guessed " + guess + ". Please guess a new number.");
            } else {
                tries++;
            }
        
            if (number == guess) {
                System.out.println(
                        "Congratulations! You guessed the number. It took you " + tries + " tr"
                                + ((tries > 1) ? "ies." : "y."));
                System.out.println("Do you want to go to level " + Integer.toString(level+1) + "? Please answer Y or N");
                String response = scanner.nextLine();

                continueGame = (response == "Y") ? true : false;
                if (continueGame) {
                    level += 1;
                    if (level == 2){
                        guess = getRandomNumber(min * -level, max*level);
                    } else {
                        guess = getRandomNumber(min * level, max*level);
                    }
                } else {
                    scanner.close();
                    break;
                }         
            } else if (number > guess) {
                System.out.println(
                        "The number is greater than " + guess);
            } else if (number < guess) {
                System.out.println(
                        "The number is less than " + guess);
            }

            pastGuesses.add(guess);
        }
        score = level;
    
    }

    public String getScore(){
        return "" + score;
    }
    
    public void writeHighScore(File myFile){
        try (BufferedReader br = new BufferedReader(new FileReader(myFile.getPath()))) {
            String firstLine = br.readLine();
            if (Integer.parseInt(firstLine) < score){
                FileWriter fw = new FileWriter(myFile);
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

    }

    public static int errorCheck(Scanner sc) {
        // System.out.println("I am expecting a number from 1 - 5!");

        if (sc.hasNextInt()) {
            int input = sc.nextInt();
            // if (input > 5 || input < 1) {
            // System.out.println("Hey! this number is not what I wanted. Try again.");
            // return errorCheck(sc);
            // } else {
            // System.out.println("Yay! This is what I expected");
            // return input;
            // }
            return input;
        } else {
            System.out.println("This is not a number. Discarding this input");
            sc.next(); // discarding the next input
            return errorCheck(sc);
        }
    }

    public static int getRandomNumber(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

}

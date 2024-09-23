import java.util.Scanner;
import java.util.Random;

public class numbergame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
        int maxTries = 5; // Set the maximum number of tries
        int numberOfTries = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I've selected a number between 1 and 100. You have " + maxTries + " attempts to guess it.");

        while (!hasGuessedCorrectly && numberOfTries < maxTries) {
            System.out.print("Enter your guess: ");
            int playerGuess = scanner.nextInt();
            numberOfTries++;

            if (playerGuess < 1 || playerGuess > 100) {
                System.out.println("Please guess a number between 1 and 100.");
                numberOfTries--; // Do not count invalid guess
            } else if (playerGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (playerGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You've guessed the number " + numberToGuess + " in " + numberOfTries + " tries.");
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("Sorry! You've used all your attempts. The number was " + numberToGuess + ".");
        }

        scanner.close();
    }
}

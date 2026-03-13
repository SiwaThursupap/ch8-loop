import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GusessNumber {
    public static void main(String[] args) {
        int score = 100;
        try (Scanner scanner = new Scanner(System.in)) {
            Random rand = new Random();
            int guess = rand.nextInt(100);
            int number;
            boolean isCorrect = false;

            while (!isCorrect) {
                IO.print("Pease Enter Guess Numer : ");
                number = scanner.nextInt();
                if (number > guess) {
                    IO.print("To Much ");
                    score--;
                }
                if (number < guess) {
                    IO.print("Less Number ");
                    score--;
                }
                if (number == guess) {
                    isCorrect = true;
                }

            }
            IO.println("Congreaturation");
            IO.println("Your Score is : " + score);
        } catch (InputMismatchException e) {
            IO.println("Incorrect");
        } catch (Exception e) {
            IO.println("Error");
        }

    }
}

import java.util.Scanner;

public class DoWhileLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        int number1, number2;
        number1 = 5;
        number2 = 10;
        do {
            IO.println("== 1. Add ==");
            IO.println("== 2. Multiply ==");
            IO.println("== 3. Subtrack ==");
            IO.println("== 0. Exit ==");
            IO.println("== Enter (0-3) ==");
            choice = sc.nextInt();
            if (choice == 1) {
                IO.println(number1 + "+" + (number1 + number2));
            }
                        if (choice == 2) {
                IO.println(number1 + "+" + (number1 * number2));
            }
            if (choice == 3) {
                IO.println(number1 + "+" + (number2 - number1));
            }


        } while (choice != 0);

    }
}

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GusessNumber2 {
    public static void main(String[] args) {
        Random rand = new Random();
        int secretCode = rand.nextInt(100) + 1;
        int attemptsLeft = 7; // จำกัดแค่ 7 ครั้ง
        int score = 1000;
        boolean isDefused = false;

        System.out.println("========================================");
        System.out.println("   ⚠️  EMERGENCY: BOMB DETECTED!  ⚠️");
        System.out.println("   Identify the 2-digit code (1-100)");
        System.out.println("   You have only 7 attempts to defuse.");
        System.out.println("========================================");

        try (Scanner scanner = new Scanner(System.in)) {
            while (attemptsLeft > 0 && !isDefused) {
                System.out.println("\n[Attempts Remaining: " + attemptsLeft + "]");
                System.out.print("Enter Defusal Code: ");
                
                int guess = scanner.nextInt();
                int distance = Math.abs(guess - secretCode); // หาความห่างของตัวเลข

                if (guess == secretCode) {
                    isDefused = true;
                } else {
                    attemptsLeft--;
                    score -= 150; // ลดคะแนนตามจำนวนครั้ง

                    // ระบบคำใบ้แบบวิเคราะห์ระยะ (Hot or Cold)
                    if (guess > secretCode) {
                        System.out.print(">> STATUS: TOO HIGH! ");
                    } else {
                        System.out.print(">> STATUS: TOO LOW! ");
                    }

                    if (distance <= 5) {
                        System.out.println("(Extremely Hot! 🔥)");
                    } else if (distance <= 15) {
                        System.out.println("(Getting Warm... ☀️)");
                    } else {
                        System.out.println("(Freezing Cold... ❄️)");
                    }
                }
            }

            if (isDefused) {
                System.out.println("\n****************************************");
                System.out.println("✅ MISSION ACCOMPLISHED!");
                System.out.println("The bomb has been defused safely.");
                System.out.println("FINAL SCORE: " + (score + (attemptsLeft * 50))); // โบนัสที่เหลือเวลา
                System.out.println("****************************************");
            } else {
                System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("💥 BOOM! The bomb exploded.");
                System.out.println("The correct code was: " + secretCode);
                System.out.println("GAME OVER");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }

        } catch (InputMismatchException e) {
            System.out.println("SYSTEM ERROR: Invalid input. The bomb triggered due to hardware interference!");
        }
    }
}
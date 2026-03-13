import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GusessNumber3 extends JFrame {
    private int secretCode;
    private int attemptsLeft = 7;
    private int score = 1000;

    private JTextField inputField;
    private JLabel statusLabel;
    private JLabel attemptsLabel;
    private JButton guessButton;
    private JTextArea logArea;

    public GusessNumber3() {
        // ตั้งค่าตัวเลขสุ่ม
        secretCode = new Random().nextInt(100) + 1;

        // ตั้งค่าหน้าต่างโปรแกรม
        setTitle("💣 TOP SECRET: BOMB DEFUSAL UNIT");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(30, 30, 30)); // สีดำแบบ Terminal

        // --- ส่วนหัว (Header) ---
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBackground(new Color(45, 45, 45));
        JLabel titleLabel = new JLabel("BOMB DETECTED!", SwingConstants.CENTER);
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        
        attemptsLabel = new JLabel("ATTEMPTS REMAINING: " + attemptsLeft, SwingConstants.CENTER);
        attemptsLabel.setForeground(Color.YELLOW);
        headerPanel.add(titleLabel);
        headerPanel.add(attemptsLabel);
        add(headerPanel, BorderLayout.NORTH);

        // --- ส่วนกลาง (Input Area) ---
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setOpaque(false);
        inputField = new JTextField(5);
        inputField.setFont(new Font("Monospaced", Font.BOLD, 20));
        
        guessButton = new JButton("DEFUSE");
        guessButton.setBackground(Color.DARK_GRAY);
        guessButton.setForeground(Color.WHITE);

        centerPanel.add(new JLabel("ENTER CODE (1-100): "));
        centerPanel.add(inputField);
        centerPanel.add(guessButton);
        add(centerPanel, BorderLayout.CENTER);

        // --- ส่วนล่าง (Log & Status) ---
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(new Color(0, 255, 0)); // สีเขียวแบบ Matrix
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(logArea);
        
        statusLabel = new JLabel("SYSTEM READY...", SwingConstants.CENTER);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setPreferredSize(new Dimension(400, 40));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- ระบบการทำงาน (Logic) ---
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });
        
        // รองรับการกด Enter ในช่องกรอก
        inputField.addActionListener(e -> processGuess());
    }

    private void processGuess() {
        try {
            int guess = Integer.parseInt(inputField.getText());
            int distance = Math.abs(guess - secretCode);
            attemptsLeft--;

            if (guess == secretCode) {
                win();
            } else if (attemptsLeft <= 0) {
                gameOver();
            } else {
                String hint = (guess > secretCode) ? "TOO HIGH" : "TOO LOW";
                String temp = (distance <= 5) ? "🔥 HOT!" : (distance <= 15) ? "☀️ WARM" : "❄️ COLD";
                
                logArea.append("Attempt " + (7-attemptsLeft) + ": " + guess + " -> " + hint + " (" + temp + ")\n");
                statusLabel.setText("STATUS: " + hint + " | TEMP: " + temp);
                attemptsLabel.setText("ATTEMPTS REMAINING: " + attemptsLeft);
                score -= 100;
            }
            inputField.setText("");
            inputField.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
        }
    }

    private void win() {
        statusLabel.setText("BOMB DEFUSED!");
        statusLabel.setForeground(Color.GREEN);
        JOptionPane.showMessageDialog(this, "MISSION ACCOMPLISHED!\nFinal Score: " + score);
        System.exit(0);
    }

    private void gameOver() {
        statusLabel.setText("BOOM! EXPLODED");
        statusLabel.setForeground(Color.RED);
        JOptionPane.showMessageDialog(this, "GAME OVER\nThe code was: " + secretCode);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GusessNumber3().setVisible(true);
        });
    }
}
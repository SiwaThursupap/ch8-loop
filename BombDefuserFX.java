import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Random;

public class BombDefuserFX extends Application {
    private int secretCode = new Random().nextInt(100) + 1;
    private int attemptsLeft = 7;
    private int score = 1000;

    @Override
    public void start(Stage primaryStage) {
        // --- UI Components ---
        Label titleLabel = new Label("NUCLEAR DEFUSAL INTERFACE");
        titleLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.ORANGERED);

        Label statusLabel = new Label("ENTER 2-DIGIT OVERRIDE CODE");
        statusLabel.setTextFill(Color.LIGHTBLUE);

        TextField inputField = new TextField();
        inputField.setPromptText("0-100");
        inputField.setMaxWidth(100);
        inputField.setStyle("-fx-control-inner-background: #000; -fx-text-fill: #0f0; -fx-font-family: 'Monospaced';");

        Button defuseButton = new Button("INITIATE DEFUSAL");
        defuseButton.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-border-color: #666;");

        ProgressBar tempBar = new ProgressBar(0);
        tempBar.setPrefWidth(300);
        tempBar.setStyle("-fx-accent: red;");

        TextArea logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(150);
        logArea.setStyle("-fx-control-inner-background: #111; -fx-text-fill: #0f0; -fx-font-family: 'Monospaced';");

        Label attemptsLabel = new Label("ATTEMPTS REMAINING: 7");
        attemptsLabel.setTextFill(Color.YELLOW);

        // --- Logic ---
        defuseButton.setOnAction(e -> {
            try {
                int guess = Integer.parseInt(inputField.getText());
                int distance = Math.abs(guess - secretCode);
                attemptsLeft--;

                if (guess == secretCode) {
                    showDialog("MISSION ACCOMPLISHED", "Bomb Defused! Score: " + (score + (attemptsLeft * 100)), Alert.AlertType.INFORMATION);
                    System.exit(0);
                } else if (attemptsLeft <= 0) {
                    showDialog("CRITICAL FAILURE", "BOOM! The code was: " + secretCode, Alert.AlertType.ERROR);
                    System.exit(0);
                } else {
                    String hint = (guess > secretCode) ? "TOO HIGH" : "TOO LOW";
                    double heat = 1.0 - (Math.min(distance, 50) / 50.0); // ยิ่งใกล้ ยิ่งเต็มหลอด
                    tempBar.setProgress(heat);
                    
                    logArea.appendText(">> Attempt " + (7 - attemptsLeft) + ": " + guess + " | " + hint + "\n");
                    attemptsLabel.setText("ATTEMPTS REMAINING: " + attemptsLeft);
                }
                inputField.clear();
            } catch (NumberFormatException ex) {
                statusLabel.setText("ERROR: INPUT NUMERIC CODE ONLY!");
                statusLabel.setTextFill(Color.RED);
            }
        });

        // --- Layout ---
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #050505; -fx-border-color: #444; -fx-border-width: 5;");
        root.getChildren().addAll(titleLabel, attemptsLabel, inputField, defuseButton, new Label("PROXIMITY HEAT MAP:"), tempBar, logArea, statusLabel);

        Scene scene = new Scene(root, 400, 550);
        primaryStage.setTitle("BOMB DEFUSAL UNIT v3.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDialog(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
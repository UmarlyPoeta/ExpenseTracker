package tracker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.time.LocalDate;

public class ExpenseTrackerApp extends Application {

    private final ExpenseManager manager = new ExpenseManager("expenses.csv");
    private final ListView<String> expenseList = new ListView<>();
    private final Label totalLabel = new Label("Suma: 0.00 zł");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // --- Lista wydatków
        updateExpenseList();
        expenseList.setPrefHeight(200);

        // --- Pola wejściowe
        TextField categoryField = new TextField();
        TextField amountField = new TextField();
        DatePicker datePicker = new DatePicker(LocalDate.now());
        TextField noteField = new TextField();

        Button addButton = new Button("Dodaj wydatek");
        addButton.setOnAction(e -> {
            try {
                String cat = categoryField.getText();
                double amt = Double.parseDouble(amountField.getText());
                LocalDate date = datePicker.getValue();
                String note = noteField.getText();

                manager.addExpense(cat, amt, date, note);
                manager.saveExpenses();
                updateExpenseList();

                categoryField.clear();
                amountField.clear();
                noteField.clear();
                datePicker.setValue(LocalDate.now());
            } catch (Exception ex) {
                showAlert("Błąd", "Nieprawidłowe dane.");
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Kategoria:"), 0, 0);
        form.add(categoryField, 1, 0);

        form.add(new Label("Kwota:"), 0, 1);
        form.add(amountField, 1, 1);

        form.add(new Label("Data:"), 0, 2);
        form.add(datePicker, 1, 2);

        form.add(new Label("Notatka:"), 0, 3);
        form.add(noteField, 1, 3);

        form.add(addButton, 1, 4);

        root.getChildren().addAll(new Label("Twoje wydatki:"), expenseList, totalLabel, new Separator(), form);

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("Expense Tracker");
        stage.setScene(scene);
        stage.show();
    }

    private void updateExpenseList() {
        expenseList.getItems().clear();
        for (Expense e : manager.getAllExpenses()) {
            expenseList.getItems().add(String.format("[%s] %.2f zł - %s (%s)",
                    e.getCategory(), e.getAmount(), e.getDate(), e.getNote()));
        }
        totalLabel.setText(String.format("Suma: %.2f zł", manager.getTotalSpent()));
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

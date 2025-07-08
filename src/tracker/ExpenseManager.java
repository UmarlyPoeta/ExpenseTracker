package tracker;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseManager {
    private final List<Expense> expenses = new ArrayList<>();
    private final File file;

    public ExpenseManager(String filename) {
        this.file = new File(filename);
        loadExpenses();
    }

    private void loadExpenses() {
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().map(Expense::fromCSV).forEach(expenses::add);
        } catch (IOException e) {
            System.out.println("Błąd wczytywania danych: " + e.getMessage());
        }
    }

    public void saveExpenses() {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Expense e : expenses) {
                writer.println(e.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu danych: " + e.getMessage());
        }
    }

    public void addExpense(String category, double amount, LocalDate date, String note) {
        expenses.add(new Expense(category, amount, date, note));
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }

    public double getTotalSpent() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public Map<String, Double> getSpendingByCategory() {
        return expenses.stream().collect(Collectors.groupingBy(
                Expense::getCategory,
                Collectors.summingDouble(Expense::getAmount)
        ));
    }

    public List<Expense> getExpensesByMonth(int year, int month) {
        return expenses.stream()
                .filter(e -> e.getDate().getYear() == year && e.getDate().getMonthValue() == month)
                .collect(Collectors.toList());
    }
}

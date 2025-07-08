package tracker;

import java.time.LocalDate;

public class Expense {
    private String category;
    private double amount;
    private LocalDate date;
    private String note;

    public Expense(String category, double amount, LocalDate date, String note) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-10.2f | %-10s | %s", category, amount, date, note);
    }

    public String toCSV() {
        return String.join(",", category, String.valueOf(amount), date.toString(), note);
    }

    public static Expense fromCSV(String line) {
        String[] parts = line.split(",", -1);
        return new Expense(parts[0], Double.parseDouble(parts[1]), LocalDate.parse(parts[2]), parts[3]);
    }
}

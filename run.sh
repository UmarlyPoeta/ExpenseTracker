#!/bin/bash

# === Ścieżki ===
JAVAFX_LIB="/opt/javafx-sdk-24.0.1/lib"
SRC_DIR="src"
OUT_DIR="out"

# === Tworzenie katalogu wyjściowego ===
mkdir -p "$OUT_DIR"

# === Kompilacja ===
echo "🔧 Kompilowanie..."
javac --module-path "$JAVAFX_LIB" \
  --add-modules javafx.controls,javafx.fxml \
  -d "$OUT_DIR" "$SRC_DIR"/tracker/*.java

# === Sprawdzenie czy kompilacja się powiodła ===
if [ $? -ne 0 ]; then
  echo "❌ Błąd kompilacji."
  exit 1
fi

# === Uruchomienie ===
echo "🚀 Uruchamianie aplikacji..."
java --module-path "$JAVAFX_LIB":"$OUT_DIR" \
  --add-modules javafx.controls,javafx.fxml \
  -cp "$OUT_DIR" tracker.ExpenseTrackerApp

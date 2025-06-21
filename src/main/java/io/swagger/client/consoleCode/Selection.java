package io.swagger.client.consoleCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Selection {
    private final List<Dice> selectedDice; // Dés choisis par le joueur
    private List<Dice> availableDices; // Dés disponibles pour la sélection
    private static final Scanner SCANNER = new Scanner(System.in);

    public Selection(List<Dice> initialDices) {
        this.selectedDice = new ArrayList<>();
        this.availableDices = new ArrayList<>(initialDices);
        chooseDice();
    }

    public Selection(List<Dice> initialDices, boolean interactif) {
        this.selectedDice = new ArrayList<>();
        this.availableDices = new ArrayList<>(initialDices);
        if (interactif) chooseDice();
    }

    public void chooseDice() {
        if (!canSelectDice()) {
            return;
        }

        while (true) {
            if (trySelectDice()) {
                if (isHotDice()) {
                    resetDice();
                }
                return;
            }
        }
    }

    private boolean trySelectDice() {
        selectedDice.clear();
        showSelectionState(availableDices);
        List<Integer> chosenValues = getDiceValues();

        if (chosenValues.isEmpty()) {
            System.out.println("Aucune sélection effectuée ce tour.");
            return false;
        }

        selectDicesByValues(chosenValues);
        if (selectedDice.isEmpty()) {
            System.out.println("Aucune correspondance trouvée pour les valeurs choisies.");
            return false;
        }

        if (confirmSelection()) {
            availableDices.removeAll(selectedDice);
            showSelectedDice();
            return true;
        } else {
            System.out.println("Sélection annulée. Veuillez choisir à nouveau.");
            return false;
        }
    }

    private boolean canSelectDice() {
        if (availableDices.isEmpty()) {
            System.out.println("Aucun dé disponible à sélectionner.");
            return false;
        }

        if (Rules.isFarkle(availableDices)) {
            System.out.println("Farkle");
            return false;
        }
        return true;
    }

    private void resetDice() {
//        availableDices = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            availableDices.add(new Dice());
//        }
        System.out.println("Tous les dés utilisés, nouvelle main !");
    }

    private boolean isHotDice() {
        return availableDices.isEmpty();
    }

    private void showSelectedDice() {
        System.out.print("Dés sélectionnés : ( ");
        for (Dice dice : selectedDice) {
            System.out.print(dice + " ");
        }
        System.out.println(")");
    }

    private void showSelectionState(List<Dice> remainingDices) {
        System.out.print("Dés disponibles : ( ");
        for (Dice dice : remainingDices) {
            System.out.print(dice + " ");
        }
        System.out.println(")");

        showSelectedDice();
    }

    private List<Integer> getDiceValues() {
        List<Integer> chosenValues = new ArrayList<>();
        boolean entreeValide = false;

        while (!entreeValide) {
            System.out.print("Entrez les valeurs des dés à sélectionner (ex: 1 5): ");
            String input = SCANNER.nextLine().trim();

            if (input.isEmpty()) {
                return chosenValues;
            }

            String[] parts = input.split("\\s+");
            chosenValues.clear();
            entreeValide = true;

            for (String part : parts) {
                try {
                    int value = Integer.parseInt(part);
                    if (value >= 1 && value <= 6) {
                        chosenValues.add(value);
                    } else {
                        System.out.println("Erreur : " + value + " n'est pas une valeur valide (doit être entre 1 et 6).");
                        entreeValide = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur : '" + part + "' n'est pas un nombre valide.");
                    entreeValide = false;
                    break;
                }
            }
            if (!entreeValide) {
                System.out.println("Veuillez entrer uniquement des valeurs valides.");
            }
        }
        return chosenValues;
    }

    public void selectDicesByValues(List<Integer> chosenValues) {
        List<Dice> tempDices = new ArrayList<>(availableDices);
        for (Integer value : chosenValues) {
            for (Dice dice : tempDices) {
                if (dice.getFaceValue() == value && !selectedDice.contains(dice)) {
                    selectedDice.add(dice);
                    tempDices.remove(dice);
                    break;
                }
            }
        }
    }

    public List<Dice> getSelectedDices() {
        return new ArrayList<>(selectedDice);
    }

    public boolean bankOrReroll(int turnScore, String name) {
        while (true) {
            System.out.print(name + " ( score actuel = " + turnScore  + " ) - Bank or reroll? (b/r): ");
            String input = SCANNER.nextLine().trim().toLowerCase();
            if (input.equals("reroll") || input.equals("r")) {
                return true;
            } else if (input.equals("bank") || input.equals("b")) {
                return false;
            }
            System.out.println(name + " - Entrée invalide. Entrez 'b' (banquer) ou 'r' (relancer).");
        }
    }

    private boolean confirmSelection() {
        int score = Rules.calculateScore(selectedDice);
        if (score == 0) {
            System.out.println("La sélection n'a aucun point.");
            return false;
        }
        if (!Rules.isValidSelection(selectedDice)) {
            System.out.println("La sélection n'est pas valide.");
            return false;
        }
        System.out.println("Sélection valide pour " + score + " points.");
        return true;
    }

    public int getScore() {
        return Rules.calculateScore(selectedDice);
    }

    public List<Dice> getAvailableDices() {
        return new ArrayList<>(availableDices);
    }
}

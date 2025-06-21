package io.swagger.client.consoleCode; /**
 * Classe représentant un joueur, gérant ses actions et son score dans le jeu.
 */

import java.util.ArrayList;

import io.swagger.client.ApiException;
import io.swagger.client.model.RestDices;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.client.api.DefaultApi;

public class Player {
    private final String name;
    private int score;
    private int id;
    private List<Dice> dices;

    /**
     * Constructeur qui initialise un joueur avec un nom.
     * @param name Nom du joueur.
     */
    public Player(String name, int id) {
        this.name = name;
        this.score = 0;
        this.id = id;
        this.dices = new ArrayList<>();
    }

    public void addToScore(int points) {
        this.score += points;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Exécute un tour de jeu : lance les dés, gère la sélection et termine proprement.
     */
    public void playTurn(DefaultApi api, String name) {
        try {
            System.out.println("\n*** " + name + " commence son tour ***");
            int turnScore = 0;
            List<Dice> currentDices = new ArrayList<>();

            String rollMessage = api.roll();
            System.out.println(rollMessage);
            if (rollMessage.toLowerCase().contains("farkle")) return;

            while (true) {
                currentDices.clear();
                List<Integer> apiDices = api.getDicesPlates().getDices();
                for (Integer dice : apiDices) {
                    currentDices.add(new Dice(dice));
                }

                Selection selection = new Selection(currentDices, true);
                List<Dice> selected = selection.getSelectedDices();
                if (selected.isEmpty()) {
                    System.out.println("Aucune sélection. Fin du tour.");
                    break;
                }

                String selectionStr = selected.stream()
                        .map(d -> String.valueOf(d.getFaceValue()))
                        .collect(Collectors.joining(" "));
                api.select(selectionStr);

                int selectionScore = api.getActualTurnPoints();
                if (selectionScore == 0) {
                    System.out.println("Farkle. Fin du tour.");
                    break;
                }

                turnScore = selectionScore;
                currentDices = selection.getAvailableDices();

                if (!selection.bankOrReroll(turnScore, name)) {
                    api.bank();
                    System.out.println("Points bankés.");
                    break;
                }

                String rerollMessage = api.roll();
                System.out.println(rerollMessage);
                if (rerollMessage.toLowerCase().contains("farkle")) return;
            }

            score = api.getPlayer(id).getScore();
            endTurn();
        } catch (ApiException e) {
            System.out.println("Erreur API : " + e.getMessage());
        }
    }

    /**
     * Termine le tour du joueur et réinitialise ses dés.
     */
    public void endTurn() {
        System.out.println(name + " - termine son tour. Score : " + score);
        this.dices = new ArrayList<>();
    }



    /**
     * Lance tous les dés dans la liste.
     * @param dices Liste des dés à lancer.
     */
    public void rollDices(List<Dice> dices) {
        System.out.print("Lancer des dés : ");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
        for (Dice dice : dices) {
            dice.roll();
        }
    }

    /**
     * Définit la liste des dés du joueur.
     * @param dices Nouvelle liste de dés à assigner au joueur.
     */
    public void setDices(List<Dice> dices) {
        this.dices = dices != null ? new ArrayList<>(dices) : new ArrayList<>();
    }

    /**
     * Retourne le score actuel du joueur.
     * @return Score total du joueur.
     */
    public int getScore() {
        return score;
    }

    /**
     * Retourne une représentation textuelle du joueur (son nom).
     * @return Nom du joueur sous forme de chaîne.
     */
    @Override
    public String toString() {
        return name;
    }
}
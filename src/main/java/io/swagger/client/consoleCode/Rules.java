package io.swagger.client.consoleCode; /**
 * Classe contenant les règles du jeu Farkle, incluant le calcul des scores et la validation des sélections.
 */
import java.util.List;

public class Rules {
    /**
     * Vérifie si une liste de dés est un Farkle (aucun point).
     * @param dices Liste des dés à vérifier.
     * @return true si aucun point n'est marqué, false sinon.
     */
    public static boolean isFarkle(List<Dice> dices) {
        return calculateScore(dices) == 0;
    }

    /**
     * Calcule le score total d'une liste de dés selon les règles du Farkle.
     * @param dices Liste des dés à évaluer.
     * @return Le score total calculé.
     */
    public static int calculateScore(List<Dice> dices) {
        if (dices == null || dices.isEmpty()) {
            return 0;
        }

        int[] counts = countDiceValues(dices);

        // Vérifie les combinaisons spéciales d'abord
        if (isRun(counts)) {
            return 2500;
        }
        if (isThreePairs(counts)) {
            return 1500;
        }

        // Calcule les points pour les combinaisons simples
        return calculateSimpleScore(counts);
    }

    /**
     * Vérifie si une sélection de dés est valide (tous les dés doivent contribuer au score).
     * @param dices Liste des dés sélectionnés à vérifier.
     * @return true si la sélection est valide, false sinon.
     */
    public static boolean isValidSelection(List<Dice> dices) {
        if (dices == null || dices.isEmpty()) {
            return false;
        }

        int[] counts = countDiceValues(dices);

        // Cas spéciaux : suite ou trois paires
        if (isRun(counts) || isThreePairs(counts)) {
            return true;
        }

        // Vérifie si tous les dés sont utilisés dans un score valide
        int[] remainingCounts = counts.clone();
        int score = calculateSimpleScore(remainingCounts);

        // Vérifie qu'il ne reste aucun dé non utilisé
        for (int count : remainingCounts) {
            if (count > 0) {
                return false; // Des dés ne contribuent pas au score
            }
        }

        return score > 0; // La sélection doit rapporter des points
    }
    /**
     * Compte les occurrences de chaque valeur dans une liste de dés.
     * @param dices Liste des dés.
     * @return Tableau des occurrences (index 0 = valeur 1, index 5 = valeur 6).
     */
    private static int[] countDiceValues(List<Dice> dices) {
        int[] counts = new int[6];
        for (Dice dice : dices) {
            counts[dice.getFaceValue() - 1]++;
        }
        return counts;
    }

    /**
     * Calcule le score pour les combinaisons simples (triplets, 1, 5).
     * Modifie-les counts pour refléter les dés utilisés.
     * @param counts Tableau des occurrences des valeurs des dés.
     * @return Le score calculé.
     */
    private static int calculateSimpleScore(int[] counts) {
        int score = 0;

        // Gérer les multiples combinaisons (6, 5, 4, 3 dés identiques)
        for (int i = 0; i < 6; i++) {
            int count = counts[i];
            if (count >= 6) {
                score += 3000;
                counts[i] -= 6;
            } else if (count == 5) {
                score += 2000;
                counts[i] -= 5;
            } else if (count == 4) {
                score += 1000;
                counts[i] -= 4;
            } else if (count == 3) {
                score += (i == 0) ? 1000 : (i + 1) * 100; // 1 = 1000, autres = valeur * 100
                counts[i] -= 3;
            }
        }

        // Ajouter les points pour les 1 et 5 restants
        score += counts[0] * 100; // Chaque 1 restant = 100 points
        counts[0] = 0;
        score += counts[4] * 50;  // Chaque 5 restants = 50 points
        counts[4] = 0;

        return score;
    }

    /**
     * Vérifie si les dés forment une suite (1,2,3,4,5,6).
     * @param counts Tableau des occurrences des valeurs des dés.
     * @return true si c'est une suite, false sinon.
     */
    private static boolean isRun(int[] counts) {
        if (counts.length != 6) return false;
        for (int count : counts) {
            if (count != 1) return false; // Une suite nécessite un de chaque
        }
        return true;
    }

    /**
     * Vérifie si les dés forment trois paires.
     * @param counts Tableau des occurrences des valeurs des dés.
     * @return true si trois paires sont présentes, false sinon.
     */
    private static boolean isThreePairs(int[] counts) {
        int pairCount = 0;
        for (int count : counts) {
            if (count == 2) {
                pairCount++;
            } else if (count == 4) {
                pairCount += 2; // Quatre dés = deux paires
            } else if (count != 0) {
                return false; // Toute autre combinaison invalide trois paires
            }
        }
        return pairCount == 3;
    }
}
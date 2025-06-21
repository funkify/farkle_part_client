package io.swagger.client.consoleCode; /**
 * Classe qui gère la logique globale du jeu, incluant les tours et la condition de victoire.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static Game instance;
    private List<Player> players;
    private boolean isGaming;
    private int currentPlayerIndex;
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Constructeur qui demande à l'utilisateur d'entrer les noms des deux joueurs.
     */
    private Game() {
        System.out.println("Bienvenue dans Farkle !");
        System.out.print("Entrez le nom du joueur: ");
        String name1 = SCANNER.nextLine().trim();
        if (name1.isEmpty()) name1 = "Joueur 1"; // Nom par défaut si vide

        //System.out.print("Entrez le nom du joueur 2 : ");
        //String name2 = SCANNER.nextLine().trim();
        //if (name2.isEmpty()) name2 = "Joueur 2"; // Nom par défaut si vide

//        this.players = new Player[]{new Player(name1), new Player(name2)};
        this.currentPlayerIndex = 0;
        this.isGaming = false;
        this.players = new ArrayList<>();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Démarre la partie et boucle jusqu'à ce qu'un joueur gagne.
     */
    public void startGame() {
        /*isGaming = true;
        while (!checkWinCondition() && isGaming) {
            playTurn();
        }
        isGaming = false;*/
        isGaming = true;
        currentPlayerIndex = 0;
    }

    public Player getPlayerById(int id) {
        for (Player p : players) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public int addPlayer(String name) {
        if (players.size() >= 2) return -1;

        boolean id0Used = false;
        boolean id1Used = false;

        for (Player p : players) {
            if (p.getId() == 0) id0Used = true;
            if (p.getId() == 1) id1Used = true;
        }

        int newId = id0Used ? 1 : 0;

        Player player = new Player(name, newId);
        players.add(player);
        for (Player p : players) {
//            System.out.println(p.getId());
        }

        return newId;
    }


    public boolean removePlayer(int idToRemove) {
        boolean removed = players.removeIf(p -> p.getId() == idToRemove);
        if (removed) {
            System.out.println("Player with id " + idToRemove + " removed from game");
            players.forEach(p -> System.out.println("Player " + p.getName() + " " + p.getId()));
        }
        return removed;
    }



    /**
     * Retourne le joueur actuel en fonction de l'index.
     * @return Le joueur actuel.
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Retourne l'index du joueur actuel
     * @return L'inded du joueur actuel
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Gère un tour de jeu pour le joueur actuel.
     */
    public void playTurn() {

        Player currentPlayer = getCurrentPlayer();
        List<Dice> turnDice = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            turnDice.add(new Dice());
        }
        currentPlayer.setDices(turnDice);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(currentPlayer + " joue !  Score : " + currentPlayer.getScore());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        getCurrentPlayer().playTurn();

        endTurn();

    }
    public void endTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }


    public boolean isGaming() {
        return isGaming;
    }

    public void endGame(){
        isGaming = false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getNumberOfPlayers(){
        return players.size();
    }
    public Player getWinner() {
        for (Player player : players) {
            if (player.getScore() >= 10000) {
                return player;
            }
        }
        return null;
    }


    /**
     * Vérifie si un joueur a atteint la condition de victoire (10000 points).
     * @return true si un joueur a gagné, false sinon.
     */
    public boolean checkWinCondition() {
        for (Player player : players) {
            if (player.getScore() >= 10000) {
                System.out.println(player + " a gagné !");
                isGaming = false;
                return true;
            }
        }
        return false;
    }

}
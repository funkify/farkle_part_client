import io.swagger.client.api.DefaultApi;
import io.swagger.client.consoleCode.Player;
import io.swagger.client.model.RestPlayer;
import io.swagger.client.model.RestDices;
import java.util.logging.Level;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        DefaultApi apiInstance;
        apiInstance = new DefaultApi();
        apiInstance.getApiClient().setBasePath("http://127.0.0.1:8090/v1");

        System.out.println("Bienvenue dans Farkle !");

        // Connection du joueur
        RestPlayer player = null;
        while (player == null) {
            System.out.print("Entrez le nom du joueur: ");
            String name1 = SCANNER.nextLine().trim();
            if (name1.isEmpty()) name1 = "Joueur 1";

            try {
                player = apiInstance.name(name1);  // appel API
                System.out.println("Connecté en tant que : " + player.getName() + ", id: " + player.getId() + " score: " + player.getScore());
            } catch (io.swagger.client.ApiException e) {
                System.out.println("Impossible de se connecter. Veuillez réessayer.");
            }
        }

       // En attente du deuxieme joueur

        System.out.println("En attente du deuxième joueur pour commencer la partie...");

        while (true) {
            try {
                int currentId = apiInstance.getCurrentPlayerID();  // appelle GET /currentPlayerId
                if (currentId != -1) {
                    System.out.println("La partie peut commencer !");
                    break;
                }
            } catch (io.swagger.client.ApiException e) {
                System.out.println("Erreur lors de la vérification de l'état du jeu. Nouvelle tentative dans 1 seconde...");
            }

            try {
                Thread.sleep(1000);  // pause 1 seconde entre les tentatives
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // Tour du joueur
        while (true) {
            try {
                int currentPlayerId = apiInstance.getCurrentPlayerID();

                if (currentPlayerId == player.getId()) {
                    // C’est ton tour
                    System.out.println("\nC’est votre tour !");
                    Player clientPlayer = new Player(player.getName(), player.getId());  // ta classe locale
                    clientPlayer.playTurn(apiInstance, player.getName());
                } else {
                    // En attente de l’adversaire
                    System.out.println("En attente de l’autre joueur...");
                    try {
                        RestPlayer winner = apiInstance.getWinner();
                        if (winner != null) {
                            System.out.println("\nFin de la partie !");
                            System.out.println(winner.getName() + " a gagné avec le score de " + winner.getScore());
                            break;  // Fin de la boucle principale
                        }
                    } catch (io.swagger.client.ApiException e) {
                        if (e.getCode() != 400) {
                            System.out.println("Erreur lors de la vérification du gagnant : " + e.getMessage());
                        }
                        // Sinon : pas encore de gagnant, on continue simplement
                    }
                    Thread.sleep(10000);
                }

                // Vérifie s’il y a un gagnant
                try {
                    RestPlayer winner = apiInstance.getWinner();  // GET /winner
                    if (winner != null) {
                        System.out.println("\nFin de la partie !");
                        System.out.println("Le gagnant est : " + winner.getName() + " avec " + winner.getScore() + " points.");
                        break;
                    }
                } catch (io.swagger.client.ApiException e) {
                    // 400 = pas encore de gagnant → on ignore
                }

            } catch (Exception e) {
                System.out.println("Erreur pendant la boucle principale : " + e.getMessage());
                break;
            }
        }







    }



}

package io.swagger.client.consoleCode;

/**
 * Classe représentant un dé avec une valeur aléatoire et une représentation graphique.
 */
public class Dice {
    private int faceValue;

    Dice (int faceValue) {
        this.faceValue = faceValue;
    }
    Dice (){
        roll();
    }

    /**
     * Lance le dé et génère une valeur aléatoire entre 1 et 6.
     */
    public void roll() {
        faceValue = (int)((Math.random() * 6) + 1);
    }


    /**
     * Retourne la valeur actuelle du dé.
     * @return Valeur faciale du dé (entre 1 et 6).
     */
    public int getFaceValue() {
        return faceValue;
    }

    /**
     * Retourne une représentation textuelle du dé (sa valeur).
     * @return Valeur du dé sous forme de chaîne.
     */
    @Override
    public String toString() {
        return faceValue + "";
    }
}
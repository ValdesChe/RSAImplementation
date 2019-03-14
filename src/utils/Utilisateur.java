package utils;

/**
 * Created by ValdoR on 2019-03-12.
 */
public class Utilisateur {
    private static int number = 0;
    private String nom = "DEFAUT";
    private int id;

    public static int getNumber() {
        return number;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public Utilisateur(String nom) {
        this.nom = nom;
        this.id = ++number;
    }
}

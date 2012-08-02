package SanaIndeksi;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Väliaikainen luokka sanan ja rivin jolla se sijaitsee tallentamiseksi.
 * @author heidi
 */
public class Sana {
    String sana;
    int rivi;

    public Sana(String sana, int rivi) {
        this.sana = sana;
        this.rivi = rivi;
    }

    public String getSana() {
        return sana;
    }

    public int getRivi() {
        return rivi;
    }
    
    
}

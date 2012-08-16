/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

/**
 * Luokka huolehtii Tiedosto-olioista
 * @author heidi
 */
public class Tiedosto {
    /**
     * Tiedoston nimi
     */
    String nimi;
    /**
     * Tiedoston ensimmäinen rivi kaikkien rivinumeroiden joukossa.
     */
    int alku;
    /**
     * Tiedoston viimeinen rivi kaikkien rivinumeroiden joukossa
     */
    int loppu;

    /**
     * Konstruktori luo Tiedosto-olion, jolla on nimi ja ensimmäisen rivin numero.
     * @param nimi
     * @param alku 
     */
    public Tiedosto(String nimi, int alku) {
        this.nimi = nimi;
        this.alku = alku;
    }
    
    /**
     * Setteri asettaa oliolle viimeisen rivin numeron.
     * @param loppu 
     */
    public void setLoppu(int loppu) {
        this.loppu = loppu;
    }
}

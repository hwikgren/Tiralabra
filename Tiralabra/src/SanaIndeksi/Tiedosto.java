/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.io.Serializable;

/**
 * Luokka huolehtii Tiedosto-olioista
 * @author heidi
 */
public class Tiedosto implements Serializable {
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
     * @param nimi String-muotoinen tiedoston nimi.
     * @param alku Tiedoston int-muotoinen ensimmäisen rivin numero.
     */
    public Tiedosto(String nimi, int alku) {
        this.nimi = nimi;
        this.alku = alku;
    }
    
    /**
     * Setteri asettaa oliolle viimeisen rivin numeron.
     * @param loppu Int-muotoinen tiedoston viimeisen rivin numero.
     */
    public void setLoppu(int loppu) {
        this.loppu = loppu;
    }
}

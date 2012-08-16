/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.util.StringTokenizer;

/**
 *
 * @author heidi
 */
public class Muotoilija {

    public Muotoilija() {
    }
    
    /**
     * Metodi poistaa annetun sanan alusta ja lopusta mahdolliset merkit, jotka eivät ole kirjaimia.
     * <p>Tarkistaakseen onko merkki kirjain, metodi kutsuu eiKirjain-metodia.
     * @param sana
     * @return Palauttaa sanan ilman alussa tai lopussa olevia erikoismerkkejä.
     */
    public String trimmaa(String sana) {
        String karsittuSana = sana.trim();
        int alku = 0;
        while (karsittuSana.length()>0 && eiKirjain(karsittuSana.charAt(0))) {
            karsittuSana = karsittuSana.substring(1);
        }
        while (karsittuSana.length()>0 && eiKirjain(karsittuSana.charAt(karsittuSana.length()-1))) {
            karsittuSana = karsittuSana.substring(0, karsittuSana.length()-1);
        }
        return karsittuSana;
    }
    
    /**
     * Metodi tarkastaa onko annettu merkki kirjain vai ei.
     * @param merkki
     * @return true jos merkki ei ole kirjain.
     */
    private boolean eiKirjain(char merkki) {
        return ("abcdefghijklmnopqrstuvwxyzåäö".indexOf(merkki) ==-1);
    }
    
}

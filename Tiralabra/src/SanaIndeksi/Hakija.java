/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Luokka hoitaa haetun sanan hakemisen puusta ja sitä vastaavien rivien tulostamisen. 
 * @author heidi
 */
public class Hakija {
    
    public int viimeisinRivi;

    public Hakija() {
    }
    
    /**
     * Metodi printaa kaikki annetun sanan sisältävät rivit.
     * Hakee haetun sanan esiintymät puusta. Printaa ArrayListissa olevan rivin mikäli sitä ei ole juuri tulostettu.
     * Jos sanaa ei löytynyt, kertoo siitä käyttäjälle.
     * @param haettu 
     */
    public void printtaa(String haettu, Tallentaja tallentaja) {
        TreeMap<Integer, Sana> sanat = tallentaja.getPuu();
        ArrayList<String> rivit = tallentaja.getRivit();
        viimeisinRivi = 0;
        for (int i=0; i<sanat.size(); i++) {
            Integer luku = new Integer(i);
            Sana puunSana = sanat.get(luku);
            if (puunSana.getSana().equals(haettu)) {
                int sananRivi = puunSana.getRivi()+1;
                if (sananRivi >viimeisinRivi) {
                    System.out.println(""+(sananRivi)+". "+rivit.get(sananRivi-1));
                    viimeisinRivi = sananRivi;
                }
            }
        }
        if (viimeisinRivi == 0) {
            System.out.println("Sanaa \""+haettu+"\" ei löytynyt tiedostosta!");
        }
    }
    
}

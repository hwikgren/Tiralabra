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
    /**
     * Puuolento.
     */
    Puu puu;
    
    /**
     * Tallentaja-olento
     */
    Tallentaja tallentaja;

    /**
     * Konstruktori luo Hakija-olennon, jolla on tieto käytetystä puusta ja ohjelman tallentaja olennosta.
     */
    public Hakija(Puu puu, Tallentaja tallentaja) {
        this.puu = puu;
        this.tallentaja = tallentaja;
    }
    
    /**
     * Metodi printaa kaikki annetun sanan sisältävät rivit.<p>
     * Pyytää tallentajalta viimeisimmän rivit sisältävän taulukon.
     * Jos haetaan sanan osaa (sanan perässä on *), kutsuu puun etsiOsa-metodia.
     * Muuten kutsuu etsiSana-metodia.
     * Printaa taulukosta saamaansa listaa vastaavat rivit.
     * Jos sanaa ei löytynyt, kertoo siitä käyttäjälle.
     * @param haettu 
     */
    public void printtaa(String haettu) {
        ArrayList<String> tekstit = tallentaja.rivit;
        int[] rivit;
        if (haettu.charAt(haettu.length()-1) == '*') {
            rivit = puu.etsiOsa(haettu);
        }
        else {
            rivit = puu.etsiSana(haettu);
        }
        if (rivit != null) {
            for (int i=0; i<rivit.length; i++) {
                    if (rivit[i] > 0) {
                        System.out.println(tekstit.get(rivit[i]));
                    }
            }
        }
        else {
            System.out.println("Sanaa \""+haettu+"\" ei löydy tiedostosta!");
        }
    }
    
}

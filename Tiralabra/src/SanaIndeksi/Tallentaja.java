package SanaIndeksi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Luokka hoitaa annetun tiedoston rivien ja sanojen tallentamisen.
 * @author heidi
 */
public class Tallentaja {
    
    public ArrayList<String> rivit;
    public TreeMap<Integer, Sana> sanat;
    public int viimeisinRivi;

    public Tallentaja() {
    }
    
    /**
     * Metodi tallentaa annetun tiedoston rivit ArrayListiin ja sanat puuhun.
     * Ennen sanojen lisäämistä metodi kutsuu trimmaa-metodia.
     * @param tiedosto
     * @throws FileNotFoundException 
     */
    public void tallenna(File tiedosto) throws FileNotFoundException {
        Scanner syotto = new Scanner(tiedosto);
        
        rivit = new ArrayList<String>();
        sanat = new TreeMap<Integer, Sana>();
        int riviLaskin = 0;
        Integer avain = 0;
        
        while (syotto.hasNextLine()) {
            String rivi = syotto.nextLine();
            rivit.add(rivi);
            
            String karsittuRivi = rivi.trim();
            StringTokenizer sanoittaja = new StringTokenizer(karsittuRivi);
            while (sanoittaja.hasMoreTokens()) {
                String sana = sanoittaja.nextToken().toLowerCase();
                String karsittuSana = trimmaa(sana);
                Sana sanaOlio = new Sana(karsittuSana, riviLaskin);
                sanat.put(avain, sanaOlio);
                avain++;
            }
            riviLaskin++;
        }
    }
    
    /**
     * Metodi poistaa annetun sanan alusta ja lopusta mahdolliset merkit, jotka eivät ole kirjaimia.
     * Metodi kutsuu eiKirjain-metodia.
     * @param sana
     * @return Palauttaa sanan ilman alussa tai lopussa olevia erikoismerkkejä.
     */
    private String trimmaa(String sana) {
        String karsittuSana = sana.trim();
        int alku = 0;
        while (eiKirjain(karsittuSana.charAt(0))) {
            karsittuSana = karsittuSana.substring(1);
        }
        while (eiKirjain(karsittuSana.charAt(karsittuSana.length()-1))) {
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
    
    public TreeMap<Integer, Sana> getPuu() {
        return sanat;
    }
    
    public ArrayList<String> getRivit() {
        return rivit;
    }
}

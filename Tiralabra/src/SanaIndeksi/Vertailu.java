/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Luokka testaa hae-metodin toimintaa ja vertaa sitä javan TreeSet-rakenteen toimintaan. 
 * @author heidi
 */
public class Vertailu {
    
    /**
     * Testataan hae-metodin toimintaa kun puussa on numeroita 0-99999, 
     * jokaisella solmulla on 10 lasta (0-9) ja jokainen numero sijaitsee yhdessä rivillä.<p>
     * Samanlaajuinen testi tehdään javan TreeSet-rakenteelle johon on syötetty samat numerot.
     * @param args 
     */
    public static void main(String[] args) {
        TreeMap<String, Integer> punaMusta= new TreeMap<String, Integer>();
        Tallentaja kakkonen = new Tallentaja();
        Hakija toinen = new Hakija(kakkonen);
        
        String[] haut = new String[5];
        haut[0] = "6";
        haut[1] = "15";
        haut[2] = "189";
        haut[3] = "5432";
        haut[4] = "35879";
        
        int rivimaara;
        for (int i=0; i<100000; i++) {
            String sana = ""+i;
            kakkonen.sanaPuu.lisaaSana(sana, i);
            punaMusta.put(sana, i);
        }
        long alkuAika;
        long loppuAika;
        //System.out.println("Verryttely:");
        for (int k=0; k<200; k++) {
            for (int j=0; j<haut.length; j++) {
            long yhteisaika = 0;
            for (int i=0; i<1000; i++) {
                alkuAika = System.nanoTime();
                try {
                    toinen.hae(haut[j]);
                }
                finally {
                    loppuAika = System.nanoTime();
                }
                yhteisaika += (loppuAika-alkuAika);
            }
            //System.out.println("Sanan "+haut[j]+" hakuun meni: "+(yhteisaika/1000));
            }
        }
        
        
        
        System.out.println("TRIE:");
        for (int j=0; j<haut.length; j++) {
            long yhteisaika = 0;
            for (int i=0; i<1100; i++) {
                alkuAika = System.nanoTime();
                try {
                    toinen.hae(haut[j]);
                }
                finally {
                    loppuAika = System.nanoTime();
                }
                if (i>99) {
                    yhteisaika += (loppuAika-alkuAika);
                }
            }
            System.out.println("Sanan "+haut[j]+" hakuun meni: "+(yhteisaika/1000));
        }
        
        for (int k=0; k<200; k++) {
            for (int j=0; j<haut.length; j++) {
            long yhteisaika = 0;
            for (int i=0; i<1000; i++) {
                alkuAika = System.nanoTime();
                try {
                    punaMusta.get(haut[j]);
                }
                finally {
                    loppuAika = System.nanoTime();
                }
                yhteisaika += (loppuAika-alkuAika);
            }
            //System.out.println("Sanan "+haut[j]+" hakuun meni: "+(yhteisaika/1000));
            }
        }
        
        System.out.println("\nPunamusta:");
        for (int j=0; j<haut.length; j++) {
            long yhteisaika = 0;
            for (int i=0; i<1100; i++) {
                alkuAika = System.nanoTime();
                try {
                    punaMusta.get(haut[j]);
                }
                finally {
                    loppuAika = System.nanoTime();
                }
                if (i>99) {
                    yhteisaika += (loppuAika-alkuAika);
                }
            }
            System.out.println("Sanan "+haut[j]+" hakuun meni: "+(yhteisaika/1000));
        }
    }
}

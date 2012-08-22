package SanaIndeksi;


import SanaIndeksi.Tallentaja;
import SanaIndeksi.Tiedosto;
import com.sun.tools.internal.xjc.api.S2JJAXBModel;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heidi
 */
public class AikaTestit {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        String[] tiedostot = new String[4];
        tiedostot[0] = "Koe.txt";
        tiedostot[1] = "Prometheus.txt";
        tiedostot[2] = "Pitkia.txt";
        tiedostot[3] = "Pitka.txt";
        //TESTATAAN TALLENNUSTA
        Tallentaja kakkonen = new Tallentaja();
        File tiedosto;
        tiedosto = new File("Prometheus.txt");
        
        long loppuAika;
        long alkuAika = System.nanoTime();
        try {
            kakkonen.tallenna("Prometheus.txt", tiedosto);
        }
        finally {
            loppuAika = System.nanoTime();
        }
        long kulunutAika = loppuAika - alkuAika;
        double sekunnit = (double)kulunutAika / 1000000000.0;
        System.out.println("Tallenukseen meni: "+sekunnit);
        
        
        //TESTATAAN HAKU JA PRINTTAUS
        
        tiedosto = new File("Koe.txt");
        kakkonen.tallenna("Koe.txt", tiedosto);
        tiedosto = new File("Pitkia.txt");
        kakkonen.tallenna("Pitkia.txt", tiedosto);
        
        Hakija toinen = new Hakija(kakkonen);
        final long loppuAika2;
        final long alkuAika2 = System.nanoTime();
        try {
            toinen.printtaa("the / he", null);
        }
        finally {
            loppuAika2 = System.nanoTime();
        }
        kulunutAika = loppuAika2 - alkuAika2;
        sekunnit = (double)kulunutAika / 1000000000.0;
        int rivit = toinen.rivit.length;
        for (int i=toinen.rivit.length-1; i<0; i--) {
            if (toinen.rivit[i] == 0) {
                rivit--;
            }
            else break;
        }
        System.out.println("Rivejä: "+rivit);
        System.out.println("Hakuun meni: "+sekunnit);
        
        
        //TESTATAAN PELKKÄÄ hakua
        
        
        int[] tulos;
        final long loppuAika3;
        final long alkuAika3 = System.nanoTime();
        try {
            tulos = toinen.hae("him");
        }
        finally {
            loppuAika3 = System.nanoTime();
        }
        //kulunutAika = loppuAika3 - alkuAika3;
        //sekunnit = (double)kulunutAika / 1000000000.0;
        rivit = tulos.length;
        for (int i=tulos.length-1; i>0; i--) {
            if (tulos[i] == 0) {
                rivit--;
            }
            else break;
        }
        System.out.println("Rivejä: "+rivit);
        System.out.println("Hakuun meni: "+(loppuAika3 - alkuAika3));
        
        
        //TESTATAAN pitkien hakua
        
        
        
        final long loppuAika4;
        final long alkuAika4 = System.nanoTime();
        try {
            //tulos = toinen.hae("kolmivaihekilowattituntimittari");
            tulos = toinen.hae("jarjestelmallisentelentelemattomyydellansakaan");
        }
        finally {
            loppuAika4 = System.nanoTime();
        }
        //kulunutAika = loppuAika3 - alkuAika3;
        //sekunnit = (double)kulunutAika / 1000000000.0;
        rivit = tulos.length;
        for (int i=tulos.length-1; i>0; i--) {
            if (tulos[i] == 0) {
                rivit--;
            }
            else break;
        }
        System.out.println("Rivejä: "+rivit);
        System.out.println("Hakuun meni: "+(loppuAika4 - alkuAika4));
        
        
        //TESTATAAN PELKKÄÄ ETSIMISTÄ
        
        Puu sanapuu = kakkonen.sanaPuu;
        for (int i=0; i<100000000; i++) {
            sanapuu.lisaaSana("i", 1);
        }

        final long loppuAika5;
        final long alkuAika5 = System.nanoTime();
        try {
            tulos = toinen.etsi("brother");
        }
        finally {
            loppuAika5 = System.nanoTime();
        }
        //kulunutAika = loppuAika3 - alkuAika3;
        //sekunnit = (double)kulunutAika / 1000000000.0;
        rivit = tulos.length;
        for (int i=tulos.length-1; i>0; i--) {
            if (tulos[i] == 0) {
                rivit--;
            }
            else break;
        }
        System.out.println("Rivejä: "+rivit);
        System.out.println("Hakuun meni: "+(loppuAika5 - alkuAika5));
        
        
        //Testataan numeroilla
        
        final long loppuAika6;
        final long alkuAika6 = System.nanoTime();
        try {
            toinen.etsi("890028");
        }
        finally {
            loppuAika6 = System.nanoTime();
        }
        System.out.println("Hakuun meni: "+(loppuAika6 - alkuAika6));
    }
    
    
    
}

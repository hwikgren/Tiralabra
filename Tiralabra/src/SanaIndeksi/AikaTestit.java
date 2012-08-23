package SanaIndeksi;


import com.sun.tools.internal.xjc.api.S2JJAXBModel;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Luokka testaa ajankäyttöä erilaisissa tilanteissa.<p>
 * Luokka ei vaikuta ohjelman toimintaan millään tavoin ja se täytyy ajaa yksinään.
 * @author heidi
 */
public class AikaTestit {
    
    /**
     * Testaa tallentamiseen ja erilaisiin hakuihin kuluvaa aikaa.
     * Hauista testataan printtaamista, hae-metodia ja etsi-metodia eripituisilla hauilla,
     * joiden tuloksena on erimääriä rivejä.
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        String[] tiedostot = new String[4];
        tiedostot[0] = "Koe.txt";
        tiedostot[1] = "Prometheus.txt";
        tiedostot[2] = "Pitkia.txt";
        tiedostot[3] = "1001.txt";
        long loppuAika;
        long alkuAika;
        long kulunutAika;
        double sekunnit;
        Tallentaja kakkonen = new Tallentaja();
        Hakija toinen = new Hakija(kakkonen);
        File tiedosto;
        String[] haut = new String[6];
        haut[0] = "sun";
        haut[1] = "him*";
        haut[2] = "the&he";
        haut[3] = "(the / he)";
        haut[4] = "(him & (he / her))";
        haut[5] = "jarjestelmallisentelentelemattomyydellansakaan";
        int[] rivit;
        int rivimaara;
        
        
        //Testataan hakua kun rivejä on vähän
        System.out.println("TESTATAAN HAE-METODIA KUN RIVEJÄ ON TALLENNETTU VÄHÄN");
        tiedosto = new File("Pitkia.txt");
        kakkonen.tallenna("Pitkia.txt", tiedosto);
        rivimaara = 0;
        for (int i=0; i<haut.length; i++) {
            for (int k=0; k<10; k++) {
                alkuAika = System.nanoTime();
                try {
                    //rivit = toinen.hae(haut[i]);
                    rivit = toinen.hae("sun");
                }
                finally {
                    loppuAika = System.nanoTime();
                }

                rivimaara = rivit.length;
                for (int j=rivit.length-1; j>=0; j--) {
                    if (rivit[j] == 0) {
                        rivimaara--;
                    }
                    else break;
                }
                System.out.println("Sanan "+haut[i]+" hakuun meni: "+(loppuAika - alkuAika));
            System.out.println("Sanan sun hakuun meni: "+(loppuAika-alkuAika));
              }
            
            System.out.println("Rivejä: "+rivimaara);
                
                System.out.println("");
        }
        
        //TESTATAAN TALLENNUSTA
        System.out.println("\nTESTATAAN TALLENNUSTA");
        for (int i=0; i<tiedostot.length; i++) {
            tiedosto = new File(tiedostot[i]);

            alkuAika = System.nanoTime();
            try {
                kakkonen.tallenna(tiedostot[i], tiedosto);
            }
            finally {
                loppuAika = System.nanoTime();
            }
            System.out.println("Tiedoston "+tiedostot[i]+" tallenukseen meni: "+(loppuAika - alkuAika));
            System.out.println("");
        }
        
        
        //TESTATAAN HAKU JA PRINTTAUS
        System.out.println("TESTATAAN HAKU PRINTTAUKSELLA");
        for (int i=0; i<haut.length; i++) {
            alkuAika = System.nanoTime();
            try {
                toinen.printtaa(haut[i], null);
            }
            finally {
                loppuAika = System.nanoTime();
            }
            rivit = toinen.rivit;
            rivimaara = rivit.length;
            for (int j=rivit.length-1; j>=0; j--) {
                if (rivit[j] == 0) {
                    rivimaara--;
                }
                else break;
            }
            System.out.println("Rivejä: "+rivimaara);
            System.out.println("Sanan "+haut[i]+" hakuun meni: "+(loppuAika - alkuAika));
            System.out.println("");
        }
        
        
        //TESTATAAN PELKKÄÄ hakua monesta rivistä
        System.out.println("TESTATAAN HAKU-METODIA KUN RIVEJÄ ON TIEDOSTOSSA PALJON");
        for (int i=0; i<haut.length; i++) {
            for (int k=0; k<10; k++) {
                alkuAika = System.nanoTime();
                try {
                    rivit = toinen.hae(haut[i]);
                }
                finally {
                    loppuAika = System.nanoTime();
                }

                rivimaara = rivit.length;
                for (int j=rivit.length-1; j>=0; j--) {
                    if (rivit[j] == 0) {
                        rivimaara--;
                    }
                    else break;
                }
                System.out.println("Sanan "+haut[i]+" hakuun meni: "+(loppuAika - alkuAika));
            }
            System.out.println("Rivejä: "+rivimaara);
            
            System.out.println("");
        }
        
        
        
        
        //TESTATAAN PELKKÄÄ ETSIMISTÄ
        System.out.println("TESTATAAN ETSI-METODIA ERIPITUISILLA SANOILLA");
        haut[0] = "h";
        haut[2] = "brother";
        haut[3] = "kolmivaihekilowatti*";
        haut[4] = "kolmivaihekilowattituntimittari";

        for (int i=0; i<haut.length; i++) {
            for (int k=0; k<10; k++) {
                alkuAika = System.nanoTime();
                try {
                    rivit = toinen.etsi(haut[i]);
                }
                finally {
                    loppuAika = System.nanoTime();
                }

                rivimaara = rivit.length;
                for (int j=rivit.length-1; j>=0; j--) {
                    if (rivit[j] == 0) {
                        rivimaara--;
                    }
                    else break;
                }
                
                System.out.println("Sanan "+haut[i]+" hakuun meni: "+(loppuAika - alkuAika));
            }
            System.out.println("Rivejä: "+rivimaara);
            System.out.println("");
        }
        
        
        
    }
    
    
    
}

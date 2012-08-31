package SanaIndeksi;



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
        long loppuAika = 0;
        long alkuAika = 0;
        long kulunutAika;
        double sekunnit;
        Tallentaja kakkonen = new Tallentaja();
        Hakija toinen = new Hakija(kakkonen);
        File tiedosto;
        String[] haut = new String[6];
        haut[0] = "sun";
        haut[1] = "sun&him*";
        haut[2] = "brother/(sun&him*)";
        haut[3] = "(brother/a)/(sun&him*)";
        haut[4] = "(sun / sun) / (sun / sun)";
        haut[5] = "sun / sun";
        
        
        
        //System.out.println("Alkuverryttely:");
        tiedosto = new File("Pitkia.txt");
        kakkonen.tallenna("Pitkia.txt", tiedosto);
        int rivimaara = 0;
        int[] rivit = null;
        for (int j=0; j<100; j++) {
            for (int i=0; i<haut.length; i++) {
            
            long yhteisaika = 0;
            for (int k=0; k<1100; k++) {
                alkuAika = System.nanoTime();
                try {
                    rivit = toinen.hae(haut[i]);
                    //rivit = toinen.hae("sun");
                }
                finally {
                    loppuAika = System.nanoTime();
                }
                if (k>99) {
                    yhteisaika += (loppuAika-alkuAika);
                }
            }
            
            //System.out.println("Sanan "+haut[i]+" hakuun meni: "+(yhteisaika/1000));
            
            }
        }
        
        System.out.println("TESTATAAN HAE-METODIA KUN RIVEJÄ ON TALLENNETTU VÄHÄN");
        for (int i=0; i<haut.length-2; i++) {
            
            long yhteisaika = 0;
            for (int k=0; k<1100; k++) {
                alkuAika = System.nanoTime();
                try {
                    rivit = toinen.hae(haut[i]);
                    //rivit = toinen.hae("sun");
                }
                finally {
                    loppuAika = System.nanoTime();
                }
                if (k>99) {
                    yhteisaika += (loppuAika-alkuAika);
                }
            }
            rivimaara = rivit.length;
                for (int j=rivit.length-1; j>=0; j--) {
                    if (rivit[j] == 0) {
                        rivimaara--;
                    }
                    else break;
                }
            System.out.println("Sanan "+haut[i]+" hakuun meni: "+(yhteisaika/1000));
            System.out.println("Rivejä: "+rivimaara);
            System.out.println("");
        }
        
        //Tallennetaan lisää tiedostoja
        for (int i=0; i<tiedostot.length; i++) {
            tiedosto = new File(tiedostot[i]);
            kakkonen.tallenna(tiedostot[i], tiedosto);
        }
        
        
        //TESTATAAN HAKU JA PRINTTAUS
        System.out.println("TESTATAAN HAKU PRINTTAUKSELLA");
        for (int i=0; i<haut.length-2; i++) {
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
        
        
        //TESTATAAN PELKKÄÄ hakua eri määrillä hakusanoja
        System.out.println("TESTATAAN HAKU-METODIA KUN RIVEJÄ ON TIEDOSTOSSA PALJON");
        for (int i=0; i<haut.length-2; i++) {
            long yhteisaika = 0;
            for (int k=0; k<1100; k++) {
                alkuAika = System.nanoTime();
                try {
                    rivit = toinen.hae(haut[i]);
                    //rivit = toinen.hae("sun");
                }
                finally {
                    loppuAika = System.nanoTime();
                }
                if (k>99) {
                    yhteisaika += (loppuAika-alkuAika);
                }
            }
            rivimaara = rivit.length;
                for (int j=rivit.length-1; j>=0; j--) {
                    if (rivit[j] == 0) {
                        rivimaara--;
                    }
                    else break;
                }
            System.out.println("Sanan "+haut[i]+" hakuun meni: "+(yhteisaika/1000));
            System.out.println("Rivejä: "+rivimaara);
            System.out.println("");
        }
        
        //TESTATAAN Hakua eri pituisilla sanoilla
        System.out.println("TESTATAAN hae-METODIA ERIPITUISILLA SANOILLA");
        haut[0] = "a";
        haut[1] = "sun";
        haut[2] = "brother";
        haut[3] = "kolmivaihekilowatti*";
        haut[4] = "kolmivaihekilowattituntimittari";
        haut[5] = "jarjestelmallisentelentelemattomyydellansakaan";

        for (int i=0; i<haut.length; i++) {
            long yhteisaika = 0;
            for (int k=0; k<1100; k++) {
                alkuAika = System.nanoTime();
                try {
                    rivit = toinen.hae(haut[i]);
                    //rivit = toinen.hae("sun");
                }
                finally {
                    loppuAika = System.nanoTime();
                }
                if (k>99) {
                    yhteisaika += (loppuAika-alkuAika);
                }
            }
            rivimaara = rivit.length;
                for (int j=rivit.length-1; j>=0; j--) {
                    if (rivit[j] == 0) {
                        rivimaara--;
                    }
                    else break;
                }
            System.out.println("Sanan "+haut[i]+" hakuun meni: "+(yhteisaika/1000));
            System.out.println("Rivejä: "+rivimaara);
            System.out.println("");
        }
        
        
        //TESTATAAN PELKKÄÄ ETSIMISTÄ
        System.out.println("TESTATAAN ETSI-METODIA ERIPITUISILLA SANOILLA");
        haut[0] = "a";
        haut[1] = "sun";
        haut[2] = "brother";
        haut[3] = "kolmivaihekilowatti*";
        haut[4] = "kolmivaihekilowattituntimittari";
        haut[5] = "jarjestelmallisentelentelemattomyydellansakaan";

        for (int i=0; i<haut.length; i++) {
            long yhteisaika = 0;
            for (int k=1; k<=1100; k++) {
                alkuAika = System.nanoTime();
                try {
                    rivit = toinen.etsi(haut[i]);
                }
                finally {
                    loppuAika = System.nanoTime();
                }
                if (k>100) {
                    yhteisaika += (loppuAika-alkuAika);
                }
            }
            rivimaara = rivit.length;
                for (int j=rivit.length-1; j>=0; j--) {
                    if (rivit[j] == 0) {
                        rivimaara--;
                    }
                    else break;
                }
            System.out.println("Sanan "+haut[i]+" hakuun meni: "+(yhteisaika/1000));
            System.out.println("Rivejä: "+rivimaara);
            System.out.println("");
        }
        
        
        
    }
    
    
    
}

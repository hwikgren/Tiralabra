
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author heidi
 */
public class Tiralabra {

    /**
     * @param args the command line arguments
     */
    private static Scanner lukija = new Scanner(System.in);
    
    private static ArrayList<String> rivit;
    private static TreeMap<Integer, Sana> sanat;
    
    private static void tallenna(File tiedosto) throws FileNotFoundException {
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
    
    private static String trimmaa(String sana) {
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
    
    private static boolean eiKirjain(char merkki) {
        return ("abcdefghijklmnopqrstuvwxyzåäö".indexOf(merkki) ==-1);
    }
    
    private static void printtaa(String haettu) {
        int viimeisinRivi = 0;
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
    
    
    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Anna talletettavan tiedoston nimi:");
        String tiedostoNimi = lukija.nextLine();
        File tiedosto = new File(tiedostoNimi);
        
        if (!tiedosto.exists()) {
            System.out.println("Tiedostoa "+ tiedostoNimi + " ei löydy!");
            return;
        }
        
        tallenna(tiedosto);
        
        System.out.println("Anna haettava sana:");
        String haettu = lukija.nextLine();
        printtaa(haettu);
    }
}

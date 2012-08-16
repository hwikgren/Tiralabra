/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

/**
 * Luokka huolehtii sanojen ja rivien säilömisestä solmuihinsa. 
 * @author heidi
 */
public class Puu {
    /**
     * Puun juurisolmu.
     */
    public PuuSolmu juuri;

    /**
     * Konstruktori luo puulle tyhjän juurisolmun.
     */
    public Puu() {
        juuri = new PuuSolmu(' ', 0, false);
    }
    
    /**
     * Metodi lisää sanan puuhun.<p>
     * Lähtee liikkeelle juurisolmusta. Sana käydään läpi kirjain kirjaimelta. 
     * Jos kirjain on sanan viimeinen, asetaan boolean arvo "vika" trueksi.
     * Hakee solmun lapsen, jolla on käsittelyssä oleva kirjain.
     * Jos lapsi löytyy, kutsuu lapsisolmun setRivi-metodia.
     * Jos lasta ei ollut ennestään, kutsuu solmun setLapsi-metodia.
     * Siirtyy lapsi solmuun ja seuraavaan kirjaimeen.
     * @param sana
     * @param rivi 
     */
    public void lisaaSana(String sana, int rivi) {
        //käsittelyssä ensin juuri.
        PuuSolmu nyt = juuri;
        boolean vika = false;
        //Käy sanan läpi.
        for (int i=0; i<sana.length(); i++) {
            char kirjain = sana.charAt(i);
            //Jos viimeinen kirjain
            if (i == sana.length()-1) {
                vika = true;
            }
            //Hakee lapsen, jossa haettu kirjain
            PuuSolmu lapsi = nyt.getLapsi(kirjain);
            //Jos lapsi löytyi
            if (lapsi != null) {
                nyt = lapsi;
                nyt.setRivi(rivi, vika);
            }
            //Muuten luodaan lapsisolmu
            else {
                nyt.setLapsi(kirjain, rivi, vika);
                nyt = nyt.getLapsi(kirjain);
            }
        }
    }
    
    /**
     * Metodi hakee listan riveistä, joilla esiintyy haettu kokonainen sana.<p>
     * Lähtee liikkelle juurisolmusta.
     * Jos lapset loppuvat ennen kuin haetun sanan kirjaimet, palauttaa null.
     * Muuten palauttaa viimeisen kirjaimen listan riveistä, jolla sana esiintyy.
     * @param sana
     * @return 
     */
    public int[] etsiSana(String sana) {
        PuuSolmu nyt = juuri;
        int[] palautus;
        //Etsii lapsisolmuista seuraavan kirjaimen ja siirtyy eteenpäin
        for (int i=0; i<sana.length(); i++) {
            char kirjain = sana.charAt(i);
            //Jos kirjaimen sisältävää sanaa ei löydy, sanaa ei ole indeksissä.
            PuuSolmu lapsi = nyt.getLapsi(kirjain);
            if (lapsi == null) {
                return null;
            }
            else {
                nyt = lapsi;
            }
        }
        palautus = nyt.sanaRivit;
        return palautus;
    }
    
    /**
     * Metodi hakee listan riveistä, joilla esiintyy haettu sanan osa.<p>
     * Lähtee liikkelle juurisolmusta ja käy läpi haetun sanan kirjaimet, 
     * (paitsi viimeisen joka on *).
     * Jos lapset loppuvat ennen kuin haetun sanan kirjaimet, palauttaa null.
     * Muuten palauttaa viimeisen kirjaimen listan riveistä, jolla sana ja kaikki sen "lapsisanat" esiintyvät.
     * @param sana
     * @return 
     */
    public int[] etsiOsa(String sana) {
        PuuSolmu nyt = juuri;
        int[] palautus;
        for (int i=0; i<sana.length()-1; i++) {
            char kirjain = sana.charAt(i);
            PuuSolmu lapsi = nyt.getLapsi(kirjain);
            if (lapsi == null) {
                return null;
            }
            else {
                nyt = lapsi;

            }
        }    
        palautus = nyt.rivit;
        return palautus;
    }
}

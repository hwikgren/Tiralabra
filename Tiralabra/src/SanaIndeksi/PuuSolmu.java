/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.io.Serializable;

/**
 * Luokka huolehtii puun solmuista ja niihin talletetuista rivitiedoista. 
 * @author heidi
 */
public class PuuSolmu implements Serializable {
    /**
     * Solmuun liittyvä kirjain.
     */
    public char kirjain;
    /**
     * Lista riveistä, joilla kaikki sanat jotka alkavat näin, esiintyvät.
     */
    public int[] rivit;
    /**
     * Pitää kirjaa rivit-taulukossa olevien rivien määrästä.
     */
    public int rivienMaara;
    /**
     * Lista riveistä, joilla kyseiseen kirjaimeen päättyvä sana esiintyy.
     */
    public int[] sanaRivit;
    /**
     * Pitää kirjaa sanaRivit-taulukossa olevien rivein määrästä.
     */
    public int sanaRiveja;
    /**
     * Lista lapsisolmuista.
     */
    public PuuSolmu[] lapset;
    /**
     * Pitää kirjaa lapset-taulukossa olevien lapsisolmujen määrästä.
     */
    public int lastenMaara;

    /**
     * Konstruktori luo solmun, jolla on annettu kirjain.<p>
     * Luo rivit-, sanaRivit- ja lapset-taulukot.
     * Asettaa lasten määrän nollaksi.
     * Säilöö annetun rivin rivit-taulukkoon ja asettaa rivien määrän yhdeksi.
     * Asettaa sanaRivien määrän nollaksi.
     * Jos kirjain on sanan viimeinen, lisää annetun rivin sanaRivit-taulukkoon
     * ja kasvattaa sanaRivien määrää.
     * @param kirjain
     * @param rivi
     * @param vika 
     */
    public PuuSolmu(char kirjain, int rivi, boolean vika) {
        this.kirjain = kirjain;
        this.rivit = new int[20];
        this.sanaRivit = new int[20];
        this.lapset = new PuuSolmu[40];
        lastenMaara = 0;
        rivit[0] = rivi;
        rivienMaara = 1;
        sanaRiveja = 0;
        if (vika) {
            sanaRivit[sanaRiveja++] = rivi;
        }
    }

    /**
     * Getteri palauttaa lasten joukosta halutun kirjaimen sisältävän solmun.<p>
     * Jos solmulla on lapsia, kutsuu binäärihakua saadakseen lapsen indeksin.
     * Palauttaa indeksiä vastaavan solmun lapset-taulukosta.
     * Jos kirjainta vastaavaa lasta ei löydy, palauttaa null.
     * @param kirjain
     * @return 
     */
    public PuuSolmu getLapsi(char kirjain) {
        // Jos lapset-taulukko ei ole tyhjä.
        if (lapset != null) {
            //Haetaan indeksi, jossa haetun kirjaiminen lapsi on taulukossa.
            int indeksi = haeKirjain(kirjain);
            //Jos indeksi on positiivinen, lapsi löytyi ja palautetaan kyseinen solmu.
            if (indeksi > -1) {
                return lapset[indeksi];
            }
        }
        //Muuten palautetaan null.
        return null;
    }
    
    /**
     * Metodi asettaa solmulle lapsen.<p>
     * Jos lapsia ei ole ennestään, asettaa uuden solmun lapset-taulukon ensimmäiselle paikalle.
     * Muuten kutsutaan binäärihakua. Jos solmulla ei vielä ole lasta jolla on haluttu kirjain,
     * siirretään tarvittava määrä solmuja ja sijoitetaan uusi solmu oikeaan paikkaan aakkosissa.
     * Suurennetaan lasten määrän laskuria.
     * @param kirjain
     * @param rivi
     * @param vika 
     */
    public void setLapsi(char kirjain, int rivi, boolean vika) {  
        // jos lapsia ei ole ennestään
        if (lastenMaara == 0) {
            lapset[0] = new PuuSolmu(kirjain, rivi, vika);
            lastenMaara++;
        }
        //muuten haetaan kohta, johon uusi solmu tulee aakkosissa
        else {
            int paikka = haeKirjain(kirjain);
            if (paikka < 0) {
                if (lastenMaara == lapset.length) {
                    lapset = kasvataLapsia(lapset, lastenMaara);
                }
                paikka = -(paikka)-1;
                for (int i=lastenMaara-1; i>=paikka; i--) {
                    lapset[i+1] = lapset[i];
                }
                lapset[paikka] = new PuuSolmu(kirjain, rivi, vika);
                lastenMaara++;
            }
        }
    }
    
    /**
     * Metodi asettaa jos valmiille solmulle uuden rivin.<p>
     * Ensin kutsutaan rivin tarkistusta. Jos riviä ei vielä ole kyseisellä solmulla,
     * kutsutaan lisaaRivi-metodia ja kasvatetaan rivien määrää.
     * Jos parametri vika on true (solmu on sanan viimeinen), lisätään rivinumero myös sanaRivit-tauluun.
     * @param rivi
     * @param vika 
     */
    public void setRivi(int rivi, boolean vika) {
        if (!haeRivi(rivit, rivienMaara, rivi)) {
            rivit = lisaaRivi(rivi, rivit, rivienMaara);
            rivienMaara++;
        }
        if (vika) {
            if (!haeRivi(sanaRivit, sanaRiveja, rivi)) {
                sanaRivit = lisaaRivi(rivi, sanaRivit, sanaRiveja);
                sanaRiveja++;
            }
        }
    }
    
    /**
     * Metodi lisää rivin solmun tauluun.
     * Tarkistetaan ensin onko taulu täynnä, tarvittaessa kutustaan kasvata-metodia.
     * Lisätään rivi taulukon viimeiseksi.
     * @param rivi
     * @param taulu
     * @param lkm
     * @return Palauttaa taulun johon rivi on lisätty.
     */
    private int[] lisaaRivi(int rivi, int[] taulu, int lkm) {
            if (lkm == taulu.length) {
                taulu = kasvata(taulu, lkm);
            }
            taulu[lkm] = rivi;
            return taulu;
    }
    
    /**
     * Binäärihaku tarkistaa onko solmulla jo lapsi, jolla haettu kirjain.<p>
     * Jos lapsi on olemassa palauttaa indeksin, jossa kyseinen lapsi on.
     * Muuten palauttaa sen indeksin negaation -1.
     * @param kirjain
     * @return 
     */
    private int haeKirjain(char kirjain) {
        int alku = 0;
        int loppu = lastenMaara-1;
        int keski;
        while (alku <= loppu) {
            keski = (alku+loppu)/2;
            if (lapset[keski].kirjain == kirjain) {
                return keski;
            }
            else if (lapset[keski].kirjain < kirjain) {
                alku = keski + 1;
            }
            else {
                loppu = keski - 1;
            }
        }
            return -alku-1;
    }
    
    /**
     * Binäärihaku tarkistaa onko annetussa taulussa jo haettu rivinumero.<p>
     * Käydään läpi vain taulussa todella olevat rivit, siksi parametrina saadaan taulun alkioiden lukumäärä.
     * @param taulu
     * @param lkm
     * @param nro
     * @return Palauttaa true, joa rivi löytyy.
     */
    private boolean haeRivi(int[] taulu, int lkm, int nro) {
        int alku = 0; 
        int loppu = lkm-1;
        int keski;
        while (alku <= loppu) {
            keski = (alku+loppu)/2;
            if (taulu[keski] == nro) {
                return true;
            }
            else if (taulu[keski] < nro) {
                alku = keski + 1;
            }
            else {
                loppu = keski -1;
            }
        }
        return false;
    }
    
    /**
     * Metodi kaksinkertaistaa annetun taulun koon.<p>
     * Parametrina saadaa myös taulun koko ennen kasvatusta.
     * Kopioidaan taulun alkiot uuteen isompaan tauluun.
     * @param taulu
     * @param maara
     * @return Palauttaa uuden taulun.
     */
    private int[] kasvata(int[] taulu, int maara) {
        
        int kasvu;
        if (maara < Integer.MAX_VALUE/2) {
            kasvu = maara*2;
        }
        else {
            kasvu = maara +100;
        }
        int[] uusi = new int[kasvu];
        for (int i=0; i<taulu.length; i++) {
            uusi[i] = taulu[i];
        }
        return uusi;
    }

    private PuuSolmu[] kasvataLapsia(PuuSolmu[] taulu, int koko) {
        PuuSolmu[] uusi = new PuuSolmu[koko*2];
        for (int i=0; i<taulu.length; i++) {
            uusi[i] = taulu[i];
        }
        return uusi;
    }
}

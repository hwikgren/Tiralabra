/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Luokka hoitaa haetun sanan hakemisen puusta ja sitä vastaavien rivien tulostamisen. 
 * @author heidi
 */
public class Hakija {
    /**
     * Puuolento, jossa tiedostojen sanat.
     */
    Puu puu;
    
    /**
     * Tallentaja-olento
     */
    Tallentaja tallentaja;

    /**
     * Pino hakutermien (and, or) ja sulkeiden tallentamiseen.
     */
    Pino hakutermit;
    /**
     * Pino hakusanoilla saatujen rivitaulukoiden tallentamiseen.
     */
    Pino sanat;
        
    /**
     * Konstruktori luo Hakija-olennon, jolla on tieto käytetystä puusta ja ohjelman tallentaja-olennosta.
     */
    public Hakija(Puu puu, Tallentaja tallentaja) {
        this.puu = puu;
        this.tallentaja = tallentaja;
    }
    
    /**
     * Metodi printaa kaikki annetun sanan sisältävät rivit.<p>
     * Pyytää tallentajalta viimeisimmät rivit ja tiedosto-oliot sisältävät taulukot.
     * Kutsuu hae-metodia.
     * Printaa jokaisen tiedoston nimen jossa hakusana(t) esiintyy 
     * ja sen perään taulukosta saamaansa listaa vastaavat rivit,jotka esiintyvät kyseisessä tiedostossa.
     * Jos sanaa ei löytynyt, kertoo siitä käyttäjälle.
     * @param haettu 
     */
    public void printtaa(String haettu) {
       
        String[] tekstit = tallentaja.rivit;
        Tiedosto[] tiedostot = tallentaja.tiedostot;
        
        int[] rivit = hae(haettu);
        if (rivit != null && rivit[0] !=0) {
            int indeksi = 0;
            System.out.println("\nHaulla '"+haettu+"' löytyi seuraavat rivit:");
            //käydään läpi tiedostot
            for (int j=0; j<tallentaja.lukumaara; j++) {
                if (rivit[indeksi]>=tiedostot[j].alku && rivit[indeksi]<=tiedostot[j].loppu) {
                    System.out.println(tiedostot[j].nimi+":");
                }
                //käydään läpi rivinumerot joilla hakusana esiintyy
                //alkaen indeksistä johon edellisellä kerralla päästiin 
                //ja lopettaen kun riviä ei enää esiinny käsittelyssä olevassa tiedostossa.
                for (int i=indeksi; i<rivit.length; i++) {
                    if (rivit[i] > tiedostot[j].loppu) {
                        break;
                    }
                    else if (rivit[i] > 0) {
                        System.out.println(tekstit[rivit[i]]);
                        indeksi++;
                    }
                    else break;
                }
            }
        }
        else {
            System.out.println("\nHaku '"+haettu+"' ei tuottanut yhtään riviä!");
        }
        
    }
    /**
     * Shunting Yard tallentaa hakutermit pinoon ja hakusanat toiseen pinoon.<p>
     * Käydään läpi hakulauseke yksi kerrallaan.
     * Hakutermit 'and' ja 'or sekä oikeat-sulkeet laitetaan hakutermit-pinoon. 
     * Kun hakusana on selvillä kutsutaan etsi-metodia, 
     * jonka jälkeen lisätään sanat-pinoon lista riveistä joilla sana esiintyy. 
     * Jos käsittelyssä oleva char on loppu-sulku kutsutaan haeRivit-metodia.
     * Jos hakutermit-pinossa on vielä termejä hakulausekkeen käsittelyn jälkeen, 
     * kutsutaan haeRivit-metodia kunnes se on tyhjä.
     * @param haettu
     * @return Palauttaa sanat-pinossa jäljellä olevan yhden rivinumero-taulukon.
     */
    private int[] hae(String haettu) {
        hakutermit = new Pino();
        sanat = new Pino();
        //käydään läpi hakusanat
        for (int i=0; i<haettu.length(); i++) {
            switch(haettu.charAt(i)) {
                case '(':
                    hakutermit.push("(", null);
                    break;
                case '&':
                    hakutermit.push("and", null);
                    break;
                case '/':
                    hakutermit.push("or", null);
                    break;
                case ' ':
                    break;
                //jos ), haetaan sen ja aloitussulun väliset sanat
                case ')':
                    while (!hakutermit.huippu.sana.equals("(")) {
                        haeRivit();
                    }
                    hakutermit.pop();
                    break;
                default:
                    int alku = i;
                    while (i<haettu.length() && " )/&".indexOf(haettu.charAt(i)) <0 ) {
                        i++;
                    }
                    String sana = haettu.substring(alku, i--);
                    sanat.push(null, etsi(sana));
            }
        }
        
        //Tyhjennetään pino
        while (!hakutermit.empty()) {
            haeRivit();
        }

        return sanat.pop().rivit;
    }
    
    /**
     * Metodi valitsee metodin hakusanojen yhdistämiselle.<p>
     * Jos hakusana on 'or', kutsuu yhdistaOr-metodia.
     * Muuten kutsuu yhdistaAnd-metodia.
     */
    private void haeRivit() {
        String termi = hakutermit.pop().sana;
        if (termi.equals("or")) {
            yhdistaOr();
        }
        else {
            yhdistaAnd();
        }
        //sanat.push(null, keko.getRivit());
        //keko = new RiviKeko();
    }
    
    /**
     * Metodi päättää mitä puun hakumetodia kutsutaan.<p>
     * Jos haetaan sanan osaa (sanan perässä on *), kutsuu  etsiOsa-metodia.
     * Muuten kutsuu etsiSana-metodia.
     * @param sana
     * @return Palauttaa taulukon riveistä joilla kyseinen sana esiintyy.
     */
    private int[] etsi(String sana) {
        int[] palautus;
        if (sana.charAt(sana.length()-1) == '*') {
            palautus = puu.etsiOsa(sana);
        }
        else {
            palautus = puu.etsiSana(sana);
        }
        return palautus;
    }
    
    /**
     * Metodi yhdistää kahden hakusanan rivinumero-taulukot.<p>
     * Käy yhtä aikaa läpi molempia taulukoita 
     * ja valitsee aina pienemmän lisättäväksi kolmanteen tauluun.
     * Jos tauluissa on sama rivinumero, se lisätään vain kerran.
     * Varaudutaan myös siihen, että tauluissa on 0-alkoita, jotka eivät ole rivinumeroita
     * vaan tyhjiä paikkoja dynaamisessa taulukossa.
     * Lopuksi lisää uuden taulun sanat-pinoon.
     */
    private void yhdistaOr() {
        int[] rivit1 = sanat.pop().rivit;
        int[] rivit2 = sanat.pop().rivit;
        int[] yhdistetty = new int[rivit1.length+rivit2.length];
        int indeksi = 0;
        int i = 0;
        int j = 0;
        while (i<rivit1.length && j<rivit2.length) {
            if (rivit1[i] == rivit2[j] && rivit1[i] !=0) {
                yhdistetty[indeksi++] = rivit1[i++];
                j++;
            }
            else if (rivit1[i] < rivit2[j]) {
                if (rivit1[i]!=0) {
                    yhdistetty[indeksi++] = rivit1[i++];
                }
                else {
                    yhdistetty[indeksi++] = rivit2[j++];
                }
            }
            else {
                if (rivit2[j]!=0) {
                    yhdistetty[indeksi++] = rivit2[j++];
                }
                else {
                    yhdistetty[indeksi++] = rivit1[i++];
                }
            }
        }
        sanat.push(null, yhdistetty);
    }
    
    /**
     * Metodi kerää kahden hakusanan yhteiset rivinumerot kolmanteen tauluun.<p>
     * Lopuksi lisää uuden taulun sanat-pinoon.
     */
    private void yhdistaAnd() {
        int[] rivit1 = sanat.pop().rivit;
        int[] rivit2 = sanat.pop().rivit;
        int i=0;
        int j=0;
        int koko;
        if (rivit1.length <= rivit2.length) {
            koko = rivit1.length;
        }
        else {
            koko = rivit2.length;
        }
        int[] yhdistetty = new int[koko];
        int indeksi = 0;
        while (i<rivit1.length && j<rivit2.length) {
            if (rivit1[i] == rivit2[j] && rivit1[i] != 0) {
                yhdistetty[indeksi++] = rivit1[i++];
                j++;
            }
            else if (rivit1[i] < rivit2[j]) {
                i++;
            }
            else  {
                j++;
            }
        }
        sanat.push(null, yhdistetty);
    }
    
    
}

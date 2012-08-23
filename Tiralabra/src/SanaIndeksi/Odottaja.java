/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.util.Scanner;

/**
 * Luokka huolehtii main-metodin syötteiden odottamisesta.<p>
 * Do-while looppi tulkitsee nextLine()n painallukseksi eikä pysähdy.
 * Täällä luodaan toinen Scanneri hoitaa kyselyn ja odottaa, että käyttäjä on painanut enteriä.
 * @author heidi
 */
public class Odottaja {
    
    Scanner odota;

    /**
     * Kostruktori luo Odottaja-olion, jolla on oma scanneri.
     */
    public Odottaja() {
        odota = new Scanner(System.in);
    }
    
    /**
     * Metodi pysäyttää do-while -loopin ja odottaa, että käyttäjä painaa enteriä<p>
     * Kehoitetaan käyttäjää painamaan enteriä päästäkseen eteenpäin.
     * Luokan scannerilla luetaan rivi.
     */
    public void odotaEnteria() {
        System.out.print("\nPaina enter palataksesi päävalikkoon");
        odota.nextLine();
    }
    
    /**
     * Metodi pysäyttää do-while -loopin ja kysyy käyttäjältä hakusanoja.<p>
     * Kysytään hakusanoja.
     * Scanner lukee rivin.
     * @return Käyttäjän hakusanat.
     */
   public String odotaHakua() {
        System.out.println("\nSanakosta voit hakea rivejä yhdellä tai useammalla sanalla.\n"
                + "Voit yhdistää sanahakuja merkeillä & (= ja) ja / (= tai).\n"
            + "Jos haet kolmea tai useampaa sanaa, ryhmitä hakusi suluilla.\n"
            + "Minkä tahansa sanoista voi katkaista *-merkillä.");
        System.out.println("Anna haetta(vat) sana(t):");
        return odota.nextLine().toLowerCase();
    }
    
    /**
     * Metodi pysäyttää do-while -loopin ja kysyy käyttäjältä tiedostoa, josta hän haluaa etsiä sanoja.
     * @return halutun tiedoston nimi.
     */
    public String odotaTiedostoa() {
        System.out.println("Anna tiedoston nimi, josta haluat hakea rivejä:");
        return odota.nextLine();
    }
}

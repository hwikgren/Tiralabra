/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

/**
 *
 * @author heidi
 */
public class Tiedosto {
    String nimi;
    int alku;
    int loppu;

    public Tiedosto(String nimi, int alku) {
        this.nimi = nimi;
        this.alku = alku;
    }
    
    public void setLoppu(int loppu) {
        this.loppu = loppu;
    }
}

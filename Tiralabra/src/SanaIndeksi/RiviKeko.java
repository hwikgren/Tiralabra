package SanaIndeksi;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Luokka pitää kirjaa kunkin sanan tai sananosan riveistä.
 * KESKEN, EI SIDOTTU MAIN-LUOKKAAN
 * @author heidi
 */
public class RiviKeko {
    int koko;
    int heapSize;
    int[] rivit;

    public RiviKeko() {
        this.koko = 10;
        this.heapSize = 0;
        this.rivit = new int[10];
    }
    
    public void lisaaRivi(int rivi) {
        if (heapSize == koko) {
            kasvata();
        }
        int i = heapSize;
        while (i>0 && rivit[i/2] < rivi) {
            rivit[i] = rivit[i/2];
            i = i/2;
        }
        rivit[i] = rivi;
        heapSize++;
    }
    
    private void kasvata() {
        koko = 2*koko;
        int[] uusi = new int[koko];
        for (int i=0; i<rivit.length; i++) {
            uusi[i] = rivit[i];
        }
        rivit = uusi;
    }
    
    public int[] getRivit() {
        jarjesta();
        return rivit;
    }
    
    private void jarjesta() {
        int[] palautus = new int[heapSize];
        int keonKoko = heapSize;
        for (int i=keonKoko-1; i>=0; i--) {
            int apu = rivit[0];
            rivit[0] = rivit[i];
            rivit[i] = apu;
            keonKoko--;
            heapify(rivit, keonKoko, 0);
        }
    }
    
    private void heapify(int[] keko, int size, int i) {
        int left = 1;
        int right = 2;
        if (i>0) {
            left = 2*i;
            right = 2*i+1;
        }
        int isompi;
        if (right<=size-1) {
            if (keko[left] > keko[right]) {
                isompi = left;
            }
            else {
                isompi = right;
            }
            if (keko[i] < keko[isompi]) {
                int apu = keko[i];
                keko[i] = keko[isompi];
                keko[isompi] = apu;
                heapify(keko, size, isompi);
            }
        }
        else if (left == size-1 && keko[i] < keko[left]) {
            int apu = keko[i];
            keko[i] = keko[left];
            keko[left] = apu;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.io.*;

/**
 * Luokka huolehtii tiedon lataamisesta ohjelman käynnistyessä ja sen tallentamista ohjelman lopettaessa. 
 * @author heidi
 */
public class Muistiin {
    /**
     * Hakija-olento joka talletettaan tiedostoon.
     */
    Hakija hakija;

    /**
     * Kostruktori luo tyhjän Muistiin-olion.
     */
    public Muistiin() {
    }
    
    /**
     * Metodi lataa Hakija-olennon tiedostosta.<p>
     * Ensin luo tiedoston, jolla tallennustiedoston nimi.
     * Sitten tarkistaa, että tiedosto on olemassa. Muuten luo uuden tiedoston.
     * Jos tiedosto ei ole tyhjä, lukee Hakija-olennon tiedostosta.
     * @return Hakija-olio tai null.
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Hakija lataaTiedot() throws IOException, ClassNotFoundException {
        File f = new File("tiedot.ser");
        if (!f.exists()) {
            f.createNewFile();
        }
        if (f.length()>0) {
            hakija = null;
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                hakija = (Hakija)ois.readObject();
                ois.close();
                return hakija;
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
            catch(ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * Metodi tallentaa Hakija-olion tiedostoon.
     * @param hakija Hakija-olio, joka tuntee kaikki muut luokat ohjelmassa suoraan tai välillisesti,
     * paitsi Tiralabran, jossa ei ole mitään tallennettuja tietoja.
     */
    public void talletaTiedot(Hakija hakija) {
        FileOutputStream fos = null;
        ObjectOutputStream ous = null;
        try {
            fos = new FileOutputStream("tiedot.ser");
            ous = new ObjectOutputStream(fos);
            ous.writeObject(hakija);
            ous.close();
        }
        catch(IOException ex) {
            
        }
        
    }
}

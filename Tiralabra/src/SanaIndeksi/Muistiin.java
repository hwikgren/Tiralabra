/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.io.*;

/**
 *
 * @author heidi
 */
public class Muistiin {
    Hakija hakija;

    public Muistiin() {
    }
    
    public boolean lataaTiedot() throws IOException, ClassNotFoundException {
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
                return true;
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
            catch(ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public Hakija getHakija() {
        return hakija;
    }
    
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

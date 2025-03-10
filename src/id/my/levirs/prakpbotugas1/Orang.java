package id.my.levirs.prakpbotugas1;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

/**
 *
 * @author levir
 */
public class Orang {
    public enum Gender {
        PRIA, WANITA;
        
        public String asString() {
            return this == PRIA ? "Pria" : "Wanita";
        }
    }
    
    private String mNamaDepan, mNamaBelakang;
    private Gender mGender;

    public Orang(String namaDepan, String namaBelakang, Gender gender) {
        this.mNamaDepan = namaDepan;
        this.mNamaBelakang = namaBelakang;
        this.mGender = gender;
    }
    
    public String asString() {
        return String.format(
            "%s %s | Gender : %s", 
            mNamaDepan, 
            mNamaBelakang, 
            mGender.asString()
        );
    }
}

package prototypeprogmob.com.Model;

public class KRSSI {
    private String Kode;
    private String Matkul;
    private String Hari;
    private String Sesi;
    private String JmlSks;
    private String Dosen;
    private String JmlMhs;

    public KRSSI(String Kode, String Matkul, String Hari, String Sesi, String JmlSks, String Dosen, String JmlMhs){
        this.Kode = Kode;
        this.Matkul = Matkul;
        this.Hari = Hari;
        this.Sesi = Sesi;
        this.JmlSks = JmlSks;
        this.Dosen = Dosen;
        this.JmlMhs = JmlMhs;
    }
    public String getKode(){
        return Kode;
    }
    public void setKode(String Kode){
        this.Kode = Kode;
    }
    public String getMatkul(){
        return Matkul;
    }
    public void setMatkul(String Matkul){
        this.Matkul = Matkul;
    }
    public String getHari(){
        return Hari;
    }
    public void setHari(String Hari){
        this.Hari = Hari;
    }
    public String getSesi(){
        return Sesi;
    }
    public void setSesi(String Sesi){
        this.Sesi = Sesi;
    }
    public String getJmlSks(){
        return JmlSks;
    }
    public void setJmlSks(String JmlSks){
        this.JmlSks = JmlSks;
    }
    public String getDosen(){
        return Dosen;
    }
    public void setDosen(String Dosen){
        this.Dosen = Dosen;
    }
    public String getJmlMhs(){ return JmlMhs; }
    public void setJmlMhs(String JmlMhs){
        this.JmlMhs = JmlMhs;
    }
}

package prototypeprogmob.com.Model;

import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MhsSI {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("alamat")
    @Expose
    private String Alamat;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("foto")
    @Expose
    private String foto;

    public MhsSI(String id,String nim,String nama, String Alamat, String Email,
                 String foto){
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.Alamat = Alamat;
        this.Email = Email;
        this.foto = foto;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getNim(){
        return nim;
    }
    public void setNim(String nim){
        this.nim = nim;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getAlamat(){
        return Alamat;
    }
    public void setAlamat(String Alamat){
        this.Alamat = Alamat;
    }
    public String getEmail(){
        return Email;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }
    public String getFoto() {

        return foto;
    }

    public void setFoto(int imageResource) {

        this.foto = foto;
    }
}

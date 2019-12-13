package prototypeprogmob.com.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DSN {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nidn")
    @Expose
    private String nidn;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("gelar")
    @Expose
    private String gelar;
    @SerializedName("foto")
    @Expose
    private String foto;

    public DSN(String id, String nidn, String nama, String email, String alamat, String gelar
            , String foto) {
        this.id = id;
        this.nidn = nidn;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.gelar = gelar;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNidn() {

        return nidn;
    }

    public void setNidn(String nidn) {

        this.nidn = nidn;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getAlamat() {

        return alamat;
    }

    public void setAlamat(String alamat) {

        this.alamat = alamat;
    }

    public String getGelar() {

        return gelar;
    }

    public void setGelar(String gelar) {

        this.gelar = gelar;
    }

    public String getFoto() {

        return foto;
    }

    public void setFoto(int imageResource) {

        this.foto = foto;
    }

//    public String toString() {
//        return "Post{" +
//                "id=" + id + '\'' +
//                ", nama=" + nama + '\'' +
//                ", email=" + email + '\'' +
//                ", alamat=" + alamat + '\'' +
//                ", gelar=" + gelar + '\'' +
//                ", foto=" + foto +
//                '}';
//    }
}
//https://code.tutsplus.com/id/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845


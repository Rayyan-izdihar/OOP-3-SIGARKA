package sigarka.models;

public abstract class Karyawan {
    private String id;
    private String nama;
    private String tipe;

    public Karyawan(String id, String nama, String tipe) {
        this.id = id;
        this.nama = nama;
        this.tipe = tipe;
    }

    public abstract double hitungGaji(); 

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getTipe() {
        return tipe;
    }
}

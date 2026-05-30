package sigarka.models;

public class KaryawanTetap extends Karyawan {
    private int alfa;
    private int izin;
    private int lembur;
    private String divisi;
    private String jabatan;
    private double gajiPokok;

    public KaryawanTetap(String nama, String id, String tipe, String divisi, String jabatan, int alfa, int izin, int lembur, double gajiPokok) {
        super(nama, id, tipe);
        this.divisi = divisi;
        this.jabatan = jabatan;
        this.alfa = alfa;
        this.izin = izin;
        this.lembur = lembur;
        this.gajiPokok = gajiPokok;
    }

    public String getDivisi() {
        return divisi;
    }

    public String getJabatan() {
        return jabatan;
    }

     public double getGajiPokok() {
        return gajiPokok;
    }

    @Override
    public double hitungGaji() {
        double gajiPokok = 0;

        if (getDivisi().equals("Bisnis Global dan Pemasaran")){

            if (getJabatan().equals("Manager")){
                gajiPokok = 7000000;
            } else {
                gajiPokok = 6000000;
            }
        }

        if (getDivisi().equals("Produksi Kreatif")){

            if (getJabatan().equals("Manager")){
                gajiPokok = 6500000;
            } else {
                gajiPokok = 5500000;
            }
        }

        if (getDivisi().equals("AR")){

            if (getJabatan().equals("Manager")){
                gajiPokok = 7500000;
            } else {
                gajiPokok = 6500000;
            }
        }

        double potongan = (alfa * 150000) + (izin * 75000); 
        
        double bonus = 0;

        if (lembur >= 5) {
            bonus += lembur * 100000; 
        }

        if (alfa == 0 && izin == 0) {
            bonus += 500000; 
        }

        double tunjanganKesehatan = 300000;

        return gajiPokok - potongan + bonus + tunjanganKesehatan;
    }
}
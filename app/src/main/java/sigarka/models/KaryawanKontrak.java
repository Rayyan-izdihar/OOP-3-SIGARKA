package sigarka.models;

public class KaryawanKontrak extends Karyawan {
    private int jamKerja;
    private double tarifPerJam;

    public KaryawanKontrak(String nama, String id, String tipe, int jamKerja, double tarifPerJam) {
        super(nama, id, tipe);    
        this.jamKerja = jamKerja;
        this.tarifPerJam = tarifPerJam;
    }

    public double getTarifPerJam() {
        return tarifPerJam;
    }

    
    @Override
    public double hitungGaji() {
        double gajiPerJam = 30000;

        return jamKerja * gajiPerJam; 
    }
}
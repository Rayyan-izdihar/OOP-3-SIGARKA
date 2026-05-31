package sigarka.scenes.hitung;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sigarka.models.Karyawan;
import sigarka.models.KaryawanTetap;
import sigarka.repository.GajiRepo;
import sigarka.repository.KaryawanRepo;

public class HitungGajiSc {

    public static VBox getView() {


        VBox root = new VBox(15);
        root.setPadding(new Insets(20));


        KaryawanRepo karyawanRepo = new KaryawanRepo();
        GajiRepo gRepo = new GajiRepo();


        final List<Karyawan> daftarKaryawan = karyawanRepo.ambilSemua();
        List<String> displayList = daftarKaryawan.stream()
            .map(karyawan -> "(" + (karyawan.getTipe().contains("Tetap") ? "Tetap" : "Kontrak") + ") " + karyawan.getNama() + " " + karyawan.getId())
            .collect(Collectors.toList());


        ComboBox<String> cbKaryawan = new ComboBox<>(FXCollections.observableArrayList(displayList));
        cbKaryawan.setPromptText("Pilih Karyawan");

        TextField periode = new TextField();
        periode.setPromptText("Periode (Contoh: 29 Mei)");

        VBox formContainer = new VBox(10);
        Button btnHitung = new Button("Hitung dan Simpan");


        cbKaryawan.setOnAction(e -> {
            formContainer.getChildren().clear();
            int idx = cbKaryawan.getSelectionModel().getSelectedIndex();
            if (idx < 0) return;

            Karyawan k = daftarKaryawan.get(idx);
            if (k instanceof KaryawanTetap) {
                formContainer.getChildren().addAll(
                    new Label("Jumlah Izin:"), new TextField("0"),
                    new Label("Jumlah Alfa:"), new TextField("0"),
                    new Label("Jumlah Hari Lembur:"), new TextField("0")
                );
            } else {
                formContainer.getChildren().addAll(
                    new Label("Total Jam Kerja 1 Bulan:"), new TextField("0")
                );
            }
        });

        btnHitung.setOnAction(e -> {
            int idx = cbKaryawan.getSelectionModel().getSelectedIndex();
            if (idx < 0) {
                new Alert(Alert.AlertType.WARNING, "Silakan pilih karyawan terlebih dahulu.").show();
                return;
            }
            if (periode.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Silakan isi periode (contoh: Mei 2026).").show();
                return;
            }

            Karyawan karyawan = daftarKaryawan.get(idx);
            double gajiBersih = 0;
            double tunjangan_kesehatan = 0;
            double bonus = 0;
            int izin = 0, alfa = 0, lembur = 0, jamKerja = 0;

            try {
                if (karyawan instanceof KaryawanTetap) {
                    izin = Integer.parseInt(((TextField)formContainer.getChildren().get(1)).getText());
                    alfa = Integer.parseInt(((TextField)formContainer.getChildren().get(3)).getText());
                    lembur = Integer.parseInt(((TextField)formContainer.getChildren().get(5)).getText());
                    
                    // Ambil gaji pokok dari database sesuai divisi dan jabatan yang disimpan
                    double gajiPokok = karyawanRepo.getGajiPokok(karyawan.getId());
                    tunjangan_kesehatan = 300000;
                    
                    // Bonus Badge sesuai kategori
                    if (lembur >= 5) bonus += 150000; // Super Productive
                    if (alfa == 0 && izin == 0) bonus += 100000; // Discipline Master
                    
                    double penghasilan = gajiPokok + tunjangan_kesehatan + (lembur * 100000) + bonus;
                    double potongan = (alfa * 150000) + (izin * 75000);
                    gajiBersih = penghasilan - potongan;

                    // Reset input setelah berhasil
                    ((TextField)formContainer.getChildren().get(1)).setText("0");
                    ((TextField)formContainer.getChildren().get(3)).setText("0");
                    ((TextField)formContainer.getChildren().get(5)).setText("0");

                } else {
                    jamKerja = Integer.parseInt(((TextField)formContainer.getChildren().get(1)).getText());
                    gajiBersih = jamKerja * 30000; // Sesuai tarif 30.000 per jam
                    
                    // Reset input setelah berhasil
                    ((TextField)formContainer.getChildren().get(1)).setText("0");
                }

                gRepo.simpanRiwayat(karyawan.getId(), periode.getText(), tunjangan_kesehatan, bonus, izin, alfa, lembur, jamKerja, gajiBersih);
                
                String hasilFormatted = String.format("%,.0f", gajiBersih).replace(',', '.');
                new Alert(Alert.AlertType.INFORMATION, "Gaji berhasil dihitung dan disimpan.\nTotal Gaji Bersih: Rp " + hasilFormatted).show();
                
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Input harus berupa angka yang valid.").show();
            }
        });


        root.getChildren().addAll(
            new Label("Pilih Karyawan:"), 
            cbKaryawan, 
            new Label("Periode:"), 
            periode, 
            formContainer, 
            btnHitung
        );

        return root;
    }
}

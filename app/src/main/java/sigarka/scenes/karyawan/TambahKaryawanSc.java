package sigarka.scenes.karyawan;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sigarka.repository.KaryawanRepo;

public class TambahKaryawanSc {

    public static void tampilkan(Runnable onSimpan) {
        Stage stage = new Stage();
        stage.setTitle("Tambah Data Karyawan");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        TextField id = new TextField();
        id.setPromptText("ID (5 angka)");
        TextField nama = new TextField();
        nama.setPromptText("Nama Karyawan");

        ComboBox<String> cbTipe = new ComboBox<>(FXCollections.observableArrayList("Karyawan Tetap", "Karyawan Kontrak"));
        ComboBox<String> cbDivisi = new ComboBox<>(FXCollections.observableArrayList("Bisnis Global dan Pemasaran", "Produksi Kreatif", "Artist & Repertoire"));
        ComboBox<String> cbJabatan = new ComboBox<>(FXCollections.observableArrayList("Manager", "Staf"));
        
        Label lblInfoGaji = new Label("Gaji/Tarif: -");

        cbDivisi.setDisable(true);
        cbJabatan.setDisable(true);

        cbTipe.setOnAction(e -> {
            boolean isTetap = "Karyawan Tetap".equals(cbTipe.getValue());
            cbDivisi.setDisable(!isTetap);
            cbJabatan.setDisable(!isTetap);
            updateInfoGaji(cbTipe, cbDivisi, cbJabatan, lblInfoGaji);
        });

        cbDivisi.setOnAction(e -> updateInfoGaji(cbTipe, cbDivisi, cbJabatan, lblInfoGaji));
        cbJabatan.setOnAction(e -> updateInfoGaji(cbTipe, cbDivisi, cbJabatan, lblInfoGaji));

        Button btnSimpan = new Button("Simpan");
        btnSimpan.setOnAction(e -> {
            String id_karyawan = id.getText();
            String nama_karyawan = nama.getText();
            String tipe = cbTipe.getValue();

            // Validasi Dasar
            if (id_karyawan.isEmpty() || !id_karyawan.matches("\\d{5}")) {
                new Alert(Alert.AlertType.ERROR, "ID harus diisi 5 angka!").show();
                return;
            }
            if (nama_karyawan.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Nama karyawan harus diisi!").show();
                return;
            }
            if (tipe == null) {
                new Alert(Alert.AlertType.ERROR, "Tipe karyawan harus dipilih!").show();
                return;
            }

            KaryawanRepo repo = new KaryawanRepo();
            double gajiPokok = 0;
            double tarif = 0;
            
            if ("Karyawan Kontrak".equals(tipe)) {
                tarif = 30000;
            } else {
                // Validasi Karyawan Tetap
                if (cbDivisi.getValue() == null || cbJabatan.getValue() == null) {
                    new Alert(Alert.AlertType.ERROR, "Divisi dan Jabatan harus dipilih untuk Karyawan Tetap!").show();
                    return;
                }
                gajiPokok = hitungGajiPokok(cbDivisi.getValue(), cbJabatan.getValue());
            }

            repo.tambah(id_karyawan, nama_karyawan, tipe, cbDivisi.getValue(), cbJabatan.getValue(), gajiPokok, tarif);
            stage.close();
            onSimpan.run();
        });

        grid.add(new Label("ID:"), 0, 0); grid.add(id, 1, 0);
        grid.add(new Label("Nama:"), 0, 1); grid.add(nama, 1, 1);
        grid.add(new Label("Tipe:"), 0, 2); grid.add(cbTipe, 1, 2);
        grid.add(new Label("Divisi:"), 0, 3); grid.add(cbDivisi, 1, 3);
        grid.add(new Label("Jabatan:"), 0, 4); grid.add(cbJabatan, 1, 4);
        grid.add(lblInfoGaji, 0, 5, 2, 1);
        grid.add(btnSimpan, 1, 6);

        stage.setScene(new Scene(grid));
        stage.show();
    }
}
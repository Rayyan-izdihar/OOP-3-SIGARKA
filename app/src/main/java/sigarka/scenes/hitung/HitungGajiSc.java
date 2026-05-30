package sigarka.scenes.hitung;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sigarka.models.Karyawan;
import sigarka.repository.GajiRepo;
import sigarka.repository.KaryawanRepo;

public class HitungGajiSc {

    public static Scene createScene(Stage stage) {


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


        Button btnKembali = new Button("Kembali");
        btnKembali.setOnAction(e -> stage.setScene(sigarka.scenes.MenuSc.createScene(stage)));

        root.getChildren().addAll(
            new Label("Pilih Karyawan:"), 
            cbKaryawan, 
            new Label("Periode:"), 
            periode, 
            formContainer, 
            btnHitung, 
            btnKembali
        );

        return new Scene(root, 400, 600);
    }
}

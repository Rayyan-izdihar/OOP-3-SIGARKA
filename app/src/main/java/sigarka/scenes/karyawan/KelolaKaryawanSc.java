package sigarka.scenes.karyawan;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sigarka.models.Karyawan;
import sigarka.repository.KaryawanRepo;

public class KelolaKaryawanSc {

    private static TableView<Karyawan> tabelTetap = new TableView<>();
    private static TableView<Karyawan> tabelKontrak = new TableView<>();
    private static KaryawanRepo repo = new KaryawanRepo();

    public static Scene createScene(Stage stage) {


        Label lblTetap = new Label("Data Karyawan Tetap");
        Label lblKontrak = new Label("Data Karyawan Kontrak");


        Button btnTambah = new Button("Tambah Data Karyawan");
        Button btnHapus = new Button("Hapus Data Karyawan");

        
        HBox layoutTabel = new HBox(20, 
            new VBox(10, lblTetap, tabelTetap), 
            new VBox(10, lblKontrak, tabelKontrak)
        );
        layoutTabel.setPadding(new Insets(10));

        HBox layoutTombol = new HBox(10, btnHapus, btnTambah);
        layoutTombol.setPadding(new Insets(10));

        Button btnKembali = new Button("Kembali ke Menu");
        btnKembali.setOnAction(e -> stage.setScene(sigarka.scenes.MenuSc.createScene(stage)));

        VBox root = new VBox(10, layoutTabel, layoutTombol, btnKembali);
        return new Scene(root, 1000, 600);
    }
}
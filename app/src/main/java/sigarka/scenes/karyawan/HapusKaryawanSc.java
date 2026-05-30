package sigarka.scenes.karyawan;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sigarka.repository.KaryawanRepo;

public class HapusKaryawanSc {

    public static boolean konfirmasiHapus(String id, String nama) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText(null);
        alert.setContentText("Yakin ingin menghapus " + nama + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            KaryawanRepo repo = new KaryawanRepo();
            repo.hapus(id);
            return true;
        }
        return false;
    }
}
package sigarka.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sigarka.database.KoneksiDatabase;

// ===== PERINTAH SQL QUERY =====
public class KaryawanRepo {

    // ===== MENAMBAH KE DATABASE =====
    public void tambah(String id, String nama, String tipe, String divisi, String jabatan, double gajiPokok, double tarif) {
        String sql = "INSERT INTO karyawan(id, nama, tipe, divisi, jabatan, gaji_pokok, tarif_per_jam) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = KoneksiDatabase.sambung();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, nama);
            pstmt.setString(3, tipe);
            pstmt.setString(4, divisi);
            pstmt.setString(5, jabatan);
            pstmt.setDouble(6, gajiPokok);
            pstmt.setDouble(7, tarif);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package sigarka.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sigarka.database.KoneksiDatabase;

// ===== PERINTAH SQL QUERY =====
public class GajiRepo {

    // ===== MENYIMPAN INFO GAJI =====
    public void simpanRiwayat(String karyawanId, String periode, double tunjangan_kesehatan, double bonus, int izin, int alfa, int lembur, int jamKerja, double gajiBersih) {
        String sql = "INSERT INTO riwayat_gaji(karyawan_id, periode, tunjangan_kesehatan, bonus_badge, izin, alfa, lembur, jam_kerja, gaji_bersih) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = KoneksiDatabase.sambung();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, karyawanId);
            pstmt.setString(2, periode);
            pstmt.setDouble(3, tunjangan_kesehatan);
            pstmt.setDouble(4, bonus);
            pstmt.setInt(5, izin);
            pstmt.setInt(6, alfa);
            pstmt.setInt(7, lembur);
            pstmt.setInt(8, jamKerja);
            pstmt.setDouble(9, gajiBersih);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

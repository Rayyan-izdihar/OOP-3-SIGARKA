package sigarka.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class KoneksiDatabase {

    // ===== LOKASI DATABASE =====
    private static final String URL = "jdbc:sqlite:sigarka.db";

    // ===== MENGHUBUNGKAN KE DATABASE =====
    public static Connection sambung() {
        try {
            // Memastikan driver SQLite terisi
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(URL);
            buatTabel(conn);
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver SQLite tidak ditemukan: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Gagal menyambung ke database: " + e.getMessage());
            return null;
        }
    }

    // ===== MEMBUAT TABEL DATA =====
    private static void buatTabel(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            // Tabel Karyawan
            stmt.execute("CREATE TABLE IF NOT EXISTS karyawan (" +
                         "id TEXT PRIMARY KEY, " +
                         "nama TEXT NOT NULL, " +
                         "tipe TEXT NOT NULL, " +
                         "divisi TEXT, " +
                         "jabatan TEXT, " +
                         "gaji_pokok REAL, " +
                         "tarif_per_jam REAL" +
                         ");");
            
            // Tabel Riwayat Gaji
            stmt.execute("CREATE TABLE IF NOT EXISTS riwayat_gaji (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "karyawan_id TEXT, " +
                         "periode TEXT, " +
                         "tunjangan_kesehatan REAL, " +
                         "bonus_badge REAL, " +
                         "izin INTEGER, " +
                         "alfa INTEGER, " +
                         "lembur INTEGER, " +
                         "jam_kerja INTEGER, " +
                         "gaji_bersih REAL, " +
                         "FOREIGN KEY(karyawan_id) REFERENCES karyawan(id)" +
                         ");");
        } catch (SQLException e) {
            System.err.println("Gagal membuat tabel: " + e.getMessage());
        }
    }
}
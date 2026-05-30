package sigarka.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDatabase {

    // ===== LOKASI DATABASE =====
    private static final String URL = "jdbc:sqlite:sigarka.db";

    // ===== MENGHUBUNGKAN KE DATABASE =====
    public static Connection sambung() {
        try {
            // Memastikan driver SQLite terisi
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(URL);
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver SQLite tidak ditemukan: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Gagal menyambung ke database: " + e.getMessage());
            return null;
        }
    }

}
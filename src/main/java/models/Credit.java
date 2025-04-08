package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Credit {
    int id;
    String libelle;
    double montant;

    public Credit() {}

    public Credit(String libelle, double montant) {
        this.libelle = libelle;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void save() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = MyCoSQL.GetConnection();
            conn.setAutoCommit(false);  
            String sql = "INSERT INTO webdyn_S4_credit (libelle, montant) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.libelle);
            ps.setDouble(2, this.montant);
            ps.executeUpdate();
            conn.commit();  
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();  
            }
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    public void update(Connection conn) throws Exception {
        PreparedStatement ps = null;

        try {
            conn.setAutoCommit(false);  
            String sql = "UPDATE webdyn_S4_credit SET libelle = ?, montant = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.libelle);
            ps.setDouble(2, this.montant);
            ps.setInt(3, this.id);
            ps.executeUpdate();
            conn.commit();  
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();  
            }
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
    }

    public void findById(int id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = MyCoSQL.GetConnection();
            String sql = "SELECT * FROM webdyn_S4_credit WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.id = rs.getInt("id");
                this.libelle = rs.getString("libelle");
                this.montant = rs.getDouble("montant");
            }

            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    public static List<Credit> findAll() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Credit> list = new ArrayList<Credit>();

        try {
            conn = MyCoSQL.GetConnection();
            String sql = "SELECT * FROM webdyn_S4_credit";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Credit c = new Credit();
                c.setId(rs.getInt("id"));
                c.setLibelle(rs.getString("libelle"));
                c.setMontant(rs.getDouble("montant"));
                list.add(c);
            }

            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return list;
    }

    public void delete() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = MyCoSQL.GetConnection();
            conn.setAutoCommit(false);  
            String sql = "DELETE FROM webdyn_S4_credit WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, this.id);
            ps.executeUpdate();
            conn.commit();  
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();  
            }
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }
}

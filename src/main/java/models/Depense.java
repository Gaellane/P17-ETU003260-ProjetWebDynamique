package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Depense {
    int id;
    int idCredit;
    double montant;
    Date date;  

    public Depense() {}

    public Depense(int idCredit, double montant, Date date) {
        this.idCredit = idCredit;
        this.montant = montant;
        this.date = date;  
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdCredit() {
        return idCredit;
    }
    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Credit getObjCredit() throws Exception {
        Credit d = new Credit();
        d.setId(idCredit);
        d.findById(idCredit);
        return d;
    }

    // save
    public void save(Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            conn = MyCoSQL.GetConnection();
            String sql = "INSERT INTO webdyn_S4_depense (id_credit, montant, date) VALUES (?, ?, ?)";  // Ajout de la date dans la requête
            ps = conn.prepareStatement(sql);
            ps.setInt(1, this.idCredit);
            ps.setDouble(2, this.montant);
            ps.setDate(3, this.date);  
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    // findById
    public void findById() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MyCoSQL.GetConnection();
            String sql = "SELECT * FROM webdyn_S4_depense WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id");
                this.idCredit = rs.getInt("id_credit");
                this.montant = rs.getDouble("montant");
                this.date = rs.getDate("date");  
            }
            rs.close();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    // findAll
    public static List<Depense> findAll() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        List<Depense> list = new ArrayList<>();
        try {
            conn = MyCoSQL.GetConnection();
            String sql = "SELECT * FROM webdyn_S4_depense";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Depense d = new Depense();
                d.setId(rs.getInt("id"));
                d.setIdCredit(rs.getInt("id_credit"));
                d.setMontant(rs.getDouble("montant"));
                d.setDate(rs.getDate("date"));  // Récupération de la date
                list.add(d);
            }
            rs.close();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return list;
    }
}

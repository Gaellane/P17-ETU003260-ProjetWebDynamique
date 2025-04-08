package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    int id;
    String login;
    String mdp;

    public User() {}

    public User(String login, String mdp) {
        this.login = login;
        this.mdp = mdp;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    // save
    public void save() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MyCoSQL.GetConnection();
            String sql = "INSERT INTO webdyn_S4_users (login, mdp) VALUES (?, ?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.login);
            ps.setString(2, this.mdp);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
            rs.close();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    // update
    public void update() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MyCoSQL.GetConnection();
            String sql = "UPDATE webdyn_S4_users SET login = ?, mdp = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.login);
            ps.setString(2, this.mdp);
            ps.setInt(3, this.id);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    // delete
    public void delete() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MyCoSQL.GetConnection();
            String sql = "DELETE FROM webdyn_S4_users WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, this.id);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    // findById
    public void findById(int id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MyCoSQL.GetConnection();
            String sql = "SELECT * FROM webdyn_S4_users WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.id = rs.getInt("id");
                this.login = rs.getString("login");
                this.mdp = rs.getString("mdp");
            }
            rs.close();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    // findAll
    public List<User> findAll() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        List<User> list = new ArrayList<>();

        try {
            conn = MyCoSQL.GetConnection();
            String sql = "SELECT * FROM webdyn_S4_users";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setMdp(rs.getString("mdp"));
                list.add(u);
            }

            rs.close();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return list;
    }

    // check login/mdp
    public boolean login() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean isValid = false;

        try {
            conn = MyCoSQL.GetConnection();
            String sql = "SELECT COUNT(*) FROM webdyn_S4_users WHERE login = ? AND mdp = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, this.login);
            ps.setString(2, this.mdp);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isValid = rs.getInt(1) > 0;
            }

            rs.close();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return isValid;
    }
}

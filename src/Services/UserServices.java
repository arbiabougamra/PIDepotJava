/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Utils.DataSource;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import Utils.BCrypt;
import Utils.SessionUser;
import java.sql.SQLException;
import java.util.UUID;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Bia
 */
public class UserServices implements IUserServices {
    
    Connection con= DataSource.getInstance().getCnx();
    Statement stmt;
    

    @Override
    public boolean Authentification(User u) {
        boolean status = false;
        try {
            String req = "select * from user where username=? ";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, u.getUsername());

           ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (BCrypt.checkpw(u.getPassword(), rs.getString("password")) == true) {
                    
                    status = true;
                    u = this.findById(rs.getInt("id"));
                    SessionUser.setUser(u);
                    System.out.println(u.getId());

                } else {
                    status = false;
                }

            }
        } catch (Exception ex) {

        }
        return status;
    }

public User findById(Integer id) {
        User u = null;
        try {
            String req = "select * from user where id=? ";
            PreparedStatement st = con.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                        (Timestamp) rs.getObject(9),
                        rs.getString(10),
                        (Timestamp) rs.getObject(11),
                        rs.getString(12),
                        rs.getString(13),
                        (Timestamp) rs.getObject(14),
                        rs.getString(15),
                        rs.getInt(16));
            }
        } catch (Exception a) {
        }
        return u;
    }

    @Override
 public void create(User u) {

        try {

            String req = "INSERT INTO `user`(`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `prenom`, `dateNai`, `adresse`, `tel`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(req);
            String token = UUID.randomUUID().toString();
            String Role = u.getRoles();
            st.setString(1, u.getUsername());
            st.setString(2, u.getUsernameCanonical());
            st.setString(3, u.getEmail());
            st.setString(4, u.getEmailCanonical());
            st.setInt(5, 0);
            st.setString(6, u.getSalt());
            st.setString(7, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setObject(8, u.getLastLogin());
            st.setString(9, token);
            st.setObject(10, u.getPasswordRequestedAt());
            st.setString(11, u.getRoles());
            st.setString(12, u.getPrenom());
            st.setObject(13, u.getDateNai());
            st.setString(14, u.getAdresse());
            st.setInt(15, u.getTel());

            st.executeUpdate();
        } catch (Exception ex) {
        }
    }
 
    @Override
  public void update(User u) {
        try {
            String req = "UPDATE `user` SET `username`=?,`username_canonical`=?,`email`=?,`email_canonical`=?,`enabled`=?,`salt`=?,`password`=?,`last_login`=?,`confirmation_token`=?,`password_requested_at`=?,`roles`=?,`prenom`=?,`dateNai`=?,`adresse`=?,`tel`=? WHERE `id` = ?";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getUsernameCanonical());
            st.setString(3, u.getEmail());
            st.setString(4, u.getEmailCanonical());
            st.setInt(5, 0);
            st.setString(6, u.getSalt());
            st.setString(7, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setObject(8, u.getLastLogin());
            st.setString(9, null);
            st.setObject(10, u.getPasswordRequestedAt());
            st.setString(11, u.getRoles());
            st.setString(12, u.getPrenom());
            st.setObject(13, u.getDateNai());
            st.setString(14, u.getAdresse());
            st.setInt(15, u.getTel());

            st.executeUpdate();
        } catch (Exception ex) {
        }
    }
  
    @Override
   public void delete(User u) {

        try {
            String req = "delete from user where id = ?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
    @Override
    public ObservableList<User> findAll() {
        ObservableList<User> users=FXCollections.observableArrayList();

        try {
            String req = "select * from user";
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt("id"),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                       (Timestamp) rs.getObject(9),
                        rs.getString(10),
                        (Timestamp) rs.getObject(11),
                        rs.getString(12),
                        rs.getString(13),
                        (Timestamp) rs.getObject(14),
                        rs.getString(15),
                        rs.getInt(16));              
                users.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }
 
    @SuppressWarnings("UnusedAssignment")
    @Override
    public String CheckRole(User u) {
        String role = null;
        if (u.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
            role = "admin";
        } 
        if(u.getRoles().equals("a:1:{i:0;s:13:\"ROLE_CAISSIER\";}")) {
            role = "caissier";
        } 
        if(u.getRoles().equals("a:1:{i:0;s:12:\"ROLE_LIVREUR\";}")){
            role = "livreur";
        }
        else {
            role = "client";
        }

        return role;
    }
    
}

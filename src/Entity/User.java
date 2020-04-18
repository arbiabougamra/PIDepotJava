/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Bia
 */
public class User {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private boolean enabled;
    private String salt;
    private String password;
    private Timestamp lastLogin;
    private String confirmationToken;
    private Timestamp passwordRequestedAt;
    private String roles;
    private String prenom ;
    private Timestamp dateNai ;
    private String adresse ;
    private int tel ;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String prenom) {
        this.id = id;
        this.prenom = prenom;
    }

    public User(String username, String username_canonical, String email, String email_canonical, String password, String roles) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.roles = roles;
    }
    
    

    public User(Integer id, String username, String username_canonical, String email, String email_canonical, boolean enabled, String salt, String password, Timestamp lastLogin, String confirmationToken, Timestamp passwordRequestedAt, String roles, String prenom, String adresse, int tel) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
    }

    public User(Integer id, String username, String username_canonical, String email, String email_canonical, boolean enabled, String salt, String password, Timestamp lastLogin, String confirmationToken, Timestamp passwordRequestedAt, String roles, Timestamp dateNai) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.dateNai = dateNai;
    }

    public User(Integer id, String username, String username_canonical, String email, String email_canonical, boolean enabled, String salt, String password, Timestamp lastLogin, String confirmationToken, Timestamp passwordRequestedAt, String roles, String prenom, Timestamp dateNai, String adresse, int tel) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.prenom = prenom;
        this.dateNai = dateNai;
        this.adresse = adresse;
        this.tel = tel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return username_canonical;
    }

    public void setUsernameCanonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return email_canonical;
    }

    public void setEmailCanonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Timestamp getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Timestamp passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Timestamp getDateNai() {
        return dateNai;
    }

    public void setDateNai(Timestamp dateNai) {
        this.dateNai = dateNai;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User  other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "User{" + "Id= " + id + " Prenom= " + prenom + "Date de Naissance= " + dateNai + "Adresse= " + adresse +"Téléphone= " + tel + '}' + "\n" ;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import java.util.Objects;


/**
 *
 * @author PCS
 */
public class Stock {
   private int id ;
   private String nom ;
   private int quantite ;
   private String marque;
   private Date dates;
   private Date datee ;
   private String nomfile;
   private int idFornisseur;
   private int idproduit ;
   
   public Stock(int id , String nom,int quantite,String marque ,Date dates ,Date datee ,String nomfile,int idFornisseur,int idproduit ){
       this.id=id;
       this.nom=nom;
       this.quantite=quantite;
       this.marque=marque;
       this.dates=dates;
       this.datee=datee;
       this.nomfile=nomfile;
       this.idFornisseur=idFornisseur;
       this.idproduit=idproduit;
       
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Date getDatee() {
        return datee;
    }

    public void setDatee(Date datee) {
        this.datee = datee;
    }

    public String getNomfile() {
        return nomfile;
    }

    public void setNomfile(String nomfile) {
        this.nomfile = nomfile;
    }

    public int getIdFornisseur() {
        return idFornisseur;
    }

    public void setIdFornisseur(int idFornisseur) {
        this.idFornisseur = idFornisseur;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.nom);
        hash = 43 * hash + Objects.hashCode(this.marque);
        hash = 43 * hash + Objects.hashCode(this.nomfile);
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
        final Stock other = (Stock) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.marque, other.marque)) {
            return false;
        }
        if (!Objects.equals(this.nomfile, other.nomfile)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", nom=" + nom + ", quantite=" + quantite + ", marque=" + marque + ", dates=" + dates + ", datee=" + datee + ", nomfile=" + nomfile + ", idFornisseur=" + idFornisseur + ", idproduit=" + idproduit + '}';
    }
   
   
    
}

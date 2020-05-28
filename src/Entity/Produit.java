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
public class Produit {
  private int id ;
    private String nom;
     private int quantite;
    private double prix;
     private String marque ;
    private Date dates;
    private Date datee;
    private double nvprix;
    private String nomfile;  
    private int nvquantite;
    private double prix_Mg;
    private int id_categorie;
    private String nom_marque;
    public Produit(){};
   


    public Produit(int id,String nom, int quantite,double prix, String marque, Date dates, Date datee) {
        this.id=id;
        this.nom = nom;
        this.quantite = quantite;
        this.prix=prix;
        this.marque = marque;
        this.dates = dates;
        this.datee = datee;
        
     }
    public Produit(int id,String nom, int quantite,int id_categorie,double prix, String marque, Date dates, Date datee) {
        this.id=id;
        this.nom = nom;
        this.quantite = quantite;
        this.id_categorie=id_categorie;
        this.prix=prix;
        this.marque = marque;
        this.dates = dates;
        this.datee = datee;
        
   
     }
      public Produit(int id ,String nom, int nvquantite,double prix_Mg, String marque,int id_categorie,Double nvprix) {
        this.id=id;
        this.nom = nom;
        this.nvquantite = nvquantite;
        this.prix_Mg=prix_Mg;
        this.marque = marque;
        this.id_categorie=id_categorie;
        this.nvprix=nvprix;
     
                
     }
       public Produit(int id ,String nom, double prix_Mg, String marque,int id_categorie,Double nvprix,int nvquantite) {
        this.id=id;
        this.nom = nom;
        this.nvquantite = nvquantite;
        this.prix_Mg=prix_Mg;
        this.marque = marque;
        this.id_categorie=id_categorie;
        this.nvprix=nvprix;
     
                
     }
        public Produit(int nvquantite,double prix_Mg) {
       
        this.nvquantite = nvquantite;
        this.prix_Mg=prix_Mg;
       
                
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
        final Produit other = (Produit) obj;
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

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getNvprix() {
        return nvprix;
    }

    public void setNvprix(double nvprix) {
        this.nvprix = nvprix;
    }

    public int getNvquantite() {
        return nvquantite;
    }

    public void setNvquantite(int nvquantite) {
        this.nvquantite = nvquantite;
    }
     public double getPrix_Mg() {
        return prix_Mg;
    }

    public void setPrix_Mg(double prix_Mg) {
        this.prix_Mg = prix_Mg;
    }

    public String getNom_marque() {
        return nom_marque;
    }

    public void setNom_marque(String nom_marque) {
        this.nom_marque = nom_marque;
    }
    

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", id_categorie=" + id_categorie + ", prix=" + prix + ", quantite=" + quantite + ", nvprix=" + nvprix + ", nomfile=" + nomfile + ", nom=" + nom + ", marque=" + marque + ", nvquantite=" + nvquantite + ", dates=" + dates + ", datee=" + datee + '}';
    }


   
}

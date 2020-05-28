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
public class Promotion {
   private int id ;
   private int id_produit;
   private double valeur ;
   private Date datef ; 
   
   
   public Promotion(int id, int id_produit,double valeur, Date datef){
       this.id=id;
       this.id_produit=id_produit;
       this.valeur=valeur;
       this.datef=datef;
   }
    public Promotion(int id_produit , double valeur, Date datef){
        this.id_produit=id_produit;
        this.valeur=valeur;
        this.datef=datef;
    }
       public Promotion(double valeur, Date datef){
        
        this.valeur=valeur;
        this.datef=datef;
    }
            public Promotion(int id, Date datef){
        
        this.id=id;
        this.datef=datef;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Date getDatef() {
        return datef;
    }

    public void setDatef(Date datef) {
        this.datef = datef;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id_produit;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.valeur) ^ (Double.doubleToLongBits(this.valeur) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.datef);
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
        final Promotion other = (Promotion) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (Double.doubleToLongBits(this.valeur) != Double.doubleToLongBits(other.valeur)) {
            return false;
        }
        if (!Objects.equals(this.datef, other.datef)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", id_produit=" + id_produit + ", valeur=" + valeur + ", datef=" + datef + '}';
    }
    
    
    
    
}

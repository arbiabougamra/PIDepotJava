/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author PCS
 */
public class Produit {
      private int id ;
    private int id_categorie;
    private double prix;
    private int quantite;
    private double nvprix;
    private String nomfile;
    private String nom;
    private String marque ;
    private int nvquantite;
    private Date dates;
    private Date datee;
    
    
    public Produit(int id , int id_categorie,double prix , int quantite,double nvprix,String nomfile,String nom,String marque,int nvquantite,Date dates,Date datee){
        this.id=id;
        this.id_categorie=id_categorie;
        this.prix=prix;
        this.quantite=quantite;
        this.nvprix=nvprix;
        this.nomfile=nomfile;
        this.nom=nom;
        this.marque=marque;
        this.nvquantite=nvquantite;
        this.dates=dates;
        this.datee=datee;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getNvprix() {
        return nvprix;
    }

    public void setNvprix(double nvprix) {
        this.nvprix = nvprix;
    }

    public String getNomfile() {
        return nomfile;
    }

    public void setNomfile(String nomfile) {
        this.nomfile = nomfile;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getNvquantite() {
        return nvquantite;
    }

    public void setNvquantite(int nvquantite) {
        this.nvquantite = nvquantite;
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

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", id_categorie=" + id_categorie + ", prix=" + prix + ", quantite=" + quantite + ", nvprix=" + nvprix + ", nomfile=" + nomfile + ", nom=" + nom + ", marque=" + marque + ", nvquantite=" + nvquantite + ", dates=" + dates + ", datee=" + datee + '}';
    }
    
    
    
    
}

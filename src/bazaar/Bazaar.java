/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazaar;

import CONNECTION.DataSource;
import Entity.Categorie;
import Services.CategorieService;




/**
 *
 * @author PCS
 */
public class Bazaar {

    /**
     * @param args the command line arguments
     */
 
   
public static void main(String[] args)  {
        DataSource  ds =DataSource.getInstance();
       Categorie c1=new Categorie("Electronique");
       Categorie c2=new Categorie("Gaming");
       Categorie c3 = new Categorie("Porduit laitiers");
       Categorie c4 = new Categorie("Electromenager");
       Categorie c5 = new Categorie("jeux");
       
       CategorieService cat=new CategorieService();
      cat.ajouter(c1);
      cat.ajouter(c2);  
       cat.ajouter(c3);
       cat.ajouter(c5);
       cat.ajouter(c4);
        
       
       
       
  //     cat.SupprimerParID(15);
   //   cat.SupprimerParID(16);
      System.out.println(cat.getListCategorie());
    
     //  System.out.println(cat.getListCategorie());
       
       
     
        
    }
}

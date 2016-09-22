/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magasin.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import magasin.entities.Categorie;
import magasin.entities.Client;
import magasin.entities.Commande;
import magasin.entities.Produit;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author admin
 */
public class MagasinTEST {

    //creation test
    // @Test
    //@Test
    public void testliste() {

        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Categorie cat = em.find(Categorie.class, 52L);                         // recup une cat selon id              //L = long car id en long
        for (Produit p : cat.getProduits()) {
            System.out.println(p);
        }

        Client client = em.find(Client.class, 1);
        for (Commande c : client.getCommandes()) {
            System.out.println(c);

        }

    }

    @Before
    public void avant() {

        //vide toutes les tables
         EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
         em.getTransaction().begin();
         Query query = em.createQuery("DELETE FROM Produit p");
         query.executeUpdate();
         em.createQuery("DELETE FROM Categorie c").executeUpdate();
        
         em.getTransaction().commit();
        
        //ajoute des donnes en spécifiant les IDS que l on recup ds les tests unitaires
        
        
       

        //demarre transaction
       

        //Persister en base des données
        em.getTransaction().begin();
        Categorie c1 = new Categorie();
        c1.setId(1L);
        c1.setNom("Basket");
        //commande pour inserer c1
        em.persist(c1);

        Categorie c2 = new Categorie();
        c2.setId(2L);
        c2.setNom("Lunettes de soleil");
        em.persist(c2);

        Produit rayBan = new Produit();
        rayBan.setId(3L);
        rayBan.setCategorie(c2);
        rayBan.setTitre("rayBan");
        em.persist(rayBan);

        em.persist(rayBan);

        em.getTransaction().commit();

    }

    @Test
    public void verifiQueCatIdEstBasket() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Categorie cat = em.find(Categorie.class, 1L);

        if (cat.getNom().equals("Basket") == false) {
            fail("MARCHE PAS MEC");
            
        }
 
    }

}

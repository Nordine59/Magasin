/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magasin.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import magasin.entities.Categorie;
import magasin.entities.Client;
import magasin.entities.Commande;
import magasin.entities.Produit;
import org.junit.Test;

/**
 *
 * @author admin
 */
public class MagasinTEST {

    //creation test
    // @Test
    @Test
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
}
    public void test() {

        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        //demarre transaction
        em.getTransaction().begin();

        //Persister en base des données
        Categorie c1 = new Categorie();
        c1.setNom("Basket");
        //commande pour inserer c1
        em.persist(c1);

        Categorie c2 = new Categorie();
        c2.setNom("Lunettes de soleil");
        em.persist(c2);

        Produit rayBan = new Produit();
        rayBan.setCategorie(c2);
        rayBan.setTitre("rayBan");
        em.persist(rayBan);

        em.persist(rayBan);

        em.getTransaction().commit();

    }

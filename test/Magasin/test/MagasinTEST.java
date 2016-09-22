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
import org.junit.Assert;
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

        em.createQuery("DELETE FROM Commande c").executeUpdate();
        em.createQuery("DELETE FROM Client c").executeUpdate();
        em.createQuery("DELETE FROM Produit c").executeUpdate();
        em.createQuery("DELETE FROM Categorie c").executeUpdate();

        em.getTransaction().commit();

        //ajoute des donnes en spécifiant les IDS que l on recup ds les tests unitaires
        //demarre transaction
        //Persister en base des données
        em.getTransaction().begin();

        Client cli1 = new Client();//persiste 3 clients
        cli1.setId(1L);
        cli1.setNom("RIRI");
        em.persist(cli1);

        Client cli2 = new Client();
        cli2.setId(2L);
        cli2.setNom("FIFI");
        em.persist(cli2);

        Client cli3 = new Client();
        cli3.setId(3L);
        cli3.setNom("LOULOU");
        em.persist(cli3);

        Commande com1 = new Commande();//persiste commande
        com1.setId(1L);
        com1.setClient(cli1);
        cli1.getCommandes().add(com1);
        com1.setPxTotal(1000);
        em.persist(com1);

        Commande com2 = new Commande();
        com2.setId(2L);
        com2.setClient(cli3);
        cli3.getCommandes().add(com2);
        com2.setPxTotal(5);
        em.persist(com2);

        Commande com3 = new Commande();
        com3.setId(3L);
        com3.setClient(cli3);
        cli3.getCommandes().add(com3);
        com3.setPxTotal(2);
        em.persist(com3);

        Categorie cat1 = new Categorie();
        cat1.setId(1L);
        cat1.setNom("Basket");
        //commande pour inserer c1
        em.persist(cat1);

        Produit rayBan = new Produit();

        Categorie cat2 = new Categorie();
        cat2.setId(2L);
        cat2.setNom("Lunettes de soleil");
        cat2.getProduits().add(rayBan);
        em.persist(cat2);

        rayBan.setId(1L);
        rayBan.setTitre("rayBan");
        rayBan.setCategorie(cat2);
        em.persist(rayBan);

        em.getTransaction().commit();

    }

    @Test
    public void VerifQueNombreCmdLoulouEgal2OK() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        Client c = em.find(Client.class, 3L);
        if (c.getCommandes().size() != 2) {
            Assert.fail("Le nombre de commande de LOULOU est <> de deux");
        }

    }

    @Test
    public void verifiQueCatIdEstBasketOK() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Categorie cat = em.find(Categorie.class, 1L);

        if (cat.getNom().equals("Basket") == false) {
            fail("MARCHE PAS MEC");

        }

    }

    @Test
    public void verifQueCommande3PasseeParLouloOK() {

        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Client cloulou = em.find(Commande.class, 3L).getClient();
        if (cloulou.getNom().equals("LOULOU")); else {
            fail("MARCHE PAS MEC");
        }

    }
    @Test
    public void verifQueLaCommande2PasPasseeParRiriKO() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Client chaos = em.find(Commande.class,2L).getClient();//2L car c la commande que l'on recherche
        if(chaos.getNom().equals("RIRI")){
       
            fail("Commande passée par RIRI");
            
            // ou on peut utiliser Assert
           // Assert.assertArrayEquals("RIRI", cmd.getClient().getNom());
        }
        
        
        
    }

}

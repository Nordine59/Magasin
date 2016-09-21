/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author admin
 */
@Entity
public class Commande implements Serializable {

    public enum Statut {

        ENCOURS,
        PAYE,
        LIVRE

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Adresse adresseLivraison;

    private String moyenPaiement;
    @Enumerated(EnumType.STRING)
    private Statut statut;

    public Adresse getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(Adresse adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(String moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String getPxTotal() {
        return pxTotal;
    }

    public void setPxTotal(String pxTotal) {
        this.pxTotal = pxTotal;
    }

    public String getModeDelivraison() {
        return modeDelivraison;
    }

    public void setModeDelivraison(String modeDelivraison) {
        this.modeDelivraison = modeDelivraison;
    }

    public String getFraisDePort() {
        return fraisDePort;
    }

    public void setFraisDePort(String fraisDePort) {
        this.fraisDePort = fraisDePort;
    }

    public Date getDateEtHeureCommande() {
        return dateEtHeureCommande;
    }

    public void setDateEtHeureCommande(Date dateEtHeureCommande) {
        this.dateEtHeureCommande = dateEtHeureCommande;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private String pxTotal;

    private String modeDelivraison;

    private String fraisDePort;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEtHeureCommande;
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    
    
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "magasin.entities.Commande[ id=" + id + " ]";
    }

}

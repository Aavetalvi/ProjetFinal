package fr.ajc.ProjetFinal.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.ajc.ProjetFinal.model.Produit;
import fr.ajc.ProjetFinal.repository.ProduitRepository;




@Service
public class ProduitService {
	

    @Autowired ProduitRepository pr;
    
    
    public List<Produit> findAll() {
        return pr.findAll();
    }

    public Optional<Produit> findById(Long id) {
        return pr.findById(id);
    }
    
    public Produit create(Produit produit) {
        return pr.save(produit);
    }

    public Produit modifyProduit(Produit p) {
        
        if (Objects.isNull(p.getId()) || !(this.findById(p.getId()).isPresent())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'id n'est pas dans la BDD");
        }
        else {
            Produit produit = p;
        }
        return pr.save(p);
    }
    
    public void deleteProduit(Long id) {
        pr.deleteById(id);
    }

}

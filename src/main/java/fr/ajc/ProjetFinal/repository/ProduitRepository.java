package fr.ajc.ProjetFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajc.ProjetFinal.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}

package fr.ajc.ProjetFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ajc.ProjetFinal.model.Categorie;
import fr.ajc.ProjetFinal.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	@Query(value = "select distinct categorie from produit", nativeQuery = true)
	List<Categorie> findCategorie();

}

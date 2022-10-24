package fr.ajc.ProjetFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import fr.ajc.ProjetFinal.model.Produit;




public interface ProduitRepository extends JpaRepository<Produit, Long> {
	/*
	 * @Query("SELECT p FROM Produit p ORDER BY p.prix") List<Produit>
	 * findByPrixSort();
	 * 
	 * @Query("SELECT p FROM Produit p ORDER BY p.qte_stock") List<Produit>
	 * findByQteSort();
	 * 
	 * @Query("SELECT p FROM Produit p WHERE p.qte_stock <= 0") List<Produit>
	 * findUnavailableProd();
	 */
}

package fr.ajc.ProjetFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ajc.ProjetFinal.model.Taille;
import fr.ajc.ProjetFinal.model.TailleId;

public interface TailleRepository extends JpaRepository<Taille, TailleId> {

	@Query(value = "SELECT stock FROM taille WHERE id_produit=:id AND taille=:t", nativeQuery = true)
	public Integer findStockByTaille(Integer id, String t);

}

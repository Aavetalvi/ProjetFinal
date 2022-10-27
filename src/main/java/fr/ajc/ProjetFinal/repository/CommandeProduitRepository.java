package fr.ajc.ProjetFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ajc.ProjetFinal.model.CommandeProduit;
import fr.ajc.ProjetFinal.model.CommandeProduitId;

public interface CommandeProduitRepository extends JpaRepository<CommandeProduit, CommandeProduitId> {

	@Query(value = "select * from commande_produit where id_commande=:id and id_produit=:idProduit", nativeQuery = true)
	public CommandeProduit FindByCommandeAndProduit(Long id, Long idProduit);

}

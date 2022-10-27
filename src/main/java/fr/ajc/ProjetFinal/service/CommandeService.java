package fr.ajc.ProjetFinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ajc.ProjetFinal.exception.EmptyIdException;
import fr.ajc.ProjetFinal.exception.IdNotFound;
import fr.ajc.ProjetFinal.exception.RelationException;
import fr.ajc.ProjetFinal.exception.StockInsuffisantException;
import fr.ajc.ProjetFinal.model.Client;
import fr.ajc.ProjetFinal.model.Commande;
import fr.ajc.ProjetFinal.model.CommandeProduit;
import fr.ajc.ProjetFinal.model.Produit;
import fr.ajc.ProjetFinal.model.Taille;
import fr.ajc.ProjetFinal.repository.CommandeRepository;
import fr.ajc.ProjetFinal.repository.ProduitRepository;
import fr.ajc.ProjetFinal.to.CommandeTo;
import fr.ajc.ProjetFinal.to.ProduitTo;

@Service
public class CommandeService {

	@Autowired
	CommandeRepository repo;

	@Autowired
	ProduitRepository pRepo;

	@Autowired
	ProduitService serviceProduit;

	@Autowired
	CommandeProduitService cps;

	@Autowired
	TailleService ts;

	public List<Commande> findAll() {
		return repo.findAll();
	}

	public Optional<Commande> findById(Long id) {
		return repo.findById(id);
	}

	public List<CommandeTo> findByClient(Client c) throws EmptyIdException {
		if (Objects.isNull(c)) {
			throw new IllegalArgumentException("client ne peut pas être null");
		}

		if (Objects.isNull(c.getId())) {
			throw new EmptyIdException("aucun id");
		}

		// on va créer notre transfert object
		ProduitTo pTo = new ProduitTo();
		CommandeTo cTo = new CommandeTo();

		List<ProduitTo> pTos = new ArrayList<>();
		List<CommandeTo> cTos = new ArrayList<>();

		List<Commande> commandes = repo.FindCommandeByClient(c.getId());

		for (Commande commande : commandes) {
			for (Produit p : commande.getProduits()) {
				// on récupère la ligne associant la commande, le produit, la taille et la
				// quantité
				CommandeProduit cp = cps.getCommandeProduit(commande.getId(), p.getId());

				// on construit l'objet de transfert
				pTo.setProduit(p);
				pTo.setQuantite(cp.getQuantite());
				pTo.setTaille(cp.getTaille());
				pTos.add(pTo);
			}

			// on construit l'objet de transfert
			cTo.setClient(c);
			cTo.setId(commande.getId());
			cTo.setProduits(pTos);
			cTo.setTotal(commande.getTotal());

			cTos.add(cTo);
		}

		return cTos;
	}

	public Commande create(CommandeTo cTo)
			throws RelationException, EmptyIdException, StockInsuffisantException, IdNotFound {
		if (Objects.isNull(cTo.getProduits()))
			throw new RelationException("Il manque les produits");
		if (Objects.isNull(cTo.getClient()))
			throw new RelationException("Il manque le client");

		// on vérifie si les produits sont en stock
		for (ProduitTo pTo : cTo.getProduits()) {

			// on récupère le stock en bdd du produit à la taille demandé
			Integer stock = ts.findStockByTaille(pTo.getTaille(), pTo.getProduit().getId());

			// on le compare à la quantité commandé
			if (stock == null || stock < pTo.getQuantite())
				throw new StockInsuffisantException(
						"Stock insuffisant pour le produit [" + pTo.getProduit().getReference() + "].");
		}

		// si on est la, c'est qu'on a assez de stock. On refait la boucle, cette fois
		// pour actualiser l'ensemble de la commande
		for (ProduitTo pTo : cTo.getProduits()) {

			// on récupère le stock en bdd du produit à la taille demandé
			Integer stock = ts.findStockByTaille(pTo.getTaille(), pTo.getProduit().getId());

			// si c'est ok, on actualise le stock en bdd
			stock = stock - pTo.getQuantite();
			Produit p = serviceProduit.findById(pTo.getProduit().getId()).get();
			List<Taille> tailles = new ArrayList<>();
			for (Taille t : p.getTailles()) {
				if (t.getTaille().equals(pTo.getTaille()))
					t.setStock(stock);
				tailles.add(t);
			}

			p.setTailles(tailles);

			serviceProduit.modifyProduit(p);

		}

		// on créer la commande en bdd
		Commande c = cTo.toCommande();

		c = repo.save(c);

		// on créer la commande_produit en bdd
		cps.createCommandeProduit(c, cTo);

		return c;
	}

	public Commande modifyCommande(CommandeTo cTo)
			throws EmptyIdException, RelationException, StockInsuffisantException, IdNotFound {

		Commande commande = this.checkCommande(cTo);

		if (Objects.isNull(cTo.getProduits()))
			throw new RelationException("Il manque les produits");
		if (Objects.isNull(cTo.getClient()))
			throw new RelationException("Il manque le client");

		// on supprime la commande existante
		this.deleteCommande(cTo.getId());

		// on supprime l'id dans cTo
		cTo.setId(null);

		// on créer une nouvelle commande
		return create(cTo);
	}

	public void deleteCommande(Long id) {
		repo.deleteById(id);
	}

	private Commande checkCommande(Commande f) throws EmptyIdException {
		if (Objects.isNull(f.getId()) || !this.findById(f.getId()).isPresent()) {
			throw new EmptyIdException("La commande n'a pas d'id");
		}
		return f;
	}

	private Commande checkCommande(CommandeTo f) throws EmptyIdException {
		Optional<Commande> c = this.findById(f.getId());
		if (Objects.isNull(f.getId()) || !c.isPresent()) {
			throw new EmptyIdException("La commande n'a pas d'id");
		}
		return c.get();
	}

}

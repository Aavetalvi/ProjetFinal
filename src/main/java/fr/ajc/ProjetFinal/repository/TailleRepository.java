package fr.ajc.ProjetFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajc.ProjetFinal.model.Taille;
import fr.ajc.ProjetFinal.model.TailleId;

public interface TailleRepository extends JpaRepository<Taille, TailleId> {

}

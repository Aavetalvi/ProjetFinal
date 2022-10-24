package fr.ajc.ProjetFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajc.ProjetFinal.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

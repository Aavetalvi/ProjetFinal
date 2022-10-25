package fr.ajc.ProjetFinal.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ajc.ProjetFinal.exception.EmptyIdException;
import fr.ajc.ProjetFinal.exception.RelationException;
import fr.ajc.ProjetFinal.model.Client;
import fr.ajc.ProjetFinal.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	public Client creerClient(Client c) {

		return clientRepository.save(c);
	}

	public Client updateClient(Client c) throws IllegalArgumentException, EmptyIdException {
		if (Objects.isNull(c)) {
			throw new IllegalArgumentException("client ne peut pas être null");
		}

		if (Objects.isNull(c.getId())) {
			throw new EmptyIdException("aucun id");
		}
		return clientRepository.save(c);

	}

	public void deleteClient(Client c) {
		clientRepository.delete(c);

	}

	public void deleteClientById(Long id) throws RelationException {

		try {
			clientRepository.deleteById(id);
		} catch (Exception e) {
			throw new RelationException("id [" + id + "] client existe encore.");
		}
	}

	public List<Client> getAllClient() {
		return clientRepository.findAll();
	}

	public Optional<Client> getClient(Long id) {
		return clientRepository.findById(id);
	}

	public Optional<Client> getClientByMail(String mail) {
		return clientRepository.FindByEmail(mail);
	}

}

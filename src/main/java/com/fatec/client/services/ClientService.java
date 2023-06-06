package com.fatec.client.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.client.entities.Client;
import com.fatec.client.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    
    public Client getClientById(int id){
        return clientRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Erro 404: Cliente não encontrado")
        );
    }
    
    public List<Client> getClients(){
        return clientRepository.findAll();  
    }

    public void delete(int id) {
        Client client = getClientById(id);
        clientRepository.delete(client);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void update(int id, Client client) {
        getClientById(id);
        clientRepository.save(client);
    }
}

package com.fatec.client.resources;


import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.client.entities.Client;
import com.fatec.client.services.ClientService;

@RestController
@RequestMapping("clients")
@CrossOrigin
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    
    
    @GetMapping("{id}")
    public ResponseEntity<Client> getClient(@PathVariable int id){
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok().body(client);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getClients(){
        List<Client> client = clientService.getClients();
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Void> editClient(@PathVariable int id, @RequestBody Client client ){
        clientService.update(id, client);
        return ResponseEntity.ok().build();
    }
     
    
    
    @PostMapping
    public ResponseEntity<Client> saveCliet(@RequestBody Client client ){
        Client newClient = clientService.save(client);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(client.getId())

        .toUri();
        return ResponseEntity.created(location).body(newClient);
    }







    
}
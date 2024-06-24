package com.mayo.insurance.dao;

import com.mayo.insurance.dto.Claim;
import com.mayo.insurance.dto.Client;
import com.mayo.insurance.repository.ClaimRepository;
import com.mayo.insurance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientDAO {

    @Autowired
    private ClientRepository clientRepository;

    public Client insertClient(Client client){
        return clientRepository.save(client);
    }

    public Client getClientId(int clientId){
        Optional<Client> optional = clientRepository.findById(clientId);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public Client deleteClientId(int clientId){
        Optional<Client> optional = clientRepository.findById(clientId);
        if (optional.isPresent()){
            clientRepository.deleteById(clientId);
        }
        return null;
    }

    public Client updateClient(Client client){
        return clientRepository.save(client);
    }

    public List<Client> displayAllClient(){
        return clientRepository.findAll();
    }
}

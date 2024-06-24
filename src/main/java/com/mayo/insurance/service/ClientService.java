package com.mayo.insurance.service;

import com.mayo.insurance.dao.ClientDAO;
import com.mayo.insurance.dao.InsurancePolicyDAO;
import com.mayo.insurance.dto.ApiResponse;
import com.mayo.insurance.dto.Client;
import com.mayo.insurance.dto.InsurancePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClientService {

    @Autowired
    private InsurancePolicyDAO insurancePolicyDAO;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private ApiResponse<Client> clientResponse;

    @Autowired
    private ApiResponse<List<Client>> findAllClientResponse;

    public ApiResponse<Client> insertClaim(Client client, int policyId){
        InsurancePolicy insuracePolicy = insurancePolicyDAO.getInsuracePolicyById(policyId);
        if (Objects.isNull(insuracePolicy)){
            clientResponse.setStatusCode((HttpStatus.NOT_ACCEPTABLE.value()));
            clientResponse.setMsg("No Client Found");
            clientResponse.setData(null);
        }
        else{
            client.setInsurancePolicy(insuracePolicy);
            clientDAO.insertClient(client);

            clientResponse.setStatusCode(HttpStatus.ACCEPTED.value());
            clientResponse.setMsg("Client Successfully Done");
            clientResponse.setData(client);
        }
        return clientResponse;
    }

    public ApiResponse<Client> getByClientId(int clientId){
        Client client = clientDAO.getClientId(clientId);

        if (Objects.isNull(client)){
            throw  new RuntimeException("Client details not found");
        }
        clientResponse.setStatusCode(HttpStatus.ACCEPTED.value());
        clientResponse.setMsg("Client Exists");
        clientResponse.setData(client);

        return clientResponse;
    }

    public ApiResponse<Client> deleteClient(int clientId){
        Client client = clientDAO.deleteClientId(clientId);

        if (Objects.isNull(client)){
            throw  new RuntimeException("Client details not found");
        }
        clientResponse.setStatusCode(HttpStatus.FOUND.value());
        clientResponse.setMsg("Client Deleted Successfully");
        clientResponse.setData(client);

        return clientResponse;
    }

    public ApiResponse<Client> updateClaim(Client client){
        Client updateClient = clientDAO.getClientId(client.getClientId());

        if (Objects.isNull(updateClient)){
            throw  new RuntimeException("Claim details not found");
        }

        updateClient.setClientName(client.getClientName());
        updateClient.setClientAddress(client.getClientAddress());
        updateClient.setClientContactInformation(client.getClientContactInformation());
        updateClient.setClientDateOfBirth(client.getClientDateOfBirth());

        clientDAO.updateClient(client);

        clientResponse.setStatusCode(HttpStatus.ACCEPTED.value());
        clientResponse.setMsg("Client updated Successfully");
        clientResponse.setData(client);

        return clientResponse;
    }

    public ApiResponse<List<Client>> displayAllClient(){
        List<Client> clients =  clientDAO.displayAllClient();

        if (Objects.isNull(clients)){
            findAllClientResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            findAllClientResponse.setMsg("Client Details are not available");
            findAllClientResponse.setData(null);
        } else {
            findAllClientResponse.setStatusCode(HttpStatus.FOUND.value());
            findAllClientResponse.setMsg("Client Details are  available");
            findAllClientResponse.setData(clients);
        }
        return findAllClientResponse;
    }
}



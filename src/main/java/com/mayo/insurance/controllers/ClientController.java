package com.mayo.insurance.controllers;

import com.mayo.insurance.dto.ApiResponse;
import com.mayo.insurance.dto.Client;
import com.mayo.insurance.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/saveClaim/{policyID")
    public ApiResponse<Client> saveClient(@RequestBody Client client, @PathVariable int policyId){

        return clientService.insertClaim(client, policyId);
    }

    @GetMapping("/getByClaim/{claimId}")
    public ApiResponse<Client> getByClientId(@PathVariable int clientId){
        return clientService.getByClientId(clientId);

    }

    @DeleteMapping("/deleteClaim/{claimId")
    public ApiResponse<Client> deleteClient(@PathVariable int clientId){
        return clientService.deleteClient(clientId);
    }

    @PutMapping("/updateClaim/{claimId}")
    public ApiResponse<Client> updateClient(@RequestBody Client client){
        return clientService.updateClient(client);
        }
    @GetMapping("/displayAllClaim")
    public ApiResponse<List<Client>>displayAllClient(){
        return clientService.displayAllClient();
    }

}

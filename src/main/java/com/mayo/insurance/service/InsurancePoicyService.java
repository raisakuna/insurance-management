package com.mayo.insurance.service;

import com.mayo.insurance.dao.InsurancePolicyDAO;
import com.mayo.insurance.dto.ApiResponse;
import com.mayo.insurance.dto.InsurancePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InsurancePoicyService {

    @Autowired
    private InsurancePolicyDAO insurancePolicyDAO;



    @Autowired
    private ApiResponse<InsurancePolicy> clientResponse;

    @Autowired
    private ApiResponse<List<InsurancePolicy>> findAllClientResponse;

    public ApiResponse<InsurancePolicy> insertInsurancePolcy(InsurancePolicy insurancePolicy){
         insurancePolicy = insurancePolicyDAO.insertInsurancePolicy(insurancePolicy);

        if (Objects.isNull(insurancePolicy)){
            clientResponse.setStatusCode((HttpStatus.NOT_ACCEPTABLE.value()));
            clientResponse.setMsg("No Policy Found");
            clientResponse.setData(null);
        }
        else{

            clientResponse.setStatusCode(HttpStatus.ACCEPTED.value());
            clientResponse.setMsg("Policy Successfully Done");
            clientResponse.setData(insurancePolicy);
        }
        return clientResponse;
    }

    public ApiResponse<InsurancePolicy> getByInsurancePolciyId(int clientId){
        InsurancePolicy insurancePolicy = insurancePolicyDAO.getInsuracePolicyById(clientId);

        if (Objects.isNull(insurancePolicy)){
            throw  new RuntimeException("Policy details not found");
        }
        clientResponse.setStatusCode(HttpStatus.ACCEPTED.value());
        clientResponse.setMsg("Policy Exists");
        clientResponse.setData(insurancePolicy);

        return clientResponse;
    }

    public ApiResponse<InsurancePolicy> deleteInsurancePolicy(int policyId){
        InsurancePolicy insurancePolicy = insurancePolicyDAO.deleteInsurancePolicy(policyId);

        if (Objects.isNull(insurancePolicy)){
            throw  new RuntimeException("Policy details not found");
        }
        clientResponse.setStatusCode(HttpStatus.FOUND.value());
        clientResponse.setMsg("Policy Deleted Successfully");
        clientResponse.setData(insurancePolicy);

        return clientResponse;
    }



    public ApiResponse<List<InsurancePolicy>> displayAllPolicy(){
        List<InsurancePolicy> insurancePolicies =  insurancePolicyDAO.displayAllInsurancePolicy();

        if (Objects.isNull(insurancePolicies)){
            findAllClientResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            findAllClientResponse.setMsg("Policy Details are not available");
            findAllClientResponse.setData(null);
        } else {
            findAllClientResponse.setStatusCode(HttpStatus.FOUND.value());
            findAllClientResponse.setMsg("Policy Details are  available");
            findAllClientResponse.setData(insurancePolicies);
        }
        return findAllClientResponse;
    }
}



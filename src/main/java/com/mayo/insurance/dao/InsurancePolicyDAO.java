package com.mayo.insurance.dao;

import com.mayo.insurance.dto.Client;
import com.mayo.insurance.dto.InsurancePolicy;
import com.mayo.insurance.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InsurancePolicyDAO {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    public InsurancePolicy insurancePolicy(InsurancePolicy insurancePolicy){

        return insurancePolicyRepository.save(insurancePolicy);
    }

    public InsurancePolicy getInsuracePolicyById(int insurancePolicyId){
        Optional<InsurancePolicy> optional = insurancePolicyRepository.findById(insurancePolicyId);
        if(optional.isPresent()){
            InsurancePolicy insurancePolicy= optional.get();
            return insurancePolicy;
        }
        return null;
    }

    public InsurancePolicy deleteInsurancePolicy(int policyId){
        Optional<InsurancePolicy> optional = insurancePolicyRepository.findById(policyId);
        if (optional.isPresent()){
            insurancePolicyRepository.deleteById(policyId);
        }
        return null;
    }



    public List<InsurancePolicy> displayAllInsurancePolicy(){
        return insurancePolicyRepository.findAll();
    }
}

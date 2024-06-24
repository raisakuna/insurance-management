package com.mayo.insurance.service;

import com.mayo.insurance.dao.ClaimDAO;
import com.mayo.insurance.dao.InsurancePolicyDAO;
import com.mayo.insurance.dto.ApiResponse;
import com.mayo.insurance.dto.Claim;
import com.mayo.insurance.dto.InsurancePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClaimService {
    @Autowired
    private InsurancePolicyDAO insurancePolicyDAO;

    @Autowired
    private ClaimDAO claimDAO;

    @Autowired
    private ApiResponse<Claim> claimResponse;

    public ApiResponse<Claim> insertClaim(Claim claim, int policyId){
        InsurancePolicy insuracePolicy = insurancePolicyDAO.getInsuracePolicyById(policyId);
        if (Objects.isNull(insuracePolicy)){
            claimResponse.setStatusCode((HttpStatus.NOT_ACCEPTABLE.value()));
            claimResponse.setMsg("Not Claimed Yet");
            claimResponse.setData(null);
        }
        else{
            claim.setInsurancePolicy(insuracePolicy);
            claimDAO.insertClaim(claim);

            claimResponse.setStatusCode(HttpStatus.ACCEPTED.value());
            claimResponse.setMsg("Claim Successfully Done");
            claimResponse.setData(claim);
        }
            return claimResponse;
    }

    public ApiResponse<Claim> getByClaimId(int claimId){
        Claim claim = claimDAO.getByClaimId(claimId);

        if (Objects.isNull(claim)){
            throw  new RuntimeException("Claim details not found");
        }
        claimResponse.setStatusCode(HttpStatus.ACCEPTED.value());
        claimResponse.setMsg("Claim Exists");
        claimResponse.setData(claim);

        return claimResponse;
    }

    public ApiResponse<Claim> deleteClaim(int claimId){
        Claim claim = claimDAO.deleteClaimId(claimId);

        if (Objects.isNull(claim)){
            throw  new RuntimeException("Claim details not found");
        }
        claimResponse.setStatusCode(HttpStatus.FOUND.value());
        claimResponse.setMsg("Claim Deleted Succesfully");
        claimResponse.setData(claim);

        return claimResponse;
    }

    public ApiResponse<Claim> updateClaim(Claim claim){
        Claim updateClaim = claimDAO.deleteClaimId(claim.getClaimId());

        if (Objects.isNull(updateClaim)){
            throw  new RuntimeException("Claim details not found");
        }
        claimResponse.setStatusCode(HttpStatus.FOUND.value());
        claimResponse.setMsg("Claim Deleted Succesfully");
        claimResponse.setData(claim);

        return claimResponse;
    }
}

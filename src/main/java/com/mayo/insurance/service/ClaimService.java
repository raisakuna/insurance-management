package com.mayo.insurance.service;

import com.mayo.insurance.dao.ClaimDAO;
import com.mayo.insurance.dao.InsurancePolicyDAO;
import com.mayo.insurance.dto.ApiResponse;
import com.mayo.insurance.dto.Claim;
import com.mayo.insurance.dto.InsurancePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClaimService {
    @Autowired
    private InsurancePolicyDAO insurancePolicyDAO;

    @Autowired
    private ClaimDAO claimDAO;

    @Autowired
    private ApiResponse<Claim> claimResponse;

    @Autowired
    private ApiResponse<List<Claim>> findAllClaimResponse;

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
        claimResponse.setMsg("Claim Deleted Successfully");
        claimResponse.setData(claim);

        return claimResponse;
    }

    public ApiResponse<Claim> updateClaim(Claim claim){
        Claim updateClaim = claimDAO.getByClaimId(claim.getClaimId());

        if (Objects.isNull(updateClaim)){
            throw  new RuntimeException("Claim details not found");
        }

        updateClaim.setClaimNumber(claim.getClaimNumber());
        updateClaim.setClaimDate(claim.getClaimDate());
        updateClaim.setClaimDescription(claim.getClaimDescription());
        updateClaim.setClaimStatus(claim.getClaimStatus());

        claimResponse.setStatusCode(HttpStatus.ACCEPTED.value());
        claimResponse.setMsg("Claim updated Successfully");
        claimResponse.setData(claim);

        return claimResponse;
    }

    public ApiResponse<List<Claim>> displayAllClaim(){
    List<Claim> claims =  claimDAO.displayAllClaim();

    if (Objects.isNull(claims)){
        findAllClaimResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        findAllClaimResponse.setMsg("Claim Details are not available");
        findAllClaimResponse.setData(null);
    } else {
        findAllClaimResponse.setStatusCode(HttpStatus.FOUND.value());
        findAllClaimResponse.setMsg("Claim Details are  available");
        findAllClaimResponse.setData(claims);
    }
    return findAllClaimResponse;
    }
}

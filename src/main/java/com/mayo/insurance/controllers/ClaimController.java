package com.mayo.insurance.controllers;

import com.mayo.insurance.dto.ApiResponse;
import com.mayo.insurance.dto.Claim;
import com.mayo.insurance.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClaimController {
    @Autowired
    private ClaimService claimService;

    @PostMapping("/saveClaim/{policyID")
    public ApiResponse<Claim> saveClaim(@RequestBody Claim claim, @PathVariable int policyId){

        return claimService.insertClaim(claim, policyId);
    }

    @GetMapping("/getByClaim/{claimId}")
    public ApiResponse<Claim> getByClaimId(@PathVariable int claimId){
        return claimService.getByClaimId(claimId);

    }

    @DeleteMapping("/deleteClaim/{claimId")
    public ApiResponse<Claim> deleteClaim(@PathVariable int claimId){
        return claimService.deleteClaim(claimId);
    }

    @PutMapping("/updateClaim/{claimId}")
    public ApiResponse<Claim> updateClaim(@RequestBody Claim claim){
        return claimService.updateClaim(claim);
        }
    @GetMapping("/displayAllClaim")
    public ApiResponse<List<Claim>>displayAllClaim(){
        return claimService.displayAllClaim();
    }

}

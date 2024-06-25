package com.mayo.insurance.controllers;

import com.mayo.insurance.dto.ApiResponse;
import com.mayo.insurance.dto.InsurancePolicy;
import com.mayo.insurance.service.InsurancePoicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InsurancePolicyController {
    @Autowired
    private InsurancePoicyService insurancePoicyService;

    @PostMapping("/saveInsurancePolicy")
    public ApiResponse<InsurancePolicy> insertPolicyPolicy(@RequestBody InsurancePolicy insurancePolicy){

        return insurancePoicyService.insertInsurancePolcy(insurancePolicy);
    }

    @GetMapping("/getByInsurancePolicyId/{insurancePolicyId}")
    public ApiResponse<InsurancePolicy> getByInsurancePolicyId(@PathVariable int insurancePolicyId){
        return insurancePoicyService.getByInsurancePolciyId(insurancePolicyId);

    }

    @DeleteMapping("/deleteInsurancePolicy/{policyId")
    public ApiResponse<InsurancePolicy> deleteInsurancePolicy(@PathVariable int policyId){
        return insurancePoicyService.deleteInsurancePolicy(policyId);
    }


    @GetMapping("/displayAllPolcy")
    public ApiResponse<List<InsurancePolicy>>displayAllPolicy(){
        return insurancePoicyService.displayAllPolicy();
    }

}

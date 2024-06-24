package com.mayo.insurance.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="InsurancePolicy")
@Getter
@Setter
public class InsurancePolicy {
    @Id
    @Column(name="policyId")
    private int insurancePolicyId;

    @Column(name="policyType")
    private String insurancePolicyType;

    @Column(name="policyCoverageAmount")
    private String insurancePolicyCoverageAmount;

    @Column(name="policyPremium")
    private String insurancePolicyPremium;

    @Column(name="policyStartDate")
    private String insurancePolicyStartDate;

    @Column(name="policyEndDate")
    private String insurancePolicyEndDate;

}

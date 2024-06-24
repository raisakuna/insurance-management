package com.mayo.insurance.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Claim {
    @Id
    @Column(name="claimId")
    private int claimId;

    @Column(name="claimNumber")
    private String claimNumber;

    @Column(name="claimDescription")
    private String claimDescription;

    @Column(name="claimDate")
    private String claimDate;

    @Column(name="claimStatus")
    private String claimStatus;

    @Column(name="claimAmount")
    private String claimAmount;

    @OneToOne
    @JoinColumn(name="policyId")
    private InsurancePolicy insurancePolicy;

}

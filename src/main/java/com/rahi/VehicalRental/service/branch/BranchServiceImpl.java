package com.rahi.VehicalRental.service.branch;

import com.rahi.VehicalRental.model.entity.Branch;
import com.rahi.VehicalRental.repository.BranchRepository;
import com.rahi.VehicalRental.type.BranchType;
import com.rahi.VehicalRental.type.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Branch createBranch(BranchType branchType, VehicleType vehicleType) {
        return branchRepository.save(Branch.builder()
                .branchType(branchType)
                .vehicleType(vehicleType)
                .build());
    }
}

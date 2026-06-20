package com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.orders.application.port.in.branch.CreateBranchUseCase;
import com.alexhiz.hexagonal.orders.application.port.in.branch.GetBranchUseCase;
import com.alexhiz.hexagonal.orders.domain.model.Branch;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.request.BranchRequest;
import com.alexhiz.hexagonal.orders.infrastructure.adapter.in.rest.dto.response.BranchResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/branches")
public class BranchController {

    private final CreateBranchUseCase createBranchUseCase;
    private final GetBranchUseCase getBranchUseCase;

    @PostMapping
    public ResponseEntity<BranchResponse> createBranch(@Valid @RequestBody BranchRequest branchRequest) {
        Branch branch = Branch.builder().name(branchRequest.getName()).build();
        Branch create = createBranchUseCase.createBranch(branch);
        return ResponseEntity.status(HttpStatus.CREATED).body(BranchResponse.from(create));
    }
}

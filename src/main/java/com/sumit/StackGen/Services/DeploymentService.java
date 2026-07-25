package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Preview.DeployResponse;

public interface DeploymentService {

    public DeployResponse deploy(Long projectId);
}

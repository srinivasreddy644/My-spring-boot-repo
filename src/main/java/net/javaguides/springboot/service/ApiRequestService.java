package net.javaguides.springboot.service;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.ApiRequest;
import net.javaguides.springboot.repository.ApiRequestRepository;

@Service
public class ApiRequestService {

    private ApiRequestRepository apiRequestRepository;

    public ApiRequestService(ApiRequestRepository apiRequestRepository) {
        this.apiRequestRepository = apiRequestRepository;
    }

    public void saveRequest(String userId, String request, String response) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUserId(userId);
        apiRequest.setRequest(request);
        apiRequest.setResponse(response);
        apiRequestRepository.save(apiRequest);
    }
}

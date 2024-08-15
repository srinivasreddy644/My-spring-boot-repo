package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.springboot.model.ApiRequest;

public interface ApiRequestRepository extends JpaRepository<ApiRequest, Long> {
}

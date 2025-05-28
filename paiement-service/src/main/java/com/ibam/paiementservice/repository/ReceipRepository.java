package com.ibam.paiementservice.repository;

import com.ibam.paiementservice.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ReceipRepository extends JpaRepository<Receipt, Long> {
}

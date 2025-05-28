package com.ibam.paiementservice.feign;

import com.ibam.paiementservice.entities.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "user-service")
public interface UserRestClient {

    @GetMapping("api/users/{id}")
    User getUser(@PathVariable Long id);
}

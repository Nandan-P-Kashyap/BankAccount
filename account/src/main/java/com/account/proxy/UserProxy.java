package com.account.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.account.dto.UserEntityDto;

@FeignClient(name="user")
public interface UserProxy {
	
	@GetMapping("/getById/{id}")
    public ResponseEntity<UserEntityDto> getById(@PathVariable Long id );

}

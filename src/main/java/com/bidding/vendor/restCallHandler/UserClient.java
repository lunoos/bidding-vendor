package com.bidding.vendor.restCallHandler;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="AUTH")
public interface UserClient {
	
	@GetMapping("/auth/user/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable Long userId);
}

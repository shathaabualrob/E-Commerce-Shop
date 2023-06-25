package com.ejada.task2shop.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="wallet-service")
public interface WalletProxy {

	//	wallet creation called when user is first registered
	@PostMapping("/wallet/{userId}/wallet-creation")
	public String walletCreation(@PathVariable Integer userId);
	
	// deposit + create +transaction
	@PostMapping("/wallet/{userId}/deposit/{amount}")
	public String deposit(@PathVariable Integer userId, @PathVariable Float amount);
	
	// withdraw + create -transaction
	@PostMapping("/wallet/{userId}/withdraw/{amount}")
	public String withdraw(@PathVariable Integer userId, @PathVariable Float amount);
}

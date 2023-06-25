package com.ejada.task2wallet.wallet;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	
	private final WalletService walletService;
//	private final CircuitBreaker circuitBreaker;
	

	@Autowired
	public WalletController(WalletService walletService 
//			CircuitBreakerRegistry circuitBreakerRegistry
			) {
	    this.walletService = walletService;
//	    this.circuitBreaker = (CircuitBreaker) circuitBreakerRegistry.circuitBreaker("myCircuitBreaker");
	}
	
	//	wallet creation called when user is first registered
	@PostMapping("/{userId}/wallet-creation")
	@CircuitBreaker(fallbackMethod = "fallbackmethod", name = "default")
	public String walletCreation(@PathVariable Integer userId) {
		return walletService.walletCreation(userId);
	}
	
	// deposit + create +transaction
	@PostMapping("/wallet/{userId}/deposit/{amount}")
	@CircuitBreaker(fallbackMethod = "fallbackmethod", name = "default")
	public String deposit(@PathVariable Integer userId, @PathVariable Float amount) {
		return walletService.depositOperation(userId, amount);
	}
	
	// withdraw + create -transaction
	@PostMapping("/wallet/{userId}/withdraw/{amount}")
	@CircuitBreaker(fallbackMethod = "fallbackmethod", name = "default")
	public String withdraw(@PathVariable Integer userId, @PathVariable Float amount) {
		return walletService.withdrawalOperation(userId, amount);
	}
	public String fallbackmethod() {
		return "service is down and will be back up in no time";
	}
	
}

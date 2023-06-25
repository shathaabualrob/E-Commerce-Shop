package com.ejada.task2wallet.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	public TransactionService transactionService;
	
	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	// GET: Retrieves a specific transaction by its ID.
	@GetMapping("/{transactionId}")
	public Transaction getTransactionById(@PathVariable Integer transactionId) {
		return transactionService.getTransactionById(transactionId);
	}
	
	// GET /transactions/user/{userId}: Retrieves all transactions associated with a specific user.
	@GetMapping("/user-transactions/{userId}")
	public List<Transaction> getAllTransactionsForUser(@PathVariable Integer userId) {
		return transactionService.getAllTransactionsForUser(userId);
	} 
	
	//	POST: Must be creating a new transaction based on the request body.
	@PostMapping("/{walletId}")
	public Transaction createTransaction(
			@PathVariable Integer walletId,
			@RequestParam("amount") Float amount) {
		return transactionService.createTransaction(walletId, amount);
	}
}

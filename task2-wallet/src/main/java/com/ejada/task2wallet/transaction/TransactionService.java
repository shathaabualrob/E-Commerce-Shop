package com.ejada.task2wallet.transaction;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejada.task2wallet.exception.EntityCreationException;
import com.ejada.task2wallet.exception.NotFoundException;
import com.ejada.task2wallet.wallet.Wallet;
import com.ejada.task2wallet.wallet.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	
	public TransactionRepository transactionRepository;
	public WalletRepository walletRepository;
	
	@Autowired
	public TransactionService(
			TransactionRepository transactionRepository,
			WalletRepository walletRepository
			) {
		this.transactionRepository = transactionRepository;
		this.walletRepository = walletRepository;
	}
	
	public Transaction getTransactionById(Integer transactionId) {
		return transactionRepository.findById(transactionId).get();
	}
	
	public List<Transaction> getAllTransactionsForUser(Integer userId) {
		Wallet wallet = walletRepository.getByUserId(userId);
		return transactionRepository.getAllByWalletId(wallet.getId());
	}
	
	@Transactional
	public Transaction createTransaction(Integer walletId, Float amount ) {
		Wallet wallet;
		try {
			wallet = walletRepository.findById(walletId).get();
		} catch (Exception e) {
			throw new NotFoundException("There are no wallets created for this user.");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(amount);
		newTransaction.setDate(LocalDateTime.now());
		newTransaction.setWallet(wallet);
		try {
			return transactionRepository.save(newTransaction);
		} catch (Exception e) {
			throw new EntityCreationException("Transaction cannot be created");
		}
	}
}
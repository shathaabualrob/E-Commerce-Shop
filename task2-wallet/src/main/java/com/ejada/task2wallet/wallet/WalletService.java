package com.ejada.task2wallet.wallet;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejada.task2wallet.exception.EntityCreationException;
import com.ejada.task2wallet.exception.NotEnoughBalancException;
import com.ejada.task2wallet.exception.NotFoundException;
import com.ejada.task2wallet.transaction.Transaction;
import com.ejada.task2wallet.transaction.TransactionRepository;
import com.ejada.task2wallet.user.User;
import com.ejada.task2wallet.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class WalletService {

	private WalletRepository walletRepository;
	private TransactionRepository transactionRepository;
	private UserRepository userRepository;

	@Autowired
	public WalletService(
			WalletRepository walletRepository, 
			TransactionRepository transactionRepository,
			UserRepository userRepository
			) {
		this.walletRepository = walletRepository;
		this.transactionRepository = transactionRepository;
		this.userRepository = userRepository;
	}

	public Wallet getWalletById(Integer walletId) {
		try {
			return walletRepository.findById(walletId).get();
		} catch (Exception e) {
			throw new NotFoundException("Wallet was not found");
		}
	}
	
	public Wallet getWalletByUserId(Integer userId) {
		try {
			return walletRepository.getByUserId(userId);
		} catch (Exception e) {
			throw new NotFoundException("Wallet was not found");
		}
	}
	
	@Transactional
	public String walletCreation(Integer userId) {
		
		User user;
		try {
			user = userRepository.findById(userId).get();	
		} catch (Exception e) {
			throw new NotFoundException("Cannot create a wallet. User was not found.");
		}
		
		try {
			Wallet wallet = new Wallet();
			wallet.setBalance(0.0f);
			wallet.setUser(user);
			wallet = walletRepository.save(wallet);
			return "Your wallet has been created and your current balance is: $0.0";
		} catch (Exception e) {
			throw new EntityCreationException("Error creating the user's wallet");
		}
	}
	
	@Transactional
	public String withdrawalOperation(Integer userId, Float amount) {
		
		Wallet wallet;
		try {
			wallet = walletRepository.getByUserId(userId);
		} catch (Exception e) {
			throw new NotFoundException("No wallet was found for this user");
		}
		if(wallet.getBalance() < amount*-1) 
			throw new NotEnoughBalancException("No enough balance.");
		else {
			wallet.setBalance(wallet.getBalance()-(amount*-1));
			try {walletRepository.save(wallet);} 
			catch (Exception e) {
				throw new EntityCreationException
					("Error occurred while trying to create a wallet for this user");
			}
			try {
				Transaction createdTrans = transactionRepository
						.save(new Transaction(amount,LocalDateTime.now(),wallet));
				return "You transaction has been created: "+ createdTrans.toString();
			} catch (Exception e) {
				throw new EntityCreationException
				("Error occurred while trying to create a transaction for this user");
			}	 
		}
	}
	
	@Transactional
	public String depositOperation(Integer userId, Float amount) {
		Wallet wallet;
		try {
			wallet = walletRepository.getByUserId(userId);
		} catch (Exception e) {
			throw new NotFoundException("No wallet was found for this user");
		}
		if(wallet.getBalance() < amount*-1) 
			throw new NotEnoughBalancException("No enough balance.");
		else {
			wallet.setBalance(wallet.getBalance()+amount);
			try {walletRepository.save(wallet);} 
			catch (Exception e) {
				throw new EntityCreationException
					("Error occurred while trying to create a wallet for this user");
			}
			try {
				Transaction createdTrans = transactionRepository
						.save(new Transaction(amount,LocalDateTime.now(),wallet));
				return "You transaction has been created: "+ createdTrans.toString();
			} catch (Exception e) {
				throw new EntityCreationException
				("Error occurred while trying to create a transaction for this user");
			}	 
		}
	}
}

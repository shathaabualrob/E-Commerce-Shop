package com.ejada.task2wallet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejada.task2wallet.dto.CreateUserDto;
import com.ejada.task2wallet.dto.LoginUserDto;
import com.ejada.task2wallet.exception.AccessDeniedException;
import com.ejada.task2wallet.exception.EntityCreationException;
import com.ejada.task2wallet.exception.FeignException;
import com.ejada.task2wallet.exception.NotFoundException;
import com.ejada.task2wallet.proxy.ShopProxy;
import com.ejada.task2wallet.wallet.Wallet;
import com.ejada.task2wallet.wallet.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private WalletRepository walletRepository;
	private ShopProxy shopProxy;
	
	@Autowired
	public UserService(
			UserRepository userRepository,
			WalletRepository walletRepository,
			ShopProxy shopProxy
			) {
		this.userRepository 	= userRepository;
		this.walletRepository 	= walletRepository;
		this.shopProxy 			= shopProxy;
	}
	
	public User getUserById(Integer userId) {
		try {
			return userRepository.findById(userId).get();
		} catch (Exception e) {
			throw new NotFoundException("User was not found");
		}
	}
	
	@Transactional
	public User userRegistration(CreateUserDto user) {
		User createdUser = new User();
		createdUser.setEmail(user.getEmail());
		createdUser.setUsername(user.getUsername());
		createdUser.setPassword(user.getPassword());
		createdUser.setIsAdmin(false);
		try {
			// Step1: creating the user
			createdUser = userRepository.save(createdUser);
			
			// Step2: creating the user's wallet
			Wallet wallet = new Wallet();
			wallet.setBalance(0.0f);
			wallet.setUser(createdUser);
			wallet = walletRepository.save(wallet);
			
			// Step3: creating the user's shopping cart
			try {
				shopProxy.createShoppingCartForUser(createdUser.getId());
			} catch (Exception e) {
				throw new FeignException("Error calling the Shop proxy inside the Wallet service.");
			}
			
			// Step4: Return the created user
			return createdUser;
			
		} catch (Exception e) {
			throw new EntityCreationException("User cannot be created");
		}
	}

	public String userLogin(LoginUserDto userInfo){
		try {
			User user;
			try {
				user = userRepository.findByUsername(userInfo.getUsername());
			} catch (Exception e) {
				throw new NotFoundException("User was not found");
			}
			if (userInfo.getPassword().equals(user.getPassword()))
				return "User is logged in.";
			else
				throw new AccessDeniedException("Wrong password.");
		}catch(Exception e) {
			throw new NotFoundException("User was not found.");
		}
	}
}
package com.ejada.task2wallet.wallet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
	public Wallet getByUserId(Integer userId);
}

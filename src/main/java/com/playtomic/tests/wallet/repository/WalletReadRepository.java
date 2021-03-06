package com.playtomic.tests.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playtomic.tests.wallet.repository.entity.Wallet;

@Repository
public interface WalletReadRepository extends JpaRepository<Wallet, Long> {
	
	public Optional<Wallet> findById(Long customerId);
	
}

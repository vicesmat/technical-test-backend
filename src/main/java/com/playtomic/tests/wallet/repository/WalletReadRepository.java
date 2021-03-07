package com.playtomic.tests.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.playtomic.tests.wallet.repository.entity.Wallet;

@RepositoryRestResource(exported = false)
public interface WalletReadRepository extends JpaRepository<Wallet, Long> {
	
	public Optional<Wallet> findById(Long customerId);
	
}

package com.playtomic.tests.wallet.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.playtomic.tests.wallet.repository.entity.Wallet;

@Repository
public interface WalletWriteRepository extends JpaRepository<Wallet, Long> {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Optional<Wallet> findById(Long customerId);
	
}

package com.pefonseca.pic.repository;

import com.pefonseca.pic.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}

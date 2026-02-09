package com.pefonseca.pic.repository;

import com.pefonseca.pic.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
    boolean existsByDescription(String description);
}

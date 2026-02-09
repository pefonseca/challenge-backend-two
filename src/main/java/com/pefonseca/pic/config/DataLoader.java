package com.pefonseca.pic.config;

import com.pefonseca.pic.entity.WalletType;
import com.pefonseca.pic.repository.WalletTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) {
        for (WalletType.Enum type : WalletType.Enum.values()) {
            if (!walletTypeRepository.existsByDescription(type.name().toLowerCase())) {
                walletTypeRepository.save(type.toEntity());
            }
        }
    }
}

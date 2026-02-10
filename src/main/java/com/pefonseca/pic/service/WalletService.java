package com.pefonseca.pic.service;

import com.pefonseca.pic.controller.dto.CreateWalletDTO;
import com.pefonseca.pic.entity.Wallet;
import com.pefonseca.pic.exception.WalletDataAlreadyExistsException;
import com.pefonseca.pic.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDTO dto) {
        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if(walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CnpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}

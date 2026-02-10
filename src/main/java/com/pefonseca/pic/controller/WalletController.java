package com.pefonseca.pic.controller;

import com.pefonseca.pic.controller.dto.CreateWalletDTO;
import com.pefonseca.pic.entity.Wallet;
import com.pefonseca.pic.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping(value = "/wallets")
    public ResponseEntity<Wallet> createWallet(@Valid @RequestBody CreateWalletDTO dto) {
        var wallet = walletService.createWallet(dto);

        return ResponseEntity.ok(wallet);
    }
}

package com.pefonseca.pic.controller.dto;

import com.pefonseca.pic.entity.Wallet;
import com.pefonseca.pic.entity.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDTO(@NotBlank String fullName,
                              @NotBlank String cpfCnpj,
                              @NotBlank String email,
                              @NotBlank String password,
                              @NotNull WalletType.Enum walletType) {

    public Wallet toWallet() {
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }

}

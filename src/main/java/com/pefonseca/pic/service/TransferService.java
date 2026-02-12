package com.pefonseca.pic.service;

import com.pefonseca.pic.controller.dto.TransferDTO;
import com.pefonseca.pic.entity.Transfer;
import com.pefonseca.pic.entity.Wallet;
import com.pefonseca.pic.exception.InsufficientBalanceException;
import com.pefonseca.pic.exception.TransferNotAllowedTypeException;
import com.pefonseca.pic.exception.TransferNotAuthorizedException;
import com.pefonseca.pic.exception.WalletNotFoundException;
import com.pefonseca.pic.repository.TransferRepository;
import com.pefonseca.pic.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository, AuthorizationService authorizationService, NotificationService notificationService, WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDTO transferDTO) {
        var sender = walletRepository.findById(transferDTO.payer()).orElseThrow(() -> new WalletNotFoundException(transferDTO.payer()));

        var receiver = walletRepository.findById(transferDTO.payer()).orElseThrow(() -> new WalletNotFoundException(transferDTO.payee()));

        validateTransfer(transferDTO, sender);

        sender.debit(transferDTO.value());
        receiver.credit(transferDTO.value());

        var transfer = new Transfer(sender, receiver, transferDTO.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);

        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDTO transferDTO, Wallet sender) {

        if(!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedTypeException();
        }

        if(!sender.isBalancerEqualOrGreatherThan(transferDTO.value())) {
            throw new InsufficientBalanceException();
        }

        if(!authorizationService.isAuthorized(transferDTO)) {
            throw new TransferNotAuthorizedException();
        }

    }

}

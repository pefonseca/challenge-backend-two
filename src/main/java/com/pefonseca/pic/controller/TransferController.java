package com.pefonseca.pic.controller;

import com.pefonseca.pic.controller.dto.TransferDTO;
import com.pefonseca.pic.entity.Transfer;
import com.pefonseca.pic.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(value = "/transfer")
    public ResponseEntity<Transfer> transfer(@Valid @RequestBody TransferDTO dto) {
        var resp = transferService.transfer(dto);

        return ResponseEntity.ok(resp);
    }

}

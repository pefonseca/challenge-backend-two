package com.pefonseca.pic.service;

import com.pefonseca.pic.client.AuthorizationClient;
import com.pefonseca.pic.controller.dto.TransferDTO;
import com.pefonseca.pic.exception.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDTO transfer) {
        var resp = authorizationClient.isAuthorized();

        if(resp.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return Boolean.TRUE.equals(resp.getBody().authorized());
    }
}

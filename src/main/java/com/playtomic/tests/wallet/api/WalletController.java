package com.playtomic.tests.wallet.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playtomic.tests.wallet.api.dto.OperationDto;
import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.service.OperationService;
import com.playtomic.tests.wallet.service.WalletService;

@RestController
@RequestMapping("/wallets")
public class WalletController {
	
    private Logger log = LoggerFactory.getLogger(WalletController.class);
    
    @Autowired
    WalletService walletService;
    
    @Autowired
    OperationService operationService;
    
    @GetMapping("/{id}")
    WalletDto getWalletById(@PathVariable Long id) {
        return walletService.getWalletById(id);
    }
    
    @PostMapping("/{id}/payments")
    void charge(@PathVariable Long id, @Valid @RequestBody OperationDto operation) {
    	operationService.charge(id, operation.getAmount());
    }
    
    @PostMapping("/{id}/top-ups")
    void recharge(@PathVariable Long id, @Valid @RequestBody OperationDto operation) {
    	operationService.recharge(id, operation.getAmount());
    }
    
}

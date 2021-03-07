package com.playtomic.tests.wallet.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.text.NumberFormat;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playtomic.tests.wallet.api.dto.OperationDto;
import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.service.WalletService;

@RestController
@RequestMapping("/wallets")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class WalletController {
	
    private Logger log = LoggerFactory.getLogger(WalletController.class);
    
    @Autowired
    WalletService walletService;
    
    @GetMapping("/{id}")
    WalletDto getWallet(@PathVariable Long id) {
    	log.info("Getting wallet {}", id);
    	WalletDto walletDto = walletService.getWalletById(id);
    	
    	walletDto.add(getWalletLink(id, true));
    	walletDto.add(getPaymentLink(id, false));
    	walletDto.add(getTopupLink(id, false));
    	
    	return walletDto;
    }
    
    @PostMapping("/{id}/payments")
    WalletDto charge(@PathVariable Long id, @RequestBody @Valid OperationDto operation) {
    	log.info("Charging {} to wallet {}", NumberFormat.getCurrencyInstance().format(operation.getAmount()), id);
    	WalletDto walletDto = walletService.charge(id, operation.getAmount());

    	walletDto.add(getPaymentLink(id, true));
    	walletDto.add(getWalletLink(id, false));
    	
    	return walletDto;
    }
    
    @PostMapping("/{id}/top-ups")
    WalletDto recharge(@PathVariable Long id, @RequestBody @Valid OperationDto operation) {
    	log.info("Recharging {} to wallet {}", NumberFormat.getCurrencyInstance().format(operation.getAmount()), id);
    	WalletDto walletDto = walletService.recharge(id, operation.getAmount());
    	
    	walletDto.add(getTopupLink(id, true));
    	walletDto.add(getWalletLink(id, false));
    	
    	return walletDto;
    }
    
    private Link getWalletLink(Long id, boolean isSelf) {
    	ControllerLinkBuilder builder = linkTo(methodOn(WalletController.class).getWallet(id));
    	return isSelf ? builder.withSelfRel() : builder.withRel("wallet");
    }
    
    private Link getPaymentLink(Long id, boolean isSelf) {
    	ControllerLinkBuilder builder = linkTo(methodOn(WalletController.class).charge(id, null));
    	return isSelf ? builder.withSelfRel() : builder.withRel("payment");
    }
    
    private Link getTopupLink(Long id, boolean isSelf) {
    	ControllerLinkBuilder builder = linkTo(methodOn(WalletController.class).recharge(id, null));
    	return isSelf ? builder.withSelfRel() : builder.withRel("top-up");
    }
    
}

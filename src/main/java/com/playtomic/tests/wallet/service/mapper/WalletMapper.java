package com.playtomic.tests.wallet.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.repository.entity.Wallet;

@Mapper(componentModel = "spring")
public interface WalletMapper {
	
	@Mapping(source = "id", target = "walletId")
	@Mapping(target = "links", ignore = true)
    WalletDto toDto(Wallet entity);
    
}

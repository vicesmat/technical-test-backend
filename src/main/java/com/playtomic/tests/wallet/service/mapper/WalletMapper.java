package com.playtomic.tests.wallet.service.mapper;

import org.mapstruct.Mapper;

import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.repository.entity.Wallet;

@Mapper(componentModel = "spring")
public interface WalletMapper {
	
    WalletDto toDto(Wallet entity);
    
    Wallet toEntity(WalletDto dto);
    
}

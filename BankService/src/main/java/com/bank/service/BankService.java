package com.bank.service;

import com.bank.persistence.entity.Account;
import org.jpos.iso.ISOException;
import org.springframework.stereotype.Component;

@Component
public interface BankService {
    Account getAccount(String accountNumber);
    String processTransaction(String type, String data, String processingCode) throws ISOException;

}

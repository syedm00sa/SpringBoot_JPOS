package com.bank.controller;

import com.bank.persistence.entity.Account;
import com.bank.persistence.model.TransactionRequest;
import com.bank.service.BankService;
import org.jpos.iso.ISOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banks")
public class BankController {

   @Autowired
    BankService bankService;

    @GetMapping("/account/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        return bankService.getAccount(accountNumber);
    }


    @PostMapping("/transaction")
    public String handleTransaction(@RequestBody TransactionRequest request) throws ISOException {
        return bankService.processTransaction(request.getUserId(), request.getAmount(),request.getProcessingCode());
    }
}
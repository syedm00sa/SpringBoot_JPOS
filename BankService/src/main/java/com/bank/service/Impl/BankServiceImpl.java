package com.bank.service.Impl;

import com.bank.persistence.entity.Account;
import com.bank.persistence.repository.AccountRepository;
import com.bank.service.BankService;
import com.bank.service.JposService;
import org.jpos.iso.ISOMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankServiceImpl implements BankService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JposService jposService;

    @Override
    public Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public String processTransaction(String userId, String amount, String processingCode) {
        // Create the ISO 8583 message
        ISOMsg isoMsg = new ISOMsg();
        try {
            isoMsg.setMTI("0200"); // Message Type Indicator (e.g., Authorization Request)
            isoMsg.set(3, processingCode); // Processing Code (e.g., Purchase)
            isoMsg.set(4, amount); // Amount
            isoMsg.set(7, getCurrentDateTime()); // Date and Time
            isoMsg.set(11, generateTraceNumber()); // System Trace Audit Number
            isoMsg.set(41, "12345678"); // Card Acceptor Terminal Identification (example value)
            isoMsg.set(42, "123456789012345"); // Card Acceptor Identification Code (example value)
            isoMsg.set(43, "BANK SERVICE"); // Card Acceptor Name/Location (example value)
            isoMsg.set(102, userId); // User ID

            // Send and receive message
            return jposService.sendAndReceiveMessage(isoMsg);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing transaction.";
        }
    }

    // Helper method to get current date and time in the format required by ISO 8583
    private String getCurrentDateTime() {
        // Example format: MMddHHmmss
        return java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("MMddHHmmss"));
    }

    // Helper method to generate a trace number (unique for each transaction)
    private String generateTraceNumber() {
        // Example: Generate a unique trace number for the transaction
        return String.format("%06d", (int) (Math.random() * 1000000));
    }
}

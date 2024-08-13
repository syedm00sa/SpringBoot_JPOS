package com.bank.service;

import org.jpos.iso.ISOMsg;

public interface JposService {
    String sendAndReceiveMessage(ISOMsg isoMsg);
}

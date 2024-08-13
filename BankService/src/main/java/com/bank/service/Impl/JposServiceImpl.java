package com.bank.service.Impl;


import com.bank.service.JposService;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class JposServiceImpl implements JposService {

    @Value("${jpos.server.host}")
    private String host;

    @Value("${jpos.server.port}")
    private int port;

    @Override
    public String sendAndReceiveMessage(ISOMsg isoMsg) {
        ISOChannel isoChannel = null;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ISOMsg> future = null;
        try {
            isoChannel = new ASCIIChannel(host, port, new ISO87APackager());
            isoChannel.connect();

            isoChannel.send(isoMsg);

            System.out.println("Message sent. Waiting for response...");

            ISOChannel finalIsoChannel = isoChannel;
            future = executor.submit(() -> finalIsoChannel.receive());
            ISOMsg response = future.get(10, TimeUnit.SECONDS);
            if (response != null) {
                System.out.println("Response received: ");
                return formatISOMsg(response);
            } else {
                System.out.println("No response received.");
                return "No response received.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred.";
        } finally {
            if (isoChannel != null) {
                try {
                    isoChannel.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String formatISOMsg(ISOMsg isoMsg) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("MTI: ").append(isoMsg.getMTI()).append("\n");
            for (int i = 0; i < isoMsg.getMaxField(); i++) {
                String field = isoMsg.getString(i + 1);
                if (field != null) {
                    sb.append("Field ").append(i + 1).append(": ").append(field).append("\n");
                }
            }
        } catch (Exception e) {
            sb.append("Error formatting ISO message: ").append(e.getMessage()).append("\n");
        }
        return sb.toString();
    }
}
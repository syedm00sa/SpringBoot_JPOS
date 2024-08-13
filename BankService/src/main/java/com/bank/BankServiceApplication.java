package com.bank;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BankServiceApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(BankServiceApplication.class, args);

        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));

        ServerChannel channel = new ASCIIChannel(new ISO87APackager());
        ISOServer server = new ISOServer(8001, channel, null);
        server.setLogger(logger, "jpos-server");

        server.addISORequestListener((channel1, m) -> {
            try {
                System.out.println("Received message:");
                m.dump(System.out, "");

                ISOMsg response = (ISOMsg) m.clone();
                response.setMTI("0210");
                response.set(39, "00");
                channel1.send(response);
                System.out.println("Response sent:");
                response.dump(System.out, "");
                return true;
            } catch (ISOException | IOException e) {
                e.printStackTrace();
                return false;
            }
        });
        new Thread(server).start();
        System.out.println("Simple jPOS Server started on port 8001");
    }
}
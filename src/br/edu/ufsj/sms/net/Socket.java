/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsj.sms.net;

import br.edu.ufsj.sms.GUI.Main;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flavio
 */
public class Socket extends Thread {

    private MulticastSocket multicastSocket;
    private InetAddress address;
    private Main main;

    public Socket(Main main) throws UnknownHostException {
        this.main = main;
        this.address = InetAddress.getByName(Main.INET_ADDR);
        try {
            multicastSocket = new MulticastSocket(this.main.getPort());
            multicastSocket.setSendBufferSize(256000);
            multicastSocket.setReceiveBufferSize(256000);
            multicastSocket.setReuseAddress(true);
            multicastSocket.joinGroup(address);
        } catch (IOException ex) {
            System.out.println("There is no socket connection. Sorry.");
            System.out.println(ex.toString());
        }
    }

    public void receive() throws UnknownHostException, IOException, ClassNotFoundException {

        byte[] buf = new byte[256000];

        while (true) {
            // Receive the information and print it.
            DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
            multicastSocket.receive(msgPacket);

            ByteArrayInputStream bis = new ByteArrayInputStream(msgPacket.getData());
            ObjectInput in = new ObjectInputStream(bis);
            Message message = (Message) in.readObject();
            this.main.receiveMessage(message);
        }
    }

    public void send(Message message) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(baos);
        out.writeObject(message);
        out.flush();
        byte[] msg = baos.toByteArray();
        DatagramPacket msgPacket;
        msgPacket = new DatagramPacket(msg, msg.length, this.address, this.main.getPort());
        multicastSocket.send(msgPacket);
    }

    @Override
    public void run() {
        try {
            this.receive();
        } catch (IOException ex) {
            Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsj.sms.net;

import br.edu.ufsj.sms.GUI.MainWindow;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flavio
 */
public class Socket extends Thread {

    private MulticastSocket multicastSocket;
    private InetAddress address;
    private MainWindow main;

    public Socket(MainWindow main) throws UnknownHostException {
        this.main = main;
        this.address = InetAddress.getByName(MainWindow.INET_ADDR);
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
            Object message;
            message = in.readObject();
            if (message instanceof ScreenCastMessage) {
                this.main.receiveScreenCastMessage((ScreenCastMessage) message);
            }
            if (message instanceof ChatMessage) {
                this.main.receiveChatMessage((ChatMessage) message);
            }
        }
    }

    public void send(byte[] msg) throws IOException {
        DatagramPacket msgPacket;
        msgPacket = new DatagramPacket(msg, msg.length, this.address, this.main.getPort());
        multicastSocket.send(msgPacket);
    }

    @Override
    public void run() {
        try {
            this.receive();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

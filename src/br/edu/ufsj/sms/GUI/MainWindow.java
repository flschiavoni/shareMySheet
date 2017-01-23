/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsj.sms.GUI;

import br.edu.ufsj.sms.control.AlarmClock;
import br.edu.ufsj.sms.net.ChatMessage;
import br.edu.ufsj.sms.net.ScreenCastMessage;
import br.edu.ufsj.sms.net.Socket;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author flavio
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    private Socket socket;
    private AlarmClock alarmClock;
    private Timer timer;
    public final static String INET_ADDR = "224.0.0.3";
    public final static int TIME = 1000;

    private String name;
    private int port;

    private ChatWindow chatWindow;

    public MainWindow(String name, int port) throws UnknownHostException {
        this.name = name;
        this.port = port;
        this.initComponents();
    }

    private void initComponents() throws UnknownHostException {

        this.setSize(new java.awt.Dimension(1024, 768));
        this.setJMenuBar(new MenuBar(this));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Share My Sheet");

        jScrollPane1 = new javax.swing.JScrollPane();
        tabbedPane = new javax.swing.JTabbedPane();
        statusPanel = new javax.swing.JPanel();
        status = new javax.swing.JLabel();

        jScrollPane1.setViewportView(tabbedPane);
        statusPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        status.setText("Connected on port " + this.port + " name " + this.name);
        statusPanel.add(status);

        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        this.getContentPane().add(statusPanel, java.awt.BorderLayout.PAGE_END);

        this.pack();

        this.socket = new Socket(this);
        this.timer = new Timer();
        this.chatWindow = new ChatWindow(this);

        this.socket.start();

    }

    public void showChatWindow() {
        this.chatWindow.setVisible(true);
    }

    public void showAboutWindow() {

    }

    public void shareMySheet(boolean status) {
        if (status) {
            this.timer = new Timer();
            this.alarmClock = new AlarmClock(this.name, this.socket);
            this.timer.scheduleAtFixedRate(this.alarmClock, 0, MainWindow.TIME);
        } else {
            this.timer.cancel();
        }
    }

    public void closeCurrentTab() {
        tabbedPane.remove(tabbedPane.getSelectedIndex());
    }

    public void exit() {
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }

    public void sendChatMessage(String text) {
        ChatMessage message = new ChatMessage(this.name, text);
        try {
            this.socket.send(message.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveScreenCastMessage(ScreenCastMessage message) {
        // Verificar se a aba existe, se existir, atualizo.
        ByteArrayInputStream bais = new ByteArrayInputStream(message.getImageByte());
        BufferedImage image;
        try {
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                String title = tabbedPane.getTitleAt(i);
                if (title.equals(message.getName())) {
                    image = ImageIO.read(bais);
                    JLabel myShoot = new JLabel(new ImageIcon(image));
                    tabbedPane.setComponentAt(i, myShoot);
                    return;
                }
            }
            image = ImageIO.read(bais);
            JLabel myShoot = new JLabel(new ImageIcon(image));
            tabbedPane.addTab(message.getName(), myShoot);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveChatMessage(ChatMessage message) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyyy hh:mm:ss");
        Date date = new Date();
        chatWindow.receiveChatMessage(dt.format(date) + " "
                + "<b>" + message.getName() + "</b>: "
                + message.getText() + "<br>");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel status;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

}

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

    public MainWindow(String name, int port) throws UnknownHostException {
        initComponents();
        this.name = name;
        this.port = port;
        this.socket = new Socket(this);
        this.socket.start();
        this.timer = new Timer();
        jLabel1.setText("Connected on port " + this.port + " name " + this.name);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        shareButton = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        chatBoard = new javax.swing.JEditorPane();
        jPanel3 = new javax.swing.JPanel();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Share My Sheet");

        jToolBar1.setRollover(true);

        shareButton.setText("Share My Sheet!");
        shareButton.setFocusable(false);
        shareButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        shareButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        shareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shareButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(shareButton);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        chatBoard.setContentType("text/html"); // NOI18N
        jScrollPane3.setViewportView(chatBoard);

        jSplitPane1.setLeftComponent(jScrollPane3);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jPanel3.add(jTextArea2);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jSplitPane1.setBottomComponent(jPanel3);

        jPanel2.add(jSplitPane1);

        jTabbedPane1.addTab("Chat", jPanel2);

        jScrollPane1.setViewportView(jTabbedPane1);

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void shareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shareButtonActionPerformed
        if (shareButton.isSelected()) {
            this.timer = new Timer();
            this.alarmClock = new AlarmClock(this.name, this.socket);
            this.timer.scheduleAtFixedRate(this.alarmClock, 0, MainWindow.TIME);
            shareButton.setText("Stop sharing it!");
        } else {
            this.timer.cancel();
            shareButton.setText("Share My Sheet!");
        }
    }//GEN-LAST:event_shareButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextArea2.getText().trim().length() == 0) {
            return;
        }
        ChatMessage message = new ChatMessage();
        message.setText(jTextArea2.getText());
        message.setName(this.name);
        try {
            this.socket.send(message.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextArea2.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    public void receiveScreenCastMessage(ScreenCastMessage message) {
        // Verificar se a aba existe, se existir, atualizo.
        ByteArrayInputStream bais = new ByteArrayInputStream(message.getImageByte());
        BufferedImage image;
        try {
            for (int i = 0; i < jTabbedPane1.getTabCount(); i++) {
                String name = jTabbedPane1.getTitleAt(i);
                if (name.equals(message.getName())) {
                    image = ImageIO.read(bais);
                    JLabel myShoot = new JLabel(new ImageIcon(image));
                    jTabbedPane1.setComponentAt(i, myShoot);
                    return;
                }
            }

            image = ImageIO.read(bais);
            JLabel myShoot = new JLabel(new ImageIcon(image));
            jTabbedPane1.addTab(message.getName(), myShoot);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveChatMessage(ChatMessage message) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyyy hh:mm:ss");
        Date date = new Date();
        chatBoard.setText(dt.format(date) + " | " + 
                "<b>" + message.getName() + "</b>: "+ 
                message.getText() + "<br>" + 
                chatBoard.getText()
        );
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane chatBoard;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToggleButton shareButton;
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
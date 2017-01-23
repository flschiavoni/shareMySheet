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
        initComponents();
        this.name = name;
        this.port = port;
        this.socket = new Socket(this);
        this.socket.start();
        this.timer = new Timer();
        jLabel1.setText("Connected on port " + this.port + " name " + this.name);
        this.chatWindow = new ChatWindow(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu4 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        closeTabMenu = new javax.swing.JMenuItem();
        quitMenu = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        shareMySheetMenu = new javax.swing.JCheckBoxMenuItem();
        viewMenu = new javax.swing.JMenu();
        showChatWindowMenu = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenu = new javax.swing.JMenuItem();

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Share My Sheet");

        jScrollPane1.setViewportView(jTabbedPane1);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);

        fileMenu.setText("File");
        fileMenu.setToolTipText("");

        closeTabMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        closeTabMenu.setText("Close Current Tab");
        closeTabMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeTabMenuActionPerformed(evt);
            }
        });
        fileMenu.add(closeTabMenu);

        quitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        quitMenu.setText("Quit");
        quitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuActionPerformed(evt);
            }
        });
        fileMenu.add(quitMenu);

        jMenuBar1.add(fileMenu);

        editMenu.setText("Edit");

        shareMySheetMenu.setText("Share My Sheet!");
        shareMySheetMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shareMySheetMenuActionPerformed(evt);
            }
        });
        editMenu.add(shareMySheetMenu);

        jMenuBar1.add(editMenu);

        viewMenu.setText("View");

        showChatWindowMenu.setText("Chat Window");
        showChatWindowMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showChatWindowMenuActionPerformed(evt);
            }
        });
        viewMenu.add(showChatWindowMenu);

        jMenuBar1.add(viewMenu);

        helpMenu.setText("Help");

        aboutMenu.setText("About");
        aboutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenu);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showChatWindowMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showChatWindowMenuActionPerformed
        this.chatWindow.setVisible(true);
    }//GEN-LAST:event_showChatWindowMenuActionPerformed

    private void aboutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutMenuActionPerformed

    private void shareMySheetMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shareMySheetMenuActionPerformed
        if (shareMySheetMenu.isSelected()) {
            this.timer = new Timer();
            this.alarmClock = new AlarmClock(this.name, this.socket);
            this.timer.scheduleAtFixedRate(this.alarmClock, 0, MainWindow.TIME);
            shareMySheetMenu.setText("Stop sharing it!");
        } else {
            this.timer.cancel();
            shareMySheetMenu.setText("Share My Sheet!");
        }
    }//GEN-LAST:event_shareMySheetMenuActionPerformed

    private void closeTabMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeTabMenuActionPerformed
        jTabbedPane1.remove(jTabbedPane1.getSelectedIndex());
    }//GEN-LAST:event_closeTabMenuActionPerformed

    private void quitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuActionPerformed
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_quitMenuActionPerformed

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
        chatWindow.receiveChatMessage(dt.format(date) + " | "
                + "<b>" + message.getName() + "</b>: "
                + message.getText() + "<br>");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenu;
    private javax.swing.JMenuItem closeTabMenu;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem quitMenu;
    private javax.swing.JCheckBoxMenuItem shareMySheetMenu;
    private javax.swing.JMenuItem showChatWindowMenu;
    private javax.swing.JMenu viewMenu;
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

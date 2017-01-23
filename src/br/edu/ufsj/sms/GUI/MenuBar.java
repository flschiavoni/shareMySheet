/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsj.sms.GUI;

import javax.swing.JMenuBar;

/**
 *
 * @author flavio
 */
public class MenuBar extends JMenuBar {

    private javax.swing.JMenuItem aboutMenu;
    private javax.swing.JMenuItem closeTabMenu;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;

    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JCheckBoxMenuItem shareMySheetMenu;
    private javax.swing.JMenuItem showChatWindowMenu;
    private javax.swing.JMenu viewMenu;

    private MainWindow mainWindow;

    public MenuBar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        fileMenu = new javax.swing.JMenu();
        closeTabMenu = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        shareMySheetMenu = new javax.swing.JCheckBoxMenuItem();
        viewMenu = new javax.swing.JMenu();
        showChatWindowMenu = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenu = new javax.swing.JMenuItem();

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

        exitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenu);

        this.add(fileMenu);

        editMenu.setText("Edit");

        shareMySheetMenu.setText("Share My Sheet!");
        shareMySheetMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shareMySheetMenuActionPerformed(evt);
            }
        });
        editMenu.add(shareMySheetMenu);

        this.add(editMenu);

        viewMenu.setText("View");

        showChatWindowMenu.setText("Chat Window");
        showChatWindowMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showChatWindowMenuActionPerformed(evt);
            }
        });
        viewMenu.add(showChatWindowMenu);

        this.add(viewMenu);

        helpMenu.setText("Help");

        aboutMenu.setText("About");
        aboutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenu);

        this.add(helpMenu);

    }

    private void showChatWindowMenuActionPerformed(java.awt.event.ActionEvent evt) {
        mainWindow.showChatWindow();
    }

    private void aboutMenuActionPerformed(java.awt.event.ActionEvent evt) {
        mainWindow.showAboutWindow();
    }

    private void shareMySheetMenuActionPerformed(java.awt.event.ActionEvent evt) {
        if (shareMySheetMenu.isSelected()) {
            shareMySheetMenu.setText("Stop sharing it!");
        } else {
            shareMySheetMenu.setText("Share My Sheer!");
        }

        mainWindow.shareMySheet(shareMySheetMenu.isSelected());
    }

    private void closeTabMenuActionPerformed(java.awt.event.ActionEvent evt) {
        mainWindow.closeCurrentTab();
    }

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {
        mainWindow.exit();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsj.sms.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author flavio
 */
public final class Login extends JDialog {

    JLabel nameLabel = new JLabel("Name: ");
    JLabel portLabel = new JLabel("Port: ");

    JTextField nameField = new JTextField();
    JSpinner portField = new JSpinner();

    JButton okButton = new JButton("OK");
    JButton cancelButton = new JButton("Cancel");

    public Login() {
        setupUI();
        setUpListeners();
        this.setSize(400, 150);
        this.setModal(true);
    }

    public void setupUI() {

        this.setTitle("Login");

        portField.setModel(new javax.swing.SpinnerNumberModel(8000, 8000, 9000, 1));
        nameField.setText(System.getProperty("user.name"));

        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 4, 4, 4);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        topPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        topPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        topPanel.add(portLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        topPanel.add(portField, gbc);

        this.add(topPanel);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setUpListeners() {
        okButton.addActionListener((ActionEvent e) -> {
            Login.this.setVisible(false);
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    @Override
    public String getName() {
        return nameField.getText();
    }

    public int getPort() {
        return ((Integer) portField.getValue());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsj.sms.net;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author flavio
 */
public class Message implements Serializable {

    private String name;
    private byte[] imageByte;

    public Message(String name, byte[] imageByte) {
        this.name = name;
        this.imageByte = imageByte;
    }

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
     * @return the imageByte
     */
    public byte[] getImageByte() {
        return imageByte;
    }

    /**
     * @param imageByte the image to set
     */
    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

}

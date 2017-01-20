/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsj.sms.control;

import br.edu.ufsj.sms.GUI.ScreenShot;
import br.edu.ufsj.sms.net.Message;
import br.edu.ufsj.sms.net.Socket;
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 *
 * @author flavio
 */
public class AlarmClock extends TimerTask {

    ScreenShot ss;
    String name;
    Socket socket;

    public AlarmClock(String name, Socket socket) {
        ss = new ScreenShot();
        this.name = name;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedImage image = ss.takeAShot();
            BufferedImage rescaled = ImageTransformation.resize(image, 1024, 768);
            byte[] data = ImageTransformation.compress(rescaled, 0.45f);
            Message message = new Message(this.name, data);
            this.socket.send(message);
        } catch (AWTException ex) {
            Logger.getLogger(AlarmClock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlarmClock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

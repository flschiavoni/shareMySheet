/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsj.sms.control;

import br.edu.ufsj.sms.GUI.ScreenShot;
import br.edu.ufsj.sms.net.ScreenCastMessage;
import br.edu.ufsj.sms.net.Socket;
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flavio
 */
public class AlarmClock extends TimerTask {

    ScreenShot ss;
    String name;
    Socket socket;
    float compressRatio;
    
    public AlarmClock(String name, Socket socket) {
        ss = new ScreenShot();
        this.name = name;
        this.socket = socket;
        this.compressRatio = 0.5f;
    }

    @Override
    public void run() {
        byte[] msg = null;
        try {
            BufferedImage image = ss.takeAShot();
            BufferedImage rescaled = ImageTransformation.resize(image, 1024, 768);
            byte[] byteImage = ImageTransformation.compress(rescaled, compressRatio);
            ScreenCastMessage message = new ScreenCastMessage(this.name, byteImage);
            msg = message.toByteArray();
        } catch (AWTException | IOException ex) {
            Logger.getLogger(AlarmClock.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (msg != null) {
            try {
                this.socket.send(msg);
            } catch (IOException ex) {
                Logger.getLogger(AlarmClock.class.getName()).log(Level.SEVERE, null, ex);
                this.compressRatio -= 0.05f;
            }
        }
    }
}

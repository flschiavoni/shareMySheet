/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsj.sms.control;

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

    private final ScreenShot ss;
    private final String name;
    private final Socket socket;
    private float compressRatio;
    
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

            int size = ImageTransformation.toByteArray(rescaled).length / 65000;
            size++;
            int number_of_cuts = rescaled.getWidth() / size;
            
            byte[] byteImage = ImageTransformation.compress(rescaled, compressRatio);
            for (int i = 0 ; i < size ; i++){
                
            }
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

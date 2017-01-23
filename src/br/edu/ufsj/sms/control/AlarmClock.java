/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsj.sms.control;

import br.edu.ufsj.sms.GUI.MainWindow;
import java.util.TimerTask;

/**
 *
 * @author flavio
 */
public class AlarmClock extends TimerTask {

    private final MainWindow mainWindow;
    
    public AlarmClock(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void run() {
        this.mainWindow.sendScreenCastMessage();
    }
}

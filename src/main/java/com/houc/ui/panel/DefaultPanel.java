package com.houc.ui.panel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class DefaultPanel extends JPanel {
    static int lineWidth = 180;
    static int lineHeight = 25;
    static Color color = new Color(205, 85,  85);
    static Border border = BorderFactory.createLineBorder(color);

    boolean isInit = false;

    public void rendering(){
        setBackground(Color.WHITE);
        if (!isInit) {
            init();
        }
    }

    protected abstract void init();

}

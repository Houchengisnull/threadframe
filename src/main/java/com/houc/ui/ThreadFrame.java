package com.houc.ui;

import com.houc.ui.panel.ButtonPanel;
import com.houc.ui.panel.RunnerPanel;
import com.houc.ui.panel.WaitPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class ThreadFrame extends JFrame {

    int width = 600;
    int height = 300;

    @Autowired
    ButtonPanel buttonPanel;

    @Autowired
    WaitPanel waitPanel;

    @Autowired
    RunnerPanel runnerPanel;

    public void rendering() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int x = (screenWidth - width)/2;
        int y = (screenHeight - height)/2;
        setBounds(x, y, width, height);
        setVisible(true);

        add(buttonPanel, BorderLayout.WEST);
        buttonPanel.rendering();
        buttonPanel.updateUI();

        add(waitPanel, BorderLayout.EAST);
        waitPanel.rendering();

        add(runnerPanel, BorderLayout.CENTER);
        runnerPanel.rendering();
    }

}

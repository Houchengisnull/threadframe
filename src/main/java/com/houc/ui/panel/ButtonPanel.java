package com.houc.ui.panel;

import com.houc.ui.adapter.GetReadLockAdapter;
import com.houc.ui.adapter.GetWriteLockAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class ButtonPanel extends DefaultPanel {

    @Autowired
    GetWriteLockAdapter getWriteLockAdapter;

    @Autowired
    GetReadLockAdapter getReadLockAdapter;

    public void rendering() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, getParent().getHeight()));
        JLabel writeButton = new JLabel("write", JLabel.CENTER);
        writeButton.setBackground(color);
        writeButton.setOpaque(true);
        writeButton.setPreferredSize(new Dimension(lineWidth, lineHeight));
        writeButton.addMouseListener(getWriteLockAdapter);
        add(writeButton);

        JLabel readButton = new JLabel("read", JLabel.CENTER);
        readButton.setOpaque(true);
        readButton.setBackground(color);
        readButton.setPreferredSize(new Dimension(lineWidth, lineHeight));
        readButton.addMouseListener(getReadLockAdapter);
        add(readButton);

    }

}

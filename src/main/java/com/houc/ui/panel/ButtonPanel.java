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

    @Override
    public void init() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        setPreferredSize(new Dimension(200, getParent().getHeight()));

        JLabel writeButton = new JLabel("write", JLabel.CENTER);
        writeButton.setBackground(color);
        writeButton.setForeground(Color.WHITE);
        writeButton.setOpaque(true);
        writeButton.setPreferredSize(new Dimension(lineWidth, lineHeight));
        writeButton.addMouseListener(getWriteLockAdapter);
        add(writeButton);

        JLabel readButton = new JLabel("read", JLabel.CENTER);
        readButton.setOpaque(true);
        readButton.setForeground(Color.WHITE);
        readButton.setBackground(color);
        readButton.setPreferredSize(new Dimension(lineWidth, lineHeight));
        readButton.addMouseListener(getReadLockAdapter);
        add(readButton);
    }
}

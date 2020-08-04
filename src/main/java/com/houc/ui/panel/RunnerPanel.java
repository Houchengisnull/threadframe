package com.houc.ui.panel;

import com.houc.service.ReadWriteLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
public class RunnerPanel extends DefaultPanel {

    @Autowired
    ReadWriteLockService service;

    @Override
    public void rendering() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        setBackground(color);
        /*setBorder(boreder);*/
        setPreferredSize(new Dimension(200, getParent().getHeight()));
        refreshRunnerPanel();
    }

    @Scheduled(fixedRate = 500)
    public void refreshRunnerPanel() {
        removeAll();
        Thread writer = service.getOwner();
        List<Thread> readers = service.getSharers();
        if (writer != null) {
            /*System.out.println("owner[" + owner.getId() + "]");*/
            addLabel(writer, true);
        } else {
            for (Thread reader : readers) {
                addLabel(reader, false);
            }
        }
        updateUI();
    }

    protected void addLabel(Thread thread, boolean isWrite) {
        JLabel label = new JLabel( (isWrite ? "writer":"reader")+ "[" + thread.getId() + "]", JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(lineWidth, lineHeight));
        add(label);
    }

}

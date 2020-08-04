package com.houc.ui.panel;

import com.houc.service.ReadWriteLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.util.Collection;

@Component
public class WaitPanel extends DefaultPanel {

    @Autowired
    ReadWriteLockService service;

    public void rendering() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, getParent().getHeight()));
        refreshWaitPanel();
    }

    @Scheduled(fixedRate = 1000)
    public void refreshWaitPanel() {
        removeAll();
        Collection<Thread> writers = service.getQueuedWriterThreads();
        Collection<Thread> readers = service.getQueuedReaderThreads();
        System.out.println("等待队列数量: " + (writers.size() + readers.size()));
        for (Thread writer : writers) {
            addLabel(writer, true);
        }
        for (Thread reader : readers) {
            addLabel(reader, false);
        }
        updateUI();
    }

    protected void addLabel(Thread thread, boolean isWrite) {
        JLabel label = new JLabel((isWrite ? "writer" : "reader")+ "[" + thread.getId() + "]", JLabel.CENTER);
        label.setPreferredSize(new Dimension(lineWidth, lineHeight));
        add(label);
    }

}

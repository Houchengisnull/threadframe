package com.houc.ui.adapter;

import com.houc.common.GetWriteLockRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class GetWriteLockAdapter extends MouseAdapter {

    @Autowired
    GetWriteLockRunnable target;

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Thread thread = new Thread(target);
        thread.setName("writer[" + thread.getId() + "]");
        thread.start();
    }
}

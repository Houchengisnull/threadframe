package com.houc.ui.adapter;

import com.houc.Application;
import com.houc.service.ReadWriteLockService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReleaseLockAdapter extends MouseAdapter {

    ReadWriteLockService service = Application.context.getBean(ReadWriteLockService.class);

    Thread thread;

    public ReleaseLockAdapter(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.out.println("pre unlock " + thread.getName());
        service.preUnlock(thread);
    }
}

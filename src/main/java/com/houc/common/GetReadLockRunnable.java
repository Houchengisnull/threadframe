package com.houc.common;

import com.houc.service.ReadWriteLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetReadLockRunnable implements Runnable{

    @Autowired
    ReadWriteLockService service;

    @Override
    public void run() {
        service.read();
    }
}

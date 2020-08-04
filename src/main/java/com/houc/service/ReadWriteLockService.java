package com.houc.service;

import com.houc.utils.Reflections;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class ReadWriteLockService {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    List<Thread> sharers = new ArrayList<>();

    /*public Collection<Thread> getQueuedThreads(){
        return Reflections.invokeUnaccessible(ReentrantReadWriteLock.class, "getQueuedThreads", lock, Collection.class);
    }*/

    public Collection<Thread> getQueuedWriterThreads() {
        return Reflections.invokeUnaccessible(ReentrantReadWriteLock.class, "getQueuedWriterThreads", lock, Collection.class);
    }

    public Collection<Thread> getQueuedReaderThreads() {
        return Reflections.invokeUnaccessible(ReentrantReadWriteLock.class, "getQueuedReaderThreads", lock, Collection.class);
    }

    /**
     * getOwner只能获得排他锁的线程
     * @return
     */
    public Thread getOwner() {
        return Reflections.invokeUnaccessible(ReentrantReadWriteLock.class, "getOwner", lock, Thread.class);
    }

    public List<Thread> getSharers() {
        return sharers;
    }

    public void write() {
        writeLock.lock();
        try {
            long id = Thread.currentThread().getId();
            System.out.println("Thread[" + id + "] is writing");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Thread[" + id + "] is write finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }

    }

    public void read() {
        readLock.lock();
        try {
            sharers.add(Thread.currentThread());
            long id = Thread.currentThread().getId();
            System.out.println("Thread[" + id + "] is reading");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Thread[" + id + "] is reading finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sharers.remove(Thread.currentThread());
            readLock.unlock();
        }
    }
}

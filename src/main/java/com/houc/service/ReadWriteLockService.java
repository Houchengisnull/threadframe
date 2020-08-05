package com.houc.service;

import com.houc.utils.Reflections;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class ReadWriteLockService {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    /*Condition writeCondition = writeLock.newCondition();
    Condition readCondition = readLock.newCondition();*/

    List<Thread> sharers = new ArrayList<>();

    /**
     * 持有锁或阻塞中的线程
     */
    List<Thread> runners = new ArrayList();
    /*public Collection<Thread> getQueuedThreads(){
        return Reflections.invokeUnaccessible(ReentrantReadWriteLock.class, "getQueuedThreads", lock, Collection.class);
    }*/

    public Lock getWriteLock() {
        return writeLock;
    }

    public Lock getReadLock() {
        return readLock;
    }

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
        runners.add(Thread.currentThread());
        try {
            // ReleaseLockAdapter通过调用preUnlock方法移除thread
            while (runners.contains(Thread.currentThread())) {
                System.out.println(Thread.currentThread().getName() + " is writing");
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public void read() {
        readLock.lock();
        runners.add(Thread.currentThread());
        try {
            sharers.add(Thread.currentThread());
            while (runners.contains(Thread.currentThread())) {
                System.out.println(Thread.currentThread().getName() + " is reading");
                TimeUnit.MICROSECONDS.sleep(200);
            }
            /*System.out.println("Thread[" + id + "] is reading finish");*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sharers.remove(Thread.currentThread());
            readLock.unlock();
        }
    }

    public boolean preUnlock(Thread thread) {
        return runners.remove(thread);
    }
}

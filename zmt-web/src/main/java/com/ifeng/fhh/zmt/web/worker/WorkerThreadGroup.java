package com.ifeng.fhh.zmt.web.worker;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-31
 */
public class WorkerThreadGroup {

    private final WorkerThread[] works;

    private AtomicInteger cursor = new AtomicInteger(0);

    private WorkerThreadMonitor threadMonitor = new WorkerThreadMonitor(5000);

    private static final int THREADS_DEFAULT = 10;

    WorkerThreadGroup(){
        this(THREADS_DEFAULT);
    }

    WorkerThreadGroup(int nThreads) {
        this.works = new WorkerThread[nThreads];
    }

    public WorkerThread next() {
        int index = cursor.incrementAndGet() % works.length;
        WorkerThread worker = works[index];
        if(Objects.isNull(worker)){
            synchronized (WorkerThreadGroup.class) {
                if(Objects.isNull(worker)){
                    worker = new WorkerThread();
                    threadMonitor.registerThread(worker);
                    worker.start();
                    works[index] = worker;
                    worker.setName("workerThread-"+index);
                }
            }
        }
        return worker;
    }
}

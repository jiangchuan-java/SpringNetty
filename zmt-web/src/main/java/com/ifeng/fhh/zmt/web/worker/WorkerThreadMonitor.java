package com.ifeng.fhh.zmt.web.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-9
 */
public class WorkerThreadMonitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerThreadMonitor.class);

    private final Timer timer;
    private final List<WorkerThread> workerList;

    WorkerThreadMonitor(long interval) {
        this.workerList = new LinkedList<>();
        this.timer = new Timer("spring-netty-worker-monitor", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (WorkerThread workerThread : workerList) {
                    LOGGER.info("totalCount:{}, currentWorker:{} task queue size : {}", workerList.size(), workerThread.getName(), workerThread.queueSize());
                }
            }
        }, interval, interval);
    }

    public synchronized void registerThread(WorkerThread workerThread) {
        workerList.add(workerThread);
    }
}

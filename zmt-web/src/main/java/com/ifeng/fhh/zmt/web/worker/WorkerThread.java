package com.ifeng.fhh.zmt.web.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-31
 */
public class WorkerThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerThread.class);

    private LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

    private WorkingContext workingContext;

    WorkerThread(){

    }

    @Override
    public void run() {
        // TODO: 20-8-31 循环处理taskQueue中的任务
        while (true) {
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTask(Runnable task) {
        try {
            this.taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int queueSize(){
        return taskQueue.size();
    }

    public WorkingContext getWorkingContext() {
        return workingContext;
    }

    public void setWorkingcontext(WorkingContext workingContext) {
        this.workingContext = workingContext;
    }
}

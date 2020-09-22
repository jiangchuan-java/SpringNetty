package com.ifeng.fhh.zmt.web.worker;

/**
 * @Des: 阿拉丁
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-11
 */
public class Aladdin {

    private final WorkerThreadGroup workerGroup;

    public Aladdin(int normalWorkerThreads) {
        this.workerGroup = new WorkerThreadGroup(normalWorkerThreads);
    }

    public WorkingContext getOrCreateContext(){
        WorkingContext context = getContext();
        if(context == null){
            context = new WorkingContext(workerGroup);
        }
        return context;
    }

    private WorkingContext getContext() {
        Thread currentThread = Thread.currentThread();
        if(currentThread instanceof WorkerThread){
            return ((WorkerThread) currentThread).getWorkingContext();
        } else {
            return null;
        }
    }
}

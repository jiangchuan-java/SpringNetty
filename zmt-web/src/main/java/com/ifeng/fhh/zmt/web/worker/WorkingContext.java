package com.ifeng.fhh.zmt.web.worker;

/**
 * @Des: 一次请求的上下文
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-31
 */
public final class WorkingContext {

    private WorkerThreadGroup workerGroup;


    WorkingContext(WorkerThreadGroup group) {
        this.workerGroup = group;
    }

    public void runOnContext(Runnable task){
        this.workerGroup.next().addTask(wrapTask(task, this));
    }


    private Runnable wrapTask(Runnable task, WorkingContext workingContext) {
        return new Runnable() {
            @Override
            public void run() {
                WorkerThread worker = (WorkerThread) Thread.currentThread();
                worker.setWorkingcontext(workingContext);
                task.run();
            }
        };
    }
}

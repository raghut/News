package com.rgu.news.utils.rx;


import io.reactivex.Scheduler;

public class RxSchedulersTest extends RxSchedulersAbstractBase {

    private Scheduler testScheduler;

    // private constructor
    private RxSchedulersTest() {
    }

    public RxSchedulersTest(Scheduler testScheduler) {
        this.testScheduler = testScheduler;
    }

    @Override
    public Scheduler getMainThreadScheduler() {
        return testScheduler;
    }

    @Override
    public Scheduler getIOScheduler() {
        return testScheduler;
    }

    @Override
    public Scheduler getComputationScheduler() {
        return testScheduler;
    }

}

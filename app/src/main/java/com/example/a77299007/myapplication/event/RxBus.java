package com.example.a77299007.myapplication.event;


import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by Administrator on 2017/3/15.
 * <p>
 * 发送信息 使用方法  RxBus.getInstance().send(Events.TAP,obj);
 * <p>
 * 订阅者   使用方法   RxBus.with(Events.TAP).onNext(onNext).onError().startForResult();
 * 或者    RxBus.with().setEventCode(Events.TAP).onNext(onNext).startForResult();
 * <p>
 * 通过Event.TAP来筛选数据
 */

public class RxBus {

    private static RxBus mRxBus;
    private final FlowableProcessor<Events<?>> mSubject;

    private RxBus() {
        mSubject = PublishProcessor.create();
    }

    public static RxBus getInstance() {
        if (mRxBus == null) {
            synchronized (RxBus.class) {
                if (mRxBus == null) {
                    mRxBus = new RxBus();
                }
            }
        }
        return mRxBus;
    }

    private void send(Events<?> o) {
        mSubject.onNext(o);
    }

    private Flowable<Events<?>> toObservable() {
        return mSubject;
    }

    public void send(@Events.EventCode int code, Object content) {
        Events<?> event = new Events<>(code, content);
        send(event);
    }

    /**
     * 给订阅者使用
     *
     * @return
     */
    public static DisposableBuilder with() {
        return new DisposableBuilder();
    }

    /**
     * 给订阅者使用
     * <p>
     * 过滤器
     *
     * @param code 接受数据的code
     * @return
     */
    public static DisposableBuilder with(@Events.EventCode int code) {
        return new DisposableBuilder().setEventCode(code);
    }

    public static class DisposableBuilder {

        @Events.EventCode
        private int eventCode;
        private Consumer<Object> onNext;
        private Consumer<Throwable> onError;

        public DisposableBuilder setEventCode(int eventCode) {
            this.eventCode = eventCode;
            return this;
        }

        public DisposableBuilder onNext(Consumer<Object> onNext) {
            this.onNext = onNext;
            return this;
        }

        public DisposableBuilder onError(Consumer<Throwable> onError) {
            this.onError = onError;
            return this;
        }

        /**
         * 当前线程处理
         *
         * @return
         */
        public Disposable start() {
            return checkEvent()
                    .subscribe(onNext, onError == null ? Throwable::printStackTrace : onError);
        }

        /**
         * 主线程处理
         *
         * @return
         */
        public Disposable startOnMainThread() {
            return checkEvent()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onNext, onError == null ? Throwable::printStackTrace : onError);
        }

        private Flowable<?> checkEvent() {
            return RxBus.getInstance().toObservable()
                    .onBackpressureBuffer()
                    .filter(v -> v.code == eventCode)
                    .map(events -> events.content);
        }

    }
}

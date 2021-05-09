package com.zjh.demo.design;

/**
 * @author zhangjiahao
 * @create 2021/03/29 22:59
 */
public class BuilderDesign {

    private int maxThreadNum;

    private int coreThreadNum;

    private int initThreadNum;

    private BuilderDesign(ThreadPoolBuilder builder) {
        this.maxThreadNum = builder.maxThreadNum;
        this.coreThreadNum = builder.coreThreadNum;
        this.initThreadNum = builder.coreThreadNum;
    }

    public class ThreadPoolBuilder {
        private int maxThreadNum;

        private int coreThreadNum;

        private int initThreadNum;

        private ThreadPoolBuilder() {

        }

        public BuilderDesign build() {
            // 逻辑判断
            return new BuilderDesign(this);
        }

        public ThreadPoolBuilder setMaxThreadNum(int maxThreadNum) {
            this.maxThreadNum = maxThreadNum;
            return this;
        }

        public ThreadPoolBuilder setCoreThreadNum(int coreThreadNum) {
            this.coreThreadNum = coreThreadNum;
            return this;
        }

        public ThreadPoolBuilder setInitThreadNum(int initThreadNum) {
            this.initThreadNum = initThreadNum;
            return this;
        }
    }

}

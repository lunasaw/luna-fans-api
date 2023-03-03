package io.github.lunasaw.listener;

import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiMethodListener implements TestExecutionListener, Ordered {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        TestExecutionListener.super.beforeTestClass(testContext);
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        TestExecutionListener.super.prepareTestInstance(testContext);
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        log.info("beforeTestMethod::testContext = {}", testContext);
    }

    @Override
    public void beforeTestExecution(TestContext testContext) throws Exception {
        TestExecutionListener.super.beforeTestExecution(testContext);
    }

    @Override
    public void afterTestExecution(TestContext testContext) throws Exception {
        TestExecutionListener.super.afterTestExecution(testContext);
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        log.info("afterTestMethod::testContext = {}", testContext);
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        TestExecutionListener.super.afterTestClass(testContext);
    }

    /**
     * 监听顺序
     * 
     * @return
     */
    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}

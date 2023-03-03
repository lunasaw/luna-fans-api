package io.github.lunasaw.message.email;

import io.github.lunasaw.BaseTest;
import io.github.lunasaw.listener.ApiMethodListener;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author chenzhangyue
 * 2023/3/3
 */
public class EmailBaseTest extends BaseTest {

    @Test
    public void atest() {
        System.out.println("hello world");
    }

}

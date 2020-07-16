package com.luna.file.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.luna.file.FileApplicationTest;
import com.luna.file.config.FtpConfigValue;

/**
 * @Package: com.luna.file.tests
 * @ClassName: FtpTest
 * @Author: luna
 * @CreateTime: 2020/7/16 15:48
 * @Description:
 */
public class FtpTest extends FileApplicationTest {

    @Autowired
    private FtpConfigValue ftpConfigValue;

    @Test
    public void atest() {
        System.out.println(ftpConfigValue.local);
    }
}

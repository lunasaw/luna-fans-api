package com.luna.commons;

import com.luna.commons.config.JavaCvConfigValue;
import com.luna.commons.javacv.CheckFace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommonApplicationTests {

    @Autowired
    JavaCvConfigValue javaCvConfigValue;

    @Test
    public void aTest() {
        try {
            CheckFace.chackFaceAndShow("C:\\Users\\improve\\Pictures\\Saved Pictures\\friends.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

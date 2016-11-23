package com.aeon;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GbfResourceApiApplicationTests {

    @Test
    public void contextLoads() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(1479742430);
        System.out.println(":: " + cal.getTime());
        //Sun Jan 18 11:02:22 CST 1970
        //Sun Jan 18 11:01:46 CST 1970
    }

}

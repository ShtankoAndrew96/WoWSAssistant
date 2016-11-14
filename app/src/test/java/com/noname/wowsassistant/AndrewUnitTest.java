package com.noname.wowsassistant;

import org.testng.annotations.Test;

import static com.noname.wowsassistant.MainActivity.getParseParams;
import static org.junit.Assert.assertEquals;

public class AndrewUnitTest {
    @Test
    public void CheckParsing(){
        String params="&status=ok&access_token=cxjhvs322nkxcj2ncxvj3&nickname=ggg&account_id=000";
        String [] parsed=getParseParams(params);
        assertEquals("ok",parsed[0]);
        assertEquals("cxjhvs322nkxcj2ncxvj3",parsed[1]);
        assertEquals("ggg",parsed[2]);
        assertEquals("000",parsed[3]);
    }
}
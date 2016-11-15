package com.noname.wowsassistant;

import android.content.Intent;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * Created by Andrew
 */
public class MainActivityTest {

    @Test
    public void onCreate() throws Exception {
        Intent intent = mock(Intent.class);
        when(intent.getStringExtra("params")).thenReturn("&status=ok&access_token=cxjhvs322nkxcj2ncxvj3&nickname=ggg&account_id=000");
        String[] par = MainActivity.getParseParams(intent);
        assertEquals(par[0],"ok");
        assertEquals(par[1],"cxjhvs322nkxcj2ncxvj3");
        assertEquals(par[2],"ggg");
        assertEquals(par[3],"000");
    }

}
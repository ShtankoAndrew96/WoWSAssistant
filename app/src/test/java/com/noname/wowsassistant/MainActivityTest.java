package com.noname.wowsassistant;

        import android.content.Intent;

        import com.noname.wowslibrary.Api;
        import com.noname.wowslibrary.Ship;
        import com.noname.wowslibrary.StatsContainer;

        import org.junit.Test;

        import static com.noname.wowsassistant.helper.FileHelper.checkForValidImageName;
        import static com.noname.wowslibrary.Api.getShipsUrl;
        import static com.noname.wowslibrary.Ship.getFullName;
        import static com.noname.wowslibrary.StatsContainer.getString;
        import static junit.framework.Assert.assertFalse;
        import static org.junit.Assert.assertEquals;
        import static org.mockito.Mockito.mock;
        import static org.mockito.Mockito.when;
/**
 * Created by Andrew
 */
public class MainActivityTest {
    //andrew
    @Test
    public void parseCheck() throws Exception {
        Intent intent = mock(Intent.class);
        when(intent.getStringExtra("params")).thenReturn("&status=ok&access_token=cxjhvs322nkxcj2ncxvj3&nickname=ggg&account_id=000");
        String[] par = MainActivity.getParseParams(intent);
        assertEquals(par[0],"ok");
        assertEquals(par[1],"cxjhvs322nkxcj2ncxvj3");
        assertEquals(par[2],"ggg");
        assertEquals(par[3],"000");
    }
    //dima
    @Test
    public void validFileName(){
        assertEquals(true,checkForValidImageName("ggg.gif"));
    }
    @Test
    public void TestShipData() throws Exception {
        Ship ship = mock(Ship.class);
        when(ship.getName()).thenReturn("Yamato");
        when(ship.getType()).thenReturn("Linkor");
        assertEquals(getFullName(ship),"Yamato (Linkor)");
    }
    //maxim
    @Test
    public void validFileNameFalse(){
        assertFalse(checkForValidImageName("ggg.gdf"));
    }



    @Test
    public void validStateEquals(){
        StatsContainer sc = mock(StatsContainer.class);
        when(sc.getName()).thenReturn("maxis");
        when(sc.getBattles()).thenReturn(500);
        when(sc.getMiles()).thenReturn(456);
        assertEquals(getString(sc),"maxis;500;456");
    }
    //denis
    @Test
    public void testJSONAdapter(){
        JSONAdapter ja = new JSONAdapter(null,null);
        assertEquals(ja.getCount(),0);
    }
    @Test
    public void checkShipsUrl(){
        Api api = mock(Api.class);
        when(api.getApiKey()).thenReturn("53171588f86f7958d53b4b8cf6440241");
        assertEquals(getShipsUrl(api),"https://api.worldofwarships.ru/wows/encyclopedia/ships/?application_id=53171588f86f7958d53b4b8cf6440241");
    }
}
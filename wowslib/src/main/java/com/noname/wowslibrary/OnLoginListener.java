package com.noname.wowslibrary;

import java.sql.Timestamp;

/**
 * Created by Instant on 08.11.2016.
 */

public interface OnLoginListener {
    void onLogin(int access_token, String nickname, int account_id, Timestamp experies_at);
}

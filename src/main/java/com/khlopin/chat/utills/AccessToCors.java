package com.khlopin.chat.utills;

import jakarta.servlet.http.HttpServletResponse;

public class AccessToCors {

    public static void allowCors(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Content-Type");
        response.addHeader("Access-Control-Max-Age", "86400");
    }
}

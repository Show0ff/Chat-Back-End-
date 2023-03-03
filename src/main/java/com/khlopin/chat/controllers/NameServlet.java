package com.khlopin.chat.controllers;

import com.google.gson.Gson;
import com.khlopin.chat.services.OwnerOfMessageService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "NameServlet", value = "/userName")
public class NameServlet extends HttpServlet {

    private final Gson gson = new Gson();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        accessToCross(response);
        String s = gson.toJson(OwnerOfMessageService.name);
        request.getSession().setAttribute("name",s);
        response.getWriter().write(s);
    }
//TODO There is not good place and bug with name(ownerOfMessage)

    private static void accessToCross(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Content-Type");
        response.addHeader("Access-Control-Max-Age", "86400");
    }

}

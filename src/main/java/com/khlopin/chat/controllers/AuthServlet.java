package com.khlopin.chat.controllers;


import com.google.gson.Gson;
import com.khlopin.chat.entity.Access;
import com.khlopin.chat.services.LoginInAccountService;
import com.khlopin.chat.utills.AccessToCors;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;


@WebServlet(name = "AuthServlet", value = "/auth")
public class AuthServlet extends HttpServlet {

    private final Gson gson = new Gson();

    private final LoginInAccountService loginInAccountService = new LoginInAccountService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AccessToCors.allowCors(response);
        String dataFromFront = request.getReader().readLine();

        Access access = loginInAccountService.getAccess(dataFromFront);
        response.getWriter().write(gson.toJson(access));

    }


    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)  {
        AccessToCors.allowCors(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}


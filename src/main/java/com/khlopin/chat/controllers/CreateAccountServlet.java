package com.khlopin.chat.controllers;

import com.google.gson.Gson;
import com.khlopin.chat.services.CreateAccountService;
import com.khlopin.chat.utills.AccessToCors;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateAccountServlet", value = "/register")
public class CreateAccountServlet extends HttpServlet {

    private final Gson gson = new Gson();

    private final CreateAccountService createAccountService = new CreateAccountService();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AccessToCors.allowCors(response);
        String dataFromFront = request.getReader().readLine();
        response.getWriter().write(gson.toJson(createAccountService.getAccessForCreateAccount(dataFromFront)));
    }



    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response){
        AccessToCors.allowCors(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

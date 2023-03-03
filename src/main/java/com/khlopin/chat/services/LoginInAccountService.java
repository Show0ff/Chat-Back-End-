package com.khlopin.chat.services;

import com.google.gson.Gson;
import com.khlopin.chat.entity.Access;
import com.khlopin.chat.entity.User;

public class LoginInAccountService {

    private final Gson gson = new Gson();

    public Access getAccess(String dataFromFront) {
        User clientUser = gson.fromJson(dataFromFront, User.class);
        User userFromDbByLogin = DBRepository.findUserFromDbByLogin(clientUser.getLogin());
        Access access;
        if (userFromDbByLogin != null
                && userFromDbByLogin.getLogin().equals(clientUser.getLogin())
                && userFromDbByLogin.getPassword().equals(clientUser.getPassword())) {
            OwnerOfMessageService.name = userFromDbByLogin.getLogin();
            access = Access.TRUE;
        } else {
            access = Access.FALSE;
        }
        return access;
    }
}

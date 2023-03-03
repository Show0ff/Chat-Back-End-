package com.khlopin.chat.services;

import com.google.gson.Gson;
import com.khlopin.chat.entity.Access;
import com.khlopin.chat.entity.User;

public class CreateAccountService {

    private final Gson gson = new Gson();

    public Access getAccessForCreateAccount(String dataFromFront) {
        User clientUser = gson.fromJson(dataFromFront, User.class);
        if (DBRepository.findUserFromDbByLogin(clientUser.getLogin()) == null) {
            DBRepository.addUserInDB(clientUser);
            OwnerOfMessageService.name = clientUser.getLogin();
            return Access.TRUE;
        }
        return Access.FALSE;
    }
}

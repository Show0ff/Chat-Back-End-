package com.khlopin.chat.services;

import com.google.gson.Gson;
import com.khlopin.chat.entity.Message;
import com.khlopin.chat.entity.PackageMessage;

public class PackageMessageService {

    private final Gson gson = new Gson();

    public PackageMessage getPackageMessageFromJson(String str) {
        return gson.fromJson(str, PackageMessage.class);
    }

    public Message unPackage(PackageMessage packageMessage) {
        return Message.builder()
                .ownerOfMessage(DBRepository.findUserFromDbByLogin(packageMessage.getName()))
                .text(packageMessage.getMessage())
                .build();
    }
}

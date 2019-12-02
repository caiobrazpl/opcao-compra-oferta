package com.caiobraz.opcaocompraoferta.core.service;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessagesService implements Serializable {

    private MessageSource messageSource;

    @Autowired
    public MessagesService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String string(String key) {
        return messageSource.getMessage(key, null, new Locale("pt", "BR"));
    }
}

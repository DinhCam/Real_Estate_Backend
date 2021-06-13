package com.gsu21se45.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageResourceHelper {

    @Autowired
    private MessageSource messageSource;


    public String getMessage(String message, Object... params) {

        return messageSource.getMessage(message, params, LocaleContextHolder.getLocale());

    }

    public String getMessage(String message, Locale locale, Object... params) {

        return messageSource.getMessage(message, params, locale);

    }
}

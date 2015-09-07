package com.easygovm.vendingapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by dwwang on 9/2/15.
 */
@Component
public class UserName {
    private String name;

    public String getName() {
        return name;
    }
}

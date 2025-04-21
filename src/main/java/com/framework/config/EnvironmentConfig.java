package com.framework.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface EnvironmentConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("db.user")
    String dbUser();

    @Key("db.password")
    String dbPassword();
}

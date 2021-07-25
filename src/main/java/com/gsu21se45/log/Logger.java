package com.gsu21se45.log;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public abstract class Logger {

    private String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src\\main\\resources\\log4j.properties";

    public Logger() {
        PropertyConfigurator.configure(log4jConfigFile);
    }
}

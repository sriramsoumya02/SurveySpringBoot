package com.soumya.springbasic.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application")
public class ApplicationPropertiesConfiguration {
    private String name;
    private String type;
    private String programminglanguage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProgramminglanguage() {
        return programminglanguage;
    }

    public void setProgramminglanguage(String programminglanguage) {
        this.programminglanguage = programminglanguage;
    }
}

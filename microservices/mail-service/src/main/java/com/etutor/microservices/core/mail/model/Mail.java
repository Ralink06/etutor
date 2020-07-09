package com.etutor.microservices.core.mail.model;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class Mail {

    private String to;
    private String subject;
    private String template;
    private Map<String, String> variables = new HashMap<>();

    public Mail(String to, String subject, String template) {
        this.to = to;
        this.subject = subject;
        this.template = template;
    }

    public void addVariable(String key, String value) {
        this.variables.put(key, value);
    }
}
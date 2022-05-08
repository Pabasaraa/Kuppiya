package com.example.mad_app;

public class NewsHelperClass {

    String key;
    String Topic,Description;

    public NewsHelperClass(String key, String topic, String description) {
        this.key = key;
        Topic = topic;
        Description = description;
    }

    public NewsHelperClass() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

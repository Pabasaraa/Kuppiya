package com.example.mad_app;

public class UserHelperClass {

    String Title;
    String Description;



    String key;

    public UserHelperClass() {

    }

    public UserHelperClass(String title, String description, String key) {
        Title = title;
        Description = description;
        this.key=key;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

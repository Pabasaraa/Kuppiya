package com.example.mad_app;

public class JobsHelperClass {

    private String key, title, companyName, contactNo, email, location, salary;

    public JobsHelperClass(){}

    public JobsHelperClass(String key, String title, String companyName, String contactNo, String email, String location, String salary) {
        this.key = key;
        this.title = title;
        this.companyName = companyName;
        this.contactNo = contactNo;
        this.email = email;
        this.location = location;
        this.salary = salary;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getSalary() {
        return salary;
    }
}

package com.itfactory.sbdemo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person
{
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private int jobId;
//mai avem putem avea si fieldul de telefon, am facut aborbarea pentru telefon, cu update, in BD am creat coloana telefon

    public Person(int id, String name, String email, int jobId)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobId = jobId;
    }

    public Person() {
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public int getJobId()
    {
        return jobId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobId=" + jobId +
                '}';
    }
}

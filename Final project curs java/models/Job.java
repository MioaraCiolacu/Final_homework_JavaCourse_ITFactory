package com.itfactory.sbdemo.models;

public class Job
{
    private final int id;
    private final String name;
    private final String domain;
    private final double baseSalary;

    public Job(int id, String name, String domain, double baseSalary)
    {
        this.id = id;
        this.name = name;
        this.domain = domain;
        this.baseSalary = baseSalary;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDomain()
    {
        return domain;
    }

    public double getBaseSalary()
    {
        return baseSalary;
    }
}

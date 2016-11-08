package com.zene.aseel.aseeltasksmanger.data;

import java.util.Date;

/**
 * Created by user on 9/8/2016.
 */
public class MyTask
{

    /**
     * rakam almhama
     *
     */
    private  String id;
    /**
     * enwan
     */
    private String title;
    private float priority;
    private Date when;
    private  String address;
    private  String phone;
    private  boolean isCompleted;


    public MyTask(String id, String title, int priority, Date when, String address, String phone) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.address = address;
        this.phone = phone;
        this.when=when;
        this.isCompleted=isCompleted();
    }

    public MyTask()
    {

    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(boolean isCompleted){this.isCompleted=isCompleted;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        when = when;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", When=" +when +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
    public boolean setIsCompleted(){
        if (isCompleted);
        return true;

    }

}

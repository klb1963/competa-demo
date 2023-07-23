package com.competa.competademo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Competa {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id; // идентификатор
    private String competa_type; // тип = ed-competa, job-competa, hs-competa, ss-competa
    private String title;
    private String description;
    private boolean status;
    private int views;
    @DateTimeFormat (pattern = "yyyy-mm-dd")
    private Date dateOut;
    private String timeOut;
    private String userName;

    // конструктор со всеми полями
    public Competa(Long id, String competa_type, String title, String description, boolean status, int views, Date dateOut, String timeOut, long userId) {
        this.id = id;
        this.competa_type = competa_type;
        this.title = title;
        this.description = description;
        this.status = status;
        this.views = views;
        this.dateOut = dateOut;
        this.timeOut = timeOut;
        this.userName = userName;
    }

    public Competa(String title, String description, String competa_type, Date dateOut, boolean status) {
        this.title = title;
        this.description = description;
        this.competa_type = competa_type;
        this.status = status;
        this.dateOut = dateOut;
        this.status = status;
    }

    // конструктор пустой
    public Competa() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getCompeta_type() {
        return competa_type;
    }

    public void setCompeta_type(String competa_type) {
        this.competa_type = competa_type;
    }

    public Competa(String titel, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

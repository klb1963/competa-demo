package com.competa.competademo.models;

import com.competa.competademo.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
// @NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "competa")
public class Competa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column
    private Long id; // идентификатор
    // можно удалять
    private String competaType; // должен быть тип - из таблицы Type
    // TODO - как здесь выполнить замену competaType на список из базы данных?
    // так же как и с user -> competa
    // ===========================
    // @Column(nullable=false)
    // private List<Type> types = new ArrayList<Type>();
    // ===========================
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean status;
    private int views;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateOut;
    private String timeOut; 

    @ManyToOne
    private User user;
    //fix
    @ManyToOne
    private Type type;

    // конструктор со всеми полями
    public Competa(Long id, String competaType, String title, String description, boolean status, int views, Date dateOut, String timeOut, User user) {
        this.id = id;
        this.competaType = competaType;
        this.title = title;
        this.description = description;
        this.status = status;
        this.views = views;
        this.dateOut = dateOut;
        this.timeOut = timeOut;
        this.user = user;
    }

    public Competa(String title, String description, String competaType, Date dateOut, boolean status) {
        this.title = title;
        this.description = description;
        this.competaType = competaType;
        this.dateOut = dateOut;
        this.status = status;
    }

    // конструктор пустой
    public Competa() {
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getCompetaType() {
        return competaType;
    }

    public void setCompetaType(String competaType) {
        this.competaType = competaType;
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

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competa competa = (Competa) o;
        return Objects.equals(id, competa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

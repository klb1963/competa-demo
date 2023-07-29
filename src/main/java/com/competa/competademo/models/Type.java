package com.competa.competademo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="competa_type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column
    private Integer id;

    @Column(nullable=false)
    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competa") // каждый тип компеты может быть у многих компет
    private List<Competa> competas = new ArrayList<>();

    public String getType() {
        return type;
    }

    public List<Competa> getCompetas() {
        return competas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type that = (Type) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

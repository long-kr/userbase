package com.bugblogs.blog.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class BBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Date createdOn = new Date();

    @Column(nullable = false)
    private Date updatedOn;

    // @Column(nullable = false)
    // private Date updatedBy;

}

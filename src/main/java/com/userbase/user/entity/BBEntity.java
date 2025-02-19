package com.userbase.user.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

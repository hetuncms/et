package com.example.cms9cc.admin.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="float_ad")
@Setter
@Getter
public class FloatBean {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String url;
    private String picUrl;
    private Date status;
    private String tel;
    @Transient
    private boolean update;

}

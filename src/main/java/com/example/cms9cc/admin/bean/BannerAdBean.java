package com.example.cms9cc.admin.bean;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "banner_ad")
@Data
public class BannerAdBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    private Integer sort;
    private String url;
    private String picUrl;
    private String picWidth;
    private String picHeight;
    private Date statusTime;
    private String tel;
}

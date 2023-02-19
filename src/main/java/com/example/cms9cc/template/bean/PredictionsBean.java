package com.example.cms9cc.template.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "predictions")
@Setter
@Getter
public class PredictionsBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String leagueName;
    private String changci;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;
    private String boDan;
    private String duYing;
    private String bigBall;
    private String smallBall;
    private Integer goal;
    private Boolean goalsFromBothSides;

    private String mainTeamName;
    private String mainTeamImg;
    private String visitTeamName;
    private String visitTeamImg;
}

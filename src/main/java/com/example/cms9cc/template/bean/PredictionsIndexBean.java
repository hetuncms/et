package com.example.cms9cc.template.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PredictionsIndexBean {
    private List<Date> allStartTime;

    private Date currentShowDate;
    List<PredictionsBean> predictionsBeans;
}

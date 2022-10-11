package com.example.cms9cc;

import java.util.Date;
import java.util.List;

public class CmsInfoBean {

    private Date version;
    private List<String> zip;
    private String bug;
    private String Statistics;
    private String zhiboapi;
    private String txtapi;
    private String imgapi;

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public List<String> getZip() {
        return zip;
    }

    public void setZip(List<String> zip) {
        this.zip = zip;
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) {
        this.bug = bug;
    }

    public String getStatistics() {
        return Statistics;
    }

    public void setStatistics(String Statistics) {
        this.Statistics = Statistics;
    }

    public String getZhiboapi() {
        return zhiboapi;
    }

    public void setZhiboapi(String zhiboapi) {
        this.zhiboapi = zhiboapi;
    }

    public String getTxtapi() {
        return txtapi;
    }

    public void setTxtapi(String txtapi) {
        this.txtapi = txtapi;
    }

    public String getImgapi() {
        return imgapi;
    }

    public void setImgapi(String imgapi) {
        this.imgapi = imgapi;
    }

}
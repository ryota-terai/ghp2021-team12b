/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.jsf.bean;

import ghp2021.ghp2021app.ejb.PostInformationEJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Ryota-Terai
 */
@Named(value = "disasterPostBean")
@RequestScoped
public class disasterPostBean {

    /**
     * 緯度
     */
    private String latitude;

    /**
     * 経度
     */
    private String longitude;

    /**
     * 投稿内容
     */
    private String information;
    @Inject
    private PostInformationEJB postInformationEJB;

    public disasterPostBean() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String postDisaster() {
//        latitude = FacesContext.getCurrentInstance().
//                getExternalContext().getRequestParameterMap().get("latitude");
//        longitude = FacesContext.getCurrentInstance().
//                getExternalContext().getRequestParameterMap().get("longitude");

        postInformationEJB.postDisasterInformation(latitude, longitude, information);

        return "disasterPosted?faces-redirect=true";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.jsf.bean;

import ghp2021.ghp2021app.ejb.PostInformationEJB;
import ghp2021.ghp2021entity.PostInformation;
import java.io.Serializable;
import java.util.Date;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author r-terai
 */
@Named(value = "checkDisasterInformationBean")
@ViewScoped
public class CheckDisasterInformationBean implements Serializable {

    private long id;

    private PostInformation postInformation;

    @Inject
    private PostInformationEJB postInformationEJB;

    /**
     * Creates a new instance of CheckDisasterInformationBean
     */
    public CheckDisasterInformationBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PostInformation getPostInformation() {
        return postInformation;
    }

    public void load() {
        this.postInformation = postInformationEJB.getPostInformation(id);
    }

    public String confirm() {
        postInformation.setTime(new Date());

        postInformationEJB.confirm(postInformation);

        return "listUncheckedDisasterInformation?faces-redirect=true";
    }

    public String delete() {
        postInformationEJB.delete(id);

        return "listUncheckedDisasterInformation?faces-redirect=true";
    }
}

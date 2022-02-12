/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.rest;

import ghp2021.ghp2021app.ejb.PostInformationGeoJsonBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.RequestScoped;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author r-terai
 */
@RequestScoped
@TransactionManagement(TransactionManagementType.BEAN)
@Path("rest")
public class GenericResource {

    @Context
    private UriInfo context;

    @Resource
    UserTransaction tx;

    @EJB(name = "PostInformationGeoJsonBean")
    PostInformationGeoJsonBean postInformationGeoJsonBean;

    private static final Logger LOG = Logger.getLogger(GenericResource.class.getName());

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of
     * ghp2021.ghp2021app.rest.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path(value = "/disasterInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        String geoJson = null;
        try {
            tx.begin();
            geoJson = postInformationGeoJsonBean.getDisasterInformation();

            tx.commit();
        } catch (HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException ex) {
            try {
                tx.rollback();
                LOG.log(Level.SEVERE, null, ex);
                return null;
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex1);
                LOG.log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return geoJson;
    }
}

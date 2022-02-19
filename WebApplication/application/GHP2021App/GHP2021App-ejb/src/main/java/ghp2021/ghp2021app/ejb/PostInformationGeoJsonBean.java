/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.ejb;

import ghp2021.ghp2021entity.File;
import ghp2021.ghp2021entity.PostInformation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author r-terai
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class PostInformationGeoJsonBean {

    @PersistenceContext(unitName = "GHP2021Entity")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(ShelterSearchEJB.class.getName());

    public String getDisasterInformation() {
        List<PostInformation> approvedInformation = em.createNamedQuery("PostInformation.findByApproved", PostInformation.class)
                .setParameter("approved", 1)
                .getResultList();

        List<String> features = new ArrayList();
        for (PostInformation information : approvedInformation) {
            File file = em.find(File.class, information.getId());
            StringBuffer sb = new StringBuffer();
            sb.append("{ \"type\": \"Feature\", \"properties\": { \"comment\":\"");
            sb.append(information.getInformation().replace("\n", ""));
            sb.append("\",\"id\":\"");
            sb.append(information.getId());
            sb.append("\",\"picture\":");
            sb.append(file != null ? "\"true\"" : "\"false\"");
            sb.append("}, \"geometry\": { \"type\": \"Point\", \"coordinates\": [ ");
            sb.append(information.getLongitude());
            sb.append(", ");
            sb.append(information.getLatitude());
            sb.append(" ] } }");

            System.out.println(sb);
            features.add(sb.toString());
        }
        System.out.println(features);
        List<Map<String, Object>> featureCollection = new ArrayList();
        featureCollection.add((Map<String, Object>) (new HashMap()).put("type", "FeatureCollection"));
        featureCollection.add((Map<String, Object>) (new HashMap()).put("name", "Disaster Information"));
        featureCollection.add((Map<String, Object>) (new HashMap()).put("features", features));

        System.out.println(featureCollection);

        StringBuffer json = new StringBuffer();
        json.append("{\n"
                + "\"type\": \"FeatureCollection\",\n"
                + "\"name\": \"Disaster Information\",\n"
                + "\"crs\": { \"type\": \"name\", \"properties\": { \"name\": \"urn:ogc:def:crs:OGC:1.3:CRS84\" } },\n"
                + "\"features\": ");
        json.append(features.toString());
        json.append("}");

        return json.toString();
    }

}

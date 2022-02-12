/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.ejb;

import ghp2021.ghp2021entity.ShelterInformation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryota-Terai
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ShelterSearchEJB implements ShelterSearchEJBLocal {

    @PersistenceContext(unitName = "GHP2021Entity")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(ShelterSearchEJB.class.getName());

    @Override
    public List<ShelterInformation> search(String administrativeAreaCode, boolean p20_007, boolean p20_008, boolean p20_009, boolean p20_010, boolean p20_011) {

        List<ShelterInformation> shelters = em.createNamedQuery("ShelterInformation.findByAdministrativeAreaCode", ShelterInformation.class)
                .setParameter("administrativeAreaCode", administrativeAreaCode)
                .getResultList();

        List<ShelterInformation> shelters2 = new ArrayList();

        for (ShelterInformation shelter : shelters) {
            if (shelter.getP20012() != 0) {
                shelters2.add(shelter);
            } else if (p20_007 && shelter.getP20007() != 0) {
                shelters2.add(shelter);
            } else if (p20_008 && shelter.getP20008() != 0) {
                shelters2.add(shelter);
            } else if (p20_009 && shelter.getP20009() != 0) {
                shelters2.add(shelter);
            } else if (p20_010 && shelter.getP20010() != 0) {
                shelters2.add(shelter);
            } else if (p20_011 && shelter.getP20011() != 0) {
                shelters2.add(shelter);
            }
        }
        return shelters2;
    }
}

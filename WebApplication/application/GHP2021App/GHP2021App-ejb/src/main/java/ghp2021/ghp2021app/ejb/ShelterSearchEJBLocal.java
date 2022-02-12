/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.ejb;

import ghp2021.ghp2021entity.ShelterInformation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ryota-Terai
 */
@Local
public interface ShelterSearchEJBLocal {

    /**
     * 避難所一覧を検索
     * @param administrativeAreaCode 行政区域コード
     * @param p20_007 地震災害
     * @param p20_008 津波災害
     * @param p20_009 水害
     * @param p20_010 火山災害
     * @param p20_011 その他
     * @return 避難所一覧
     */
    List<ShelterInformation> search(String administrativeAreaCode, boolean p20_007, boolean p20_008, boolean p20_009, boolean p20_010, boolean p20_011);
}

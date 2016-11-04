/**
 * 2013-7-9 上午1:36:34
 */
package com.xnradmin.core.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @autohr: bin_liu
 */
@Repository("CommonMasterDAO")
public class CommonMasterDAO extends BaseHibernateMasterDAO{

    private static final Logger log = LoggerFactory
            .getLogger(CommonMasterDAO.class);
}

/**
 * 2014年2月3日 下午5:26:19
 */
package com.xnradmin.core.business;


import com.xnradmin.dto.client.SyncDTO;
import com.xnradmin.dto.client.SyncDTOAck;

/**
 * @author: bin_liu
 */
public interface BusinessScript{
    public SyncDTOAck execute(SyncDTO sync);

}

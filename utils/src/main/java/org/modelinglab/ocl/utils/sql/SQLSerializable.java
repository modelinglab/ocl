/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.utils.sql;

import javax.annotation.Nonnull;

/**
 *
 */
public interface SQLSerializable {
    
    @Nonnull SQLSerializer getSerializer();
    
    int getSqlType();
}

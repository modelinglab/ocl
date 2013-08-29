/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.utils.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 */
public interface SQLSerializer {

    OclValue<?> fromSQL(ResultSet rs, String columnName, Classifier expectedType) throws SQLException;
    
    void toSQL(PreparedStatement ps, int columnIndex, OclValue<?> val, Classifier staticType) throws SQLException;
    
}

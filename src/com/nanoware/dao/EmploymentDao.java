/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.dao;

import com.nanoware.model.Employment;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mothusi Molorane
 */
public abstract class EmploymentDao {
    
    public abstract int AddEmployment(Employment employment);
    public abstract int EditEmployment(Employment employment);
    public abstract int RemoveEmployment(long personId);    
    public abstract Employment GetEmployment(long personId);   
    
    // COURSE CONVERSION METHODS
    protected Employment GetEmploymentDetailsFromResultSet(ResultSet rs) throws SQLException{
        Employment employment = new Employment();
        employment.setEmployment(rs.getLong("personId"), 
                        rs.getString("company"), 
                        rs.getInt("positionId"), 
                        rs.getString("address"),
                        rs.getString("postalCode"),
                        rs.getString("contactPerson"),
                        rs.getString("telephone"),
                        rs.getString("cellphone"),
                        rs.getString("fax"),
                        rs.getString("position"));
        return employment;
    }
}

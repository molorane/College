/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.bll;

import com.nanoware.model.TestTimeTable;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public interface TestTimeTableBo {
    
    public abstract int AddTestTimeTable(TestTimeTable testTimeTable);
    public abstract int EditTestTimeTable(TestTimeTable testTimeTable);
    public abstract int RemoveTestTimeTable(int ttId);    
    public abstract TestTimeTable GetTestTimeTable(int ttId);
    public abstract ArrayList<TestTimeTable> GetCampusModuleTestTimeTable(int campusCode,String moduleCode,int termId);
    
}

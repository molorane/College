/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanoware.view;

import com.nanoware.bll.impl.GradeBoImpl;
import com.nanoware.bll.impl.TestTimeTableBoImpl;
import com.nanoware.model.Grade;
import com.nanoware.model.TestTimeTable;
import java.util.ArrayList;

/**
 *
 * @author Mothusi Molorane
 */
public class College {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        TestTimeTableBoImpl testTimeTableBoImpl = new TestTimeTableBoImpl();
        ArrayList<TestTimeTable> list = testTimeTableBoImpl.GetCampusModuleTestTimeTable(1, "06040026", 1);
        System.out.println(list.size());
    }
    
}

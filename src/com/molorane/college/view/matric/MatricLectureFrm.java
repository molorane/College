/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.molorane.college.view.matric;

import com.molorane.college.bll.impl.CollegeBoImpl;
import com.molorane.college.custom.Functions;
import com.molorane.college.bll.impl.MatricBoImpl;
import com.molorane.college.bll.impl.StaffSubjectBoImpl;
import com.molorane.college.bll.impl.SubjectBoImpl;
import com.molorane.college.bll.impl.UserBoImpl;
import com.molorane.college.custom.LoginSession;
import com.molorane.college.custom.Printing;
import com.molorane.college.custom.TableColumnHider;
import com.molorane.college.jasperservice.JasperMatric;
import com.molorane.college.model.Matric;
import com.molorane.college.model.StaffSubject;
import com.molorane.college.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 * @author Mothusi Molorane
 */

public final class MatricLectureFrm extends javax.swing.JFrame {
    
    private final CollegeBoImpl collegeBoImpl = new CollegeBoImpl();
    private final MatricBoImpl matricBoImpl = new MatricBoImpl();
    private final StaffSubjectBoImpl staffSubjectBoImpl = new StaffSubjectBoImpl();
    private final JasperMatric jasperMatric = new JasperMatric();
    private final UserBoImpl userBoImpl = new UserBoImpl();
    private final SubjectBoImpl subjectBoImpl = new SubjectBoImpl();
    
    // variables
    private final DefaultTableModel tblModelStudentMarks;
    private final DefaultTableModel tblModelSearchStudent;
    private final DefaultTableModel tblModelSubjectsToAuthorize;
    private final DefaultTableModel tblModelMarksToAuthorize;
    
    private final int selectedYear;
    private String selectedSubject;
    
    private final User loggedInUser;
    
    private boolean jcbYearFilled = false;
    private boolean jcbSubjectsFilled = false;
    
    private List<HashMap<String,Object>> uploadMarks = new ArrayList<>();
    private List<HashMap<String,Object>> uploadStudents = new ArrayList<>();
    
    private Printing p;

    
    private JFileChooser fc;
    
    private TableColumnHider tblHider;
    
    private StaffSubject selectedStaffSubject;
    private StaffSubject staffSubjectClicked;
    List<HashMap<String, Object>> marksToAUtorize = new ArrayList<>();
    private int rowOfSubjectsToAuthorize;
    /**
     * Creates new form MatricFrm
     */
    public MatricLectureFrm() {
        super("Matric - "+Functions.appName());
        initComponents();    
        
        loggedInUser = LoginSession.GetSessionUser();
        title.setUser(loggedInUser);
        title.setCurrFrm(MatricLectureFrm.this);
        
        selectedYear = collegeBoImpl.GetCurrentYear();
        lblYear.setText(selectedYear+"");
        jcbYearFilled = true;
        jpnProgress.setVisible(false);
        lblNeedAuthorization.setVisible(false);
       
        tblModelStudentMarks = (DefaultTableModel)tblStudentsMarks.getModel();
        tblModelSearchStudent = (DefaultTableModel)tblSearchStudent.getModel();
        tblModelSubjectsToAuthorize = (DefaultTableModel)tblSubjectsToAuthorize.getModel();
        tblModelMarksToAuthorize = (DefaultTableModel)tblMarksToAuthorize.getModel();
        
        fillSubjectsToAuthorize();
        fillStaffSubjects();
        
        Functions.initForm(MatricLectureFrm.this);
    }
    
    public final void fillStaffSubjects(){
        List<HashMap<String, Object>> staffSubjects = staffSubjectBoImpl.GetStaffSubjects(loggedInUser.getPersonId());
        jcbSubject.addItem("SELECT");
        staffSubjects.forEach((bean) -> {
            jcbSubject.addItem(bean.get("subjectCode").toString());
        });
        jcbSubjectsFilled = true;
    }
    
    void btnViewStudentPressed(){
        Functions.setColor(btnViewStudents);
        Functions.resetColor(btnSearchStudent);
        Functions.resetColor(btnStudent);
        Functions.resetColor(btnAuthorize);
    }
    
    void btnSearchStudentPressed(){
       Functions.resetColor(btnViewStudents);
       Functions.setColor(btnSearchStudent);
       Functions.resetColor(btnStudent);
       Functions.resetColor(btnAuthorize);
    }
    
    void btnStudentPressed(){
       Functions.resetColor(btnViewStudents);
       Functions.resetColor(btnSearchStudent);
       Functions.setColor(btnStudent);
       Functions.resetColor(btnAuthorize);
    }
    
    void btnAuthorizePressed(){
       Functions.resetColor(btnViewStudents);
       Functions.resetColor(btnSearchStudent);
       Functions.resetColor(btnStudent);
       Functions.setColor(btnAuthorize);
    }
    
    void btnPasswordPressed(){
       Functions.resetColor(btnViewStudents);
       Functions.resetColor(btnSearchStudent);
       Functions.resetColor(btnStudent);
       Functions.resetColor(btnAuthorize);
    }
    
    public void ViewStudent(ArrayList<Matric> students){
        tblModelStudentMarks.setRowCount(0);
        students.forEach((bean) -> {
            tblModelStudentMarks.addRow(new Object[]{
                bean.getIdno(),
                bean.getLastName(),
                bean.getFirstName(),
                bean.getMyear(),
                bean.getSubject(),
                bean.getTerm1(),
                bean.getTerm2(),
                bean.getTerm3()
             });
        });
    }
    
    public void SearchStudent(ArrayList<Matric> students){
        tblModelSearchStudent.setRowCount(0);
        students.forEach((bean) -> {
            tblModelSearchStudent.addRow(new Object[]{
                bean.getIdno(),
                bean.getLastName(),
                bean.getFirstName(),
                bean.getSubject(),
                bean.getTerm1(),
                bean.getTerm2(),
                bean.getTerm3()
             });
        });
    }
    
    public void endProgress(){
        jpnProgress.setVisible(false);
        lblProgress.setText("");
    }
    
    public void startProcess(String msg){
        jpnProgress.setVisible(true);
        lblProgress.setText(msg);
    }
    
    private void fillSubjectsToAuthorize(){
       List<HashMap<String, Object>> subjectsToAuthorize = matricBoImpl.GetSubjectsToAuthorize(loggedInUser.getPersonId());
       tblModelSubjectsToAuthorize.setRowCount(0);
       subjectsToAuthorize.forEach((row) -> {
            tblModelSubjectsToAuthorize.addRow(new Object[]{
                row.get("subject")
             });
        });
        
        if(tblModelSubjectsToAuthorize.getRowCount() > 0){
            lblNeedAuthorization.setVisible(true);
            alert.notify("Pending updates.", 0);
        }
    }
    
    public void displayMarksToAuthorize(boolean checked){
        tblModelMarksToAuthorize.setRowCount(0);
        marksToAUtorize.forEach((row) -> {
            tblModelMarksToAuthorize.addRow(new Object[]{
                row.get("idno"),
                row.get("lastName"),
                row.get("firstName"),
                row.get("subject"),
                row.get("myear"),
                row.get("mark1"),
                row.get("mark2"),
                row.get("mark3"),
                checked
             });
        });
        
        if(tblModelMarksToAuthorize.getRowCount() == 0){
            tblModelSubjectsToAuthorize.removeRow(rowOfSubjectsToAuthorize);
            if(tblModelSubjectsToAuthorize.getRowCount() == 0){
                lblNeedAuthorization.setVisible(false);
            }
        }
    }
    
    public void fillMarksToAuthorize(List<HashMap<String, Object>> marks){
        marksToAUtorize = marks; 
        displayMarksToAuthorize(false);
    }
    
    public void getControlData(){
        selectedSubject = jcbSubject.getSelectedItem().toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jpnSideMenu = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnViewStudents = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSearchStudent = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnStudent = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnAuthorize = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblNeedAuthorization = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        title = new com.molorane.college.view.Controls.TitlePnl();
        alert = new com.molorane.college.view.Controls.Alert();
        jpnControls = new javax.swing.JPanel();
        jpnProgress = new javax.swing.JPanel();
        lblProgress = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jpnSelect = new com.molorane.college.custom.JPanelSliding();
        pnlSelect = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jcbSubject = new javax.swing.JComboBox<>();
        pnlEmpty = new javax.swing.JPanel();
        jpnSlider = new com.molorane.college.custom.JPanelSliding();
        jpnStudentMarks = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblStudentsMarks = new javax.swing.JTable();
        btnUpdateMarks = new javax.swing.JButton();
        jpnSearchStudent = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSearchStudent = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtSearchStudent = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jpnStudent = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        txtNames = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtIDNo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtSubject = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTerm1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTerm2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTerm3 = new javax.swing.JTextField();
        btnSaveMarks = new javax.swing.JButton();
        jpnAuthorize = new javax.swing.JPanel();
        btnRevertUpdates = new javax.swing.JButton();
        rbtnCheckAll = new javax.swing.JRadioButton();
        rbtnUncheckAll = new javax.swing.JRadioButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblMarksToAuthorize = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSubjectsToAuthorize = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpnSideMenu.setBackground(Functions.pnlBackgroundSideMenu());

        btnViewStudents.setBackground(new java.awt.Color(64, 43, 100));
        btnViewStudents.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnViewStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewStudentsMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/users.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("View Students");

        javax.swing.GroupLayout btnViewStudentsLayout = new javax.swing.GroupLayout(btnViewStudents);
        btnViewStudents.setLayout(btnViewStudentsLayout);
        btnViewStudentsLayout.setHorizontalGroup(
            btnViewStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnViewStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnViewStudentsLayout.setVerticalGroup(
            btnViewStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnViewStudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnViewStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnSearchStudent.setBackground(Functions.pnlBackgroundSideMenu());
        btnSearchStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchStudentMouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/search.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Search Student");

        javax.swing.GroupLayout btnSearchStudentLayout = new javax.swing.GroupLayout(btnSearchStudent);
        btnSearchStudent.setLayout(btnSearchStudentLayout);
        btnSearchStudentLayout.setHorizontalGroup(
            btnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSearchStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSearchStudentLayout.setVerticalGroup(
            btnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSearchStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnStudent.setBackground(Functions.pnlBackgroundSideMenu());
        btnStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStudentMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/profile.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Student");

        javax.swing.GroupLayout btnStudentLayout = new javax.swing.GroupLayout(btnStudent);
        btnStudent.setLayout(btnStudentLayout);
        btnStudentLayout.setHorizontalGroup(
            btnStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnStudentLayout.setVerticalGroup(
            btnStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnAuthorize.setBackground(Functions.pnlBackgroundSideMenu());
        btnAuthorize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuthorize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAuthorizeMouseClicked(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/mail.png"))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setText("Updates");

        lblNeedAuthorization.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/notification.png"))); // NOI18N

        javax.swing.GroupLayout btnAuthorizeLayout = new javax.swing.GroupLayout(btnAuthorize);
        btnAuthorize.setLayout(btnAuthorizeLayout);
        btnAuthorizeLayout.setHorizontalGroup(
            btnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAuthorizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(btnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAuthorizeLayout.createSequentialGroup()
                    .addContainerGap(162, Short.MAX_VALUE)
                    .addComponent(lblNeedAuthorization, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        btnAuthorizeLayout.setVerticalGroup(
            btnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAuthorizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(btnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnAuthorizeLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(lblNeedAuthorization, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(12, 12, 12)))
        );

        lblYear.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblYear.setForeground(new java.awt.Color(204, 204, 204));
        lblYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jpnSideMenuLayout = new javax.swing.GroupLayout(jpnSideMenu);
        jpnSideMenu.setLayout(jpnSideMenuLayout);
        jpnSideMenuLayout.setHorizontalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSearchStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnViewStudents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAuthorize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(lblYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnSideMenuLayout.setVerticalGroup(
            jpnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSideMenuLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAuthorize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnControls.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblProgress.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblProgress.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProgress.setText("Exporting please wait..");
        lblProgress.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/loaderIcon.gif"))); // NOI18N

        javax.swing.GroupLayout jpnProgressLayout = new javax.swing.GroupLayout(jpnProgress);
        jpnProgress.setLayout(jpnProgressLayout);
        jpnProgressLayout.setHorizontalGroup(
            jpnProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnProgressLayout.createSequentialGroup()
                .addComponent(lblProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
        );
        jpnProgressLayout.setVerticalGroup(
            jpnProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpnSelect.setBorder(null);

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 51));
        jLabel60.setText("Subject:");

        jcbSubject.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbSubject.setForeground(new java.awt.Color(51, 51, 51));
        jcbSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSubjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSelectLayout = new javax.swing.GroupLayout(pnlSelect);
        pnlSelect.setLayout(pnlSelectLayout);
        pnlSelectLayout.setHorizontalGroup(
            pnlSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectLayout.createSequentialGroup()
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbSubject, 0, 100, Short.MAX_VALUE))
        );
        pnlSelectLayout.setVerticalGroup(
            pnlSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectLayout.createSequentialGroup()
                .addGroup(pnlSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jcbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jpnSelect.add(pnlSelect, "card2");

        javax.swing.GroupLayout pnlEmptyLayout = new javax.swing.GroupLayout(pnlEmpty);
        pnlEmpty.setLayout(pnlEmptyLayout);
        pnlEmptyLayout.setHorizontalGroup(
            pnlEmptyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pnlEmptyLayout.setVerticalGroup(
            pnlEmptyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        jpnSelect.add(pnlEmpty, "card3");

        javax.swing.GroupLayout jpnControlsLayout = new javax.swing.GroupLayout(jpnControls);
        jpnControls.setLayout(jpnControlsLayout);
        jpnControlsLayout.setHorizontalGroup(
            jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnControlsLayout.setVerticalGroup(
            jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnControlsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpnProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        tblStudentsMarks.setAutoCreateRowSorter(true);
        tblStudentsMarks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblStudentsMarks.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblStudentsMarks.setForeground(new java.awt.Color(51, 51, 51));
        tblStudentsMarks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDNo", "LastName", "Names", "Year", "Subject", "Term 1", "Term 2", "Term 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudentsMarks.setGridColor(new java.awt.Color(204, 204, 204));
        tblStudentsMarks.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblStudentsMarks.setRowHeight(40);
        jScrollPane5.setViewportView(tblStudentsMarks);
        if (tblStudentsMarks.getColumnModel().getColumnCount() > 0) {
            tblStudentsMarks.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblStudentsMarks.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        btnUpdateMarks.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnUpdateMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/semi_success.png"))); // NOI18N
        btnUpdateMarks.setText("Update");
        btnUpdateMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMarksActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnStudentMarksLayout = new javax.swing.GroupLayout(jpnStudentMarks);
        jpnStudentMarks.setLayout(jpnStudentMarksLayout);
        jpnStudentMarksLayout.setHorizontalGroup(
            jpnStudentMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStudentMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnStudentMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnStudentMarksLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUpdateMarks)))
                .addContainerGap())
        );
        jpnStudentMarksLayout.setVerticalGroup(
            jpnStudentMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnStudentMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpnSlider.add(jpnStudentMarks, "card2");

        tblSearchStudent.setAutoCreateRowSorter(true);
        tblSearchStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblSearchStudent.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblSearchStudent.setForeground(new java.awt.Color(51, 51, 51));
        tblSearchStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDNo", "Last Name", "Names", "Subject", "Term 1", "Term 2", "Term 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSearchStudent.setGridColor(new java.awt.Color(204, 204, 204));
        tblSearchStudent.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblSearchStudent.setRowHeight(40);
        tblSearchStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSearchStudentMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblSearchStudent);
        if (tblSearchStudent.getColumnModel().getColumnCount() > 0) {
            tblSearchStudent.getColumnModel().getColumn(2).setHeaderValue("Year");
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Search:");

        txtSearchStudent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSearchStudent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchStudentKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/information.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnSearchStudentLayout = new javax.swing.GroupLayout(jpnSearchStudent);
        jpnSearchStudent.setLayout(jpnSearchStudentLayout);
        jpnSearchStudentLayout.setHorizontalGroup(
            jpnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jpnSearchStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnSearchStudentLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpnSearchStudentLayout.setVerticalGroup(
            jpnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSearchStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnSearchStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnSlider.add(jpnSearchStudent, "card3");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("LastName:");

        txtLastName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtLastName.setEnabled(false);

        txtNames.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNames.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Names:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("IDNo:");

        txtIDNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtIDNo.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Year:");

        txtYear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtYear.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Subject:");

        txtSubject.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSubject.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Term 1:");

        txtTerm1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Term 2:");

        txtTerm2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Term 3:");

        txtTerm3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnSaveMarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/semi_success.png"))); // NOI18N
        btnSaveMarks.setText("Update");
        btnSaveMarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMarksActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLastName)
                    .addComponent(txtIDNo, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(txtNames))
                .addGap(68, 68, 68)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTerm1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTerm2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYear, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSubject, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSaveMarks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTerm3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(267, 267, 267))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtIDNo, txtLastName});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTerm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTerm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTerm3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSaveMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtIDNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Student Information", jPanel4);

        javax.swing.GroupLayout jpnStudentLayout = new javax.swing.GroupLayout(jpnStudent);
        jpnStudent.setLayout(jpnStudentLayout);
        jpnStudentLayout.setHorizontalGroup(
            jpnStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jpnStudentLayout.setVerticalGroup(
            jpnStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jpnSlider.add(jpnStudent, "card4");

        btnRevertUpdates.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRevertUpdates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/molorane/college/images/32x32/error_delete.png"))); // NOI18N
        btnRevertUpdates.setText(" Revert changes");
        btnRevertUpdates.setToolTipText("Revert changes");
        btnRevertUpdates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevertUpdatesActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnCheckAll);
        rbtnCheckAll.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rbtnCheckAll.setText("CHECK ALL");
        rbtnCheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCheckAllActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnUncheckAll);
        rbtnUncheckAll.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rbtnUncheckAll.setText("UNCHECK");
        rbtnUncheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnUncheckAllActionPerformed(evt);
            }
        });

        tblMarksToAuthorize.setAutoCreateRowSorter(true);
        tblMarksToAuthorize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblMarksToAuthorize.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblMarksToAuthorize.setForeground(new java.awt.Color(51, 51, 51));
        tblMarksToAuthorize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDNo", "Last Name", "Names", "Subject", "Year", "Term 1", "Term 2", "Term 3", "Check"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMarksToAuthorize.setGridColor(new java.awt.Color(204, 204, 204));
        tblMarksToAuthorize.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblMarksToAuthorize.setRowHeight(40);
        jScrollPane8.setViewportView(tblMarksToAuthorize);
        if (tblMarksToAuthorize.getColumnModel().getColumnCount() > 0) {
            tblMarksToAuthorize.getColumnModel().getColumn(2).setHeaderValue("Year");
        }

        tblSubjectsToAuthorize.setAutoCreateRowSorter(true);
        tblSubjectsToAuthorize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblSubjectsToAuthorize.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblSubjectsToAuthorize.setForeground(new java.awt.Color(51, 51, 51));
        tblSubjectsToAuthorize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSubjectsToAuthorize.setGridColor(new java.awt.Color(204, 204, 204));
        tblSubjectsToAuthorize.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tblSubjectsToAuthorize.setRowHeight(40);
        tblSubjectsToAuthorize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSubjectsToAuthorizeMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblSubjectsToAuthorize);

        javax.swing.GroupLayout jpnAuthorizeLayout = new javax.swing.GroupLayout(jpnAuthorize);
        jpnAuthorize.setLayout(jpnAuthorizeLayout);
        jpnAuthorizeLayout.setHorizontalGroup(
            jpnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnAuthorizeLayout.createSequentialGroup()
                .addGap(406, 406, 406)
                .addGroup(jpnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnAuthorizeLayout.createSequentialGroup()
                        .addComponent(btnRevertUpdates)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbtnCheckAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnUncheckAll))
                    .addComponent(jScrollPane8))
                .addContainerGap())
            .addGroup(jpnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnAuthorizeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(530, Short.MAX_VALUE)))
        );
        jpnAuthorizeLayout.setVerticalGroup(
            jpnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAuthorizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRevertUpdates)
                    .addComponent(rbtnCheckAll)
                    .addComponent(rbtnUncheckAll))
                .addContainerGap())
            .addGroup(jpnAuthorizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnAuthorizeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jpnSlider.add(jpnAuthorize, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(alert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jpnControls, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jpnSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jpnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewStudentsMouseClicked
        // TODO add your handling code here:
        btnViewStudentPressed();
        jpnSlider.nextPanel(10, jpnStudentMarks, jpnSlider.right);
        jpnSelect.nextPanel(1, pnlSelect, jpnSelect.right);
    }//GEN-LAST:event_btnViewStudentsMouseClicked

    private void btnSearchStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchStudentMouseClicked
        // TODO add your handling code here:
        btnSearchStudentPressed();
        jpnSlider.nextPanel(10, jpnSearchStudent, jpnSlider.right);
        jpnSelect.nextPanel(1, pnlSelect, jpnSelect.right);
    }//GEN-LAST:event_btnSearchStudentMouseClicked

    private void btnStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStudentMouseClicked
        // TODO add your handling code here:
        btnStudentPressed();
        jpnSlider.nextPanel(10, jpnStudent, jpnSlider.right);
        jpnSelect.nextPanel(1, pnlEmpty, jpnSelect.right);
    }//GEN-LAST:event_btnStudentMouseClicked

    private void tblSearchStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSearchStudentMouseClicked
        // TODO add your handling code here:
        int row = tblSearchStudent.getSelectedRow();
        String idno = tblSearchStudent.getModel().getValueAt(row, 0).toString();
        String lastName = tblSearchStudent.getModel().getValueAt(row, 1).toString();
        String names = tblSearchStudent.getModel().getValueAt(row, 2).toString();
        String subject = tblSearchStudent.getModel().getValueAt(row, 3).toString();
        String term1 = tblSearchStudent.getModel().getValueAt(row, 4).toString();
        String term2 = tblSearchStudent.getModel().getValueAt(row, 5).toString();
        String term3 = tblSearchStudent.getModel().getValueAt(row, 6).toString();
        
        if(idno.replace(" ", "").length() > 0){
            txtIDNo.setText(idno);
            txtLastName.setText(lastName);
            txtNames.setText(names);
            txtYear.setText(selectedYear+"");
            txtSubject.setText(subject);
            txtTerm1.setText(term1);
            txtTerm2.setText(term2);
            txtTerm3.setText(term3);
            btnStudentPressed();
            jpnSlider.nextPanel(10, jpnStudent, jpnSlider.right);
        }else{
           alert.notify("ID number can't be empty.", 0);
        }
    }//GEN-LAST:event_tblSearchStudentMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Functions.infoMessage("Search student by lastname or firstnames only.");
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txtSearchStudentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchStudentKeyReleased
        // TODO add your handling code here:
        String search = txtSearchStudent.getText();
        if(search.length() > 1){
            getControlData();
            SearchStudent(matricBoImpl.GetStudentsForALecture(selectedYear, selectedSubject, search));
        }
    }//GEN-LAST:event_txtSearchStudentKeyReleased

    private void btnSaveMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveMarksActionPerformed
        // TODO add your handling code here:
        try{
            int year = Integer.parseInt(txtYear.getText());
            String subject = txtSubject.getText();
            String IDNo = txtIDNo.getText();            
            String term1 = txtTerm1.getText();
            String term2 = txtTerm2.getText();
            String term3 = txtTerm3.getText();
            String lastName = txtLastName.getText();
            String names = txtNames.getText();
            
            if(subject.length() > 0){
                Matric m = new Matric();
                m.setMatric(IDNo, year, subject, term1, term2, term3);
                m.setLastName(lastName);
                m.setFirstName(names);
                int editMark = matricBoImpl.LectureUpdateStudentMark(m,loggedInUser.getPersonId());
                if(editMark > 0){
                    fillSubjectsToAuthorize();
                    alert.notify("Mark update lodged.", 1);
               }else{
                    alert.notify("No changes saved.", 0);
               }
            }else{
                Functions.warningMessage("Subject field must not be emtpty.");
            }
        }catch(NumberFormatException ex){
            Functions.errorMessage("Required fields must not be empty.\n"
                    + "Error: "+ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnSaveMarksActionPerformed

    private void btnUpdateMarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMarksActionPerformed
        // TODO add your handling code here:
        if(tblModelStudentMarks.getRowCount() > 0){
            int row = 0;
            List<HashMap<String,Object>> marks = new ArrayList<>();
            while(row < tblModelStudentMarks.getRowCount()){
                String idno =  tblModelStudentMarks.getValueAt(row, 0).toString();
                String lastName =  tblModelStudentMarks.getValueAt(row, 1).toString();
                String names =  tblModelStudentMarks.getValueAt(row, 2).toString();
                String year =  tblModelStudentMarks.getValueAt(row, 3).toString();
                String subject =  tblModelStudentMarks.getValueAt(row, 4).toString();
                String term1 =  tblModelStudentMarks.getValueAt(row, 5).toString();
                String term2 =  tblModelStudentMarks.getValueAt(row, 6).toString();
                String term3 =  tblModelStudentMarks.getValueAt(row, 7).toString();

                HashMap<String,Object> mark = new HashMap<>(5);
                mark.put("idno", idno);
                mark.put("lastName", lastName);
                mark.put("names", names);
                mark.put("subject", subject);
                mark.put("year", year);
                mark.put("term1", term1);
                mark.put("term2", term2);
                mark.put("term3", term3);
                marks.add(mark);
                row++;
            }
            int saved = matricBoImpl.LectureUploadMatricMarks(marks, loggedInUser.getPersonId());
            if(saved > 0){
                fillSubjectsToAuthorize();
                alert.notify("An update got lodged.", 1);
            }else{
                alert.notify("No changes saved.", 0);
            }
        }else{
            alert.notify("There are no marks to update.", 0);
        }
    }//GEN-LAST:event_btnUpdateMarksActionPerformed

    private void btnAuthorizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAuthorizeMouseClicked
        // TODO add your handling code here:
        btnAuthorizePressed();
        jpnSlider.nextPanel(10, jpnAuthorize, jpnSlider.right);
        jpnSelect.nextPanel(1, pnlEmpty, jpnSelect.right);
    }//GEN-LAST:event_btnAuthorizeMouseClicked

    private void rbtnCheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCheckAllActionPerformed
        // TODO add your handling code here:
        displayMarksToAuthorize(true);
    }//GEN-LAST:event_rbtnCheckAllActionPerformed

    private void rbtnUncheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnUncheckAllActionPerformed
        // TODO add your handling code here:
        displayMarksToAuthorize(false);
    }//GEN-LAST:event_rbtnUncheckAllActionPerformed

    private void tblSubjectsToAuthorizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubjectsToAuthorizeMouseClicked
        // TODO add your handling code here:
        int row = tblSubjectsToAuthorize.getSelectedRow();
        rowOfSubjectsToAuthorize = row;
        int staffNo = loggedInUser.getPersonId();
        String subject = tblSubjectsToAuthorize.getModel().getValueAt(row, 0).toString();
        staffSubjectClicked = new StaffSubject();
        staffSubjectClicked.setStaffSubject(staffNo, subject, selectedYear, "");
        fillMarksToAuthorize(matricBoImpl.GetMarksToAuthorize(staffNo, selectedYear, subject));
    }//GEN-LAST:event_tblSubjectsToAuthorizeMouseClicked

    private void btnRevertUpdatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevertUpdatesActionPerformed
        // TODO add your handling code here:
        if(tblModelMarksToAuthorize.getRowCount() > 0){
            if(staffSubjectClicked != null){
                startProcess("Reverting updates..");
                int row = 0;
                List<HashMap<String,Object>> marks = new ArrayList<>();
                while(row < tblModelMarksToAuthorize.getRowCount()){
                    boolean checked = (tblModelMarksToAuthorize.getValueAt(row, 8)!= null)? 
                            (boolean)tblModelMarksToAuthorize.getValueAt(row, 8):false;
                    if(checked){
                        String idno =  tblModelMarksToAuthorize.getValueAt(row, 0).toString();
                        String subject =  tblModelMarksToAuthorize.getValueAt(row, 3).toString();
                        String year =  tblModelMarksToAuthorize.getValueAt(row, 4).toString();

                        HashMap<String,Object> mark = new HashMap<>(5);
                        mark.put("idno", idno);
                        mark.put("subject", subject);
                        mark.put("year", year);
                        marks.add(mark);
                    }
                    row++;
                }
                int reverted = matricBoImpl.RevertMarkUpdate(marks, staffSubjectClicked.getPersonId());
                if(reverted > 0){
                    alert.notify("Selected marks got reverted.", 1);
                    endProgress();
                }else{
                    alert.notify("No marks got reverted.", 0);
                    endProgress();
                }
                fillMarksToAuthorize(matricBoImpl.GetMarksToAuthorize(staffSubjectClicked.getPersonId(), 
                                                                    staffSubjectClicked.getSyear(),
                                                                    staffSubjectClicked.getSubjectCode()));
            }else{
                Functions.infoMessage("Select marks to revert first.");
            }
        }else{
            alert.notify("No marks selected for revert.", 0);
        }
    }//GEN-LAST:event_btnRevertUpdatesActionPerformed

    private void jcbSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSubjectActionPerformed
        // TODO add your handling code here:
        if(jcbSubjectsFilled){
            selectedSubject = jcbSubject.getSelectedItem().toString();
            ViewStudent(matricBoImpl.GetMatricBySubjectAndYear(selectedYear, selectedSubject));
        }
    }//GEN-LAST:event_jcbSubjectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        Functions.setFormTheme();
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MatricLectureFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.molorane.college.view.Controls.Alert alert;
    private javax.swing.JPanel btnAuthorize;
    private javax.swing.JButton btnRevertUpdates;
    private javax.swing.JButton btnSaveMarks;
    private javax.swing.JPanel btnSearchStudent;
    private javax.swing.JPanel btnStudent;
    private javax.swing.JButton btnUpdateMarks;
    private javax.swing.JPanel btnViewStudents;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JComboBox<String> jcbSubject;
    private javax.swing.JPanel jpnAuthorize;
    private javax.swing.JPanel jpnControls;
    private javax.swing.JPanel jpnProgress;
    private javax.swing.JPanel jpnSearchStudent;
    private com.molorane.college.custom.JPanelSliding jpnSelect;
    private javax.swing.JPanel jpnSideMenu;
    private com.molorane.college.custom.JPanelSliding jpnSlider;
    private javax.swing.JPanel jpnStudent;
    private javax.swing.JPanel jpnStudentMarks;
    private javax.swing.JLabel lblNeedAuthorization;
    private javax.swing.JLabel lblProgress;
    private javax.swing.JLabel lblYear;
    private javax.swing.JPanel pnlEmpty;
    private javax.swing.JPanel pnlSelect;
    private javax.swing.JRadioButton rbtnCheckAll;
    private javax.swing.JRadioButton rbtnUncheckAll;
    private javax.swing.JTable tblMarksToAuthorize;
    private javax.swing.JTable tblSearchStudent;
    private javax.swing.JTable tblStudentsMarks;
    private javax.swing.JTable tblSubjectsToAuthorize;
    private com.molorane.college.view.Controls.TitlePnl title;
    private javax.swing.JTextField txtIDNo;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtNames;
    private javax.swing.JTextField txtSearchStudent;
    private javax.swing.JTextField txtSubject;
    private javax.swing.JTextField txtTerm1;
    private javax.swing.JTextField txtTerm2;
    private javax.swing.JTextField txtTerm3;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}

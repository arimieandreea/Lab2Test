package controller;

import junit.framework.TestCase;
import model.Laboratory;
import model.Student;
import repository.FileDataPersistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 23.03.2018.
 */
public class LaboratoriesControllerTest extends TestCase {

    LaboratoriesController ctrl;
    public void setUp() throws Exception {
        super.setUp();
        ctrl=new LaboratoriesController("testStudents.txt", "testLaboratories.txt");

    }

    public void tearDown() throws Exception {
    }


    public void testSaveStudent1() throws Exception {

        //adding an existing student
        Student s1 = new Student("aaaa1111", "Arimie Andreea", 932);
        boolean r1 = ctrl.saveStudent(s1);
        assertEquals(r1, false);
    }


    public void testSaveStudent2() throws Exception {

        //group
        Student s2=new Student("aaaa2222","Arimie Andreea",1090);
        boolean r2=ctrl.saveStudent(s2);
        assertEquals(r2,false);
    }

    public void testSaveStudent3() throws Exception {

        //regNr
        Student s3=new Student("aaaa222","Arimie Andreea",3);
        boolean r3=ctrl.saveStudent(s3);
        assertEquals(r3,false);

    }

    public void testSaveStudent4() throws Exception {

        //name(length!=2)
        Student s4=new Student("aaaa3333","Arimie ",3);
        boolean r4=ctrl.saveStudent(s4);
        assertEquals(r4,false);

    }

    public void testSaveStudent5() throws Exception {

        //regNr empty
        Student s5=new Student("","Arimie ",3);
        boolean r5=ctrl.saveStudent(s5);
        assertEquals(r5,false);


    }









    public void testSaveStudent() throws Exception {

        //adding an existing student
        Student s1=new Student("aaaa1111","Arimie Andreea",932);
        boolean r1=ctrl.saveStudent(s1);
        assertEquals(r1,true);

        //group
        Student s2=new Student("aaaa2222","Arimie Andreea",1090);
        boolean r2=ctrl.saveStudent(s2);
        assertEquals(r2,false);

        //regNr
        Student s3=new Student("aaaa222","Arimie Andreea",3);
        boolean r3=ctrl.saveStudent(s3);
        assertEquals(r3,false);

        //name(length!=2)
        Student s4=new Student("aaaa3333","Arimie ",3);
        boolean r4=ctrl.saveStudent(s4);
        assertEquals(r4,false);

        //regNr empty
        Student s5=new Student("","Arimie ",3);
        boolean r5=ctrl.saveStudent(s5);
        assertEquals(r5,false);


    }

    public void testSaveLaboratory() throws Exception {
        Laboratory l1=new Laboratory(4, "29/03/2018" ,4 ,"nnop1998");
        boolean r1=ctrl.saveLaboratory(l1);
        assertEquals(r1,false);

        //date
        Laboratory l2=new Laboratory(5, "29/03/2017" ,4 ,"nnop1998");
        boolean r2=ctrl.saveLaboratory(l2);
        assertEquals(r2,false);

        //nrLab<1
        Laboratory l3=new Laboratory(-5, "29/03/2018" ,4 ,"nnop1998");
        boolean r3=ctrl.saveLaboratory(l3);
        assertEquals(r3,false);

        //pbnr
        Laboratory l4=new Laboratory(5, "29/03/2018" ,19 ,"nnop1998");
        boolean r4=ctrl.saveLaboratory(l4);
        assertEquals(r4,false);

    }


    //adding an existing lab (with the same number)
    public void testSaveLaboratory1() throws Exception{
        Laboratory l1=new Laboratory(4, "28/04/2018" ,4 ,"nnop1998");
        boolean r1=ctrl.saveLaboratory(l1);
        assertEquals(r1,false);
    }

    //date of the lab is wrong
    public void testSaveLaboratory2() throws Exception{
        Laboratory l2=new Laboratory(5, "29/03/2017" ,4 ,"nnop1998");
        boolean r2=ctrl.saveLaboratory(l2);
        assertEquals(r2,false);
    }

    //nrLab<1
    public void testSaveLaboratory3() throws Exception{

        Laboratory l3=new Laboratory(-5, "30/04/2018" ,4 ,"nnop1998");
        boolean r3=ctrl.saveLaboratory(l3);
        assertEquals(r3,false);
    }

    //pbNr
    public void testSaveLaboratory4() throws Exception{

        Laboratory l4=new Laboratory(5, "29/03/2018" ,19 ,"nnop1998");
        boolean r4=ctrl.saveLaboratory(l4);
        assertEquals(r4,false);
    }





    //wrong grade
    public void testAddGrade1() throws Exception {
        boolean r1=ctrl.addGrade("aaie1856","2",12);
        assertEquals(r1,false);
    }

    public void testAddGrade2() throws  Exception{
        List<Student> passedStudents=new ArrayList<Student>();
        List<Student> passedStudents1=new ArrayList<Student>();
        passedStudents=ctrl.passedStudents();
        Student s1=new Student("yyyy9999","Vancea Vlad",989);
        boolean r1=passedStudents.contains(s1);
        assertEquals(r1,false);
        ctrl.addGrade("yyyy9999","3",8);
        passedStudents1=ctrl.passedStudents();
        boolean r2=passedStudents1.contains(s1);
        //failed(should be true)
        assertEquals(r2,false);

    }

    public void testPassedStudents() throws Exception {
        List<Student> passedStudents=new ArrayList<Student>();
        passedStudents=ctrl.passedStudents();
        Student s1=new Student("yyyy9999","Vancea Vlad",989);
        Student s2=new Student("aaie1856","as nb",932);
        Student s3=new Student("asdf1234","as we",932);
        Student s4=new Student("nnop1998","Monica Iovan",945);
        Student s5=new Student("nnop0000","Monica Der",930);


        //student having grade<5
        boolean r1=passedStudents.contains(s1);
        assertEquals(r1,false);

        boolean r2=passedStudents.contains(s2);
        assertEquals(r2,true);

        boolean r3=passedStudents.contains(s3);
        assertEquals(r3,true);

        //when student has not participated to the lab=>false
        boolean r4=passedStudents.contains(s4);
        assertEquals(r4,false);

        //when student is not in our database
        boolean r5=passedStudents.contains(s5);
        assertEquals(r4,false);

    }

    public void testIntegrationBigBang() throws  Exception{
        //adding an existing student
        Student s1=new Student("aaaa1111","Arimie Andreea",932);
        boolean r1=ctrl.saveStudent(s1);
        assertEquals(r1,true);

        //group
        Student s2=new Student("aaaa2222","Arimie Andreea",1090);
        boolean r2=ctrl.saveStudent(s2);
        assertEquals(r2,false);

        //regNr
        Student s3=new Student("aaaa222","Arimie Andreea",3);
        boolean r3=ctrl.saveStudent(s3);
        assertEquals(r3,false);

        //name(length!=2)
        Student s4=new Student("aaaa3333","Arimie ",3);
        boolean r4=ctrl.saveStudent(s4);
        assertEquals(r4,false);

        //regNr empty
        Student s5=new Student("","Arimie ",3);
        boolean r5=ctrl.saveStudent(s5);
        assertEquals(r5,false);

        Laboratory l1=new Laboratory(4, "28/04/2018" ,4 ,"nnop1998");
        boolean rl1=ctrl.saveLaboratory(l1);
        assertEquals(rl1,false);


        List<Student> passedStudents=new ArrayList<Student>();
        passedStudents=ctrl.passedStudents();
        Student sp1=new Student("yyyy9999","Vancea Vlad",989);
        Student sp2=new Student("aaie1856","as nb",932);
        Student sp3=new Student("asdf1234","as we",932);
        Student sp4=new Student("nnop1998","Monica Iovan",945);
        Student sp5=new Student("nnop0000","Monica Der",930);


        //student having grade<5
        boolean rp1=passedStudents.contains(sp1);
        assertEquals(rp1,false);

        boolean rp2=passedStudents.contains(sp2);
        assertEquals(rp2,true);

        boolean rp3=passedStudents.contains(sp3);
        assertEquals(rp3,true);

        //when student has not participated to the lab=>false
        boolean rp4=passedStudents.contains(sp4);
        assertEquals(rp4,false);

        //when student is not in our database
        boolean rp5=passedStudents.contains(sp5);
        assertEquals(rp5,false);


    }

    public void incrementalAB() throws Exception{
        //adding an existing student
        Student s1=new Student("aaaa1111","Arimie Andreea",932);
        boolean r1=ctrl.saveStudent(s1);
        assertEquals(r1,true);

        //group
        Student s2=new Student("aaaa2222","Arimie Andreea",1090);
        boolean r2=ctrl.saveStudent(s2);
        assertEquals(r2,false);

        //regNr
        Student s3=new Student("aaaa222","Arimie Andreea",3);
        boolean r3=ctrl.saveStudent(s3);
        assertEquals(r3,false);

        //name(length!=2)
        Student s4=new Student("aaaa3333","Arimie ",3);
        boolean r4=ctrl.saveStudent(s4);
        assertEquals(r4,false);

        //regNr empty
        Student s5=new Student("","Arimie ",3);
        boolean r5=ctrl.saveStudent(s5);
        assertEquals(r5,false);

        Laboratory l1=new Laboratory(4, "28/04/2018" ,4 ,"nnop1998");
        boolean rl1=ctrl.saveLaboratory(l1);
        assertEquals(rl1,false);
    }

    public void incrementalABC() throws Exception{
        //adding an existing student
        Student s1=new Student("aaaa1111","Arimie Andreea",932);
        boolean r1=ctrl.saveStudent(s1);
        assertEquals(r1,true);

        //group
        Student s2=new Student("aaaa2222","Arimie Andreea",1090);
        boolean r2=ctrl.saveStudent(s2);
        assertEquals(r2,false);

        //regNr
        Student s3=new Student("aaaa222","Arimie Andreea",3);
        boolean r3=ctrl.saveStudent(s3);
        assertEquals(r3,false);

        //name(length!=2)
        Student s4=new Student("aaaa3333","Arimie ",3);
        boolean r4=ctrl.saveStudent(s4);
        assertEquals(r4,false);

        //regNr empty
        Student s5=new Student("","Arimie ",3);
        boolean r5=ctrl.saveStudent(s5);
        assertEquals(r5,false);

        Laboratory l1=new Laboratory(4, "28/04/2018" ,4 ,"nnop1998");
        boolean rl1=ctrl.saveLaboratory(l1);
        assertEquals(rl1,false);


        List<Student> passedStudents=new ArrayList<Student>();
        passedStudents=ctrl.passedStudents();
        Student sp1=new Student("yyyy9999","Vancea Vlad",989);
        Student sp2=new Student("aaie1856","as nb",932);
        Student sp3=new Student("asdf1234","as we",932);
        Student sp4=new Student("nnop1998","Monica Iovan",945);
        Student sp5=new Student("nnop0000","Monica Der",930);


        //student having grade<5
        boolean rp1=passedStudents.contains(sp1);
        assertEquals(rp1,false);

        boolean rp2=passedStudents.contains(sp2);
        assertEquals(rp2,true);

        boolean rp3=passedStudents.contains(sp3);
        assertEquals(rp3,true);

        //when student has not participated to the lab=>false
        boolean rp4=passedStudents.contains(sp4);
        assertEquals(rp4,false);

        //when student is not in our database
        boolean rp5=passedStudents.contains(sp5);
        assertEquals(rp5,true);
    }





}
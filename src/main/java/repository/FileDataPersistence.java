package repository;

import model.Laboratory;
import model.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDataPersistence {
    private String file;

    public FileDataPersistence(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }



    public List<Laboratory> geLaboratoriesList() throws NumberFormatException,
            IOException,ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<Laboratory> allLaboratoriesList = new ArrayList<Laboratory>();

        String line = null;

        while ((line = reader.readLine()) != null) {
            if(line.equals("")==false) {
                String[] temp = line.split(" ");
                Laboratory lab = new Laboratory(Integer.valueOf(temp[0]), temp[1],Integer.valueOf(temp[2]),
                                                temp[4]);
                allLaboratoriesList.add(lab);
            }
        }
        return allLaboratoriesList;
    }



    public List<Student> getStudentsList() throws NumberFormatException,
            IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<Student> allStudentsList = new ArrayList<Student>();

        String line = null;

        while ((line = reader.readLine()) != null) {
            if(line.equals("")==false) {
                String[] temp = line.split(" ");
                Student student = new Student(temp[0], temp[1] + temp[2], Integer.valueOf(temp[3]));

                allStudentsList.add(student);
            }
        }

        return allStudentsList;
    }


    public boolean existLab(int nr) {
        List<Laboratory> allLaboratoriesList=new ArrayList<Laboratory>();
        try {
            allLaboratoriesList = this.geLaboratoriesList();
        }
        catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(ParseException e){
            System.out.println(e.getMessage());
        }

        for (Laboratory l : allLaboratoriesList) {
            if(l.getNumber()==nr){
                return false;
            }
        }
        return true;
    }





    public boolean existStudent(String regNr) {
        List<Student> allStudentsList=new ArrayList<Student>();
        try {
            allStudentsList =  this.getStudentsList();
        }
        catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        for (Student s : allStudentsList) {
            if(s.getRegNumber().equals(regNr)==true){
                return false;
            }
        }
        return true;
    }

    public void saveStudent(Student student) throws Exception{
        if(this.existStudent(student.getRegNumber())==false){
            throw new Exception("This student already is in out database");
        }
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(student.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // No check if laboratory is unique
    public void saveLaboratory(Laboratory laboratory) throws Exception {
        if(this.existLab(laboratory.getNumber())==false){
            throw new Exception("This lab already is in out database");
        }
        BufferedWriter writter;
        try {
            writter = new BufferedWriter(new FileWriter(file, true));
            writter.write(laboratory.toString() + "\n");
            writter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Ambiguous what field of "Student" should be passed
    public void addGrade(String student, String labNumber, float grade)
            throws IOException, NumberFormatException, ParseException {
        File fileA = new File(file);
        File fileB = new File("temp");

        BufferedReader reader = new BufferedReader(new FileReader(fileA));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileB));

        String line;

        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(" ");
            String fileLabNumber = temp[0];
            String fileStudentNumber = temp[4];
            if (fileLabNumber.equals(labNumber) && fileStudentNumber.equals(student)) {
                Laboratory laboratory = new Laboratory(
                        Integer.valueOf(temp[0]), temp[1],
                        Integer.valueOf(temp[2]), temp[4]);
                laboratory.setGrade(grade);
                writer.write(laboratory.toString() + "\n");
            } else {
                writer.write(line + "\n");
                System.out.println("Does not exist this comb of student and lab");
            }
        }
        writer.close();
        reader.close();

        fileA.delete();
        fileB.renameTo(fileA);
    }

    public Map<String, List<Laboratory>> getLaboratoryMap()
            throws NumberFormatException, IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Map<String, List<Laboratory>> laboratoryMap = new HashMap<String, List<Laboratory>>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(" ");
            Laboratory laboratory = new Laboratory(Integer.valueOf(temp[0]),
                                                   temp[1], Integer.valueOf(temp[2]), Float.valueOf(temp[3]),
                                                   temp[4]);
            if (laboratoryMap.get(laboratory.getStudentRegNumber()) == null) {
                List<Laboratory> laboratoryList = new ArrayList<Laboratory>();
                laboratoryList.add(laboratory);
                laboratoryMap.put(laboratory.getStudentRegNumber(),
                                  laboratoryList);
            } else {
                laboratoryMap.get(laboratory.getStudentRegNumber()).add(
                        laboratory);
            }
        }
        return laboratoryMap;
    }


} 
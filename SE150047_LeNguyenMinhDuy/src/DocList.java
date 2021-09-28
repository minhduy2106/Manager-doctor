import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class DocList extends Vector<Doctor> {
    static Scanner sc = new Scanner(System.in);


    //Menu
    public static int Menu(){
        int choice;
        System.out.println("=========Menu=========");
        System.out.println("1. Add new a Doctor");
        System.out.println("2. Update a Doctor");
        System.out.println("3. Delete a Doctor");
        System.out.println("4. Search Doctors by character");
        System.out.println("5. Out the program");
        System.out.println("======================");
        System.out.print("Enter your choice :");
        choice = Validate.checkIn(1,5);
        return choice;
    }

    // display 1 doctor
    public static void displayDoctor(Doctor doctor) {
        System.out.printf("%-10s%-10s%-25s%-25d\n", doctor.getCode(), doctor.getName(),
                doctor.getSpecialization(), doctor.getAvailability());
    }

    // display
    public static void displayAll(ArrayList<Doctor> doctors) {
        if (doctors.size() == 0) {
            System.out.println("Empty List");
        } else {
            System.out.printf("%-10s%-10s%-25s%-25s\n", "Code", "Name", "Specialization", "Availability");
            System.out.println("-----------------------------------------------------------------------");
            for (var doctor : doctors) {
                displayDoctor(doctor);
            }
        }
    }

    // Add doctor
    public static void addDoctor(ArrayList<Doctor> doctors) {
        System.out.print("Enter code: ");
        String code = Validate.checkString();
        if (!Validate.checkCode(doctors, code)) {
            System.out.println("The doctor has : " + code + " is not exist.");
            return;
        } else {
            System.out.print("Enter name: ");
            String name = Validate.checkString();
            System.out.print("Enter Specialization: ");
            String specialization = Validate.checkString();
            System.out.print("Enter Availability: ");
            int availability = Validate.checkInt();
            doctors.add(new Doctor(code, name, specialization, availability));
            System.out.println("Add Successful");
        }
    }

    //update Doctor
    public static void updateDoctor(ArrayList<Doctor> doctors){
        System.out.println("Enter Code of Doctor : ");
        String id = Validate.checkString();

        if (Validate.checkCode(doctors,id)){
            System.out.println("The doctor has : " + id + " is not exist.");
            return;
        }

        Doctor doctor = getDoctorByCode(doctors, id);

        String oldName = doctor.getName();
        System.out.println("Old name : " + oldName + ", change to : ");
        String name = Validate.checkString();
        doctor.setName(name);

        String OldSpecialization = doctor.getSpecialization();
        System.out.println("Old specialization : " + OldSpecialization + ", change to : ");
        String specialization = Validate.checkString();
        doctor.setSpecialization(specialization);

        int oldAvai = doctor.getAvailability();
        System.out.println("Old Availability : " + oldAvai + ", change to : ");
        int avai = Validate.checkInt();
        doctor.setAvailability(avai);
    }

    // remove a doctor
    public static void deleteDoctor(ArrayList<Doctor> doctors) {
        if (doctors.size() == 0 ) {
            System.out.println("Empty List");
            return;
        }
        System.out.print("Enter code you want to remove: ");
        String id = Validate.checkString();
        Doctor doctor = getDoctorByCode(doctors, id);
        if (Validate.checkCode(doctors,id) ) {
            System.out.println("No found doctor");
            return;
        } else {
            doctors.remove(doctor);
        }
        System.out.println("Remove Successfully");
    }

    // get doctor from code
    public static Doctor getDoctorByCode(ArrayList<Doctor> doctors, String code) {
        for (Doctor doctor : doctors) {
            if (doctor.getCode().equalsIgnoreCase(code)) {
                return doctor;
            }
        }
        return null;
    }

    // search doctor
    public static void searchDoctor(ArrayList<Doctor> doctors){
        if (doctors.size()==0){
            System.out.println("Empty list");
            return;
        }
        System.out.println("Enter name : ");
        String id = Validate.checkString();
        ArrayList<Doctor> found = getListByName(doctors, id);
        displayAll(found);
    }

    // search by name
    public static ArrayList<Doctor> getListByName (ArrayList<Doctor> doctors, String name){
        ArrayList<Doctor> doctorFoundByName = new ArrayList<>();
        for (Doctor doctor:doctors) {
            if (doctor.getName().contains(name)) {
                doctorFoundByName.add(doctor);
            }
        }
        return doctorFoundByName;
    }


    //write file
    public static void saveToFile(ArrayList<Doctor> doctors, String fName) {
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < doctors.size(); i++) {
                pw.println(doctors.get(i).getCode() + "," + doctors.get(i).getName() + "," +
                        doctors.get(i).getSpecialization() + "," + doctors.get(i).getAvailability());
            }
            fw.close();
            pw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void readFromFile(ArrayList<Doctor> doctors, String fName) {
        try {
            File f = new File(fName);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String detail;
            while ((detail = br.readLine())!= null){
                StringTokenizer stk = new StringTokenizer(detail, ",");
                String code = stk.nextToken();
                String name = stk.nextToken();
                String specialization = stk.nextToken();
                int availability = Integer.parseInt(stk.nextToken());
                Doctor dt = new Doctor(code, name, specialization, availability);
                doctors.add(dt);
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

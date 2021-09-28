import javax.print.Doc;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Doctor> doctors = new ArrayList<>();
        int choice;
        String fName = "doctor.txt";

        DocList.readFromFile(doctors,fName);

        do {
            choice=DocList.Menu();
            switch (choice){
                case 1 :
                    DocList.addDoctor(doctors);
                    break;
                case 2:
                    DocList.displayAll(doctors);
                    DocList.updateDoctor(doctors);
                    break;
                case 3:
                    DocList.displayAll(doctors);
                    DocList.deleteDoctor(doctors);
                    break;
                case 4:
                    DocList.displayAll(doctors);
                    DocList.searchDoctor(doctors);
                    break;
                case 5:
                    DocList.saveToFile(doctors,fName);
                    System.out.println("Thanks");
                    return;
            }
        }while (choice < 5 && choice >0);

    }
}

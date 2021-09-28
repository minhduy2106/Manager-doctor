import java.util.ArrayList;
import java.util.Scanner;

public class Validate {

    static Scanner sc = new Scanner(System.in);

    public static String checkString(){
        boolean check = false;
        String str = "";
        do{
            try{
                str = sc.nextLine().trim();
                if(str.isEmpty()) throw new Exception();
                check = false;
            }catch (Exception e){
                System.out.println("Input need to be not empty");
                System.out.println("==========================");
                System.err.print("Input try again : ");
            }
        }while (check);
        return str;
    }

    public static int checkInt(){
        while (true){
            try{
                int result = Integer.parseInt(sc.nextLine());
                return result;
            }catch (NumberFormatException e ){
                System.out.println();
                System.err.println("Please input number integer");
                System.out.print("Enter again: ");
            }
        }
    }

    public static boolean checkCode(ArrayList<Doctor> doctors, String code) {
        for (Doctor doctor : doctors) {
            if (code.equalsIgnoreCase(doctor.getCode())) {
                return false;
            }
        }
        return true;
    }

    public static int checkIn(int min, int max){
        while (true){
            try{
                int num = Integer.parseInt(sc.nextLine().trim());
                if(num < min || num > max) throw new Exception();
                return num;
            }catch (Exception e){
                System.out.println("Invalid input");
                System.out.println("Enter again : ");
            }
        }
    }
}

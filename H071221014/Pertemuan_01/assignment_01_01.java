import java.util.Scanner;

public class assignment_01_01 {

    public static void main(String[] args) {

        Scanner inp = new Scanner(System.in);
        System.out.print("Nim : ");
        String nim = inp.nextLine();
        System.out.print("Soal : No.");
        int angka = Integer.parseInt(nim.substring(nim.length()-3));
        if (angka%7 == 0) {
            System.out.println(7);

        } else{
            System.out.println(angka%7);
        }
        inp.close();

    }
}
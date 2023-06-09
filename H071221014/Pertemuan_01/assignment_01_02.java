import java.util.Scanner;

public class assignment_01_02 {
    
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        try{
        System.out.print("Masukkan jumlah bilangan: ");
        int n = inp.nextInt();
        
        int desimal = 0;
        int bulat = 0;
        
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan bilangan ke-" + (i+1) + ": ");
            double num = inp.nextDouble();
            
            if (num % 1 == 0) {
                bulat++;
            } else {
                desimal++;
            }
        }
        
        System.out.println(bulat + " Bilangan Bulat");
        System.out.println(desimal + " Bilangan Desimal");
        inp.close();
    } catch (Exception e) {
        System.out.println("Hanya bisa menerima angka");
    }
    }
}

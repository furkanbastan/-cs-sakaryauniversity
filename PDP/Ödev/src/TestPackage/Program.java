package TestPackage;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        int numberArray[] = GetTheNumbers();  //Konsoldan boşluklarla ayrılmış sayıları alıyorum.
        Koloni[] koloniler = GetKoloniler(numberArray);

        Oyun oyun = new Oyun();
        oyun.OyunuBaslat(koloniler);
        
    }

    public static int[] GetTheNumbers() {
        Scanner scanner = new Scanner(System.in);
        String[] numbers;
        int[] numberArray;
        String input;
        
        do {
            System.out.print("Sayıları girin (boşluklarla ayrılmış): ");
            input = scanner.nextLine();

            numbers = input.split(" ");
            numberArray = new int[numbers.length];

            try {
                for (int i = 0; i < numbers.length; i++) {
                    numberArray[i] = Integer.parseInt(numbers[i]);
                }
            } catch(Exception e){
                System.out.print("Lütfen doğru düzgün giriniz !!!!!!!!!!!!!!!!! ");
                numbers = null;
                numberArray = null;
                input = null;
                continue;
            }

            scanner.close();
            return numberArray;

        } while (true);
    }

    public static Koloni[] GetKoloniler(int[] numberArray) {
        Koloni[] result = new Koloni[numberArray.length];

        for (int i = 0; i < numberArray.length; i++) {
            result[i] = new Koloni(numberArray[i], numberArray[i]*numberArray[i]);
        }
        return result;
    }
}
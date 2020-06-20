
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PersonID {
    public static boolean sprawdz(String pesel){
        // zakładamy tablicę z wagami
        int[] wagi = {1, 3, 7, 9, 1, 3, 7 ,9 ,1 ,3};

        // sprawdzamy długość PESEL'a, jeśli nie jest to 11 zwracamy false
        if (pesel.length() != 11) return false;

        // zakładamy zmienną będącą sumą kontrolną
        int suma = 0;

        // liczymy w pętli sumę kontrolną przemnażając odpowiednie
        // cyfry z PESEL'a przez odpowiednie wagi
        for (int i = 0; i < 10; i++)
            suma += Integer.parseInt(pesel.substring(i, i+1)) * wagi[i];

        // pobieramy do zmiennej cyfraKontrolna wartość ostatniej cyfry z PESEL'a
        int cyfraKontrolna = Integer.parseInt(pesel.substring(10, 11));

        // obliczamy cyfrę kontrolną z sumy (najpierw modulo 10 potem odejmujemy 10 i jeszcze raz modulo 10)
        suma %= 10;
        suma = 10 - suma;
        suma %= 10;

        // zwracamy wartość logiczną porównania obliczonej i pobranej cyfry kontrolnej
        return (suma == cyfraKontrolna);

    }

    private static int typing;

    public static void main(String[] args){


        Scanner sc = new Scanner(System.in);
        System.out.println("Choose 1 - save new person, Choose 2 - save to file and exit");


        ArrayList<String> cities = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> surnames = new ArrayList<String>();
        ArrayList<String> ssnids = new ArrayList<String>();




        loop: while(sc.hasNextInt()){
            typing = sc.nextInt();
            //variable
        String city, personID, name, surname;
        int personIDint;

        // patern
        Pattern letters = Pattern.compile("[A-Za-z]{3,29}");
        Pattern ssn1 = Pattern.compile("\\d{11}");

        //Loop Case
        switch(typing){
            case 0:
                break loop;
            case 1:

            // save input to variable
            System.out.println("Enter your city");
            while (!sc.hasNext(letters)) {
                System.out.println("Cannot be a number!");
                sc.next();
            }
            city = sc.next();


            System.out.println("Enter your name");
            while (!sc.hasNext(letters)) {
                System.out.println("Cannot be a number!");
                sc.next();
            }
            name = sc.next();


            System.out.println("Enter your surname");
            while (!sc.hasNext(letters)) {
                System.out.println("Cannot be a number!");
                sc.next();
            }
            surname = sc.next();


            while (!sc.hasNext(ssn1)) {                     // ZAMIAST TEGO CHCĘ WYKORZYSTAĆ FUNKCJĘ Boolen sprawdz
                System.out.println("That's not a SSN!");
                sc.next();
            }
            personID = sc.next();


            int index = ssnids.indexOf(personID);
                System.out.println(index);
                System.out.println(cities);
            if (index < 0 ){
                cities.add(city);
                names.add(name);
                surnames.add(surname);
                ssnids.add(personID);
            } else if (index >= 0){
                cities.set(index, city);
                names.set(index, name);
                surnames.set(index, surname);
            }

            System.out.println("Choose 1 - save new person, Choose 2 - save to file and exit");
            break;
        case 2:
            System.out.println("You choosed 2");

            // save file
            String savetofile = "";
            int longlist = cities.size();
            int licznik = 0;
                if (!cities.isEmpty()) {
                    while (licznik<longlist) {
                        savetofile += cities.get(licznik) + "\t" + names.get(licznik) + "\t" + surnames.get(licznik) + "\t" + ssnids.get(licznik) + "\r\n";
                        licznik++;
                    }
                }

            try (FileWriter writer = new FileWriter("output.txt");
                 BufferedWriter bw = new BufferedWriter(writer)) {

                bw.write(savetofile);
                System.out.println("File is saved");

            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }

            break loop;
        default:
            System.out.println("No such choice");
    }



    }
}}

import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongStudentAge extends Exception { }
class WrongDateOfBirth extends Exception { }

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imie studenta!");
            }
          catch(WrongStudentAge e) {
                System.out.println("Błędny wiek studenta!");
            }
          catch(WrongDateOfBirth e) {
                System.out.println("Błędna data urodzenia studenta!");
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();

        return name;
    }

  public static int ReadAge() throws WrongStudentAge {
        scan.nextLine();
        System.out.println("Podaj wiek: ");
        int age = scan.nextInt();
        if(age <=0 || age >120)
            throw new WrongStudentAge();

        return age;
    }

public static String ReadDate() throws WrongDateOfBirth{
        scan.nextLine();
        System.out.println("Podaj dzień urodzenia: ");
        int dzien = scan.nextInt();
        System.out.println("Podaj miesiąc urodzenia: ");
          int miesiac = scan.nextInt();
        System.out.println("Podaj rok urodzenia: ");
          int rok = scan.nextInt();
  
        String date = dzien+"."+miesiac+"."+rok;
        if(date.contains(" ") || dzien>31 || miesiac>12 || rok>3000 || dzien<1 ||miesiac<1 || rok<0)
        {throw new WrongDateOfBirth();
        }
            

        return date;
    }
  
    public static void exercise1() throws IOException, WrongStudentName, WrongStudentAge, WrongDateOfBirth {
        var name = ReadName();
        var age = ReadAge();
        var date = ReadDate();
        scan.nextLine();
        (new Service()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}

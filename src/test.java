import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class test {

  public static class ArrEmp {
    String name;
    int age, ID, salary;
    String[] dependentes;

    // @Override
    // public String toString() {
    //   return getClass().getSimpleName() + "[name=" + name + "]";
    // }
  }

  public static ArrayList<test.ArrEmp> employers() {
    ArrayList<test.ArrEmp> emp = new ArrayList<ArrEmp>();
    Scanner scName, scDenp, scID, scSalary, scAge;
    String emplName;
    int amountDenp, checkName, methodID, methodSalary, methodAge;

    for (int i = 0; i < emp.size() + 1; i++) {
      System.out.println("Insira o primeiro nome do empregado:");
      scName = new Scanner(System.in);
      emplName = scName.nextLine();

      checkName = emplName.compareTo("xxx");

      if (checkName != 0) {
        String[] denpArr;
        emp.add(new ArrEmp());

        System.out.println("Insira o ID do empregado:");
        scID = new Scanner(System.in);
        methodID = scID.nextInt();

        System.out.println("Insira a idade do empregado:");
        scAge = new Scanner(System.in);
        methodAge = scAge.nextInt();

        System.out.println("Insira o salário do empregado:");
        scSalary = new Scanner(System.in);
        methodSalary = scSalary.nextInt();

        System.out.println("Insira a quantidade de dependentes:");
        scDenp = new Scanner(System.in);
        amountDenp = scDenp.nextInt();

        emp.get(i).name = emplName;
        emp.get(i).ID = methodID;
        emp.get(i).age = methodAge;
        emp.get(i).salary = methodSalary;
        emp.get(i).ID = methodID;

        emp.get(i).dependentes = new String[amountDenp];
        denpArr = emp.get(i).dependentes;

        for (int j = 0; j < denpArr.length; j++) {
          System.out.println("Insira o nome do " + (j + 1) + "º" + (" dependente:"));
          scDenp = new Scanner(System.in);
          emp.get(i).dependentes[j] = scDenp.next();
        }
      }
    }
    return emp;

  }

  public static void main(String[] args) throws Exception {
    ArrayList<test.ArrEmp> employersList;
    String name;
    int ID, age, salary, den;
    employersList = employers();

    FileWriter arq = new FileWriter("./src/arquivo1");
    PrintWriter writeArq = new PrintWriter(arq);
    writeArq.println("================= Lista de Empregados =================");
    writeArq.println("|Nome      Idade      ID      Salario      Dependentes|");
    for (int i = 0; i < employersList.size(); i++) {
      name = employersList.get(i).name;
      ID = employersList.get(i).ID;
      age = employersList.get(i).age;
      salary = employersList.get(i).salary;
      den = employersList.get(i).dependentes.length;

      writeArq.printf("|%s     %d       %d     %d        %d|\n", name, age, ID, salary, den);
    }

    arq.close();
  }
}
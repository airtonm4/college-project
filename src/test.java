import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.print.event.PrintJobListener;

public class test {

  public static class ArrEmp {
    String name;
    int age, ID, salary;
    String[] dependentes;

    @Override
    public String toString() {
      return getClass().getSimpleName() + "[name=" + name + "]";
    }

  }

  public static ArrayList<test.ArrEmp> employers() {
    ArrayList<test.ArrEmp> emp = new ArrayList<ArrEmp>();
    ArrEmp newArrEmp = new ArrEmp();
    Scanner scName, scDenp, scID, scSalary, scAge;
    String emplName;
    int amountDenp, checkName, methodID, methodSalary, methodAge;

    for (int i = 0; i < emp.size() + 1; i++) {
      System.out.println("Insira o nome do empregado:");
      scName = new Scanner(System.in);
      emplName = scName.nextLine();

      System.out.println(emplName);

      if (checkName != 0) {
        String[] denpArr;
        emp.add(newArrEmp);
        emp.get(i).name = emplName;

        System.out.println("Insira o ID do empregado:");
        scID = new Scanner(System.in);
        methodID = scID.nextInt();

        System.out.println("Insira a idade do empregado:");
        scAge = new Scanner(System.in);
        methodAge = scID.nextInt();

        System.out.println("Insira o salário do empregado:");
        scSalary = new Scanner(System.in);
        methodSalary = scID.nextInt();

        System.out.println("Insira a quantidade de dependentes:");
        scDenp = new Scanner(System.in);
        amountDenp = scDenp.nextInt();

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
    ArrayList<test.ArrEmp> test;
    test = employers();
    System.out.println(test.toString());
  }
}
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class first {
  //Registro dos dados do empregado.
  public static class ArrEmp {
    String name;
    int age, ID, salary;
    String[] dependent;
    int[] dependentAge;

  }

  public static ArrayList<ArrEmp> employers() {
    ArrayList<ArrEmp> emp = new ArrayList<ArrEmp>();
    Scanner scName, scDenp, scID, scSalary, scAge, scDependentAge;
    String emplName, dependentName;
    int amountDenp, checkName, methodID, methodSalary, methodAge;

    //Entrada de dados.
    System.out.println("========= Entrada de dados =========");

    for (int i = 0; i < emp.size() + 1; i++) {
      System.out.println("Insira o primeiro nome do empregado:\n(Digite XXX para dar termino na inclusao de dados)");
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

        //Adicionando os dados do empregado pego pelo o Scanner no ArrayList emp.
        emp.get(i).name = emplName;
        emp.get(i).ID = methodID;
        emp.get(i).age = methodAge;
        emp.get(i).salary = methodSalary;
        emp.get(i).ID = methodID;

        emp.get(i).dependent = new String[amountDenp];
        emp.get(i).dependentAge = new int[amountDenp];

        denpArr = emp.get(i).dependent;
        //Inserindo dados do atributo dependent do registro ArrEmp.
        for (int j = 0; j < denpArr.length; j++) {
          int dependentAge;
          System.out.println("Insira o nome do " + (j + 1) + "º" + (" dependente:"));
          scDenp = new Scanner(System.in);
          dependentName = scDenp.next();

          System.out.println("Insira a idade desse dependente:");
          scDependentAge = new Scanner(System.in);
          dependentAge = scDependentAge.nextInt();

          
          emp.get(i).dependent[j] = dependentName;
          emp.get(i).dependentAge[j] = dependentAge;
        }
      }
    }
    return emp;

  }

  public static void main(String[] args) throws Exception {

    ArrayList<ArrEmp> employersList;
    String name;
    int ID, age, salary, denAmount;
    String[] den;
    int[] denAge;
    //Chamada no metodo employers() que retorna um ArrayList do tipo ArrEmp.
    employersList = employers();

    // Gerando arquivo de acordo com a entrada de dados do método employers().
    FileWriter arq = new FileWriter("./src/arquivo1");
    PrintWriter writeArq = new PrintWriter(arq);
    writeArq.println("======================= Lista de Empregados =======================");
    writeArq.printf("%s %10s %7s %10s %20s \n","Nome", "Idade", "ID", "Salario", "Dependentes/Idade");
    writeArq.println("===================================================================");
    for (int i = 0; i < employersList.size(); i++) {

      //Tornando a construção do arquivo mais simples.
      name = employersList.get(i).name;
      ID = employersList.get(i).ID;
      age = employersList.get(i).age;
      salary = employersList.get(i).salary;
      denAmount = employersList.get(i).dependent.length;
      denAge = new int[denAmount];
      den = new String[denAmount];
      denAge = employersList.get(i).dependentAge;
      den = employersList.get(i).dependent;

      //Introduzindo texto no arquivo.
      writeArq.printf("%s %7d %11d %10d ", name, age, ID, salary);
      for (int j = 0; j < denAmount; j++) {
        writeArq.printf(" %s %d ", den[j], denAge[j]);
      }
      writeArq.printf("\n");
    }

    arq.close();
  }
}
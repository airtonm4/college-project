import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class second extends test {
    public static class averageAgeList {
        String name;
        float averageAge;

    }

    public static int menu() {
        Scanner sc;
        int scOption;
        System.out.println();
        System.out.println("\n===========================");
        System.out.println("     EmployerEnterprise    ");
        System.out.println("===========================");
        System.out.println();
        System.out.println("           Menu");
        System.out.println("Selecione uma das opcoes abaixo:");
        System.out.println();
        System.out.println(
                "1 - Imprimir listagem dos empregados que possuem idade acima de 40 anos, contendo seus dependentes.");
        System.out.println("2 - Aumentar o salario de todos os empregados em 5%.");
        System.out.println(
                "3 - Apresentar empregados com a menor e a maior media de idade entre ele e seus integrantes.");
        System.out.println("4 - Inserir novo empregado.");
        System.out.println("5 - Remover empregado.");
        System.out.println("6 - Gerar relatorio atualizado.");
        System.out.println("7 - Listar dados.");
        System.out.println("0 - Sair e salvar as alteracoes.");
        sc = new Scanner(System.in);
        scOption = sc.nextInt();

        return scOption;
    }

    public static void showEmployersDependents(ArrEmp arr) {
        String emplName;
        int emplAge;
        int[] denpAgeArr;
        String[] denpNameArr;

        emplName = arr.name;
        emplAge = arr.age;

        denpAgeArr = new int[arr.dependentAge.length];
        denpNameArr = new String[arr.dependent.length];

        for (int i = 0; i < denpNameArr.length; i++) {
            denpAgeArr[i] = arr.dependentAge[i];
            denpNameArr[i] = arr.dependent[i];

        }

        if (emplAge >= 40) {
            System.out.printf("%s    %d    ", emplName, emplAge);

            for (int i = 0; i < denpNameArr.length; i++) {
                System.out.printf("%s ", denpNameArr[i]);
            }
            System.out.printf("\n");
        }
    }

    public static int increaseSalary(int salary) {
        salary += salary * 0.05;
        return salary;
    }

    public static float average(averageAgeList[] arr, int index) {
        if (index == 0) {
            return 0;
        }else if(index == arr.length){
            return (arr[index-1].averageAge + average(arr, index-1))/5;
        }
        return arr[index-1].averageAge + average(arr, index-1);

    }
    public static void emplAgeAverage(ArrayList<ArrEmp> arr) {
        float max, min, average;
        String nameMax, nameMin;

        averageAgeList[] averageArr = new averageAgeList[arr.size()];

        for (int i = 0; i < averageArr.length; i++) {
            averageArr[i] = new averageAgeList();
        }

        // for (int i = 0; i < averageArr.length; i++) {
        //     averageArr[i].name = arr.get(i).name;
        //     for (int j = 0; j < arr.get(i).dependentAge.length; j++) {
        //         averageArr[i].averageAge += arr.get(i).dependentAge[j];

        //     }
        //     averageArr[i].averageAge += arr.get(i).age;
        //     averageArr[i].averageAge = averageArr[i].averageAge / (arr.get(i).dependentAge.length + 1);
        //     System.out.println(averageArr[i].averageAge);

        // }
        
        max = 0;
        min = averageArr[0].averageAge;
        
        
        for (int i = 0; i < averageArr.length; i++) {
            average = average(averageArr[i].averageAge, averageArr.length);

            if (averageArr[i].averageAge > max) {
                max = averageArr[i].averageAge;
                nameMax = averageArr[i].name;
            } else if (averageArr[i].averageAge < min) {
                min = averageArr[i].averageAge;
                nameMin = averageArr[i].name;
            }

        }
        System.out.printf("Maior:%2s %.2f Menor: %2s %.2f \n", nameMax, max, nameMin, min);

    }

    public static int removeEmployer(ArrayList<ArrEmp> arr) {
        Scanner sc;
        int element = 0;
        System.out.printf("%10s %14s %8s %10s %20s\n", "Nome", "Idade", "ID", "Salario", "Dependentes/Idade");
        System.out.println("=====================================================================");
        for (ArrEmp e : arr) {
            System.out.printf("%s %10s %10s %10s %10s", element + 1, e.name, e.age, e.ID, e.salary);
            for (int i = 0; i < e.dependent.length; i++) {
                System.out.printf("%15s %1s ", e.dependent[i], e.dependentAge[i]);
            }
            System.out.printf("\n");
            element++;
        }
        System.out.println("Insira a numeracao do empregado que deseja remover: ");
        System.out.println("(Digite 0 para sair)");
        sc = new Scanner(System.in);
        element = sc.nextInt();

        return element;
    }

    public static void updateFile(ArrayList<ArrEmp> arr) throws IOException {
        FileWriter arq = new FileWriter("./src/arquivo1");
        try (PrintWriter writeArq = new PrintWriter(arq)) {
            writeArq.println("======================= Lista de Empregados =======================");
            writeArq.printf("%10s %10s %10s %10s %10s \n", "Nome", "Idade", "ID", "Salario", "Dependentes/Idade");
            writeArq.println("===================================================================");
            for (ArrEmp e : arr) {
                writeArq.printf("%10s %10d %10d %10d ", e.name, e.age, e.ID, e.salary);
                for (int i = 0; i < e.dependent.length; i++) {
                    writeArq.printf(" %10s %d ", e.dependent[i], e.dependentAge[i]);
                }
                writeArq.printf("\n");
            }
            
        }

    }

    public static void getRelatory(ArrayList<ArrEmp> arr) throws IOException {

        FileWriter arq = new FileWriter("./src/arquivo2");

        try (PrintWriter writeArq = new PrintWriter(arq)) {
            int element = 1;
            Collections.sort(arr, new Comparator<ArrEmp>() {
                @Override
                public int compare(test.ArrEmp o1, test.ArrEmp o2) {
                    // TODO Auto-generated method stub
                    return o1.name.compareTo(o2.name);
                }

            });

            writeArq.printf("%22s %16s\n", "Nome do empregado", "Salario");
            writeArq.println("===================================================");
            for (ArrEmp e : arr) {
                writeArq.printf("%s %15s %20s \n", element, e.name, e.salary);
                element++;
            }
            System.out.printf("%22s %16s\n", "Nome do empregado", "Salario");
            System.out.println("===================================================");
            for (ArrEmp e : arr) {
                System.out.printf("%s %15s %20s \n", element, e.name, e.salary);
                element++;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        String line = new String(), denpArr = "", numbers, letters;
        String nameFile = "./src/arquivo1";
        File arq = new File(nameFile);

        String[] lineArr, numbersArr, lettersArr;
        int count = 0, index = 0, inputSL, fileLength;

        ArrayList<ArrEmp> newEmployers = new ArrayList<ArrEmp>();
        ArrayList<ArrEmp> emplArr = new ArrayList<>();

        if (arq.exists()) {
            FileReader readFile = new FileReader(nameFile);
            BufferedReader buffFile = new BufferedReader(readFile);
            int menuIndex = 10;

            while (true) {
                line = buffFile.readLine();

                if (line == null) {
                    break;
                }

                if (count >= 3) {
                    emplArr.add(new ArrEmp());
                    
                    line = line.replace("|", "");
                    line = line.replaceAll("\\s+", " ");
                    line = line.trim();
                    lineArr = new String[line.length()];
                    
                    lineArr = line.split(" ");

                    emplArr.get(index).name = lineArr[0];
                    emplArr.get(index).age = Integer.parseInt(lineArr[1]);
                    emplArr.get(index).ID = Integer.parseInt(lineArr[2]);
                    emplArr.get(index).salary = Integer.parseInt(lineArr[3]);
                    emplArr.get(index).dependent = new String[(lineArr.length - 4) / 2];
                    emplArr.get(index).dependentAge = new int[(lineArr.length - 4) / 2];

                    fileLength = lineArr.length - 4;

                    denpArr = "";
                    for (int i = 0; i < fileLength; i++) {
                        denpArr += lineArr[i + 4];

                    }

                    letters = denpArr.replaceAll("[^a-zA-Z]+", " ");
                    lettersArr = letters.split(" ");
                    numbers = denpArr.replaceAll("[^0-9]+", " ").trim();
                    numbersArr = numbers.split(" ");

                    for (int i = 0; i < fileLength / 2; i++) {

                        emplArr.get(index).dependentAge[i] = Integer.parseInt(numbersArr[i]);
                        emplArr.get(index).dependent[i] = lettersArr[i];

                    }

                    index++;

                }
                count++;

            }

            inputSL = emplArr.size();
            ArrEmp inputShow[] = new ArrEmp[inputSL];

            for (int i = 0; i < inputShow.length; i++) {
                inputShow[i] = new ArrEmp();
            }

            for (int i = 0; i < inputShow.length; i++) {

                inputShow[i].name = emplArr.get(i).name;
                inputShow[i].age = emplArr.get(i).age;
                inputShow[i].salary = emplArr.get(i).salary;

                inputShow[i].dependent = new String[emplArr.get(i).dependent.length];
                inputShow[i].dependentAge = new int[emplArr.get(i).dependentAge.length];

                for (int j = 0; j < emplArr.get(i).dependent.length; j++) {

                    inputShow[i].dependent[j] = emplArr.get(i).dependent[j];
                    inputShow[i].dependentAge[j] = emplArr.get(i).dependentAge[j];

                }
            }
            while (menuIndex != 0) {
                int option = menu();
                menuIndex = option;

                if (option == 1) {
                    // B - Listar empregados maiores de 40 anos.
                    for (int i = 0; i < emplArr.size(); i++) {
                        showEmployersDependents(emplArr.get(i));

                    }

                } else if (option == 2) {
                    // C - Aumentar salario em 5%.
                    for (int i = 0; i < emplArr.size(); i++) {
                        int newSalary = 0;
                        newSalary = increaseSalary(emplArr.get(i).salary);
                        emplArr.get(i).salary = newSalary;

                    }

                } else if (option == 3) {
                    // D - calculo de media das idades dos integrantes de cada element da classe.
                    emplAgeAverage(emplArr);
                } else if (option == 4) {
                    // Novos empregados
                    // E

                    newEmployers.add(new ArrEmp());
                    newEmployers = employers();
                    for (int i = 0; i < newEmployers.size(); i++) {

                        emplArr.add(new ArrEmp());

                        emplArr.get(emplArr.size() - 1).name = newEmployers.get(i).name;
                        emplArr.get(emplArr.size() - 1).age = newEmployers.get(i).age;
                        emplArr.get(emplArr.size() - 1).ID = newEmployers.get(i).ID;
                        emplArr.get(emplArr.size() - 1).salary = newEmployers.get(i).salary;

                        emplArr.get(emplArr.size() - 1).dependent = new String[newEmployers.get(i).dependent.length];
                        emplArr.get(emplArr.size() - 1).dependentAge = new int[newEmployers.get(i).dependentAge.length];

                        for (int j = 0; j < newEmployers.get(i).dependent.length; j++) {
                            emplArr.get(emplArr.size() - 1).dependent[j] = newEmployers.get(i).dependent[j];
                            emplArr.get(emplArr.size() - 1).dependentAge[j] = newEmployers.get(i).dependentAge[j];

                            System.out.println(emplArr.get(emplArr.size() - 1).dependent[j]);
                            System.out.println(emplArr.get(emplArr.size() - 1).dependentAge[j]);
                        }

                    }

                } else if (option == 5) {
                    // F - Rempver empegrados.

                    int result;
                    result = removeEmployer(emplArr);
                    System.out.println(result);
                    if (result != 0) {
                        emplArr.remove(result - 1);
                    }

                } else if (option == 6) {
                    getRelatory(emplArr);
                } else if (option == 7) {
                    int element = 0;
                    System.out.printf("%10s %14s %8s %10s %20s\n", "Nome", "Idade", "ID", "Salario",
                            "Dependentes/Idade");
                    System.out.println("=====================================================================");
                    for (ArrEmp e : emplArr) {
                        System.out.printf("%s %10s %10s %10s %10s", element + 1, e.name, e.age, e.ID, e.salary);
                        for (int i = 0; i < e.dependent.length; i++) {
                            System.out.printf("%15s %1s ", e.dependent[i], e.dependentAge[i]);
                        }
                        System.out.printf("\n");
                        element++;
                    }

                } else if (option == 0) {
                    System.out.println("\nObrigado por usar nosso programa.");
                    updateFile(emplArr);
                }

                buffFile.close();
            }

        }
    }
}

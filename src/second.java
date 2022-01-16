import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class second extends first {
    public static class averageAgeList {
        String name;
        float[] averageAge;

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

    //Recursao
    public static float average(float[] arr, int index) {
        if (index == 0) {
            return 0;
        } else if (index == arr.length) {
            return (arr[index - 1] + average(arr, index - 1)) / arr.length;
        }
        return arr[index - 1] + average(arr, index - 1);

    }

    public static void emplAgeAverage(ArrayList<ArrEmp> arr) {
        float max, min = 999999, average;
        String nameMax = "", nameMin = "";

        averageAgeList[] averageArr = new averageAgeList[arr.size()];

        for (int i = 0; i < averageArr.length; i++) {
            averageArr[i] = new averageAgeList();
        }
        max = 0;

        for (int i = 0; i < averageArr.length; i++) {
            averageArr[i].name = arr.get(i).name;
            averageArr[i].averageAge = new float[arr.get(i).dependentAge.length + 1];

            for (int j = 0; j < arr.get(i).dependentAge.length; j++) {
                averageArr[i].averageAge[j] = arr.get(i).dependentAge[j];
            }
            averageArr[i].averageAge[arr.get(i).dependentAge.length] = arr.get(i).age;

            average = average(averageArr[i].averageAge, averageArr[i].averageAge.length);

            if (average > max) {
                max = average;
                nameMax = averageArr[i].name;
            } 
            if (average < min) {
                min = average;
                nameMin = averageArr[i].name;
            }
        }
        System.out.printf("Maior: %2s %.2f Menor: %2s %.2f \n", nameMax, max, nameMin, min);
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
                public int compare(first.ArrEmp o1, first.ArrEmp o2) {
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
        int count = 0, index = 0, fileLength;

        ArrayList<ArrEmp> newEmployers = new ArrayList<ArrEmp>();
        ArrayList<ArrEmp> emplArr = new ArrayList<>();

        //Lendo o arquivo.txt gerado pelo o primeiro programa.
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

                    //Formatando as linhas do arquivo para armazenar o texto.
                    line = line.replace("|", "");
                    line = line.replaceAll("\\s+", " ");
                    line = line.trim();
                    lineArr = new String[line.length()];

                    lineArr = line.split(" ");

                    //Armazenando os dados do arquivo no ArrayList emplArr
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
                    //Realizando a separação da idade e do nome dos dependentes.
                    letters = denpArr.replaceAll("[^a-zA-Z]+", " ");
                    lettersArr = letters.split(" ");
                    numbers = denpArr.replaceAll("[^0-9]+", " ").trim();
                    numbersArr = numbers.split(" ");

                    //Armazenando o nome e a idade do dependentes no ArrayList.
                    for (int i = 0; i < fileLength / 2; i++) {

                        emplArr.get(index).dependentAge[i] = Integer.parseInt(numbersArr[i]);
                        emplArr.get(index).dependent[i] = lettersArr[i];

                    }

                    index++;

                }
                count++;

            }

            //Código geral do menu.
            while (menuIndex != 0) {
                //Pegar escolha de interação pelo método menu().
                int option = menu();
                menuIndex = option;

                //Opções.
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
                    //E - Novos empregados.

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
                    //G - Gerar relatório e arquivo.txt.
                    getRelatory(emplArr);
                } else if (option == 7) {
                    //Listar os dados atuais dos empregados.
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
                    //Sair e atualizar o arquivo1.txt.
                    System.out.println("\nObrigado por usar nosso programa.");
                    updateFile(emplArr);
                }

                buffFile.close();
            }

        }
    }
}

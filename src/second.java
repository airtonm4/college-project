import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class second extends test {
    public static void main(String[] args) throws Exception {
        String line = new String();
        String nameFile = "./src/arquivo1";
        File arq = new File(nameFile);

        String[] lineArr;
        int count = 0, index = 0;

        ArrayList<ArrEmp> emplArr = new ArrayList<>();

        if (arq.exists()) {
            FileReader readFile = new FileReader(nameFile);
            BufferedReader buffFile = new BufferedReader(readFile);

            while (true) {
                line = buffFile.readLine();

                if (line == null) {
                    break;
                }

                if (count >= 2) {
                    line = line.replace("|", "");
                    line = line.replaceAll("\\s+", " ");
                    lineArr = new String[line.length()];

                    lineArr = line.split(" ");

                    emplArr.add(new ArrEmp());
                    emplArr.get(index).name = lineArr[0];
                    emplArr.get(index).age = Integer.parseInt(lineArr[1]);
                    emplArr.get(index).ID = Integer.parseInt(lineArr[2]);
                    emplArr.get(index).salary = Integer.parseInt(lineArr[3]);

                    index++;
                }
                count++;
                for (int i = 0; i < emplArr.size(); i++) {
                    String name;
                    int ID, age, salary, den;
                    System.out.println(name = emplArr.get(i).name);
                    System.out.println(ID = emplArr.get(i).ID);
                    System.out.println(age = emplArr.get(i).age);
                    System.out.println(salary = emplArr.get(i).salary);
                    // den = emplArr.get(i).dependentes.length; 
                    

                }
            }

        }

    }
}

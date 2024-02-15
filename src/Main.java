import java.io.*;
import java.util.List;
import java.util.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.lang.String;

public class Main {
    public static List<Employee> readAndCreatCsv(String Csvpath) {

        // Create a list to store all the Employee object
        List<Employee> employees=new ArrayList<>();

        // find the missing values in the database if anyone is found
        int missingValue=0;
        try(BufferedReader br = new BufferedReader(new FileReader(Csvpath))){             // Buffer Class read the data
            String line;
            br.readLine();                                                                // this will read a line if line found
            while((line = br.readLine())!= null)                                          //  NULL then it's a missing value
            {
                String[] record=line.split(",");                                    // line.split used for to extract text from csv
                for(String str:record)
                {
                    if(str.trim().isEmpty())                                             // str.trim() remove blank spaces before and after
                    {
                        missingValue++;
                    }
                }
                Employee employee = insertDataIntoemployee(record);
                employees.add(employee);
            }
            System.out.println(missingValue);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
//      finish the code of finding missing value
//      In place of missing value we can put 3 values
//      1. NULL or 0
//      2. Mean or median of all value
//      3. nearest value

        return employees;
    }
    public static Employee insertDataIntoemployee(String[] record)
    {
        // Get the data from csv file and parse it into Employee object

        String Education= record[0].trim();
        int JoiningYear=Integer.parseInt(record[1].trim());
        String City=record[2].trim();
        int PaymentTier=Integer.parseInt(record[3].trim());
        int Age=Integer.parseInt(record[4].trim());
        String Gender=record[5].trim();
        String EverBenched=record[6].trim();
        int ExperienceInCurrentDomain=Integer.parseInt(record[7].trim());
        int LeaveOrNot=Integer.parseInt(record[8].trim());

        // return new object employee
        return new Employee(Education,JoiningYear,City,PaymentTier,Age,Gender,EverBenched,ExperienceInCurrentDomain,LeaveOrNot);
    }
    public static void main(String[] args) throws Exception {
        List<Employee> employees = readAndCreatCsv("C:/Users/c22755b/IdeaProjects/Employee_Dataset/Employee.csv");
//
//        for (Employee emp:employees) {                // for printing all the data
//            emp.ptrString();
//        }

        generateCompleteAnalysis report = new generateCompleteAnalysis(employees);
    }
}
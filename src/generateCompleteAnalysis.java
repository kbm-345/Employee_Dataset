/* This class contain all the analysis report and all function which
required for make analysis in a goof manner */

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class generateCompleteAnalysis {
    List<Employee> employees;

    // Constructor of the class which initialised the object Employee
    public generateCompleteAnalysis(List<Employee> employees) {
        this.employees = employees;

        // Try and catch for handling IOException and ExecuteException
        try {

            // This is CompletableFuture for all distribution which make happen this processes in asynchronous manner
            // Used Lazy Evaluation and lambda expression for better and fast execution
            CompletableFuture<Double> FutureAvgAge = CompletableFuture.supplyAsync(() -> computeAvgAge(employees));
            CompletableFuture<Map<String, Long>> FutureEducationDistribution = CompletableFuture.supplyAsync(() -> computeEducationDistribution(employees));
            CompletableFuture<Map<String, Long>> FutureGenderDistribute = CompletableFuture.supplyAsync(() -> computeGenderDistribute(employees));

            // it will join all the task means wait till it completed all the task
            CompletableFuture.allOf(FutureAvgAge, FutureEducationDistribution, FutureGenderDistribute).join();

            // Used get method of supplier functional interface for Lazy execution
            double avgAge = FutureAvgAge.get();
            Map<String, Long> educationDistribution = FutureEducationDistribution.get();
            Map<String, Long> genderDistribute = FutureGenderDistribute.get();


            // Print all the data which shows you distribution of all different data
            System.out.println("Average Age: " + avgAge);
            System.out.println("Education Level Distribution: " + educationDistribution);
            System.out.println("Gender Distribution: " + genderDistribute);

            CompletableFuture<Map<Integer,Long>> FuturePaymentTierDistribute = CompletableFuture.supplyAsync(() -> computePaymentTierDistribute(employees));
            CompletableFuture<Map<String, Long>> FutureBenchStatusDistribute = CompletableFuture.supplyAsync(() -> computeBenchStatusDistribute(employees));
            CompletableFuture<Map<Integer,Long>> FutureExperienceDistribute = CompletableFuture.supplyAsync(() -> computeExperienceDistribute(employees));

            CompletableFuture.allOf(FuturePaymentTierDistribute, FutureBenchStatusDistribute, FutureExperienceDistribute).join();

            Map<Integer, Long> paymentTierDistribute = FuturePaymentTierDistribute.get();
            Map<String, Long> benchStatusDistribute = FutureBenchStatusDistribute.get();
            Map<Integer, Long> experienceDistribute = FutureExperienceDistribute.get();

            System.out.println("Retention by Payment Tier: " + paymentTierDistribute);
            System.out.println("Retention by Bench Status: " + benchStatusDistribute);
            System.out.println("Retention by Experience in Current Domain: " + experienceDistribute);

            CompletableFuture<Map<String, Long>> FutureEmployeeByCity = CompletableFuture.supplyAsync(() -> computeEmployeeByCity(employees));
            CompletableFuture<Map<String, Long>> FutureEmployeeByEducationLevel = CompletableFuture.supplyAsync(() -> computeEmployeeByEducationLevel(employees));

            CompletableFuture.allOf(FutureEmployeeByCity, FutureEmployeeByEducationLevel).join();

            Map<String, Long> employeeByCity = FutureEmployeeByCity.get();
            Map<String, Long> employeeByEducationLevel = FutureEmployeeByEducationLevel.get();

            System.out.println("Employees segmented by City: " + employeeByCity);
            System.out.println("Employees segmented by Education Level: " + employeeByEducationLevel);

            }catch(InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

     // Belows are all the function which used in above and it will perform task using stream and pipelining
    private static Double computeAvgAge(List<Employee> employees)
    {
        return employees.stream().mapToInt(Employee::getAge).average().orElse(0.0);
    }
    private static Map<String,Long> computeEducationDistribution(List<Employee> employees)
    {
        return employees.stream().collect(Collectors.groupingBy(Employee::getEducation,Collectors.counting()));
    }
    private static Map<String,Long> computeGenderDistribute(List<Employee> employees)
    {
        return employees.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
    }
    private static Map<Integer,Long> computePaymentTierDistribute(List<Employee> employees)
    {
        return employees.stream().collect(Collectors.groupingBy(Employee::getPaymentTier,Collectors.counting()));
    }
    private static Map<String,Long> computeBenchStatusDistribute(List<Employee> employees)
    {
        return employees.stream().collect(Collectors.groupingBy(Employee::getBenchStatus,Collectors.counting()));
    }
    private static Map<Integer,Long> computeExperienceDistribute(List<Employee> employees)
    {
        return employees.stream().collect(Collectors.groupingBy(Employee::getExperienceStatus,Collectors.counting()));
    }

    private static Map<String,Long> computeEmployeeByCity(List<Employee> employees)
    {
        return employees.stream().collect(Collectors.groupingBy(Employee::getCity,Collectors.counting()));
    }
    private static Map<String,Long> computeEmployeeByEducationLevel(List<Employee> employees)
    {
        return employees.stream().collect(Collectors.groupingBy(Employee::getEducationLevel,Collectors.counting()));
    }
}

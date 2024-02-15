/* Employee class which will help to create object of employee */

public class Employee {
    private String Education;
    private int JoiningYear;
    private String City;
    private int PaymentTier;
    private int Age;
    private String Gender;
    private String EverBenched;
    private int ExperienceInCurrentDomain;
    private int LeaveOrNot;

    public Employee(String Education,int JoiningYear,String City,int PaymentTier,int Age,String Gender,String EverBenched,int ExperienceInCurrentDomain,int LeaveOrNot)
    {
        this.Education=Education;
        this.JoiningYear=JoiningYear;
        this.City=City;
        this.PaymentTier=PaymentTier;
        this.Age=Age;
        this.Gender=Gender;
        this.EverBenched=EverBenched;
        this.ExperienceInCurrentDomain=ExperienceInCurrentDomain;
        this.LeaveOrNot=LeaveOrNot;
    }
    public void ptrString()
    {
        System.out.println("Employee{" + ", age=" + Age + ", paymentTier='" + PaymentTier + '\'' + ", leaveOrNot=" + LeaveOrNot + ", education='" + Education + '\'' + ", joiningYear=" + JoiningYear +
                ", city='" + City + '\'' + ", gender='" + Gender + '\'' + ", everBenched='" + EverBenched + '\'' + ", experienceInCurrentDomain=" + ExperienceInCurrentDomain + '}');
    }
    public int getAge()
    {
        return Age;
    }
    public String getEducation()
    {
        return Education;
    }
    public String getGender()
    {
        return Gender;
    }
    public int getPaymentTier()
    {
        return PaymentTier;
    }
    public String getBenchStatus()
    {
        return EverBenched;
    }
    public int getExperienceStatus()
    {
        return ExperienceInCurrentDomain;
    }
    public String getCity()
    {
        return City;
    }
    public String getEducationLevel()
    {
        return Education;
    }
}

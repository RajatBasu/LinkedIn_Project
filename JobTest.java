import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JobTest {
    private Job job;

    @BeforeEach
    public void setUp() {
        job = new Job();
    }

    @Test
    public void testAddJobWithValidData() {
        job.setJobNumber("12345ABC!");
        job.setJobPostedDate("2022-01-01");
        job.setJobPosterAddress("City, State, Country");
        job.setJobExperienceLevel("Senior");
        job.setJobType("Full-time");
        job.setJobRequiredSkills(new String[]{"Java", "Spring"});
        job.setJobSalary(120000.0);
        job.setJobDescription("Job description");

        boolean result = job.addJob();

        Assertions.assertTrue(result);
        
    }

    @Test
    public void testAddJobWithInvalidJobNumber() {
        job.setJobNumber("1234ABC!"); // Job number is not 9 characters long

        boolean result = job.addJob();

        Assertions.assertFalse(result);
        
    }

    @Test
    public void testAddJobWithInvalidJobPostedDate() {
        job.setJobNumber("12345ABC!");
        job.setJobPostedDate("2022/01/01"); // Invalid date format

        boolean result = job.addJob();

        Assertions.assertFalse(result);
        
    }

    @Test
    public void testAddJobWithInvalidJobPosterAddress() {
        job.setJobNumber("12345ABC!");
        job.setJobPostedDate("2022-01-01");
        job.setJobPosterAddress("Invalid Address"); // Invalid address format

        boolean result = job.addJob();

        Assertions.assertFalse(result);
        
    }

    @Test
    public void testAddJobWithInvalidJobSalaryForSeniorPosition() {
        job.setJobNumber("12345ABC!");
        job.setJobPostedDate("2022-01-01");
        job.setJobPosterAddress("City, State, Country");
        job.setJobExperienceLevel("Senior");
        job.setJobType("Full-time");
        job.setJobRequiredSkills(new String[]{"Java", "Spring"});
        job.setJobSalary(90000.0); // Salary is less than the required minimum

        boolean result = job.addJob();

        Assertions.assertFalse(result);
        
    }

    @Test
    public void testAddJobWithInvalidJobSalaryForJuniorPosition() {
        job.setJobNumber("12345ABC!");
        job.setJobPostedDate("2022-01-01");
        job.setJobPosterAddress("City, State, Country");
        job.setJobExperienceLevel("Junior");
        job.setJobType("Full-time");
        job.setJobRequiredSkills(new String[]{"Java", "Spring"});
        job.setJobSalary(80000.0); // Salary is less than the required minimum for Junior position

        boolean result = job.addJob();

        Assertions.assertFalse(result);
        
    }

    @Test
    public void testAddJobWithPartTimeSeniorPosition() {
        job.setJobNumber("12345ABC!");
        job.setJobPostedDate("2022-01-01");
        job.setJobPosterAddress("City, State, Country");
        job.setJobExperienceLevel("Senior");
        job.setJobType("Part-time"); // Invalid combination of position and job type
        job.setJobRequiredSkills(new String[]{"Java", "Spring"});
        job.setJobSalary(120000.0);
        job.setJobDescription("Job description");

        boolean result = job.addJob();

        Assertions.assertFalse(result);
        
    }

    @Test
    public void testAddJobWithNoRequiredSkills() {
        job.setJobNumber("12345ABC!");
        job.setJobPostedDate("2022-01-01");
        job.setJobPosterAddress("City, State, Country");
        job.setJobExperienceLevel("Junior");
        job.setJobType("Full-time");
        job.setJobRequiredSkills(new String[]{}); // No required skills specified
        job.setJobSalary(60000.0);
        job.setJobDescription("Job description");

        boolean result = job.addJob();

        Assertions.assertFalse(result);
        
    }

    @Test
    public void testUpdateJobWithValidFieldToUpdate() {
        job.setJobNumber("12345ABC!");
        job.setJobPostedDate("2022-01-01");
        job.setJobPosterAddress("City, State, Country");
        job.setJobExperienceLevel("Senior");
        job.setJobType("Full-time");
        job.setJobRequiredSkills(new String[]{"Java", "Spring"});
        job.setJobSalary(120000.0);
        job.setJobDescription("Job description");

        boolean addResult = job.addJob();
        boolean updateResult = job.updateJob("jobTitle", "Software Engineer");

        Assertions.assertTrue(addResult);
        Assertions.assertTrue(updateResult);
        
    }

    @Test
    public void testUpdateJobWithInvalidFieldToUpdate() {
        job.setJobNumber("12345ABC!");
        job.setJobPostedDate("2022-01-01");
        job.setJobPosterAddress("City, State, Country");
        job.setJobExperienceLevel("Senior");
        job.setJobType("Full-time");
        job.setJobRequiredSkills(new String[]{"Java", "Spring"});
        job.setJobSalary(120000.0);
        job.setJobDescription("Job description");

        boolean addResult = job.addJob();
        boolean updateResult = job.updateJob("invalidField", "Some value");

        Assertions.assertTrue(addResult);
        Assertions.assertFalse(updateResult);
        
    }

    
}
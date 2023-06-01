import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Job {
    private String jobNumber;
    private String jobTitle;
    private String jobPosterName;
    private String jobPosterAddress;
    private String jobPostedDate;
    private String jobExperienceLevel;
    private String jobType;
    private String[] jobRequiredSkills;
    private double jobSalary;
    private String jobDescription;

    public Job() {
        // Empty constructor
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobPosterName(String jobPosterName) {
        this.jobPosterName = jobPosterName;
    }

    public void setJobPosterAddress(String jobPosterAddress) {
        this.jobPosterAddress = jobPosterAddress;
    }

    public void setJobPostedDate(String jobPostedDate) {
        this.jobPostedDate = jobPostedDate;
    }

    public void setJobExperienceLevel(String jobExperienceLevel) {
        this.jobExperienceLevel = jobExperienceLevel;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setJobRequiredSkills(String[] jobRequiredSkills) {
        this.jobRequiredSkills = jobRequiredSkills;
    }

    public void setJobSalary(double jobSalary) {
        this.jobSalary = jobSalary;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public boolean addJob() {
        if (!validateJobNumber() || !validateJobPostedDate() || !validateJobPosterAddress() || !validateJobSalary() ||
                !validateJobTypeAndExperienceLevel() || !validateJobRequiredSkills()) {
            return false;
        }

        try (FileWriter writer = new FileWriter("jobs.txt", true)) {
            writer.write(jobNumber + "\n");
            writer.write(jobTitle + "\n");
            writer.write(jobPosterName + "\n");
            writer.write(jobPosterAddress + "\n");
            writer.write(jobPostedDate + "\n");
            writer.write(jobExperienceLevel + "\n");
            writer.write(jobType + "\n");

            for (String skill : jobRequiredSkills) {
                writer.write(skill + "\n");
            }

            writer.write(jobSalary + "\n");
            writer.write(jobDescription + "\n");
            writer.write("\n");

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateJob(String fieldToUpdate, String newValue) {
        if (!addJob()) {
            return false;
        }

        switch (fieldToUpdate) {
            case "jobNumber":
                setJobNumber(newValue);
                break;
            case "jobTitle":
                setJobTitle(newValue);
                break;
            case "jobPosterName":
                setJobPosterName(newValue);
                break;
            case "jobPosterAddress":
                setJobPosterAddress(newValue);
                break;
            case "jobPostedDate":
                setJobPostedDate(newValue);
                break;
            case "jobExperienceLevel":
                setJobExperienceLevel(newValue);
                break;
            case "jobType":
                setJobType(newValue);
                break;
            case "jobRequiredSkills":
                setJobRequiredSkills(parseJobRequiredSkills(newValue));
                break;
            case "jobSalary":
                setJobSalary(parseJobSalary(newValue));
                break;
            case "jobDescription":
                setJobDescription(newValue);
                break;
            default:
                System.out.println("Invalid field to update.");
                return false;
        }

        try (FileWriter writer = new FileWriter("jobs.txt", true)) {
            writer.write(jobNumber + "\n");
            writer.write(jobTitle + "\n");
            writer.write(jobPosterName + "\n");
            writer.write(jobPosterAddress + "\n");
            writer.write(jobPostedDate + "\n");
            writer.write(jobExperienceLevel + "\n");
            writer.write(jobType + "\n");

            for (String skill : jobRequiredSkills) {
                writer.write(skill + "\n");
            }

            writer.write(jobSalary + "\n");
            writer.write(jobDescription + "\n");
            writer.write("\n");

            return true;
        } catch (IOException e) {
            System.out.println("Failed to update the job in the file.");
            return false;
        }
    }

    private boolean validateJobNumber() {
        if (jobNumber.length() != 9) {
            return false;
        }

        String firstFiveDigits = jobNumber.substring(0, 5);
        String upperCaseLetters = jobNumber.substring(5, 8);
        String specialChar = jobNumber.substring(8);

        return firstFiveDigits.matches("[1-5]+") &&
                upperCaseLetters.matches("[A-Z]+") &&
                specialChar.matches("[!@#$%^&*_]+");
    }

    private boolean validateJobPostedDate() {
        return jobPostedDate.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    private boolean validateJobPosterAddress() {
        return jobPosterAddress.matches("^[\\p{L} ]+, [\\p{L} ]+, [\\p{L} ]+$");
    }

    private boolean validateJobSalary() {
        if ((jobExperienceLevel.equals("Senior") || jobExperienceLevel.equals("Executive")) && jobSalary < 100000) {
            return false;
        }

        if (jobExperienceLevel.equals("Junior") && (jobSalary < 40000 || jobSalary > 70000)) {
            return false;
        }

        return true;
    }

    private boolean validateJobTypeAndExperienceLevel() {
        return !(jobType.equals("Part-time") && (jobExperienceLevel.equals("Senior") || jobExperienceLevel.equals("Executive")));
    }

    private boolean validateJobRequiredSkills() {
        int requiredSkillsCount = jobRequiredSkills.length;
        return requiredSkillsCount >= 1 && requiredSkillsCount <= 3;
    }

    private String[] parseJobRequiredSkills(String skillsString) {
        return Arrays.stream(skillsString.split(","))
                .map(String::trim)
                .toArray(String[]::new);
    }

    private double parseJobSalary(String salaryString) {
        try {
            return Double.parseDouble(salaryString);
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary format. Please enter a numeric value.");
            return -1; // Indicates an error
        }
    }
}

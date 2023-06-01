import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Job job = new Job();
        job.setJobNumber("12345AJM*");
        job.setJobTitle("Software Engineer");
        job.setJobPosterName("KBC Company");
        job.setJobPosterAddress("Sydney, Victoria, Australia");
        job.setJobPostedDate("2023-05-31");
        job.setJobExperienceLevel("Junior");
        job.setJobType("Full-time");
        String[] skills = {"C", "SQL"};
        job.setJobRequiredSkills(skills);
        job.setJobSalary(50000);
        job.setJobDescription("Job description");

        boolean isAdded = job.addJob();

        if (isAdded) {
            System.out.println("Job added successfully!");
        } else {
            System.out.println("Failed to add the job. Please check the job information.");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to update the job data? (yes/no): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            // Prompt the user to enter the field to be updated
            System.out.print("Enter the field to be updated (jobNumber, jobTitle, jobPosterName, jobPosterAddress, " +
                    "jobPostedDate, jobExperienceLevel, jobType, jobRequiredSkills, jobSalary, jobDescription): ");
            String field = scanner.nextLine();

            // Prompt the user to enter the new value for the field
            System.out.print("Enter the new value for the field: ");
            String value = scanner.nextLine();

            // Update the job data based on the user's input
            boolean success = job.updateJob(field, value);

            if (success) {
                System.out.println("Job data updated successfully.");
            } else {
                System.out.println("Failed to update the job data. Please check the updated job information.");
            }
        } else {
            System.out.println("No update performed. Job information remains unchanged.");
        }

        // Close the scanner
        scanner.close();
    }
}
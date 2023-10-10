package com.api.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class GitCloneSchedulerForUploadAttendance {

	@Autowired
	private UploadAttendanceToDb attendanceToDb;
	
  //  @Scheduled(cron = "0 0 9 * * *") // Run at 9 AM daily
	 @Scheduled(cron = "0 59 18 * * *") // Run at 4:26 PM daily
    public void cloneContents() {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String repoUrl = "https://github.com/akshayraghuvanshi09/empattendance.git";
        String targetDirectory = "C:\\Users\\Its\\Downloads\\libapi\\clone";
        String username = properties.getProperty("github.username");
        String password = properties.getProperty("github.password");

        try {
           Git cloneRepo = Git.cloneRepository().setURI(repoUrl)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
                    .setDirectory(new File(targetDirectory)).call();

            System.out.println("Repository cloned successfully.");
            cloneRepo.close();
            
            String filePath = "C:\\Users\\Its\\Downloads\\libapi\\clone\\14thAug2023.xls";
            File file = new File(filePath);

            attendanceToDb.uploadAttendanceFile(file);
            // Delete contents here if needed
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}

package com.api.helper;

import java.io.File;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
@EnableScheduling
public class DeleteCloneFilesScheduler {
	
	 // @Scheduled(cron = "0 0 9 * * *")
	 @Scheduled(cron = "0 49 18 * * *") // Run at 4:26 PM daily
	public void deleteContents() {

		String repoPath = "C:\\Users\\Its\\Downloads\\libapi\\clone";

		File repoDirectory = new File(repoPath);

		if (repoDirectory.exists() && repoDirectory.isDirectory()) {
			deleteContents(repoDirectory);
			System.out.println("Directory contents deleted successfully.");
		} else {
			System.out.println("Directory does not exist or is not a directory.");
		}
	}

	public static void deleteContents(File directory) {
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						deleteContents(file);
					}
					file.delete();
				}
			}
		}
	}
}

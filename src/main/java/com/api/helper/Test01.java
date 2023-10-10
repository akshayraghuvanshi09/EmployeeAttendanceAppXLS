package com.api.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class Test01 {
	public static void main(String[] args) {
		Properties properties = new Properties();

		try (InputStream input = Test01.class.getClassLoader().getResourceAsStream("application.properties")) {
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
			Git.cloneRepository().setURI(repoUrl)
					.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
					.setDirectory(new File(targetDirectory)).call();

			System.out.println("Repository cloned successfully.");
			//DeleteFolderContentsExample(targetDirectory);
		} catch (GitAPIException e) {
			e.printStackTrace();
		}

	}

}

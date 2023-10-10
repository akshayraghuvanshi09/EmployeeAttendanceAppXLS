package com.api.helper;

import java.io.File;

public class Test02 {

	public static void main(String[] args) {
//		String repoPath = "C:\\Users\\Its\\Downloads\\libapi\\clone";
//
//		File gitFolder = new File(repoPath + "/.git");
//
//		if (gitFolder.exists()) {
//			deleteDirectory(gitFolder);
//			System.out.println(".git folder deleted successfully.");
//		} else {
//			System.out.println(".git folder does not exist.");
//		}
//	}
//
//	public static void deleteDirectory(File directory) {
//		if (directory.isDirectory()) {
//			File[] files = directory.listFiles();
//			if (files != null) {
//				for (File file : files) {
//					deleteDirectory(file);
//				}
//			}
//		}
//		directory.delete();
//	}
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

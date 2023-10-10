package com.api.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;



public class Test {
public static void main(String[] args) {
	 String filePath = "C:\\Users\\Its\\Downloads\\libapi\\clone\\14thAug2023.xls";
     File file = new File(filePath);


	UploadAttendanceToDb attendanceToDb = new UploadAttendanceToDb();
	attendanceToDb.uploadAttendanceFile(file);
}	
	
}


    

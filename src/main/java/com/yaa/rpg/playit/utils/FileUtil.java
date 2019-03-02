package com.yaa.rpg.playit.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.yaa.rpg.playit.Game;
import com.yaa.rpg.playit.model.User;

public class FileUtil {

	public static String getFileNameWithExtension(String fileName){
		StringBuilder sb= new StringBuilder(fileName);
		if(!fileName.endsWith(Constants.FILE_EXTENSION)){
			sb.append("_");
			if(Game.isHarryPotter)
				sb.append(Constants.HARRY_POTTER_GAME);
			else
				sb.append(Constants.DARK_CASTLE_GAME);
				sb.append(Constants.FILE_EXTENSION);
		}
		return sb.toString();
	}	
	public static void writeFile(Object object, String fileName) throws IOException {
	      try (RandomAccessFile raf = new RandomAccessFile(getFileNameWithExtension(fileName), "rw") ;
	    	   FileOutputStream fos = new FileOutputStream(raf.getFD());
	    	   ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos)){
	    	  objectOutputStream.writeObject(object);
	    } 
	}

	public static Object readFile(String fileName) throws IOException, ClassNotFoundException {
	      try (RandomAccessFile raf = new RandomAccessFile(getFileNameWithExtension(fileName), "rw");
	  	       FileInputStream fos = new FileInputStream(raf.getFD());
	    	   ObjectInputStream objectInputStream = new ObjectInputStream(fos)){
	    	   return  objectInputStream.readObject();
	      } 
	}
	
	public static List<String> listFiles(){
		try(Stream<Path> subPaths=Files.walk(Paths.get(Constants.WORKING_DIR),1)) {
			return subPaths.filter(Files::isRegularFile).map(Objects::toString)
		    .filter(s ->  s.endsWith(Game.isHarryPotter?"_"+Constants.HARRY_POTTER_GAME+".ser":"_"+Constants.HARRY_POTTER_GAME+".ser"))
		    .sorted()
		    .collect(Collectors.toList());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return Collections.emptyList();
	}

	public static void deleteFile(String username) throws IOException {
		Files.delete(Paths.get(getFileNameWithExtension(username)));		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.home"));
		User user=new User("yass", 1, 1, 100, null);
		writeFile(user, user.getName());
		User u=(User)readFile(user.getName());
		System.out.println(u.toString());
		deleteFile(user.getName());
	}
	
}

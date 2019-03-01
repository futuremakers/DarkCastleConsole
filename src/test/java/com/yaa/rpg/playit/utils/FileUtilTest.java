package com.yaa.rpg.playit.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

import org.junit.Test;

import com.yaa.rpg.playit.model.User;

public class FileUtilTest  {

	@Test
	public void whenSerializingAndDeserializing_ThenObjectIsTheSameAndDelete() 
	  throws IOException, ClassNotFoundException { 
	    User person = new User("User1", 1,1,100,null);

	    FileUtil.writeFile(person, person.getName()); 
	    
	    User p2 = (User) FileUtil.readFile(person.getName());
	  
	    assertEquals(p2.getHealth(), person.getHealth());
	    assertEquals(p2.getLevel() , person.getLevel());
	    assertEquals(p2.getName(),person.getName());
	    
	    FileUtil.deleteFile(person.getName());
	    assertFalse(Files.exists(Paths.get(FileUtil.getFileNameWithExtension(person.getName())), LinkOption.NOFOLLOW_LINKS));
	}
}

package com.yaa.rpg.playit.persistence;

public interface RiddleDao {
	String getRiddle(int level);
	boolean checkRiddleAnswer(String answer, int level);
	String getRiddleHint(Integer level);
}

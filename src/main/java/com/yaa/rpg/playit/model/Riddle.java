package com.yaa.rpg.playit.model;

import java.io.Serializable;

public class Riddle implements Serializable{
	private static final long serialVersionUID = 7965071782619073153L;
	
	private int difficulty ;
	private String question;
	private String answer ;
	
	
	public Riddle(int difficulty, String question, String answer) {
		this.difficulty = difficulty;
		this.question = question;
		this.answer = answer;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}

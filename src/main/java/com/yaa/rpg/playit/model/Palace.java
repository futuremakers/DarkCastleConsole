package com.yaa.rpg.playit.model;

import com.yaa.rpg.playit.theme.color.Color;
import com.yaa.rpg.playit.utils.Constants;

public class Palace extends GeoMap  {
	private static final long serialVersionUID = 7519634170517989846L;
	Room[][] rooms = new Room[Constants.rows][Constants.columns];
	Integer currentRoomX;
	Integer currentRoomY;

	public Palace() {
		// init rooms
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				rooms[i][j] = new Room();
			}
		}
		//select a room for user randomly
		currentRoomX = (int) (Math.random() * 10);
		currentRoomY = (int) (Math.random() * 10);
		
		
		int monsters = (int) (Math.random() * 10 + 20); //20 - 30 monsters are placed in palace 
		for (int i = 0; i < monsters; i++) {
			rooms[(int) (Math.random() * 10)][(int) (Math.random() * 10)].setOccupiedBy(GameCharacter.MONSTER);
		}
		//select a room for princess which is different from user's room
		int princessX = (int) (Math.random() * 10);
		int princessY = (int) (Math.random() * 10);
		if (princessX == currentRoomX && princessY == currentRoomY) {
			princessX = (int) (Math.random() * 10);
			princessY = (int) (Math.random() * 10);
		}
		rooms[princessX][princessY].setOccupiedBy(GameCharacter.PRINCESS);
		//at least four monsters are very near to princess
		rooms[princessX][princessY==0?princessY+1:princessY-1].setOccupiedBy(GameCharacter.MONSTER);
		rooms[princessX==0?princessX+1:princessX-1][princessY].setOccupiedBy(GameCharacter.MONSTER);
	}

	@Override
	void print() {
		for (int c = 0; c < Constants.columns; c++) {
			System.out.print(Color.BLUE.format(" ___ "));
		}
		System.out.println();

		StringBuilder roomString=new StringBuilder();
		
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				roomString.setLength(0);
				if (i == currentRoomX && j == currentRoomY) {
					roomString .append(Color.BLUE_BG.format("|"))
					.append(Color.MAGENTA_BG.format(Color.WHITE.format("_U_")))
					.append(Color.BLUE_BG.format("|"));
					rooms[i][j].setOccupiedBy(null);
				}else{
					roomString.append(Color.BLUE_BG.format("|___|"));
				}
				System.out.print(roomString);
			}
			System.out.println();
		}
	}

	public Integer getCurrentRoomX() {
		return currentRoomX;
	}

	public Integer getCurrentRoomY() {
		return currentRoomY;
	}
	
	public Room getCurrentRoom() {
		return rooms[currentRoomX][currentRoomY];
	}

	public void IncOrDecCurrentRoomY(int i) {
		currentRoomY += i;
	}

	public void IncOrDecCurrentRoomX(int i) {
		currentRoomX += i;
	}
}

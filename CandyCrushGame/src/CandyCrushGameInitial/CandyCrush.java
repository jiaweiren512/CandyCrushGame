package CandyCrushGameInitial;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class CandyCrush {

	public static void main(String[] args) {
		int [] arr = new int [3];
		AskingforRowColumnandTypes(arr);
		char [][]twoDArr = PrintBarsandSpace(arr);
		//Display(twoDArr);
		Play(twoDArr,arr);
		
	}
////////////////////////////////////////////////////////// ask user for input and store them into an array//////////////////////////////////////////////////////////
	public static int [] AskingforRowColumnandTypes (int [] arr) {
		System.out.println("Enter the number of rows");
		Scanner input= new Scanner(System.in);
		arr[0] = input.nextInt();	// arr[0] is row
		System.out.println("Enter the number of columns");
		arr[1] = input.nextInt();	// arr[1] is colmn
		System.out.println("Enter the number of types");
		arr[2] = input.nextInt();	// arr[2] is # of types
		return arr;
	}
	
////////////////////////////////////////////////////////// add 1234 at the top and add bars to separate colums//////////////////////////////////////////////////////////
	public static char [][] PrintBarsandSpace (int [] arr){
		char [][]twoDArr = new char [arr[0]+1] [arr[1]*2+1];
		int rlength= twoDArr.length;
		int clength= twoDArr[0].length;
		int num = 1;
		for(int i =0 ; i<clength; i+=1) {// add 1234 at the top
			if(i%2==1) {
			twoDArr[0][i]=(char)(num+'0');
			num++;
			}else {
				twoDArr[0][i]=' ';
			}
		}
		for(int row=1; row<rlength; row++) {
			for(int col=0; col<twoDArr[row].length;col+=2) { // add the bars;
				twoDArr[row][col]='|';
			}
			for(int col=1; col< twoDArr[row].length; col+=2 ) { // add the space in between each column starting row 1
				twoDArr[row][col]=' ';
			}
		}
		
		return twoDArr;
	}
	
////////////////////////////////////////////////////////// Generate random character//////////////////////////////////////////////////////////
	public static char generateRandomLetter(int type) {		
		Random random = new Random();
		int randomNumber = random.nextInt(type)+65;
		return (char) randomNumber;
	}
//////////////////////////////////////////////////////////displaying an array//////////////////////////////////////////////////////////
	public static void DisplayArray(char[][] array) {
		for(int i=0; i<array.length;i++) {
			for(int j=0; j<array[i].length;j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
	}
//////////////////////////////////////////////////Play1 //////////////////////////////////////////////////////////////////
	public static char [][]Play(char [][] Grid, int []SetupArray){
		int col ;
		while(!Gameover(Grid)) {///////While Gameover condition has not met , continue playing the game
		char tile = generateRandomLetter(SetupArray[2]);/// Generate the next tile first 
		System.out.println("Next tile : "+tile);
		DisplayArray(Grid);
		System.out.println("Enter the column: ");
		Scanner input= new Scanner(System.in);
		col=input.nextInt();
		col=2*col-1;
		for(int i=Grid.length-1; i>0; i--) {///////assign new tiles to the lowest row  cell in the column
			if(Grid[i][col]==' ') {
				Grid[i][col]=tile;
				break;
			}
		}
		DisplayArray(Grid);
		/////// When a set is found , prints message and pause for input before continue
		if(CheckSet(Grid)) {
		System.out.println("You made a set! Enter any word to continue. ");
		DisplayArray(Grid);
		Scanner scanner = new Scanner (System.in);
		scanner.nextLine();
		removeAsterisksAndSlide(Grid);
		}
		
		
		}
////////// If Game over condition is met/ true ; then print Game over , Otherwise Keep filling the Cells until Game is over		////
		{
			System.out.println("Game Over");
		}
		return Grid;	
	}
//////////////////////////////////////////////////Game Over Condition //////////////////////////////////////////////////////////////////	
public static boolean Gameover(char[][] arr){
	for(int col=1; col<arr[0].length; col+=2) {
		if(arr[1][col]!=' '&&arr[1][col]!='*') {
			DisplayArray(arr);
			return true;
		}
	}
	return false;
}
//////////////////////////////////////////////////CheckingSet//////////////////////////////////////////////////////////////////
public static Boolean CheckSet(char [][] arr){	/////arr is Grid
	//////////////check set Horizontally///////////////////
	boolean check = false;
	for(int row=1; row<=arr.length-1; row++) {
		for(int col=1; col<=arr[row].length-5;col+=2) {
			if(arr[row][col]==arr[row][col+2]&&arr[row][col]==arr[row][col+4]&& arr[row][col]!=' '&&arr[row][col]!='*') {
				check=true;
				char Hmatch = arr[row][col];
				int k=col;
				// Continue replacing until the end of the set starting from the point where you find the Set appears
				while(k<(arr[row].length-1) && arr[row][k]== Hmatch) {
					arr[row][k]='*';
					k+=2;
				}
				
			}
		}
	}
	//////////////Check  set Vertically///////////////////
	for(int row = arr.length-1; row>0;row--) {
		for(int col = 1; col<arr[row].length-1; col+=2) {
			if(arr[row][col]==arr[row-1][col]&& arr[row][col]==arr[row-2][col] && arr[row][col]!=' '&&arr[row][col]!='*') {
				check=true;
				char Vmatch= arr[row][col];
				int m=row;
				// Continue replacing until the end of the set starting from the Found set point
				while(m>0 && arr[m][col]== Vmatch) {
					arr[m][col]='*';
					m--;
				}
			}
		}
	}
	//Display(arr);
	return check;
}
//////////////////////////////Remove * and descend all above numbers////////////////////////////////////////////////
public static void removeAsterisksAndSlide(char [][] arr){ /////// arr is Grid
	for(int row=arr.length-1; row>0; row--) {
		for(int col=1; col<arr[row].length-1; col+=2) {
			int marker = row;
			////////////first replace all * with Space
			while(arr[marker][col]=='*'&& marker > 0) {
				// remove Asterisks and replace them with space
				arr[marker][col] = ' ';
				marker--;
				}
					
			}
		}
	
	for(int row = arr.length-1; row >1; row-- ) {
		for(int col =1 ; col<arr[row].length-1;col+=2) {
			//Check if any number is above space , if so then move them down and that completes the Remove and Slide action
			if(arr[row][col]==' '&&arr[row-1][col]!=' ') {
				arr[row][col]=arr[row-1][col];
				arr[row-1][col]=' ';
			}
		}
	}
}




///////////////end of the class//////////////////
}

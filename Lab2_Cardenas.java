import java.io.File;
import java.util.Scanner;

public class Lab2_Cardenas{
    public static void main(String[] args) {
        File cfile = new File("input.txt");

        String [] iArr = new String[amountDays(cfile)]; //i array is the amoung of total days there is in the input
        fileToiArray(cfile, iArr);
        int [][] twoDArray = iArrayto2DArray(iArr);
        pFirst(twoDArray);
        dailyAvg(twoDArray);
        avgTempPerTime(twoDArray);
        minMaxAvg(twoDArray);
        //the methods are dividied into sections in order to follow the format of the example and as well as sectionalize and identift problems within the code easier when making it
    }

    public static int amountDays(File fileName){//determins how long the iArr is going to be
		int amountLines = 0;	
        try{
			Scanner fileReader = new Scanner(fileName);
			while(fileReader.hasNextLine()){//counts how long the i array needs to be by counting the amount of lines in the txt file
				amountLines++;
				fileReader.nextLine();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
       return amountLines;
    }

    public static void fileToiArray(File fileName, String[] iArr){//converts the file into the iArray
		try {
			Scanner fileReader = new Scanner(fileName);
			for (int i = 0; i < iArr.length; i++){//for loop goes through each line in the txt file and sets it in the iArray for later use
				String word = fileReader.nextLine();
				iArr[i]= word;
			}
		} 
		catch (Exception e){
			e.printStackTrace();
		}
	}

    public static int [][] iArrayto2DArray(String [] iArr){
        int[][] jArr = new int[iArr.length][];
        try{
            for(int i=0;i<iArr.length;i++){//sets the j for each i so that the jagged array works
                Scanner stringReader = new Scanner(iArr[i]);
                int count = 0;
                while(stringReader.hasNextInt()){
                    count++;
                    stringReader.nextInt();
                }
                stringReader.close();//closes the scanner so you could use it from the begginging again 
                stringReader = new Scanner(iArr[i]);//reopens string reader
                jArr[i]= new int[count];
                for(int j = 0;j<count;j++){//for loop goes through the iArr and segments each int to place it into the 2d array
                    if(stringReader.hasNextInt()){
                        jArr[i][j]=stringReader.nextInt();
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return jArr;
    }
    
    public static void pFirst(int [][] twoDArray){
        System.out.println("The Input file is: input.txt");
        System.out.println("The Temperature data in the 2D Array is;");
        for(int i = 0;i<twoDArray.length;i++){//prints out the data in the 2d array
            for(int j = 0;j<twoDArray[i].length;j++){
                System.out.print(twoDArray[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    public static void dailyAvg(int[][]twoDArray){
        System.out.println("Daily averages:");
        for(int i = 0;i<twoDArray.length;i++){//goes through the 2d array and adds all the numbers within an i then divides it by the length in order to get the average
            double iAverage = 0;
            for(int j = 0;j<twoDArray[i].length;j++){
                iAverage += twoDArray[i][j];
            }
            System.out.print("Day "+(i+1)+": Average Temperature = "+iAverage/(twoDArray[i].length));
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    
    public static void avgTempPerTime(int[][]twoDArray){
        System.out.println("Average Temperature for Each Time Slot (Across All Days)");
        int maxTimeSlots = 0;
        for (int i = 0; i < twoDArray.length; i++) {//finds the amount of timeslots there is
            if (twoDArray[i].length > maxTimeSlots) {
                maxTimeSlots = twoDArray[i].length;
            }
        }
        for (int j = 0; j < maxTimeSlots; j++) {
            double iAverage = 0;
            int count = 0; //count is used to fine the avedrage
            for (int i = 0; i < twoDArray.length; i++) {//goes through each colume but if there is not a value it uses zero
                if (j < twoDArray[i].length) {
                    iAverage += twoDArray[i][j];
                    count++;
                } 
            }
            System.out.print("Time Slot "+(j+1)+": Average Temperature = "+iAverage/count);
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    public static void minMaxAvg(int[][]twoDArray){
        System.out.println("More Summary:");
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;//they are set the opposite so that i average will replace them
        int maxDay =0;
        int minDay =0;
        for(int i = 0;i<twoDArray.length;i++){//calculates the average but then checks if its less than the min(which is set to max)and or greater thatn the max(which is set to min)
            double iAverage = 0;
            for(int j = 0;j<twoDArray[i].length;j++){
                iAverage += twoDArray[i][j];
            }
            iAverage = iAverage/twoDArray[i].length;
            if(iAverage>max){
                max=iAverage;
                maxDay=i;
            }
            if(iAverage<min){
                min=iAverage;
                minDay=i;
            }
        }
        System.out.println("Day with Lowest Average Temperature: Day "+maxDay+": "+max);
        System.out.println("Day with Highest Average Temperature: Day "+minDay+": "+min);
    }


}
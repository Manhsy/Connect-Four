/*
a class that will interact with AI class by playing connect four
version: 2
date: 11.4.19
*/


import java.util.*;
public class ConnectFourPlay{
   /*
   global variables that will be used throughout the whole class 
   */
   public static char playerChar=' ';
   public static char AIChar=' ';
   public static ConnectFour board;
   public static int p = 0;
   
   /*
   main method where the program will run from where the user can 
   make the decision to move first or the AI
   as well as make selection for what character the user and AI will use 
   */
   
   public static void main(String[] args){
      
      Scanner console = new Scanner(System.in);
      
      boolean play = instruction(console);
      
      if(play == true){
         while(play==true){
         
            System.out.println("What dimension of board would you like to play with?");
            System.out.println("Enter the dimension in the order of row then column or 0 if you want to play with default size of board");
            int row = console.nextInt();
            int column = console.nextInt();
            while(row < 4 && row!=0|| column < 3 && column != 0 ){
               System.out.println("please re enter row and column that is bigger than 4X3");
               row = console.nextInt();
               column = console.nextInt();
            }
         
            if(row > 0 || column > 0){
               board = new ConnectFour(row, column);
            }else{
               board = new ConnectFour();
            }
         
            System.out.println("What character would you like the AI to use?");
            String aiStr = console.next();
            System.out.println("What character would YOU like to use?");
            String uStr = console.next();
         
            AIChar = aiStr.charAt(0);
            playerChar = uStr.charAt(0);
            play = gameOn(board, AIChar, console, play);  
               
         }
      }else{
         while(play==false){
            board = new ConnectFour();
         
            System.out.println("What character would you like the AI to use?");
            String aiStr = console.next();
         
            System.out.println("What character would YOU like to use?");
            String uStr = console.next();
         
            AIChar = aiStr.charAt(0);
            playerChar = uStr.charAt(0);
            play = gameOn(board, AIChar, console, play);
         }
      }
      
   }
   
   /*
   parameter the board, AI's character, console, and boolean for wether the user or AI will be going first 
   */
   public static boolean gameOn(ConnectFour board, char aiChar, Scanner console, boolean play){
      ConnectFourAI ai=new ConnectFourAI();
      if(play == true){
         while(board.winner()==' '){
            
            user(console, board);
            System.out.println(board);
            if(board.winner()==' '){
               ai.move(board,aiChar);
            }
            System.out.println(board);
         }
      }else{
         while(board.winner()==' '){
            
            ai.move(board,aiChar);
               
            
            if(board.winner()!=' '){
               System.out.println(board);
               break;
            }else{
               System.out.println(board);
               user(console, board);
               System.out.println(board);
            }
            
         }
      }
   
      if(board.winner() != ' ' && board.winner() != AIChar){
         System.out.println("Congrats you won!");
         return !play;
         
      }else if(board.winner()==AIChar){
         System.out.println("The AI won!");
         return !play;
      }
      return !play;
   }
   
   /*
   parameter console returns boolean to main method to decide if AI or User is going first 
   */
   public static boolean instruction(Scanner console){
      System.out.println("Welcome to a game called Connect Four agaisnt an AI "); 
      System.out.println("to win against the AI all you have to do connect four of your characters in a row");
      System.out.println("Would you like to make the first move? (only enter answer as yes or no)");
      String ans = console.next();
      if(ans.equalsIgnoreCase("yes")){
         return true;
      }else{
         return false;
      }
   }
   /*
   paramter of the board and console
   asks the user where they want their character to be dropped 
   */
   public static void user(Scanner console, ConnectFour board){
      try{
         System.out.println("Enter the column you want to drop");
         int col = console.nextInt();
         board.dropCol(col,playerChar);
      }catch(Exception e){
         System.out.println("column you entered might be full or not on the board");
      }
      
         
   }
}
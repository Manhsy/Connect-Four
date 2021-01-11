/**
Goal: write a artificial intelleigence that will control the computer's moves when a human plays against a computer
will be written similar to the math class which means it will contain static methods that provides services to toehr parts of a program
purpose: to make a move on a ConnectFour board 
version: 2 
date: 11.4.19
*/
import java.util.*;
public class ConnectFourAI{
   
   
   public static void move(ConnectFour board, char who){
      
      boolean proceed = true;
      alreadyHasWinner(board);
      
      char oppoChar = search(board, who);
      fullBoard(board);
      
      
      boolean boardEmpty = boardIsEmpty(board);
      if(boardEmpty){
         proceed = firstMove(board, who);
      }
      if(proceed){
         proceed = play(board,who, oppoChar);
      }
      if(proceed){
         proceed = blockOppo(board,who, oppoChar);
      }
      if(proceed){
         proceed = firstMove(board, who);
      }
   }
   public static boolean boardIsEmpty(ConnectFour board ){
      for(int i = 0; i < board.getRowCount(); i++){
         for(int j = 0; j < board.getColCount(); j++){
            if(board.get(i,j)!=' '){
               return false;
            }
         }
      }
      return true;
   }
   public static void alreadyHasWinner(ConnectFour board){
      if(board.winner()!=' '){
         throw new IllegalArgumentException();
      }
   }
   public static void fullBoard(ConnectFour board){
      for(int i = 0; i < board.getColCount(); i++){
         if(board.get(0,i)== ' '){
            return;
         }
      }
      throw new IllegalArgumentException();
   }
   /*
   searches for an element on the board that is not of the AI's and put that in opponent's variable 
   parameter board and AI's character and returns opponent's character 
   returns boolean 
   */
   public static char search(ConnectFour board, char who){
      int col = board.getColCount();
      int row = board.getRowCount();
      for(int i = 0; i < row; i++){
         for(int j = 0; j < col; j++){
            char character = board.get(i,j);
            if(character != who && character != ' '){
               return character;
            }
         }
      }
      return ' ';
   }
   
   /*
   parameter board, user's character and AI's character
   this method tries to make a winning move
   if it cannot then it will attack the user by blocking its win if one is found 
   returns boolean 
   */
   public static boolean play(ConnectFour board, char who, char oppoChar){
      int col = board.getColCount();
      for(int i = 0; i < col; i++){
         if(rowIsFull(board, i)== false){
            board.dropCol(i, who);
            if(board.winner()!=who){
               board.popCol(i);
               if(i == col-1){
                  return true;
               }
            }else{
               return false;
            }
         }
      
            
      }
      
   
      return true;
   }
   public static boolean rowIsFull(ConnectFour board, int i){
      if(board.get(0,i)!=' '){
         return true;
      }else{
         return false;
      }
      
   }
   /*
   paramter of board AI and user's character
   this method drops opponent's character in all the spots possible and if it results in a 
   winning move then it will take the user's character out and put theirs in 
   else, the method will move on to making a random move on the board 
   returns boolean 
   */
   public static boolean blockOppo(ConnectFour board, char who, char oppoChar){
      int col = board.getColCount();
        
      if(oppoChar!= ' '){
         for(int i = 0; i < col; i++){
            if(rowIsFull(board, i)==false){
               board.dropCol(i, oppoChar);
               if(board.winner()==oppoChar){
                  board.popCol(i);
                  board.dropCol(i, who);
                  return false;
               }
               board.popCol(i);
            }
              
              
         }
      }
        
        
      
      return true;
   
   }
   /*
   parameter of board and AI's character
   allows the AI to make a random move if all the methods above fails
   returns boolean 
   */
   public static boolean firstMove(ConnectFour board, char who){
   
      int r = board.getRowCount();
      int c = board.getColCount();
      Random rand = new Random();
      int col = rand.nextInt(c);
      int row = rand.nextInt(r);
      while(board.get(row,col)!=' '){
         col = rand.nextInt(c);
         row = rand.nextInt(r);
      }
      board.dropCol(col,who);
      
      return false;
   }
   
   
}

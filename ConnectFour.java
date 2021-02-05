/**
*A class representing a connect four boardgame. Upon creation, the user
is able to indicate what size of board they want to play with
(as long it is higher than 3(column)X4(row)). 
Else, they will be given the class's default size of board of 7(column)X6(row).
*@author: Manh Tu Sy
*/
public class ConnectFour{
   
   public static final int DEFAULT_ROW_SIZE = 6; 
   public static final int DEFAULT_COL_SIZE = 7; 
   private int r;
   private int c;
   private char[][] board = new char[r][c]; 
   /*@parameters: Accepts user's input of the size of the board they want to play with
   as long as r entered is bigger than 4 and c enter is bigger than 3
   *@throws an argument if integers entered does not satisfy the requirement stated above 
   *set the size of the board to the newly entered number if it passed the repuirement
   */
   
    
   public char popCol(int c) {
      if (c < 0 || c > this.c - 1)
         throw new IndexOutOfBoundsException("Out of bounds");
      
      int i;
      for (i = 0 ; i < this.r ; i++) {
         if (board[i][c] != ' ')
            break;
      }
      int theRow = i ;
      if (theRow == this.r)
         throw new IndexOutOfBoundsException("Empty Column");
         
      char val = board[theRow][c];
    // set the column to space
      board[theRow][c] = ' ';
      return val;
   }
   
   public ConnectFour(int row, int column){
      if(row < 4 || column < 3){
         throw new IllegalArgumentException("Size indicated cannot be accepted");
      }else{
         this.r = row;
         this.c = column;
         this.board = new char[r][c];
      }
      for(int i = 0; i < r; i ++){
         for(int k = 0; k < c; k++){
            board[i][k]=' ';
         }
      }
   }
   /*If user does not specify the size of the board, create the board with the size of 
   the class constant which was declared as row being 6 and column being 7
   */
   public ConnectFour(){
      this.r = DEFAULT_ROW_SIZE;
      this.c = DEFAULT_COL_SIZE;
      this.board = new char[r][c];
      for(int i = 0; i < r; i ++){
         for(int k = 0; k < c; k++){
            board[i][k]=' ';
         }
      }
      
   }
   /*@accepts the parameter of integers which indicated the row and column that the user want
   to know the element of
   *if the element of the chosen rowxcolumn, return e, shorthand for empty. Else, if not empty 
   *@return the element at the chosen rowxcolumn
   */
   public char get(int r, int c){
      return board[r][c];
      
   }
   /*@accepts the parameter of int which indicates which column the user want to drop their element
   and also char, to indicate what element they want to drop
   *try to loop through all the rows of the column, for as long as the element looped is empty, increase a count 
   called position 
   *when the looped element is no longer empty, set the char enterd at the space of (row,position-1)
   *if the user enter a column out of bound or a column that has already been filled, throw an exception
   
   */
   public void dropCol(int c, char ch){
      int position=0;
      // if(board[0][c]!=' '){
   //          try{
   //             throw new IndexOutOfBoundsException("That is not on the board");
   //          }catch(Exception e){
   //          }
   //       }
      int loop = 0;
      for(int i = 0; i < this.r; i++){
         loop++;
         if(board[i][c]!=' '&&loop!=1){
            position = i-1;
            break;
         }else if(board[i][c]!=' '&&loop==1){
            board[i][c]=ch;
            break;
         }else{
            position = i;
         }
         
      }
      if(loop!=1){
         board[position][c] = ch;
      }
      
      
   }
   /*accepted no parameters 
   *@return the value of r
   */
   public int getRowCount(){
      return r;
   }
   /*Accepts no parameter 
   *@return the value of c
   */
   public int getColCount(){
      return c;
   }
   
   /*accepts no parameter
   *call upon methods called horizontal, vertical, diag1, diag2 to see if any of them returns
   a char 
   *if one of the above methods return a char, winner will return that char, 
   else, winner will return nothing
   */
   public char winner(){
   
      char h = horizontal();
      char v = vertical();
      char d1 = diag1();
      char d2 = diag2();
      if(h!=' '){
         return h;
      }else if(v!=' '){
         return v;
      }else if(d1!=' '){
         return d1;
      }else if(d2!= ' '){
         return d2;
      }else{
         return ' ';
      }
   
      
   }
   /*method loops for diagonal win that starts from the right to the left
   *loops through the rows and column of the board till an element is found
   *once found, place the element in Char found then enter a loop that goes 
   on for three time ONLY if the i(row) j(column) is at an index where a diaganol win
   that starts from the right can happen
   *once loop is entered, increase the count of count to indicate how many of alike element are found. 
   when it reaches four, return the Char of found
   *if entered and fail to meet all the condition, loop back to j and i to find the next non element element
   and reset count
   *@return char found that reaches count of 4
   */
   public char diag1(){
      int count = 1;
      for(int i = 0; i < r; i ++){
         for(int j = 0; j < c; j++){
            count = 1;
            if(board[i][j]!=' '){
               if(j <= c-4 && i <= r-4){
                  char found = board[i][j];
                  for(int y = 1; y < 4; y++){
                     if(board[i+y][j+y]==found){
                        count++;
                        if(count == 4){
                           return found;
                        }
                     }
                  }
               }
               
            }
         }
      }
      return ' ';
   }
   /*method test for a diagonal win that starts from the right
   *a loop of i(row) j (column) is made to traverse the indexes of board till it find a non empty element. 
   *once found and the index of i and j surpass the requirement of what i and j need to be in order for a 
   diagonal win that starts from the right, then keep that non empty element in a Char named found
   *then enter a loops that goes on for three times to test every element that is down and to the left of Char found
   when four matches is found, variable count increase to four so the loop breaks and return whatever Char found is
   *if it doesn't find four in a row, exit the loop and return to the loops of i and j while resetting the number of count
   *@return char found that reaches count of 4
   */
   public char diag2(){
      int count = 1;
      for(int i = 0; i < r; i ++){
         for(int j = 0; j < c; j++){
            count = 1;
            if(board[i][j]!=' '){
               if(j >= c-4 && i <= r-4){
                  char found = board[i][j];
                  for(int y = 1; y < 4; y++){
                     if(board[i+y][j-y]==found){
                        count++;
                        if(count == 4){
                           return found;
                        }
                     }
                  }
               }
               
            }
         }
      }
      return ' ';
   }
   /*method that test for horizontal win
   *loops through board till a non empty element is found
   *once found keep that element in found and enter a loops that goes on for three times
   ONLY if the index of row and column allows a horizontal win
   *once the loop is entered test for element next to Char found and if its the same of Char found,
   increase int oin, when oin reaches four, return Char found
   *else, exit out of the loop and go back to i and j loop
   *@return char found that reaches count of 4
   */
   public char horizontal(){
      //TEST FOR HORIZONTAL WIN
      
      int oin = 1;
      for(int i = 0; i < r; i++){
         
         for(int u = 0; u < c; u++){
            oin = 1;
            if(board[i][u]!=' '){
               char found = board[i][u];
               if(u<=c-4){
                  for(int y = 1; y < 4; y++){
                     if(board[i][u+y]==found){
                        oin++;
                        if(oin==4){
                           return found;
                        }
                     }
                  }
               }
            
            }
         }
      }
      return ' ';
   }
   /*method that test for vertical win
   *loops through board till a non empty element is found
   *once found keep that element in found and enter a loops that goes on for three times
   ONLY if the index of row and column can even have a vertical win
   *once the loop is entered test for element next to Char found and if its the same of Char found,
   increase int xin, when xin reaches four, return Char found
   *else, exit out of the loop and go back to i and j loop
   *@return char found that reaches count of 4
   */
   public char vertical(){
      int xin = 1;
      
      
      for(int i = 0; i < r; i++){
         
         for(int j = 0; j < c; j++){
            xin = 1;
            if(board[i][j]!=' '){
               char found = board[i][j];
               if(i<=r-4){
                  for(int y = 1; y < 4; y ++){
                     if(board[i+y][j]==found){
                        xin++;
                        if(xin==4){
                           return found;
                        }
                     }
                  }
               }
            }
         }
      }
      return ' ';
   }
   
   /*@method that returns the board in the form of a String
   /*loops through board and fill in the rows and column of board with different character
   that will be appealing the user's eyes once it is printed
   */
   public String toString(){
   
      String bigStuff = "";
    
      for(int i = 0; i < this.r; i++){
         String small = "";
         for(int j = 0; j < this.c-1; j++){
            bigStuff += board[i][j] + "|";
         }
         bigStuff += board[i][this.c -1];
         bigStuff += "\n";
         for(int k = 0; k <= this.c - 2; k++){
            small += "-+";
         }
         bigStuff += small;
         bigStuff += "-\n";
      }
      return bigStuff;
         
   }
    

}

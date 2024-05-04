# MagicalArena
-Language used : JAVA

##Steps to run the code 
-1)Run the code in one of the java compiler
-2)The code prints introduction and the rules of the game .Also displays the "Health","Strength" and "attack" attribute values for each player 
-3)Turn one:Player 1 as Attcaker ,Palayer 2 as defender
   -then asks for the  player1 input :
   -asks the player1 to roll the dice by entering number 8
   -if the player has enter the number 8 the dice is rolled and a random number is displayed
   -if the player has entered a number other than 8 ,then other 2 chances are give to enter a correct number that is 8 
   -if the player has entered a charecter  other than a number  ,then other 2 chances are give to enter a correct number that is 8 
   -The input is taken from other player also ,and the below logic is performed
   -Damage of player2=((player1 dice value *attack)-(player2 dice value *strength))
   -Health of player2=Health of player2 - Damage
-4)turn two:Player 2 as Attcaker ,Palayer 1 as defender
   -then asks for the  player1 input :
   -asks the player1 to roll the dice by entering number 8
   -if the player has enter the number 8 the dice is rolled and a random number is displayed
   -if the player has entered a number other than 8 ,then other 2 chances are give to enter a correct number that is 8 
   -if the player has entered a charecter  other than a number  ,then other 2 chances are give to enter a correct number that is 8 
   -The input is taken from other player also ,and the below logic is performed
   -Damage of player1=((player2 dice value *attack)-(player1 dice value *strength))
   -Health of player1=Health of player1 - Damage
  -5)The above steps 3 and 4 are repeated until the health of any of the player becomes zero ,and the opponent is declared as winner

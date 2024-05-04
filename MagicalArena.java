/*
 ************************************************************************************************************
 File Name:MagicalArena.java
 Authour:Sandeep Reddy 
 ************************************************************************************************************
 */

/* ************************ Import **************************************** */
import java.util.Scanner;
import java.util.Random;

/*
 ************************************************************************************************************
 Class Name     :Player
 Description    :Class to represent a player  with required attributes 
 ************************************************************************************************************
 */
class Player {
    private String name;
    private int health;
    private int strength;
    private int attack;
    private int wrongInputCount; // Track the number of consecutive wrong inputs

    public Player(String name, int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
        this.wrongInputCount = 0; // Initialize wrong input count
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAttack() {
        return attack;
    }

    public int calculateDamage(int diceRoll) {
        return diceRoll * attack;
    }

    public void reduceHealth(int damage) {
        health -= damage;
    }
      public void incrementWrongInputCount() {
        wrongInputCount++;
    }

    public void resetWrongInputCount() {
        wrongInputCount = 0;
    }

    public int getWrongInputCount() {
        return wrongInputCount;
    }
}
/*
 ************************************************************************************************************
 Class Name     :MagicalArena
 Description    :Class to represents the magical arena to start the game ,get the random value for dice ,
                 get the user input and check for the valid player input 
 ************************************************************************************************************
*/
class MagicalArena {
    private Player playerA;
    private Player playerB;
    private Random random;
    private Scanner scanner;

    public MagicalArena(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }
    
    public  int rollDice() {
        return random.nextInt(6) + 1; // Generates a random number  from 1 to 6
    }
    public void startMatch() {
        // Display player attributes only once in the beginning
        System.out.println("\nPlayer 1: Health  " + playerA.getHealth() +
                    ", Strength  " + playerA.getStrength() +
                    ", Attack  " + playerA.getAttack());
        System.out.println("Player 2: Health  " + playerB.getHealth() +
                    ", Strength  " + playerB.getStrength() +
                    ", Attack  " + playerB.getAttack());
        while (playerA.getHealth() > 0 && playerB.getHealth() > 0) {
            // Player 1's turn:Player 1 as Attcaker ,Palayer 2 as defender
            System.out.println("\n Player 1's turn:Player 1 as Attcaker ,Palayer 2 as defender:");
            int attackRollA = getPlayerInput(playerA);
            if (attackRollA == -1) {
                break;
            }
            System.out.println("Player 1 rolled " + attackRollA + " for attack.");

            int defenseRollB = getPlayerInput(playerB);
            if (defenseRollB == -1) {
                break;
            }
            System.out.println("Player 2 rolled " + defenseRollB + " for defense.");

            int attackDamageA = playerA.calculateDamage(attackRollA);
            int defenseDamageB = playerB.getStrength() * defenseRollB;
            int actualDamageA = Math.max(0, attackDamageA - defenseDamageB);
            playerB.reduceHealth(actualDamageA);

            System.out.println("Player 1 attacks with roll " + attackRollA +
                    ", Player 2 defends with roll " + defenseRollB +
                    ", dealing " + actualDamageA + " damage.");
            System.out.println("Player 1: Health - " + playerA.getHealth());
            System.out.println("Player 2: Health - " + playerB.getHealth());

            if (playerB.getHealth() <= 0) {
                break;
            }

            // Player 2's turn:Player 2's turn:Player 2 as Attcaker ,Palayer 1 as defender
            System.out.println("\nPlayer 2's turn:Player 2's turn:Player 2 as Attcaker ,Palayer 1 as defender:");
            int attackRollB = getPlayerInput(playerB);
            if (attackRollB == -1) {
                break;
            }
            System.out.println("Player 2 rolled " + attackRollB + " for attack.");

            int defenseRollA = getPlayerInput(playerA);
            if (defenseRollA == -1) {
                break;
            }
            System.out.println("Player 1 rolled " + defenseRollA + " for defense.");
            int attackDamageB = playerB.calculateDamage(attackRollB);
            int defenseDamageA = playerA.getStrength() * defenseRollA;
            int actualDamageB = Math.max(0, attackDamageB - defenseDamageA);
            playerA.reduceHealth(actualDamageB);
            System.out.println("Player 2 attacks with roll " + attackRollB +
                    ", Player 1 defends with roll " + defenseRollA +
                    ", dealing " + actualDamageB + " damage.");
            System.out.println("Player 1: Health - " + playerA.getHealth());
            System.out.println("Player 2: Health - " + playerB.getHealth());
            if (playerA.getHealth() <= 0) {
                break;
            }
        }
        // Close scanner
        scanner.close();
        // Declare the winner
        System.out.println("\nGame over!");
        if (playerA.getHealth() <= 0) {
            System.out.println("Player 2 wins!");
        } 
        
    }

    int getPlayerInput(Player player) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter 8 to roll the dice.");
            if (!scanner.hasNextInt()) {
                scanner.next(); // Clear input buffer
                System.out.println("Invalid input. Please enter a number.");
                player.incrementWrongInputCount();
                if (player.getWrongInputCount() >= 3) {
                    System.out.println("Player " + (player == playerA ? "2" : "1") + " wins! Player " + (player == playerA ? "1" : "2") + " made too many consecutive wrong inputs.");
                    return -1;
                }
                continue;
            }
            int choice = scanner.nextInt();
            if (choice != 8) {
                System.out.println("Invalid input. Player " + player.getName() + " must press 8 to roll the dice.");
                player.incrementWrongInputCount();
                if (player.getWrongInputCount() >= 3) {
                    System.out.println("Player " + (player == playerA ? "2" : "1") + " wins! Player " + (player == playerA ? "1" : "2") + " made too many consecutive wrong inputs.");
                    return -1;
                }
            } else {
                player.resetWrongInputCount(); // Reset wrong input count
                return rollDice();
            }
        }
        return -1;
    }
}
/*
 ************************************************************************************************************
 Class Name     :Main
 Description    :The Main class gives the Introduction and Rules of the game and starts the match and declares 
                 the winner    
 ************************************************************************************************************
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to MAGICAL ARENA!!\n"+"Rules:");
        System.out.println("**Every Player has HEALTH,STRENGTH and an ATTACK Powers.\n"+"**Attacking player rolls the attacking dice and the defending player rolls the defending dice.\n"+ "**The ATTACK  value multiplied by the outcome of the  attacking dice roll is the damage created by the attacker.\n"+"**The defender Strength value, multiplied by the outcome of the defending dice is the damage defended by the defender.\n"+ "**Whatever damage created by attacker which is in excess of the damage defended by the defender will reduce the HEALTH of the defender\n"+"**The player dies if his HEALTH touches 0.\n"+"ROLL THE DICE WITH HOPE,FOR THE BEST NUMBERS.Good Luck,Have a nice game");
        // Create players
        Player playerA = new Player("Player 1", 50, 5, 10);
        Player playerB = new Player("Player 2", 100, 10, 5);
        // Create magical arena
        MagicalArena arena = new MagicalArena(playerA, playerB);
        // Start match
        arena.startMatch();
    }
}

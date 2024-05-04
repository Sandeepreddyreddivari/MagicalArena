import java.util.Scanner;
import java.util.Random;
// Class to represent a player
class Player {
    private String name;
    private int health;
    private int strength;
    private int attack;

    public Player(String name, int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
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
}

// Class to represent the magical arena
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

    private int rollDice() {
        return random.nextInt(6) + 1; // Generates a random number between from 1 to 6
    }

    public void startMatch() {
        while (playerA.getHealth() > 0 && playerB.getHealth() > 0) {
            // Player 1's turn
            System.out.println("Player 1: Press 7 to roll the Attacker dice.");
            int choice = scanner.nextInt();
            if (choice != 7) {
                System.out.println("Invalid input. Player 1 must press 7 to roll the Attacker dice.");
                continue;
            }
            int attackRollA = rollDice();
            System.out.println("Dice outcome for attack : " + attackRollA );

            System.out.println("Player 2: Press 7 to roll the Defender dice.");
            choice = scanner.nextInt();
            if (choice != 7) {
                System.out.println("Invalid input. Player 2 must press 6 to roll the Defender dice.");
                continue;
            }
            int defenseRollB = rollDice();
            System.out.println("Player 2 rolled " + defenseRollB + " for defense.");

            int attackDamageA = playerA.calculateDamage(attackRollA);
            int defenseDamageB = playerB.getStrength() * defenseRollB;
            int actualDamageA = Math.max(0, attackDamageA - defenseDamageB);
            playerB.reduceHealth(actualDamageA);

            System.out.println("Player 1 attacks with roll " + attackRollA +
                    ", Player 2 defends with roll " + defenseRollB +
                    ", dealing " + actualDamageA + " damage.");

            if (playerB.getHealth() <= 0) {
                break;
            }

            // Player 2's turn
            System.out.println("Player 2: Press 7 to roll the dice.");
            choice = scanner.nextInt();
            if (choice != 7) {
                System.out.println("Invalid input. Player 2 must press 5 to roll the dice.");
                continue;
            }
            int attackRollB = rollDice();
            System.out.println("Player 2 rolled " + attackRollB + " for attack.");

            System.out.println("Player 1: Press 7 to roll the dice.");
            choice = scanner.nextInt();
            if (choice != 7) {
                System.out.println("Invalid input. Player 1 must press 6 to roll the dice.");
                continue;
            }
            int defenseRollA = rollDice();
            System.out.println("Player 1 rolled " + defenseRollA + " for defense.");

            int attackDamageB = playerB.calculateDamage(attackRollB);
            int defenseDamageA = playerA.getStrength() * defenseRollA;
            int actualDamageB = Math.max(0, attackDamageB - defenseDamageA);
            playerA.reduceHealth(actualDamageB);

            System.out.println("Player 2 attacks with roll " + attackRollB +
                    ", Player 1 defends with roll " + defenseRollA +
                    ", dealing " + actualDamageB + " damage.");

            if (playerA.getHealth() <= 0) {
                break;
            }
        }

        // Close scanner
        scanner.close();

        // Declare the winner
        System.out.println("Game over!");
        if (playerA.getHealth() <= 0) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("Player 1 wins!");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to MAGICAL ARENA!!\n"+"Rules:");
        System.out.println("**Every Player has HEALTH,STRENGTH and an ATTACK Powers.\n"+"**Attacking player rolls the attacking dice and the defending player rolls the defending dice.\n"+ "**The ATTACK  value multiplied by the outcome of the  attacking dice roll is the damage created by the attacker.\n"+"**The defender Strength value, multiplied by the outcome of the defending dice is the damage defended by the defender.\n"+ "**Whatever damage created by attacker which is in excess of the damage defended by the defender will reduce the HEALTH of the defender\n"+"**The player dies if his HEALTH touches 0.\n"+"Good Luck,Have a nice game");
        // Create players
        Player playerA = new Player("Player 1", 40, 5, 10);
        Player playerB = new Player("Player 2", 30, 10, 5);

        // Create magical arena
        MagicalArena arena = new MagicalArena(playerA, playerB);

        // Start match
        arena.startMatch();
    }
}

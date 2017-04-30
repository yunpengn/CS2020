import java.util.Random;

/**
 * @author Jiayee
 *
 */
public class Slide32Exercise04Greeter {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Random generator = new Random();
        int numBuffs = 10;
        int buffNumber = generator.nextInt(numBuffs);      // random number in this range: [0, 10)
        String message = "";

        switch(buffNumber) {
            case 0:
                message = "x1.5 DROP RATE";
                break;
            case 1:
                message = "x1.5 EXP RATE";
                break;
            case 2:
                message = "+10% DAMAGE";
                break;
            case 3:
                message = "+10% DEFENCE";
                break;
            case 4:
                message = "-20% DAMAGE RECEIVED";
                break;
            case 5:
                message = "-50% EXP LOSS FROM DEATH";
                break;
            case 6:
                message = "25% DISCOUNT FOR ENHANCEMENTS AND REPAIRS";
                break;
            case 7:
                message = "x2 PARTY LOOT";
                break;
            case 8:
                message = "+30% CRITICAL RATE";
                break;
            case 9:
                message = "60 SECONDS OF SUPER ARMOUR";
                break;
            default:
                message = "BUFF FAILED";
        }

        System.out.println(message);
    }

}

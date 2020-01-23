package src.game;

public class generateCode {
    private java.util.Random rndGenerator = new java.util.Random();
    private int randomCode;
    private String randomCodeString;
    private String[] randomSplitString;
    public final static int NUMBER_OF_VALUES = 9999;


    public generateCode() {
        /**
         * Generates a random 4 digit number as key to the pharmacy
         */
        this.randomCode = rndGenerator.nextInt(NUMBER_OF_VALUES);//gets a random int that's between 0 and 9999
        this.randomCodeString = String.format("%04d", this.randomCode);//turns the int into a formatted string to make sure there are always 4 numbers
        this.randomCode = Integer.parseInt(this.randomCodeString); //converts the string back to an int
    }

    public void setRandomCode(){
        this.randomCode = randomCode;
    }

    public void setRandomCodeString(){
        this.randomCodeString=randomCodeString;
    }

    public String getRandomCodeString() {
        return randomCodeString;
    }


    public int getRandomCode() {
        return randomCode;
    }
}


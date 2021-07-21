package game;

import java.util.Random;

public class Probability {
    /**
     * Random number generator
     */
    private Random random;
    private double rainProbability[] = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6};

    /**
     * Probability constructor
     */
    public Probability() {
        random = new Random();
    }

    /**
     * To obey to DRY principle, to calculate all chances by generating a random number
     *
     * @param chances the probability to achieve some action
     * @return true/false
     */
    public boolean getChances(int chances) {
        return random.nextInt(100) < chances;
    }

    /**
     * To calculate the number of sips
     * random number 0 (inclusive) - 6 (exclusive) is generated to represent the index of rainProbability
     * random number 0 = 0.1 [at index 0 of rainProbability]
     * random number 1 = 0.2 [at index 1 of rainProbability]
     * random number 2 = 0.3 [at index 2 of rainProbability]
     * random number 3 = 0.4 [at index 3 of rainProbability]
     * random number 4 = 0.5 [at index 4 of rainProbability]
     * random number 5 = 0.6 [at index 5 of rainProbability]
     *
     * @return number of sips to add into the lake
     */
    public int getRainFallSips() {
        int randomNum = random.nextInt(6);
        return (int)(rainProbability[randomNum] * 20);
    }

}

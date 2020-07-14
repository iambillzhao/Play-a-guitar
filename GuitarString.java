/**
 * GuitarString will simulate a guitar of a given frequency.
 *          This class may pluck, tic, and sample the simulated
 *          guitar strings.
 *          Note that this class is not case-sensitive and may 
 * 
 * @author Bill Zhao
 * @version 07/09/2019
 */

import java.util.*; //import java utility library to use queues
public class GuitarString {
    private Queue<Double> ringBuffer; //the buffer that stores frequencies

    private static final double DECAY_FACTOR = 0.996; //define the decay factor

    private int desiredCapacity; //declare desired capacity, sampling rate divided 
                                 //by frequency, rounded to the nearest integer

    /**
     * Contructs and saves a guitar string of a given frequency.
     *          Throws an IllgealArgumentException if illegal, 
     *          (<= 0) unsupported frequency value is passed.
     * @param frequency     the given frequency value
     * 
     * no @return
     */
    public GuitarString(double frequency) {
        desiredCapacity = (int)Math.round(StdAudio.SAMPLE_RATE/frequency);

        if (frequency <= 0 || desiredCapacity < 2) {
            throw new IllegalArgumentException();
        }

        ringBuffer = new LinkedList<Double> ();
        for (int i = 0; i < desiredCapacity; i++) {
            ringBuffer.add(0.0);
        }
    }

    /**
     * Contructs and saves a guitar string of the given values.
     *          Throws an exception if the given list is illegal
     *          or insufficient to establish the ring buffer.
     * @param init      the given values of desired size
     * 
     * no @return
     */
    public GuitarString(double[] init) {
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }

        ringBuffer = new LinkedList<Double> ();
        for (int i = 0; i < desiredCapacity; i++) {
            ringBuffer.add(init[i]);
        }
    }

    /**
     * Replaces the values with random values between 
     *          -0.5 and 0.5.
     * no @param 
     * 
     * no @return
     */
    public void pluck() {
        for (int i = 0; i < desiredCapacity; i++) {
            ringBuffer.remove();
            ringBuffer.add(Math.random() - 0.5);
        }
    }

    /**
     * Applys the Karplus-Strong update, saves the new value
     *          to the program.
     * no @param 
     * 
     * no @return
     */
    public void tic() {
        double one = ringBuffer.remove();
        double two = ringBuffer.peek();

        ringBuffer.add((one+two) / 2 * DECAY_FACTOR);
    }

    /**
     * Gets the current sample, which is the value at the front
     *          of the ring buffer.
     * no @param 
     * 
     * @return the current sample
     */
    public double sample() {
        return (ringBuffer.peek());
    }
} //class GuitarString ends
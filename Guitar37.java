/**
 * Guitar37 will simulate guitar strings of accepted keys.
 *          This class may check, play, pluck, sample,
 *          and tic desired guitar strings. 
 *          Note that this class is not case-sensitive and may 
 *          return exceptions when illegal values are passed.
 * 
 * @author Bill Zhao
 * @version 07/09/2019
 */

import java.util.*; // import java utility library to use arrays and queues

public class Guitar37 implements Guitar {
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout

    private static final int ACCEPTED_KEYS = 37; // the number of accepted keys 
                                                 // in the program

    private GuitarString[] string = new GuitarString[ACCEPTED_KEYS]; 
                        // construct guitar strings of accepted keys

    private int ticed; // count the times tic method was called

    /**
     * Constructs and saves guitar strings of accepted keys.
     *          The defined accepted keys by the program will
     *          determine the number of guitar strings.
     * no @param 
     * 
     * no @return
     */
    public Guitar37() {
        for (int i = 0; i < ACCEPTED_KEYS; i++) {
            double frequency = 440.0 * Math.pow(2, ((i - 24.0) / 12));
            string[i] = new GuitarString(frequency);
        }
    }

    /**
     * Plays the guitar string of the given value of pitch.
     *          The value of pitch must be legal for this guitar.
     * @param pitch     the given value of pitch
     * 
     * no @return
     */
    public void playNote(int pitch) {
        if (pitch >= -24 && pitch <= 12) {
            string[pitch + 24].pluck();
        }
    }

    /**
     * Checks if the given character is accepted by the program.
     *          The character should be checked if they are one
     *          of the characters defined to be legal by the 
     *          program.
     * @param key       the character to check
     * 
     * @return true if the character is accepted and false otherwise.
     */
    public boolean hasString(char key) {
        return (KEYBOARD.indexOf(key) != -1);
    }

    /**
     * Plucks the guitar string of the given character, which will
     *          generate random values from -0.5 to 0.5. Throws an 
     *          IllegalArgumentException if the character is not
     *          accepted by the program.
     * @param key       the given character to pluck
     * 
     * no @return
     */
    public void pluck(char key) {
        if (hasString(key) == false) {
            throw new IllegalArgumentException();
        } else {
            string[KEYBOARD.indexOf(key)].pluck();
        }
    }

    /**
     * Determines the total of the values of guitar string samples.
     * no @param 
     * 
     * @return result   the value of all guitar string samples.
     */
    public double sample() {
        double result = 0.0;
        for (int i = 0; i < ACCEPTED_KEYS; i++) {
            result += string[i].sample();
        }
        return result;
    }

    /**
     * Applies the Karplus-String updates and keeps track of the 
     *          times this method was called.
     * no @param 
     * 
     * no @return
     */
    public void tic() {
        for (int i = 0; i < ACCEPTED_KEYS; i++) {
            string[i].tic();
        }
        ticed++;
    }

    /**
     * Gets the number of times the method tic was called.
     * no @param 
     * 
     * @return ticed    the count of the method tic was called
     */
    public int time() {
        return ticed;
    }
} //class Guitar37 ends
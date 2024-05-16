package frc.robot;

import edu.wpi.first.wpilibj.Timer;

/**
 * Restricts the rate of change of a value
 */
public class MotionProfile {
    private final Timer timer;
    private final double speed;
    private final double maxMagnitude;
    private double value;

    /**
     * Instantiates the Motion Profile
     *
     * @param speed the maximum speed
     * @param maxMagnitude the maximum output value magnitude
     */
    public MotionProfile(double speed, double maxMagnitude) {
        timer = new Timer();
        timer.start();
        this.speed = speed;
        this.maxMagnitude = maxMagnitude;
    }

    /**
     * Calculates and stores the restricted output value
     *
     * @param inputValue the input value
     *
     * @return the restricted output value
     */
    public double calculate(double inputValue) {
        inputValue = clip(inputValue, -maxMagnitude, maxMagnitude);

        double timePassed = timer.get();
        timer.reset();

        double deltaValue = inputValue - value;
        double maxDelta = timePassed * speed;

        if(deltaValue > maxDelta)
            value += maxDelta;
        else if(deltaValue < -maxDelta)
            value -= maxDelta;
        else
            value = inputValue;

        return value;
    }

    private double clip(double input, double min, double max) {
        if(input >= max)
            return max;
        if(input <= min)
            return min;
        return input;
    }
}
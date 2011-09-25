package com.kokakiwi.maths.generator.sample.params;

import com.kokakiwi.maths.generator.world.env.Parameter;

public class Snow extends Parameter
{
    
    public Snow(String seed)
    {
        super("snow", seed, 42132114);
    }

    @Override
    protected double value(double x, double y, double z)
    {
        double value = 0.0;
        
        value += noise.fBm(0.001 * x, 0.001 * y, 0.001 * z, 8, 1.3464641, 1.46156142) + 0.465654;
        
        return value;
    }
    
}

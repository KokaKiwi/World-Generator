package com.kokakiwi.maths.generator.sample.params;

import com.kokakiwi.maths.generator.world.env.Parameter;

public class Oasis extends Parameter
{
    
    public Oasis(String seed)
    {
        super("oasis", seed, 342246);
    }

    @Override
    protected double value(double x, double y, double z)
    {
        double o = noise.fBm(0.009 * x, 0.009 * y, 0, 4, 1.3646, 1.263424);
        
        return o;
    }
    
}

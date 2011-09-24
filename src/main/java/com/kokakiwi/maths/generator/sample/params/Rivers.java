package com.kokakiwi.maths.generator.sample.params;

import com.kokakiwi.maths.generator.world.env.Parameter;

public class Rivers extends Parameter
{
    
    public Rivers(String seed)
    {
        super("rivers", seed, 462164);
    }

    @Override
    public double value(double x, double y, double z)
    {
        double h = noise.fBm(x * 0.01, 0.01 * y, 0.01 * (z + 1), 3, 2.1836171, 0.9631);
        h = Math.sqrt(Math.abs(h));
        return h;
    }
    
}

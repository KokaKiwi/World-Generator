package com.kokakiwi.maths.generator.sample.params;

import com.kokakiwi.maths.generator.world.env.Parameter;

public class Volcano extends Parameter
{
    
    public Volcano(String seed)
    {
        super("volcano", seed, 6262146);
    }
    
    @Override
    protected double value(double x, double y, double z)
    {
        double value = 0.0;
        
        value += noise.fBm(0.0003 * x, 0.0003 * y, 0.0003 * z, 3, 2.6665,
                0.464612) + 0.26445456;
        
        return value;
    }
    
}

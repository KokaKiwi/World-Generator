package com.kokakiwi.maths.generator.sample.params;

import com.kokakiwi.maths.generator.world.env.Parameter;

public class HeightMap extends Parameter
{
    
    public HeightMap(String seed)
    {
        super("heightmap", seed, 123244);
    }
    
    @Override
    public double value(double x, double y, double z)
    {
        double result = 0.0;
        
        result += noise.fBm(0.0009 * x, 0.0009 * y, 0.0009 * z, 10, 2.2341,
                0.94321) + 0.4;
        
        return result;
    }
    
}

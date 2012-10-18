package com.kokakiwi.maths.generator.sample.params;

import com.kokakiwi.maths.generator.world.env.Parameter;

public class Forest extends Parameter
{
    
    public Forest(String seed)
    {
        super("forest", seed, 1115221652);
    }
    
    @Override
    public double value(double x, double y, double z)
    {
        double result = 0.0;
        
        result += noise.fBm(0.0009 * x, 0.0009 * y, 0.0009 * z, 5, 2.2351,
                1.422561) + 0.4;
        
        return result;
    }
    
}

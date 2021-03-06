package com.kokakiwi.maths.generator.world.env;

import com.kokakiwi.maths.generator.world.utils.PerlinNoise;

public abstract class Parameter
{
    protected final String      name;
    protected final PerlinNoise noise;
    protected double            value = -1.0;
    
    public Parameter(String name, String seed, int salt)
    {
        this.name = name;
        this.noise = new PerlinNoise(seed.hashCode() + salt);
    }
    
    public String getName()
    {
        return name;
    }
    
    public PerlinNoise getNoise()
    {
        return noise;
    }
    
    public double getValue(double x, double y)
    {
        return getValue(x, y, 0.0);
    }
    
    public double getValue(double x, double y, double z)
    {
        if(this.value == -1.0)
        {
            double value = value(x, y, z);
            this.value = value;
            
            return value;
        }

        return this.value;
    }
    
    public void reset()
    {
        this.value = -1.0;
    }
    
    protected abstract double value(double x, double y, double z);
}

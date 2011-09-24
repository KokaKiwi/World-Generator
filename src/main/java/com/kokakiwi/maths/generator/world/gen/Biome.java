package com.kokakiwi.maths.generator.world.gen;

import java.awt.Color;

import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.env.Parameter;

public abstract class Biome
{
    protected final WorldGenerator generator;
    
    public Biome(WorldGenerator generator)
    {
        this.generator = generator;
    }
    
    protected <T extends Parameter> double getValue(Class<T> clazz, double x, double y)
    {
        return getValue(clazz, x, y, 0.0);
    }
    
    protected <T extends Parameter> double getValue(Class<T> clazz, double x, double y, double z)
    {
        return getParameter(clazz).getValue(x, y, z);
    }
    
    protected <T extends Parameter> T getParameter(Class<T> clazz)
    {
        return generator.getEnvironment().getParameter(clazz);
    }
    
    public abstract boolean check(double x, double y);
    
    public abstract Color getColor(double x, double y);
}

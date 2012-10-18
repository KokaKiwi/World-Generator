package com.kokakiwi.maths.generator.world.env;

import com.kokakiwi.maths.generator.world.Tile;
import com.kokakiwi.maths.generator.world.WorldGenerator;

public abstract class Biome
{
    protected final WorldGenerator generator;
    
    public Biome(WorldGenerator generator)
    {
        this.generator = generator;
    }
    
    public <T extends Parameter> double getValue(String name, double x)
    {
        return getParameter(name).getValue(x);
    }
    
    public <T extends Parameter> double getValue(String name, double x, double y)
    {
        return getParameter(name).getValue(x, y);
    }
    
    public <T extends Parameter> double getValue(String name, double x,
            double y, double z)
    {
        return getParameter(name).getValue(x, y, z);
    }
    
    public <T extends Parameter> double getValue(Class<T> clazz, double x)
    {
        return getParameter(clazz).getValue(x);
    }
    
    public <T extends Parameter> double getValue(Class<T> clazz, double x,
            double y)
    {
        return getParameter(clazz).getValue(x, y);
    }
    
    public <T extends Parameter> double getValue(Class<T> clazz, double x,
            double y, double z)
    {
        return getParameter(clazz).getValue(x, y, z);
    }
    
    public <T extends Parameter> T getParameter(String name)
    {
        return generator.getParameter(name);
    }
    
    public <T extends Parameter> T getParameter(Class<T> clazz)
    {
        return generator.getParameter(clazz);
    }
    
    public <T extends Biome> T getBiome(Class<T> clazz)
    {
        return generator.getBiome(clazz);
    }
    
    public boolean check(Tile tile)
    {
        return check(tile.getX(), tile.getY(), 0);
    }
    
    public boolean check(double x, double y, double z)
    {
        return check(x, y, z, 0.0);
    }
    
    public abstract boolean check(double x, double y, double z, double precision);
    
    public abstract void process(Tile tile);
}

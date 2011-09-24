package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.Temperature;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.gen.Biome;

public class DesertBiome extends Biome
{
    
    public DesertBiome(WorldGenerator generator)
    {
        super(generator);
    }

    @Override
    public boolean check(double x, double y)
    {
        Temperature temperatures = generator.getEnvironment().getParameter(Temperature.class);
        double t = temperatures.getValue(x, y);
        
        if(t > 60)
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public Color getColor(double x, double y)
    {
        if(check(x, y))
        {
            return Color.yellow;
        }
        
        return null;
    }
    
}

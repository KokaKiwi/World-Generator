package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.HeightMap;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.gen.Biome;

public class MountainBiome extends Biome
{
    
    public MountainBiome(WorldGenerator generator)
    {
        super(generator);
    }
    
    @Override
    public boolean check(double x, double y)
    {
        HeightMap heightmap = generator.getEnvironment().getParameter(
                HeightMap.class);
        double h = heightmap.getValue(x, y);
        
        if (h > 0.5)
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public Color getColor(double x, double y)
    {
        if (check(x, y))
        {
            return Color.lightGray;
        }
        
        return null;
    }
    
}

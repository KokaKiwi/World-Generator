package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.HeightMap;
import com.kokakiwi.maths.generator.sample.params.Temperature;
import com.kokakiwi.maths.generator.sample.params.Volcano;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.gen.Biome;

public class ForestBiome extends Biome
{
    
    public ForestBiome(WorldGenerator generator)
    {
        super(generator);
    }
    
    @Override
    public boolean check(double x, double y)
    {
        double height = getValue(HeightMap.class, x, y);
        double temperature = getValue(Temperature.class, x, y);
        double volcano = getValue(Volcano.class, x, y);
        
        if (height > 0.1 && temperature > 35 && temperature < 50 && !(height > 0.75 && temperature < 55 && volcano > 0.7))
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
            return Color.red;
        }
        
        return null;
    }
    
}

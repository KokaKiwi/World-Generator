package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.HeightMap;
import com.kokakiwi.maths.generator.sample.params.Rivers;
import com.kokakiwi.maths.generator.sample.params.Temperature;
import com.kokakiwi.maths.generator.sample.params.Volcano;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.gen.Biome;

public class RiverBiome extends Biome
{
    
    public RiverBiome(WorldGenerator generator)
    {
        super(generator);
    }

    @Override
    public boolean check(double x, double y)
    {
        double height = getValue(HeightMap.class, x, y);
        double r = getValue(Rivers.class, x, y);
        double temperature = getValue(Temperature.class, x, y);
        double volcano = getValue(Volcano.class, x, y);
        
        if(r < 0.2 && temperature < 55 && !(height > 0.75 && temperature < 55 && volcano > 0.7))
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
            return Color.cyan;
        }
        
        return Color.black;
    }
    
}

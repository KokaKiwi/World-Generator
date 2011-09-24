package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.HeightMap;
import com.kokakiwi.maths.generator.sample.params.Oasis;
import com.kokakiwi.maths.generator.sample.params.Temperature;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.gen.Biome;

public class OceanBiome extends Biome
{
    
    public OceanBiome(WorldGenerator generator)
    {
        super(generator);
    }

    @Override
    public boolean check(double x, double y)
    {
        HeightMap heightmap = generator.getEnvironment().getParameter(HeightMap.class);
        double h = heightmap.getValue(x, y);
        Temperature temperatures = generator.getEnvironment().getParameter(Temperature.class);
        double temperature = temperatures.getValue(x, y);
        Oasis oasis = generator.getEnvironment().getParameter(Oasis.class);
        double o = oasis.getValue(x, y);
        
        if(h < 0.13)
        {
            return true;
        }
        
        if(temperature > 65 && o > 0.5)
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
            return Color.blue;
        }
        
        return null;
    }
    
}
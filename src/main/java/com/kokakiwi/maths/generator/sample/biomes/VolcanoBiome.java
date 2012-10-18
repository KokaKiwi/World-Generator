package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.HeightMap;
import com.kokakiwi.maths.generator.sample.params.Temperature;
import com.kokakiwi.maths.generator.sample.params.Volcano;
import com.kokakiwi.maths.generator.world.Tile;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.env.Biome;

public class VolcanoBiome extends Biome
{
    public VolcanoBiome(WorldGenerator generator)
    {
        super(generator);
    }
    
    @Override
    public boolean check(double x, double y, double z, double precision)
    {
        double height = getValue(HeightMap.class, x, y, z);
        double temperature = getValue(Temperature.class, x, y, z);
        double volcano = getValue(Volcano.class, x, y, z);
        
        if (height > (0.86 + precision) && temperature < (55 + precision)
                && volcano > (0.8 + precision))
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void process(Tile tile)
    {
        tile.putProperty("biome", DesertBiome.class);
        tile.putSingleProperty("color", Color.magenta);
    }
}

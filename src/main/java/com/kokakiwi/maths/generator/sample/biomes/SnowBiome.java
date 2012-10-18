package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.HeightMap;
import com.kokakiwi.maths.generator.sample.params.Snow;
import com.kokakiwi.maths.generator.sample.params.Temperature;
import com.kokakiwi.maths.generator.sample.params.Volcano;
import com.kokakiwi.maths.generator.world.Tile;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.env.Biome;

public class SnowBiome extends Biome
{
    public SnowBiome(WorldGenerator generator)
    {
        super(generator);
    }
    
    @Override
    public boolean check(double x, double y, double z, double precision)
    {
        double height = getValue(HeightMap.class, x, y, z);
        double temperature = getValue(Temperature.class, x, y, z);
        double volcano = getValue(Volcano.class, x, y, z);
        double snow = getValue(Snow.class, x, y, z);
        
        if (height > (0.95 + precision)
                && temperature < (55 + precision)
                && snow > (0.5 + precision)
                && !(height > (0.7 + precision)
                        && temperature < (55 + precision) && volcano > (0.6 + precision)))
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void process(Tile tile)
    {
        tile.putProperty("biome", SnowBiome.class);
        tile.putSingleProperty("color", Color.white);
    }
}

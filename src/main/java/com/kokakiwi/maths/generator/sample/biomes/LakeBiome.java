package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.HeightMap;
import com.kokakiwi.maths.generator.sample.params.Oasis;
import com.kokakiwi.maths.generator.sample.params.Rivers;
import com.kokakiwi.maths.generator.sample.params.Temperature;
import com.kokakiwi.maths.generator.sample.params.Volcano;
import com.kokakiwi.maths.generator.world.Tile;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.env.Biome;

public class LakeBiome extends Biome
{
    public LakeBiome(WorldGenerator generator)
    {
        super(generator);
    }
    
    @Override
    public boolean check(double x, double y, double z, double precision)
    {
        double height = getValue(HeightMap.class, x, y, z);
        double r = getValue(Rivers.class, x, y, z);
        double temperature = getValue(Temperature.class, x, y, z);
        double oasis = getValue(Oasis.class, x, y, z);
        double volcano = getValue(Volcano.class, x, y, z);
        
        if (r < (0.5 + precision)
                && temperature < (55 + precision)
                && oasis > (0.5 + precision)
                && !(height > (0.75 + precision)
                        && temperature < (55 + precision) && volcano > (0.7 + precision)))
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void process(Tile tile)
    {
        tile.putProperty("biome", LakeBiome.class);
        tile.putSingleProperty("color", Color.cyan);
    }
    
}

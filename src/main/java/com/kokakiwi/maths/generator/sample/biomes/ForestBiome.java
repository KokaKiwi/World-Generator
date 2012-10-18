package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.Forest;
import com.kokakiwi.maths.generator.sample.params.HeightMap;
import com.kokakiwi.maths.generator.sample.params.Temperature;
import com.kokakiwi.maths.generator.world.Tile;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.env.Biome;

public class ForestBiome extends Biome
{
    
    public ForestBiome(WorldGenerator generator)
    {
        super(generator);
    }
    
    @Override
    public boolean check(double x, double y, double z, double precision)
    {
        double height = getValue(HeightMap.class, x, y, z);
        double temperature = getValue(Temperature.class, x, y, z);
        double forest = getValue(Forest.class, x, y, z);
        MountainBiome mountain = getBiome(MountainBiome.class);
        
        if (height > (0.1 + precision) && temperature < (60 + precision)
                && !mountain.check(x, y, z) && forest > (0.26 + precision))
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void process(Tile tile)
    {
        tile.putProperty("biome", ForestBiome.class);
        tile.putSingleProperty("color", Color.green.darker().darker());
    }
    
}

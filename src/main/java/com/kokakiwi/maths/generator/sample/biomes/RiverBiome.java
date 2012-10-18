package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.Rivers;
import com.kokakiwi.maths.generator.world.Tile;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.env.Biome;

public class RiverBiome extends Biome
{
    
    public RiverBiome(WorldGenerator generator)
    {
        super(generator);
    }
    
    @Override
    public boolean check(double x, double y, double z, double precision)
    {
        double river = getValue(Rivers.class, x, y, z);
        
        if (river <= (0.13 + precision))
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void process(Tile tile)
    {
        tile.putProperty("biome", RiverBiome.class);
        tile.putSingleProperty("color", Color.cyan);
    }
    
}

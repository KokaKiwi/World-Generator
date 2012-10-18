package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.HeightMap;
import com.kokakiwi.maths.generator.world.Tile;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.env.Biome;

public class PlainBiome extends Biome
{
    
    public PlainBiome(WorldGenerator generator)
    {
        super(generator);
    }
    
    @Override
    public boolean check(double x, double y, double z, double precision)
    {
        double h = getValue(HeightMap.class, x, y, z);
        
        if (h <= (0.7 + precision))
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void process(Tile tile)
    {
        tile.putProperty("biome", PlainBiome.class);
        tile.putSingleProperty("color", Color.green);
    }
    
}

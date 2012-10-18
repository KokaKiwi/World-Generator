package com.kokakiwi.maths.generator.sample.biomes;

import java.awt.Color;

import com.kokakiwi.maths.generator.sample.params.HeightMap;
import com.kokakiwi.maths.generator.sample.params.Oasis;
import com.kokakiwi.maths.generator.sample.params.Temperature;
import com.kokakiwi.maths.generator.world.Tile;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.env.Biome;

public class OceanBiome extends Biome
{
    public final static double WATER_HEIGHT = 0.41;
    
    public OceanBiome(WorldGenerator generator)
    {
        super(generator);
    }
    
    @Override
    public boolean check(double x, double y, double z, double precision)
    {
        double h = getValue(HeightMap.class, x, y, z);
        double temperature = getValue(Temperature.class, x, y, z);
        double o = getValue(Oasis.class, x, y, z);
        
        if (h < WATER_HEIGHT)
        {
            return true;
        }
        
        if (temperature > (65 + precision) && o > (0.6 + precision))
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void process(Tile tile)
    {
        tile.putProperty("biome", OceanBiome.class);
        tile.putSingleProperty("color", Color.blue);
    }
    
}

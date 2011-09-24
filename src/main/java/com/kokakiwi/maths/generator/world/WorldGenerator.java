package com.kokakiwi.maths.generator.world;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.kokakiwi.maths.generator.world.env.Environment;
import com.kokakiwi.maths.generator.world.env.Parameter;
import com.kokakiwi.maths.generator.world.gen.Biome;

public class WorldGenerator
{
    private final String      seed;
    private final Environment environment;
    private final List<Biome> biomes = new ArrayList<Biome>();
    
    public WorldGenerator(String seed)
    {
        this.seed = seed;
        this.environment = new Environment(seed);
    }
    
    public String getSeed()
    {
        return seed;
    }
    
    public Environment getEnvironment()
    {
        return environment;
    }
    
    public List<Biome> getBiomes()
    {
        return biomes;
    }
    
    public Biome getBiome(double x, double y)
    {
        Biome result = null;
        
        for (Biome biome : biomes)
        {
            if (result == null)
            {
                if (biome.check(x, y))
                {
                    result = biome;
                }
            }
        }
        
        return result;
    }
    
    public <T extends Biome> void registerBiome(Class<T> clazz)
    {
        try
        {
            Constructor<T> constructor = clazz
                    .getConstructor(WorldGenerator.class);
            T biome = constructor.newInstance(this);
            registerBiome(biome);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public <T extends Biome> void registerBiome(T biome)
    {
        biomes.add(biome);
    }
}

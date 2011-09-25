package com.kokakiwi.maths.generator.world;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kokakiwi.maths.generator.world.env.Environment;
import com.kokakiwi.maths.generator.world.gen.Biome;

public class WorldGenerator
{
    private final String                             seed;
    private final Environment                        environment;
    private final Map<Class<? extends Biome>, Biome> biomes   = new HashMap<Class<? extends Biome>, Biome>();
    private final List<Class<? extends Biome>>       priority = new ArrayList<Class<? extends Biome>>();
    
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
    
    public Map<Class<? extends Biome>, Biome> getBiomes()
    {
        return biomes;
    }
    
    public List<Class<? extends Biome>> getPriority()
    {
        return priority;
    }
    
    public Biome getBiome(double x, double y)
    {
        Biome result = null;
        
        for (Class<? extends Biome> clazz : priority)
        {
            Biome biome = biomes.get(clazz);
            
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
        biomes.put(biome.getClass(), biome);
        priority.add(biome.getClass());
    }
}

package com.kokakiwi.maths.generator.world;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kokakiwi.maths.generator.world.env.Biome;
import com.kokakiwi.maths.generator.world.env.Parameter;
import com.kokakiwi.maths.generator.world.event.WorldGenListener;
import com.kokakiwi.maths.generator.world.utils.Coordinates;

public class WorldGenerator
{
    private final String                                     seed;
    
    private final Map<Class<? extends Biome>, Biome>         biomes            = new LinkedHashMap<Class<? extends Biome>, Biome>();
    private final Map<String, Class<? extends Parameter>>    parametersNames   = new LinkedHashMap<String, Class<? extends Parameter>>();
    private final Map<Class<? extends Parameter>, Parameter> parameters        = new LinkedHashMap<Class<? extends Parameter>, Parameter>();
    
    private final ListWorldGenListener                       listListener;
    private final List<WorldGenListener>                     listeners         = new LinkedList<WorldGenListener>();
    private boolean                                          eventsEnabled     = true;
    
    private boolean                                          autoParamRegister = false;
    
    public WorldGenerator(String seed)
    {
        this.seed = seed;
        
        listListener = new ListWorldGenListener(listeners);
    }
    
    public String getSeed()
    {
        return seed;
    }
    
    public Map<Class<? extends Biome>, Biome> getBiomes()
    {
        return biomes;
    }
    
    public Map<Class<? extends Parameter>, Parameter> getParameters()
    {
        return parameters;
    }
    
    public boolean isEventsEnabled()
    {
        return eventsEnabled;
    }
    
    public void setEventsEnabled(boolean eventsEnabled)
    {
        this.eventsEnabled = eventsEnabled;
    }
    
    public boolean isAutoParamRegister()
    {
        return autoParamRegister;
    }
    
    public void setAutoParamRegister(boolean autoParamRegister)
    {
        this.autoParamRegister = autoParamRegister;
    }
    
    public <T extends Parameter> T registerParameter(Class<T> clazz)
    {
        try
        {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            T param = constructor.newInstance(seed);
            registerParameter(param);
            
            return param;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public <T extends Parameter> void registerParameter(T param)
    {
        parametersNames.put(param.getName(), param.getClass());
        parameters.put(param.getClass(), param);
    }
    
    public <T extends Biome> T registerBiome(Class<T> clazz)
    {
        try
        {
            Constructor<T> constructor = clazz
                    .getConstructor(WorldGenerator.class);
            T biome = constructor.newInstance(this);
            registerBiome(biome);
            
            return biome;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public <T extends Biome> void registerBiome(T biome)
    {
        biomes.put(biome.getClass(), biome);
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Parameter> T getParameter(String name)
    {
        return (T) getParameter(parametersNames.get(name));
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Parameter> T getParameter(Class<T> clazz)
    {
        T param = (T) parameters.get(clazz);
        
        if (param == null && clazz != null && autoParamRegister)
        {
            param = registerParameter(clazz);
        }
        
        return (T) parameters.get(clazz);
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Biome> T getBiome(Class<T> clazz)
    {
        return (T) biomes.get(clazz);
    }
    
    public World generateWorld(int width, int height)
    {
        World world = new World(width, height);
        
        generateWorld(world);
        
        return world;
    }
    
    public World generateWorld(Coordinates origin, int width, int height)
    {
        World world = new World(origin, width, height);
        
        generateWorld(world);
        
        return world;
    }
    
    public World generateWorld(Coordinates origin, int width, int height,
            double zoom)
    {
        World world = new World(origin, width, height, zoom);
        
        generateWorld(world);
        
        return world;
    }
    
    public void generateWorld(World world)
    {
        Tile[][] tiles = world.getTiles();
        for (int x = 0; x < world.getWidth(); x++)
        {
            for (int y = 0; y < world.getHeight(); y++)
            {
                Tile tile = tiles[x][y];
                
                for (Biome biome : biomes.values())
                {
                    if (biome.check(tile))
                    {
                        biome.process(tile);
                    }
                }
                
                if (eventsEnabled)
                {
                    listListener.tileProcessed(tile);
                }
                
                for (Parameter param : parameters.values())
                {
                    param.reset();
                }
            }
        }
    }
    
    public void addListener(WorldGenListener listener)
    {
        listeners.add(listener);
    }
    
    public void removeListener(WorldGenListener listener)
    {
        listeners.remove(listener);
    }
    
    private static class ListWorldGenListener implements WorldGenListener
    {
        private final List<WorldGenListener> listeners;
        
        public ListWorldGenListener(List<WorldGenListener> listeners)
        {
            this.listeners = listeners;
        }
        
        public void tileProcessed(Tile tile)
        {
            for (WorldGenListener listener : listeners)
            {
                listener.tileProcessed(tile);
            }
        }
    }
}

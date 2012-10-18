package com.kokakiwi.maths.generator.world;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kokakiwi.maths.generator.world.utils.Coordinates;

public class Tile
{
    private final World               world;
    
    private final Coordinates         coord;
    private final Map<String, Object> properties = new LinkedHashMap<String, Object>();
    
    public Tile(World world, double x, double y)
    {
        this(world, new Coordinates(x, y));
    }
    
    public Tile(World world, Coordinates coord)
    {
        this.world = world;
        this.coord = coord;
    }
    
    public Coordinates getCoordinates()
    {
        return coord;
    }
    
    public double getX()
    {
        return coord.getX();
    }
    
    public void setX(double x)
    {
        coord.setX(x);
    }
    
    public double getY()
    {
        return coord.getY();
    }
    
    public void setY(double y)
    {
        coord.setY(y);
    }
    
    public Map<String, Object> getProperties()
    {
        return properties;
    }
    
    public World getWorld()
    {
        return world;
    }
    
    @SuppressWarnings("unchecked")
    public void putProperty(String key, Object value)
    {
        List<Object> list = (List<Object>) properties.get(key);
        if (list == null)
        {
            list = new LinkedList<Object>();
            properties.put(key, list);
        }
        
        list.add(value);
    }
    
    public void putSingleProperty(String key, Object value)
    {
        properties.put(key, value);
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> getProperty(String key)
    {
        List<T> list = (List<T>) properties.get(key);
        
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getSingleProperty(String key)
    {
        T value = (T) properties.get(key);
        
        return value;
    }
}

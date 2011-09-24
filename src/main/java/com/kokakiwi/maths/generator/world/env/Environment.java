package com.kokakiwi.maths.generator.world.env;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Environment
{
    private final String                                  seed;
    private final Map<String, Parameter>                  parameters = new HashMap<String, Parameter>();
    private final Map<Class<? extends Parameter>, String> classes    = new HashMap<Class<? extends Parameter>, String>();
    
    public Environment(String seed)
    {
        this.seed = seed;
    }
    
    public String getSeed()
    {
        return seed;
    }
    
    public Map<String, Parameter> getParameters()
    {
        return parameters;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Parameter> T getParameter(Class<T> clazz)
    {
        return (T) getParameter(classes.get(clazz));
    }
    
    public Parameter getParameter(String name)
    {
        return parameters.get(name);
    }
    
    public <T extends Parameter> void registerParameter(Class<T> clazz)
    {
        try
        {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            T parameter = constructor.newInstance(seed);
            registerParameter(parameter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public <T extends Parameter> void registerParameter(T parameter)
    {
        parameters.put(parameter.getName(), parameter);
        classes.put(parameter.getClass(), parameter.getName());
    }
}

package com.kokakiwi.maths.generator.world.utils;

public class MathsHelper
{
    public static int supervise(int value, int min, int max)
    {
        if (value > max)
        {
            value = max;
        }
        
        if (value < min)
        {
            value = min;
        }
        
        return value;
    }
    
    public static double supervise(double value, double min, double max)
    {
        if (value > max)
        {
            value = max;
        }
        
        if (value < min)
        {
            value = min;
        }
        
        return value;
    }
    
    public static float supervise(float value, float min, float max)
    {
        if (value > max)
        {
            value = max;
        }
        
        if (value < min)
        {
            value = min;
        }
        
        return value;
    }
}

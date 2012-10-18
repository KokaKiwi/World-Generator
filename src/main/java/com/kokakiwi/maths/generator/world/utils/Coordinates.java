package com.kokakiwi.maths.generator.world.utils;

public class Coordinates
{
    private double x;
    private double y;
    
    public Coordinates()
    {
        this(0, 0);
    }
    
    public Coordinates(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double getX()
    {
        return x;
    }
    
    public void setX(double x)
    {
        this.x = x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }
    
    public Coordinates getRelative(double dx, double dy)
    {
        Coordinates coord = new Coordinates(this.x + dx, this.y + dy);
        
        return coord;
    }
}

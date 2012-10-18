package com.kokakiwi.maths.generator.world;

import com.kokakiwi.maths.generator.world.utils.Coordinates;

public class World
{
    private final Coordinates origin;
    private final int         width;
    private final int         height;
    private final double      zoom;
    
    private final Tile[][]    tiles;
    
    public World(int width, int height)
    {
        this(new Coordinates(), width, height, 1.0);
    }
    
    public World(int width, int height, double zoom)
    {
        this(new Coordinates(), width, height, zoom);
    }
    
    public World(Coordinates origin, int width, int height)
    {
        this(origin, width, height, 1.0);
    }
    
    public World(Coordinates origin, int width, int height, double zoom)
    {
        this.origin = origin;
        this.width = width;
        this.height = height;
        this.zoom = zoom;
        
        tiles = new Tile[width][height];
        
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                Coordinates coord = origin.getRelative(x * zoom, y * zoom);
                
                Tile tile = new Tile(this, coord);
                tiles[x][y] = tile;
            }
        }
    }
    
    public Coordinates getOrigin()
    {
        return origin;
    }
    
    public double getOriginX()
    {
        return origin.getX();
    }
    
    public void setOriginX(double x)
    {
        origin.setX(x);
    }
    
    public double getOriginY()
    {
        return origin.getY();
    }
    
    public void setOriginY(double y)
    {
        origin.setY(y);
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public double getZoom()
    {
        return zoom;
    }
    
    public Tile[][] getTiles()
    {
        return tiles;
    }
    
    public Tile getAbsoluteTile(double x, double y)
    {
        int relativeX = (int) ((x - origin.getX()) / zoom);
        int relativeY = (int) ((y - origin.getY()) / zoom);
        
        return getRelativeTile(relativeX, relativeY);
    }
    
    public Tile getRelativeTile(int x, int y)
    {
        if (x < 0 || x > width || y < 0 || y > height)
        {
            throw new RuntimeException("Coordinates out of range!");
        }
        
        return tiles[x][y];
    }
}

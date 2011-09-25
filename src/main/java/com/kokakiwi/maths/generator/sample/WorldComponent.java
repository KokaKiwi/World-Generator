package com.kokakiwi.maths.generator.sample;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class WorldComponent extends Component
{
    private static final long serialVersionUID = 6066098857742430021L;
    private BufferedImage     image;
    
    public WorldComponent(BufferedImage image)
    {
        this.image = image;
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(image, 0, 0, SampleWorldGenerator.windowWidth,
                SampleWorldGenerator.windowHeight, 0, 0, image.getWidth(),
                image.getHeight(), null);
    }
    
    @Override
    public void update(Graphics g)
    {
        paint(g);
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
    
    public void setImage(BufferedImage image)
    {
        this.image = image;
    }
    
}

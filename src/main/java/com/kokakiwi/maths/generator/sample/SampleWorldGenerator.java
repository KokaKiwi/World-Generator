package com.kokakiwi.maths.generator.sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kokakiwi.maths.generator.sample.biomes.*;
import com.kokakiwi.maths.generator.sample.params.*;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.env.Parameter;
import com.kokakiwi.maths.generator.world.gen.Biome;
import com.kokakiwi.maths.generator.world.utils.FastRandom;
import com.kokakiwi.maths.generator.world.utils.MathsHelper;

public class SampleWorldGenerator
{
    private WorldGenerator       generator;
    
    private final static double  zoom         = 5.3;
    private final static int     width        = 1024;
    private final static int     height       = 1024;
    
    private final static double  startX       = 0.0;
    private final static double  startY       = 0.0;
    
    private final static int     windowWidth  = 800;
    private final static int     windowHeight = 800;
    
    private final static boolean showDensity  = true;
    private final static String  densityName  = "heightmap";
    
    private final static String  defaultSeed  = "123456789";
    
    @SuppressWarnings("unused")
    public SampleWorldGenerator()
    {
        //On definit le seed
        long start = System.currentTimeMillis();
        
        String seed = defaultSeed;
        if (seed == null)
        {
            seed = new FastRandom().randomCharacterString(13);
        }
        
        //On initialise le generateur
        generator = new WorldGenerator(seed);
        
        //On inscrit les biomes
        generator.registerBiome(OceanBiome.class);
        generator.registerBiome(RiverBiome.class);
        generator.registerBiome(DesertBiome.class);
        generator.registerBiome(ForestBiome.class);
        generator.registerBiome(PlainBiome.class);
        generator.registerBiome(MountainBiome.class);
        
        //On inscrit les variables d'environnement
        generator.getEnvironment().registerParameter(HeightMap.class);
        generator.getEnvironment().registerParameter(Rivers.class);
        generator.getEnvironment().registerParameter(Temperature.class);
        generator.getEnvironment().registerParameter(Oasis.class);
        
        //On cree l'image
        BufferedImage image = createImage();
        long end = System.currentTimeMillis();
        double diff = (end - start) / 1000;
        System.out.println("Process time: " + diff + " seconds for " + (width * height) + " blocs.");
        try
        {
            ImageIO.write(image, "png", new File("render.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        showWindow(image);
    }
    
    public BufferedImage createImage()
    {
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        
        for (int x = -(width / 2); x < (width / 2); x++)
        {
            for (int y = -(height / 2); y < (height / 2); y++)
            {
                Biome biome = generator.getBiome(x * zoom + startX, y * zoom
                        + startY);
                Color color = Color.black;
                
                if (biome != null)
                {
                    color = biome
                            .getColor(x * zoom + startX, y * zoom + startY);
                    
                    if(showDensity)
                    {
                        Parameter param = generator.getEnvironment().getParameter(densityName);
                        double value = param.getValue(x * zoom + startX, y * zoom + startY);
                        value = MathsHelper.supervise(value, 0.2, 1.0);
                        
                        int red = (int) (color.getRed() * value);
                        int green = (int) (color.getGreen() * value);
                        int blue = (int) (color.getBlue() * value);
                        
                        color = new Color(red, green, blue);
                    }
                }
                
                g.setColor(color);
                g.fillRect(x + (width / 2), y + (height / 2), 1, 1);
                
                for(Parameter param : generator.getEnvironment().getParameters().values())
                {
                    param.reset();
                }
            }
        }
        
        return image;
    }
    
    public void showWindow(final BufferedImage image)
    {
        final Frame frame = new Frame("Perlin World");
        frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.add(new Component() {
            private static final long serialVersionUID = 569027795371182663L;
            
            @Override
            public void paint(Graphics g)
            {
                g.drawImage(image, 0, 0, windowWidth, windowHeight, 0, 0,
                        image.getWidth(), image.getHeight(), null);
            }
            
        }, "Center");
        frame.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e)
            {
                frame.dispose();
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SampleWorldGenerator();
    }
    
}

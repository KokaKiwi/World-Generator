package com.kokakiwi.maths.generator.sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class SampleWorldGenerator implements KeyListener
{
    private WorldGenerator        generator;
    
    private static double         zoom         = 3.0;
    private static int            width        = 1024;
    private static int            height       = 1024;
    
    private static double         startX       = 0.0;
    private static double         startY       = 0.0;
    
    public final static int       windowWidth  = 800;
    public final static int       windowHeight = 800;
    
    private static boolean        showDensity  = true;
    private static String         densityName  = "heightmap";
    
    private static String         defaultSeed  = "123456789";
    
    private static BufferedImage  renderingImage;
    private static WorldComponent renderer;
    
    @SuppressWarnings("unused")
    public SampleWorldGenerator()
    {
        // On definit le seed
        String seed = defaultSeed;
        if (seed == null)
        {
            seed = new FastRandom().randomCharacterString(13);
        }
        
        // On initialise le generateur
        generator = new WorldGenerator(seed);
        
        // On inscrit les biomes
        generator.registerBiome(OceanBiome.class);
        generator.registerBiome(SnowBiome.class);
        generator.registerBiome(RiverBiome.class);
        generator.registerBiome(LakeBiome.class);
        generator.registerBiome(VolcanoBiome.class);
        generator.registerBiome(DesertBiome.class);
        generator.registerBiome(ForestBiome.class);
        generator.registerBiome(PlainBiome.class);
        generator.registerBiome(MountainBiome.class);
        
        // On inscrit les variables d'environnement
        generator.getEnvironment().registerParameter(HeightMap.class);
        generator.getEnvironment().registerParameter(Rivers.class);
        generator.getEnvironment().registerParameter(Temperature.class);
        generator.getEnvironment().registerParameter(Oasis.class);
        generator.getEnvironment().registerParameter(Volcano.class);
        generator.getEnvironment().registerParameter(Snow.class);
        
        // On cree l'image
        BufferedImage image = createImage();
        try
        {
            ImageIO.write(image, "png", new File("render.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        renderingImage = image;
        showWindow();
    }
    
    public BufferedImage createImage()
    {
        long start = System.currentTimeMillis();
        
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
                    
                    if (showDensity)
                    {
                        Parameter param = generator.getEnvironment()
                                .getParameter(densityName);
                        double value = param.getValue(x * zoom + startX, y
                                * zoom + startY);
                        value = MathsHelper.supervise(value, 0.2, 1.0);
                        
                        int red = (int) (color.getRed() * value);
                        int green = (int) (color.getGreen() * value);
                        int blue = (int) (color.getBlue() * value);
                        
                        color = new Color(red, green, blue);
                    }
                }
                
                g.setColor(color);
                g.fillRect(x + (width / 2), y + (height / 2), 1, 1);
                
                for (Parameter param : generator.getEnvironment()
                        .getParameters().values())
                {
                    param.reset();
                }
            }
        }
        
        long end = System.currentTimeMillis();
        double diff = (end - start) / 1000;
        double speed = diff / (width * height) * (64 * 64);
        System.out.println("Process time: " + diff + " s");
        System.out.println("Speed: " + speed + " s per chunks of 64*64 blocs");
        
        return image;
    }
    
    public void showWindow()
    {
        final Frame frame = new Frame("Perlin World");
        renderer = new WorldComponent(renderingImage);
        frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.addKeyListener(this);
        frame.add(renderer, "Center");
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
        
        while (true)
        {
            frame.repaint();
            renderer.repaint();
            try
            {
                Thread.sleep(1000L);
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args)
    {
        new SampleWorldGenerator();
    }
    
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    public void keyPressed(KeyEvent e)
    {
        
    }
    
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            startX += width / 2;
            renderer.setImage(createImage());
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            startX -= width / 2;
            renderer.setImage(createImage());
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            startY -= height / 2;
            renderer.setImage(createImage());
        }
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            startY += height / 2;
            renderer.setImage(createImage());
        }
    }
    
}

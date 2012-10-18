package com.kokakiwi.maths.generator.sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.kokakiwi.maths.generator.sample.biomes.DesertBiome;
import com.kokakiwi.maths.generator.sample.biomes.ForestBiome;
import com.kokakiwi.maths.generator.sample.biomes.LakeBiome;
import com.kokakiwi.maths.generator.sample.biomes.MountainBiome;
import com.kokakiwi.maths.generator.sample.biomes.OceanBiome;
import com.kokakiwi.maths.generator.sample.biomes.PlainBiome;
import com.kokakiwi.maths.generator.sample.biomes.RiverBiome;
import com.kokakiwi.maths.generator.sample.biomes.SnowBiome;
import com.kokakiwi.maths.generator.sample.biomes.VolcanoBiome;
import com.kokakiwi.maths.generator.world.Tile;
import com.kokakiwi.maths.generator.world.World;
import com.kokakiwi.maths.generator.world.WorldGenerator;
import com.kokakiwi.maths.generator.world.env.Parameter;
import com.kokakiwi.maths.generator.world.event.WorldGenListener;
import com.kokakiwi.maths.generator.world.utils.Coordinates;
import com.kokakiwi.maths.generator.world.utils.FastRandom;
import com.kokakiwi.maths.generator.world.utils.MathsHelper;

public class SampleWorldGenerator implements WorldGenListener
{
    private final WorldGenerator generator;
    
    private static int           chunkWidth     = 512;
    private static int           chunkHeight    = 512;
    
    private static double        zoom           = 1.0;
    private static int           width          = 12;
    private static int           height         = 12;
    
    private static Coordinates   origin         = new Coordinates(
                                                        -15 * chunkWidth,
                                                        0 * chunkHeight);
    
    public final static int      windowWidth    = 512;
    public final static int      windowHeight   = 512;
    
    private static boolean       showDensity    = false;
    private static String        densityName    = "temperature";
    
    private static boolean       useDefaultSeed = true;
    private static String        defaultSeed    = "blkablsmsqs6sq+ssq-s-/8";
    
    private JFrame               progressFrame;
    
    private int                  chunkCount     = 0;
    private JProgressBar         chunkProgressBar;
    
    private int                  count          = 0;
    private JProgressBar         mainProgressBar;
    
    public SampleWorldGenerator()
    {
        String seed = defaultSeed;
        if (!useDefaultSeed)
        {
            seed = new FastRandom().randomCharacterString(13);
        }
        
        initProgress();
        
        generator = new WorldGenerator(seed);
        
        generator.setAutoParamRegister(true);
        generator.setEventsEnabled(true);
        
        generator.addListener(this);
        
        // On inscrit les biomes
        generator.registerBiome(MountainBiome.class);
        generator.registerBiome(PlainBiome.class);
        generator.registerBiome(ForestBiome.class);
        generator.registerBiome(DesertBiome.class);
        generator.registerBiome(VolcanoBiome.class);
        generator.registerBiome(LakeBiome.class);
        generator.registerBiome(RiverBiome.class);
        generator.registerBiome(SnowBiome.class);
        generator.registerBiome(OceanBiome.class);
        
        BufferedImage image = createImage();
        try
        {
            ImageIO.write(image, "png", new File("render.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        System.exit(0);
    }
    
    public BufferedImage createImage()
    {
        BufferedImage image = new BufferedImage(width * chunkWidth, height
                * chunkHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        
        World chunk;
        
        showProgress();
        
        for (int cx = 0; cx < width; cx++)
        {
            for (int cy = 0; cy < height; cy++)
            {
                chunkCount = 0;
                chunk = generator.generateWorld(
                        origin.getRelative(cx * chunkWidth * zoom, cy
                                * chunkHeight * zoom), chunkWidth, chunkHeight,
                        zoom);
                
                Tile[][] tiles = chunk.getTiles();
                for (int x = 0; x < chunkWidth; x++)
                {
                    for (int y = 0; y < chunkHeight; y++)
                    {
                        Tile tile = tiles[x][y];
                        Color color = Color.black;
                        
                        if (tile.getSingleProperty("color") != null)
                        {
                            color = tile.getSingleProperty("color");
                        }
                        
                        if (showDensity)
                        {
                            Parameter param = generator
                                    .getParameter(densityName);
                            double value = param.getValue(tile.getX(),
                                    tile.getY());
                            value = MathsHelper.supervise(value, 0.2, 1.0);
                            
                            int red = (int) (color.getRed() * value);
                            int green = (int) (color.getGreen() * value);
                            int blue = (int) (color.getBlue() * value);
                            
                            color = new Color(red, green, blue);
                            
                            param.reset();
                        }
                        
                        g.setColor(color);
                        g.fillRect(cx * chunkWidth + x, cy * chunkHeight + y,
                                1, 1);
                    }
                }
                
                count++;
                mainProgressBar.setValue(count);
            }
        }
        
        hideProgress();
        
        return image;
    }
    
    public void initProgress()
    {
        progressFrame = new JFrame("Progress...");
        progressFrame.setLayout(new BorderLayout());
        progressFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        progressFrame.setResizable(false);
        
        mainProgressBar = new JProgressBar(0, width * height);
        progressFrame.add(mainProgressBar, BorderLayout.NORTH);
        
        chunkProgressBar = new JProgressBar(0, chunkWidth * chunkHeight);
        progressFrame.add(chunkProgressBar, BorderLayout.SOUTH);
        
        progressFrame.pack();
        progressFrame.setLocationRelativeTo(null);
    }
    
    public void showProgress()
    {
        progressFrame.setVisible(true);
    }
    
    public void hideProgress()
    {
        progressFrame.setVisible(false);
    }
    
    public static void main(String[] args)
    {
        new SampleWorldGenerator();
    }
    
    public void tileProcessed(Tile tile)
    {
        chunkCount++;
        
        chunkProgressBar.setValue(chunkCount);
    }
}

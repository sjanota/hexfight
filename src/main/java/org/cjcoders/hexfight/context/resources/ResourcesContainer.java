package org.cjcoders.hexfight.context.resources;

import org.cjcoders.hexfight.context.Context;
import org.cjcoders.hexfight.context.config.ConfigReader;
import org.newdawn.slick.*;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

import java.awt.*;
import java.io.IOException;
import java.util.*;

/**
 * Created by mrakr_000 on 2014-05-21.
 */
public class ResourcesContainer {

    private Dimension resolution;
    private String theme;
    private Set<ResourcesFileReader> resourcesFileReaders;
    private ConfigReader configReader;

    public ResourcesContainer() {
        this.resourcesFileReaders = new HashSet<>();
    }

    public void useConfigReader(ConfigReader configReader){
        this.configReader = configReader;
    }
    public boolean usesConfigReader(){
        return this.configReader != null;
    }

    public void reload() throws IOException, SlickException, FontFormatException {
        for(ResourcesFileReader resourcesFileReader : resourcesFileReaders){
            reloadImages(resourcesFileReader);
            reloadFonts(resourcesFileReader);
            reloadConfig(resourcesFileReader);
        }
    }

    private void reloadConfig(ResourcesFileReader resourcesFileReader) throws IOException {
        if(usesConfigReader()){
            configReader.read(resourcesFileReader.getConfigs());
        }
    }

    private void reloadFonts(ResourcesFileReader resourcesFileReader) throws IOException, FontFormatException {
        Collection<Resource> fonts = resourcesFileReader.getFonts();
        for(Resource font : fonts){
            Resources.get().fonts.load(font.ref, font.path);
        }
    }

    private void reloadImages(ResourcesFileReader resourcesFileReader) throws IOException, SlickException {
        Collection<Resource> images = resourcesFileReader.getImages(resolution, theme);
        for(Resource img : images){
            Resources.get().images.load(img.ref, img.path);
        }
    }

    public Image getImage(String ref){
        return Resources.get().images.get(ref);
    }

    public Font getFont(String ref, int size, String options){
        return Resources.get().fonts.get(ref, size, options);
    }
    public Font getFont(String ref, int size){
        return getFont(ref, size, "");
    }

    public void addFileReader(ResourcesFileReader fileReader){
        resourcesFileReaders.add(fileReader);
    }

    public void setResolution(Dimension resolution) throws IOException, SlickException, FontFormatException {
        this.resolution = resolution;
        reload();
    }

    public void init(Context context, GUIContext container) throws FontFormatException, SlickException, IOException {
        resolution = context.config().getScreenResolution();
        theme = context.config().getTheme();
    }
}
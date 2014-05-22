package org.cjcoders.hexfight.context;

import org.cjcoders.hexfight.context.config.ConfigContainer;
import org.cjcoders.hexfight.context.resources.ResourcesContainer;
import org.cjcoders.hexfight.context.resources.ResourcesFileReader;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

/**
 * Created by mrakr_000 on 2014-05-21.
 */
public class Context {

    private static final String DEFAULT_RESOURCE_FILE = "resources.xml";

    private static final Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private Context() {
        configContainer = new ConfigContainer();
        resourcesContainer = new ResourcesContainer();
    }

    private ResourcesContainer resourcesContainer;
    private ConfigContainer configContainer;

    public void init(GUIContext container) throws IOException, SAXException, ParserConfigurationException, SlickException, FontFormatException {
        configContainer.init(this, container);
        resourcesContainer.init(this, container);
        resourcesContainer.addFileReader(new ResourcesFileReader(DEFAULT_RESOURCE_FILE));
        resourcesContainer.reload();
    }

    public ResourcesContainer resources(){ return resourcesContainer; }
    public ConfigContainer config(){ return configContainer; }
}
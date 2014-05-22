package org.cjcoders.hexfight;

import org.cjcoders.hexfight.gui.board.BoardDrawer;
import org.cjcoders.hexfight.gui.board.Hexagon;
import org.cjcoders.hexfight.gui.states.TurnState;
import org.cjcoders.hexfight.gui.utils.resources.Resources;
import org.cjcoders.hexfight.old.utils.Point;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

import java.util.Random;

/**
* Created by mrakr_000 on 2014-05-13.
*/
public class Tile{

    public static final int FILL_SPAWN_RATE = 4;

    private int x;
    private int y;

    private Player owner;
    private int tileNo;
    private TileForces forces;

    public Tile(int x, int y, int tilesCount){
        this.x = x;
        this.y = y;
        tileNo = new Random().nextInt(tilesCount*FILL_SPAWN_RATE);
        forces = TileForces.NEUTRAL_DEFAULT;
    }

    public int getTileNo() {
        return tileNo;
    }

    public boolean isOwned(){ return owner != null; }
    public Player getOwner(){ return owner; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public TileForces getForces() {
        return forces;
    }

    public void setForces(TileForces forces) {
        this.forces = forces;
    }
}
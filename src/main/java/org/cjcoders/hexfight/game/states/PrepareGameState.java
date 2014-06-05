package org.cjcoders.hexfight.game.states;

import org.cjcoders.hexfight.board.Board;
import org.cjcoders.hexfight.context.Context;
import org.cjcoders.hexfight.game.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by mrakr_000 on 2014-05-12.
 */
public class PrepareGameState extends BasicGameState{
    public PrepareGameState() {
    }

    @Override
    public int getID() {
        return State.PREPARE_GAME.getCode();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        PlayState play = (PlayState) game.getState(State.TURN.getCode());
        int playersCount = Context.getInstance().config().getPlayersNumber();
        play.setUp(getPlayers(playersCount));
        game.enterState(State.TURN.getCode());
    }

    private Player[] getPlayers(int playersCount) {
        Player[] players = new Player[playersCount];
        for(int i = 0; i < playersCount; ++i) players[i] = new Player(i);
        return players;
    }


}

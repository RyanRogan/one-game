package com.ryan.onegame;

import com.badlogic.gdx.Game;

/**
 * The main game class that manages screens and serves as the entry point for the application.
 */
public class Main extends Game {

    /**
     * Called when the game is created.
     * Initializes resources and sets the initial screen.
     */
    @Override
    public void create() {
        // Set the initial screen to the game screen
        setScreen(new GameScreen(this));
    }

    /**
     * Called when the game should render itself.
     * Delegates rendering to the active screen.
     */
    @Override
    public void render() {
        super.render(); // Delegates to the active screen's render method
    }

    /**
     * Called when the game is destroyed.
     * Disposes of resources to prevent memory leaks.
     */
    @Override
    public void dispose() {
        // Dispose of the current screen if it exists
        if (getScreen() != null) {
            getScreen().dispose();
        }
    }
}

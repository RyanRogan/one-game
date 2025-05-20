package com.ryan.onegame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The main game screen that handles rendering and updating the game state.
 */
public class GameScreen implements Screen {
    private final Main game;
    private ShapeRenderer shapeRenderer;
    private Player player;

    /**
     * Creates a new game screen.
     *
     * @param game The main game class that manages this screen
     */
    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Initialize resources when the screen becomes visible
        shapeRenderer = new ShapeRenderer();
        player = new Player(50, 200); // 50px size, 200px/s speed
    }

    @Override
    public void render(float delta) {
        // Clear the screen with black color
        ScreenUtils.clear(0, 0, 0, 1);

        // Update game logic
        update(delta);

        // Render game objects
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        player.render(shapeRenderer);
        shapeRenderer.end();
    }

    /**
     * Updates the game state.
     *
     * @param delta The time elapsed since the last update in seconds
     */
    private void update(float delta) {
        player.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        // Handle screen resize if needed
    }

    @Override
    public void pause() {
        // Handle game pause if needed
    }

    @Override
    public void resume() {
        // Handle game resume if needed
    }

    @Override
    public void hide() {
        // Handle when screen becomes hidden if needed
    }

    @Override
    public void dispose() {
        // Dispose of resources to prevent memory leaks
        shapeRenderer.dispose();
    }
}

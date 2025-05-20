package com.ryan.onegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Represents the player character in the game.
 * Handles player movement, rendering, and input processing.
 */
public class Player {
    // Player properties
    private float x;
    private float y;
    private final float size;
    private final float speed;
    
    /**
     * Creates a new player with the specified size and speed.
     * 
     * @param size The size of the player square in pixels
     * @param speed The movement speed of the player in pixels per second
     */
    public Player(float size, float speed) {
        this.size = size;
        this.speed = speed;
        
        // Initialize player position to the center of the screen
        this.x = Gdx.graphics.getWidth() / 2 - size / 2;
        this.y = Gdx.graphics.getHeight() / 2 - size / 2;
    }
    
    /**
     * Updates the player's state based on input and delta time.
     * 
     * @param deltaTime The time elapsed since the last update in seconds
     */
    public void update(float deltaTime) {
        handleInput(deltaTime);
        keepInBounds();
    }
    
    /**
     * Handles player input for movement.
     * 
     * @param deltaTime The time elapsed since the last update in seconds
     */
    private void handleInput(float deltaTime) {
        // Move left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * deltaTime;
        }
        
        // Move right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * deltaTime;
        }
    }
    
    /**
     * Ensures the player stays within the screen boundaries.
     */
    private void keepInBounds() {
        if (x < 0) {
            x = 0;
        }
        if (x > Gdx.graphics.getWidth() - size) {
            x = Gdx.graphics.getWidth() - size;
        }
    }
    
    /**
     * Renders the player as a white square.
     * 
     * @param shapeRenderer The ShapeRenderer to use for drawing
     */
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 1, 1, 1); // White color
        shapeRenderer.rect(x, y, size, size);
    }
    
    // Getters and setters
    public float getX() {
        return x;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public float getY() {
        return y;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
    public float getSize() {
        return size;
    }
}

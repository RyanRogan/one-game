package com.ryan.onegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, GdxTestRunner.class})
class PlayerTest {
    
    @Mock
    private ShapeRenderer shapeRenderer;
    
    // Set up test values
    private final float PLAYER_SIZE = 50.0f;
    private final float PLAYER_SPEED = 200.0f;
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;
    
    private Player player;
    
    @BeforeEach
    void setUp() {
        // Create mocks for Gdx statics
        Gdx.graphics = mock(Graphics.class);
        Gdx.input = mock(Input.class);
        
        // Configure mock behavior
        when(Gdx.graphics.getWidth()).thenReturn(SCREEN_WIDTH);
        when(Gdx.graphics.getHeight()).thenReturn(SCREEN_HEIGHT);
        
        // Create a new player instance with our test values
        player = new Player(PLAYER_SIZE, PLAYER_SPEED);
    }
    
    @Test
    void testPlayerInitialization() {
        // Player should be initialized at the center of the screen
        float expectedX = SCREEN_WIDTH / 2 - PLAYER_SIZE / 2;
        float expectedY = SCREEN_HEIGHT / 2 - PLAYER_SIZE / 2;
        
        assertEquals(expectedX, player.getX(), "Player X position should be centered");
        assertEquals(expectedY, player.getY(), "Player Y position should be centered");
        assertEquals(PLAYER_SIZE, player.getSize(), "Player size should match constructor parameter");
    }
    
    @Test
    void testPlayerMovesLeftWhenLeftKeyPressed() {
        // Set up the input state
        when(Gdx.input.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);
        when(Gdx.input.isKeyPressed(Input.Keys.RIGHT)).thenReturn(false);
        
        // Starting position
        float startX = player.getX();
        
        // Update the player with a known delta time
        float deltaTime = 0.1f;
        player.update(deltaTime);
        
        // Verify the player moved left
        float expectedX = startX - PLAYER_SPEED * deltaTime;
        assertEquals(expectedX, player.getX(), 0.001f, "Player should move left when left key is pressed");
    }
    
    @Test
    void testPlayerMovesRightWhenRightKeyPressed() {
        // Set up the input state
        when(Gdx.input.isKeyPressed(Input.Keys.LEFT)).thenReturn(false);
        when(Gdx.input.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);
        
        // Starting position
        float startX = player.getX();
        
        // Update the player with a known delta time
        float deltaTime = 0.1f;
        player.update(deltaTime);
        
        // Verify the player moved right
        float expectedX = startX + PLAYER_SPEED * deltaTime;
        assertEquals(expectedX, player.getX(), 0.001f, "Player should move right when right key is pressed");
    }
    
    @Test
    void testPlayerStaysStillWhenNoKeyPressed() {
        // Set up the input state
        when(Gdx.input.isKeyPressed(Input.Keys.LEFT)).thenReturn(false);
        when(Gdx.input.isKeyPressed(Input.Keys.RIGHT)).thenReturn(false);
        
        // Starting position
        float startX = player.getX();
        
        // Update the player with a known delta time
        float deltaTime = 0.1f;
        player.update(deltaTime);
        
        // Verify the player didn't move
        assertEquals(startX, player.getX(), 0.001f, "Player should not move when no keys are pressed");
    }
    
    @Test
    void testPlayerStaysInLeftBounds() {
        // Place player at left edge
        player.setX(-10); // Try to move outside the screen
        
        // Update to trigger bounds checking
        player.update(0.1f);
        
        // Verify player is kept in bounds
        assertEquals(0, player.getX(), "Player should not move beyond left screen boundary");
    }
    
    @Test
    void testPlayerStaysInRightBounds() {
        // Place player beyond right edge of screen
        player.setX(SCREEN_WIDTH); // Beyond the right edge
        
        // Update to trigger bounds checking
        player.update(0.1f);
        
        // Verify player is kept in bounds
        assertEquals(SCREEN_WIDTH - PLAYER_SIZE, player.getX(), "Player should not move beyond right screen boundary");
    }
    
    @Test
    void testPlayerRendering() {
        // Call the render method
        player.render(shapeRenderer);
        
        // Verify shape renderer methods were called with correct arguments
        verify(shapeRenderer).setColor(1, 1, 1, 1); // White color
        verify(shapeRenderer).rect(player.getX(), player.getY(), PLAYER_SIZE, PLAYER_SIZE);
    }
    
    @Test
    void testSetterGetterMethods() {
        // Test X position
        float newX = 123.45f;
        player.setX(newX);
        assertEquals(newX, player.getX(), "getX() should return the value set by setX()");
        
        // Test Y position
        float newY = 67.89f;
        player.setY(newY);
        assertEquals(newY, player.getY(), "getY() should return the value set by setY()");
    }
}

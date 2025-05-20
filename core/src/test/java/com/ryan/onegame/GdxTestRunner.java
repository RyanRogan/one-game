package com.ryan.onegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mockito;

/**
 * JUnit 5 extension to initialize a headless LibGDX environment for testing.
 */
public class GdxTestRunner implements BeforeAllCallback {

    private static boolean initialized = false;
    
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (!initialized) {
            // Initialize the headless application only once
            HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
            new HeadlessApplication(new ApplicationAdapter() {
                @Override
                public void create() {
                    // Mock GL20 since we're running headless
                    Gdx.gl = Mockito.mock(GL20.class);
                }
            }, config);
            
            initialized = true;
        }
    }
    
    /**
     * Annotation to apply this runner to a test class
     */
    @ExtendWith(GdxTestRunner.class)
    public @interface HeadlessTest {}
}

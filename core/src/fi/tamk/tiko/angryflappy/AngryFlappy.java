package fi.tamk.tiko.angryflappy;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class AngryFlappy extends ApplicationAdapter {
    public static final String TAG = AngryFlappy.class.getName();
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Doge doge;
    private InputHandler inputHandler;
    private BitmapFont font;


    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();
        doge = new Doge();
        inputHandler = new InputHandler(doge);
        Gdx.input.setInputProcessor(inputHandler);
        font = new BitmapFont();
        font.setScale(2.0f, 2.0f);
        font.setColor(Color.BLUE);

    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();

        doge.update(deltaTime);
        batch.begin();
        doge.draw(batch, deltaTime);
        font.draw(batch, "speedX: " + doge.getSpeed().x + ", speedY: " + doge.getSpeed().y, 0, 0);
        batch.end();
    }


}

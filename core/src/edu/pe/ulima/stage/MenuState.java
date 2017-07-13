package edu.pe.ulima.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import edu.pe.ulima.MainApplication;
import edu.pe.ulima.inerface.FireBaseRealTime;
import edu.pe.ulima.sprites.Alacran;
import edu.pe.ulima.sprites.Hormigon;
import edu.pe.ulima.sprites.Hormiguita;

public class MenuState extends State {
    private Texture texBackground;
    private Texture textPlayButton;
    private Texture textTitleButton;
    private Rectangle mBounds;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        mCam.setToOrtho(false, MainApplication.WIDTH / 2, MainApplication.HEIGHT / 2);
        texBackground = new Texture("bg_game5.jpg");
        textTitleButton = new Texture("title.png");
        textPlayButton = new Texture("start1.png");
        mBounds = new Rectangle(0, 0, textPlayButton.getWidth(),textPlayButton.getHeight());
    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()) {
            Vector3 vc = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            mCam.unproject(vc);
            if (mBounds.contains(vc.x, vc.y)) {
                mGSM.set(new PlayState(mGSM));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(mCam.combined);
        sp.begin();
        sp.draw(texBackground, 0, 0, mCam.viewportWidth, mCam.viewportHeight);
        sp.draw(textPlayButton, 0, 0, mCam.viewportWidth, mCam.viewportHeight / 4);
        sp.draw(textTitleButton,0, (mCam.viewportHeight-(mCam.viewportHeight / 3))-10, mCam.viewportWidth , mCam.viewportHeight / 3);
        sp.end();
    }
    @Override
    public void dispose() {
        texBackground.dispose();
        textPlayButton.dispose();
     }





}

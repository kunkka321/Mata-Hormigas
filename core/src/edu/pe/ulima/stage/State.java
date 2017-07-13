package edu.pe.ulima.stage;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import edu.pe.ulima.MainApplication;
import edu.pe.ulima.inerface.FireBaseRealTime;


public abstract class State {
    protected OrthographicCamera mCam;
    protected Vector3 mMouse;
    protected GameStateManager mGSM;
    protected FireBaseRealTime fireF;

    protected State(GameStateManager gsm){
        mGSM = gsm;
        mCam = new OrthographicCamera();
        mCam.setToOrtho(false, MainApplication.WIDTH / 2, MainApplication.HEIGHT / 2);
        mMouse = new Vector3();
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sp);
    public abstract void dispose();

    public FireBaseRealTime getFireF() {
        return fireF;
    }

    public void setFireF(FireBaseRealTime fireF) {
        this.fireF = fireF;
    }
}

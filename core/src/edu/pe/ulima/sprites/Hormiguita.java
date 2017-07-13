package edu.pe.ulima.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Hormiguita {

    private Vector3 mPosicion;
    private Vector3 mVelocidad;
    private Rectangle mBounds;
    private static final int GRAVITY = -5;
    private Texture textureHormiga;
    Animation animationHormiga;
    private boolean life;
    private static final int PUNTAJE = 10;

    public Hormiguita(int x, int y) {
        life = true;
        mPosicion = new Vector3(x, y, 0);
        mVelocidad = new Vector3(0, 0, 0);
        textureHormiga = new Texture("hormiguita.png");
        animationHormiga = new Animation(new TextureRegion(textureHormiga), 5, 0.5f);
        mBounds = new Rectangle(mPosicion.x, mPosicion.y, textureHormiga.getWidth() / 5, textureHormiga.getHeight());
    }

    public void update(float dt) {
        animationHormiga.update(dt);
        if (getLife()) {
            if (mPosicion.y > 0) {
                mVelocidad.add(0, GRAVITY, 0);
            }
            mVelocidad.scl(dt);
            mPosicion.add(0, mVelocidad.y, 0);
            mBounds.setPosition(mPosicion.x, mPosicion.y);
            if (mPosicion.y + textureHormiga.getHeight() < 0) {
                textureHormiga.dispose();
            }
            mVelocidad.scl(1 / dt);
        }
    }

    public Vector3 getPosicion() {
        return mPosicion;
    }

    public TextureRegion getTexture() {
        if (life) {
            return animationHormiga.getFrame();
        }
        return animationHormiga.getFrameMuerto();
    }

    public Rectangle getBounds() {
        if (life) {
            return mBounds;
        }
        return null;
    }

    public void dipose() {
        textureHormiga.dispose();
    }

    public boolean getLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

    public void hide() {
        mPosicion.set(5000, 5000, 0);
        mBounds.setX(5000);
    }

    public static int getPUNTAJE() {
        return PUNTAJE;
    }
}

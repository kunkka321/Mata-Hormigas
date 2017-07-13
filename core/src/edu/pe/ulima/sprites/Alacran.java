package edu.pe.ulima.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Alacran {
    private Vector3 mPosicion;
    private Vector3 mVelocidad;
    private Rectangle mBounds;
    private static final int GRAVITY = -7;
    private static float DIAGONAL;
    private Texture textureHormigon;
    Animation animationHormiga;
    private boolean life;
    private static final int PUNTAJE = 30;


    public Alacran(int x, int y, float ancho, float alto) {
        calcularDiagonal(x, y, ancho, alto);
        life = true;
        mPosicion = new Vector3(x, y, 0);
        mVelocidad = new Vector3(0, 0, 0);
        textureHormigon = new Texture("alacran.png");
        animationHormiga = new Animation(new TextureRegion(textureHormigon), 5, 0.5f);
        mBounds = new Rectangle(mPosicion.x, mPosicion.y, textureHormigon.getWidth() / 5, textureHormigon.getHeight());
    }

    public void update(float dt) {
        animationHormiga.update(dt);
        if (getLife()) {
            if (mPosicion.y > 0) {
                mVelocidad.add(DIAGONAL, GRAVITY, 0);
            }
            mVelocidad.scl(dt);
            mPosicion.add(mVelocidad.x, mVelocidad.y, 0);
            mBounds.setPosition(mPosicion.x, mPosicion.y);
            if (mPosicion.y + textureHormigon.getHeight() < 0) {
                textureHormigon.dispose();
            }
            mVelocidad.scl(1 / dt);
        }
    }

    public void calcularDiagonal(int x, int y, float ancho, float alto) {
        long rd = Math.round(Math.random() * 1);
        System.out.println("RANDOM : " + rd);
        if (rd == 1) {
            if (x > ancho / 2) {
                DIAGONAL = (((GRAVITY * 1.0f) * x) / alto);
            } else {
                DIAGONAL = -1 * ((GRAVITY * 1.0f) * (ancho - (x + 40))) / alto;
            }
        } else {
            DIAGONAL = 0;
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
        textureHormigon.dispose();
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

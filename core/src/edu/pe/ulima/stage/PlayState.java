package edu.pe.ulima.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import edu.pe.ulima.MainApplication;
import edu.pe.ulima.sprites.Alacran;
import edu.pe.ulima.sprites.Hormiguita;
import edu.pe.ulima.sprites.Hormigon;


public class PlayState extends State implements Input.TextInputListener {

    private Texture mBackground;
    private Array<Hormiguita> hormiguitaList;
    private Array<Hormigon> hormigonList;
    private Array<Alacran> alacranList;
    private int INTERVALO = 2;
    private float acumulador_hormiguita;
    private float acumulador_hormigon;
    private float acumulador_alacran;
    private float acum_dispose;
    public boolean inGame;
    public int puntaje;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        mBackground = new Texture("bg_game1.jpg");
        hormiguitaList = new Array<Hormiguita>();
        hormigonList = new Array<Hormigon>();
        alacranList = new Array<Alacran>();
        inGame = true;
        puntaje = 0;
        addHormiga();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 vc = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            mCam.unproject(vc);
            for (int i = 0; i < hormiguitaList.size; i++) {
                Hormiguita hormiguita = hormiguitaList.get(i);
                if (hormiguita.getBounds() != null) {
                    if (hormiguita.getBounds().contains(vc.x, vc.y)) {
                        puntaje += Hormiguita.getPUNTAJE();
                        hormiguita.setLife(false);
                    }
                }
            }
            for (int i = 0; i < hormigonList.size; i++) {
                Hormigon hormigon = hormigonList.get(i);
                if (hormigon.getBounds() != null) {
                    if (hormigon.getBounds().contains(vc.x, vc.y)) {
                        puntaje += Hormigon.getPUNTAJE();
                        hormigon.setLife(false);
                    }
                }
            }
            for (int i = 0; i < alacranList.size; i++) {
                Alacran alacran = alacranList.get(i);
                if (alacran.getBounds() != null) {
                    if (alacran.getBounds().contains(vc.x, vc.y)) {
                        puntaje += Alacran.getPUNTAJE();
                        alacran.setLife(false);
                    }
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        if (isGame()) {
            handleInput();
            acumulador_hormigon += dt;
            acumulador_hormiguita += dt;
            acumulador_alacran += dt;
            acum_dispose += dt;

            if (acumulador_hormiguita > INTERVALO) {
                addHormiga();
                acumulador_hormiguita = 0.0f;
            }
            if (acumulador_hormigon > INTERVALO + 4) {
                addHormigon();
                acumulador_hormigon = 0.0f;
            }
            if (acumulador_alacran > INTERVALO + 5) {
                addAlacran();
                acumulador_alacran = 0.0f;
            }
            if (acum_dispose > INTERVALO + 3) {
                hide();
                acum_dispose = 0.0f;
            }
            for (int i = 0; i < hormiguitaList.size; i++) {
                Hormiguita hormiguita = hormiguitaList.get(i);
                hormiguita.update(dt);
            }
            for (int i = 0; i < hormigonList.size; i++) {
                Hormigon hormigon = hormigonList.get(i);
                hormigon.update(dt);
            }
            for (int i = 0; i < alacranList.size; i++) {
                Alacran alacran = alacranList.get(i);
                alacran.update(dt);
            }
            mCam.update();
        } else {
            if (inGame) {
                perdiste();
                inGame = false;
            }
        }

    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(mCam.combined);
        sp.begin();
        sp.draw(mBackground,
                mCam.position.x - (mCam.viewportWidth / 2), 0,
                MainApplication.WIDTH / 2, MainApplication.HEIGHT / 2);
        for (int i = 0; i < hormiguitaList.size; i++) {
            Hormiguita hormiguita = hormiguitaList.get(i);
            sp.draw(hormiguita.getTexture(), hormiguita.getPosicion().x, hormiguita.getPosicion().y);
        }

        for (int i = 0; i < hormigonList.size; i++) {
            Hormigon hormigon = hormigonList.get(i);
            sp.draw(hormigon.getTexture(), hormigon.getPosicion().x, hormigon.getPosicion().y);
        }
        for (int i = 0; i < alacranList.size; i++) {
            Alacran alacran = alacranList.get(i);
            sp.draw(alacran.getTexture(), alacran.getPosicion().x, alacran.getPosicion().y);
        }

        sp.end();
    }

    @Override
    public void dispose() {
        for (int i = 0; i < hormiguitaList.size; i++) {
            Hormiguita hormiguita = hormiguitaList.get(i);
            if (!hormiguita.getLife()) {
                hormiguita.dipose();
            }
        }
        for (int i = 0; i < hormigonList.size; i++) {
            Hormigon hormigon = hormigonList.get(i);
            if (!hormigon.getLife()) {
                hormigon.dipose();
            }
        }
        for (int i = 0; i < alacranList.size; i++) {
            Alacran alacran = alacranList.get(i);
            if (!alacran.getLife()) {
                alacran.dipose();
            }
        }
    }

    public void hide() {
        for (int i = 0; i < hormiguitaList.size; i++) {
            Hormiguita hormiguita = hormiguitaList.get(i);
            if (!hormiguita.getLife()) {
                hormiguita.hide();
            }
        }
        for (int i = 0; i < hormigonList.size; i++) {
            Hormigon hormigon = hormigonList.get(i);
            if (!hormigon.getLife()) {
                hormigon.hide();
            }
        }

        for (int i = 0; i < alacranList.size; i++) {
            Alacran alacran = alacranList.get(i);
            if (!alacran.getLife()) {
                alacran.hide();
            }
        }
    }

    public void addHormiga() {
        Hormiguita hormiguita;
        long rd = Math.round(Math.random() * (mCam.viewportWidth - 40));
        hormiguita = new Hormiguita((int) rd, 400);
        hormiguitaList.add(hormiguita);
    }

    public void addHormigon() {
        Hormigon hormigon;
        long rd = Math.round(Math.random() * (mCam.viewportWidth - 40));
        hormigon = new Hormigon((int) rd, 400, mCam.viewportWidth, mCam.viewportHeight);
        hormigonList.add(hormigon);
    }

    public void addAlacran() {
        Alacran alacran;
        long rd = Math.round(Math.random() * (mCam.viewportWidth - 40));
        alacran = new Alacran((int) rd, 400, mCam.viewportWidth, mCam.viewportHeight);
        alacranList.add(alacran);
    }

    public boolean isGame() {
        for (int i = 0; i < hormiguitaList.size; i++) {
            Hormiguita hormiguita = hormiguitaList.get(i);
            if (hormiguita.getBounds() != null) {
                if (hormiguita.getLife()) {
                    if (hormiguita.getPosicion().y < -1 * hormiguita.getTexture().getRegionHeight()) {
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < hormigonList.size; i++) {
            Hormigon hormigon = hormigonList.get(i);
            if (hormigon.getBounds() != null) {
                if (hormigon.getLife()) {
                    if (hormigon.getPosicion().y < -1 * hormigon.getTexture().getRegionHeight()) {
                        return false;
                    }
                }
            }
        }
        for (int i = 0; i < alacranList.size; i++) {
            Alacran alacran = alacranList.get(i);
            if (alacran.getBounds() != null) {
                if (alacran.getLife()) {
                    if (alacran.getPosicion().y < -1 * alacran.getTexture().getRegionHeight()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void perdiste() {
        Gdx.input.getTextInput(this, "Puntaje : " + puntaje, "", "Ingrese nombre");
    }

    @Override
    public void input(String text) {
        //GUARDAR EN FIRE BASE
        if (!text.equalsIgnoreCase("")) {
            fireF.submitScore(text, puntaje);
        }
        mGSM.set(new MenuState(mGSM));
    }

    @Override
    public void canceled() {
        //mGSM.set(new MenuState(mGSM));
    }
}

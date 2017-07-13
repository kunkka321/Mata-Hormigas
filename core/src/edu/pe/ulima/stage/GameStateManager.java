package edu.pe.ulima.stage;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import edu.pe.ulima.inerface.FireBaseRealTime;

public class GameStateManager {
    private Stack<State> mStates;
    private FireBaseRealTime fireF;

    public GameStateManager(FireBaseRealTime fireF){
        mStates = new Stack<State>();
        this.fireF = fireF;
    }

    public void push(State state){
        state.setFireF(fireF);
        mStates.push(state);
    }

    public void pop(){
        mStates.pop().dispose();
    }

    public void set(State state){
        pop();
        push(state);
    }

    public void update(float dt){
        mStates.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        mStates.peek().render(sb);
    }
}

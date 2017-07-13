package edu.pe.ulima.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by CVALENCIA on 12/07/2017.
 */

public class Animation {

    private Array<TextureRegion> mFrames;
    private float mMaxFrameTime;
    private float mCurrentFrameTime;
    private int mFrameCount;
    private int mCurrentFrameIndex;

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        mFrames = new Array<TextureRegion>();

        int frameWidth = region.getRegionWidth() / frameCount;

        for (int i=0; i< frameCount; i++){
            mFrames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth,
                    region.getRegionHeight()));
        }

        mFrameCount = frameCount;
        mMaxFrameTime = cycleTime / frameCount;
        mCurrentFrameIndex = 1;
    }

    public void update(float dt){
        mCurrentFrameTime += dt;

        if (mCurrentFrameTime > mMaxFrameTime){
            mCurrentFrameIndex++;
            mCurrentFrameTime = 0;
        }
        if (mCurrentFrameIndex == mFrameCount){
            mCurrentFrameIndex = 1;
        }
    }

    public TextureRegion getFrame(){
        if(mCurrentFrameIndex == 0){
            return mFrames.get(mCurrentFrameIndex+1);
        }
        return mFrames.get(mCurrentFrameIndex);

    }

    public TextureRegion getFrameMuerto(){
        return mFrames.get(0);
    }

}

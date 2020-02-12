package com.example.optimizeadvance.render;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.optimizeadvance.R;

import java.util.ArrayList;
import java.util.List;

public class DroidCardsView extends View {

    //图片与图片之间的间距
    private int mCardSpacing = 150;
    //图片与左侧距离的记录
    private int mCardLeft = 10;

    private List<DroidCard> mDroidCards = new ArrayList<DroidCard>();

    private Paint paint = new Paint();

    public DroidCardsView(Context context) {
        super(context);
        initCards();
    }

    public DroidCardsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCards();
    }


    /**
     * 初始化卡片集合
     */
    protected void initCards(){
        Resources res = getResources();
        mDroidCards.add(new DroidCard(res, R.drawable.alex,mCardLeft));

        mCardLeft+=mCardSpacing;
        mDroidCards.add(new DroidCard(res, R.drawable.claire,mCardLeft));

        mCardLeft+=mCardSpacing;
        mDroidCards.add(new DroidCard(res, R.drawable.kathryn,mCardLeft));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (DroidCard c : mDroidCards) {
            drawDroidCard(canvas,c);
        }
//        for(int i=0;i<mDroidCards.size()-1;i++){
//            drawDroidCard(canvas,mDroidCards,i);
//        }
//        drawLastDroidCard(canvas,mDroidCards.get(mDroidCards.size()-1));
        invalidate();
    }
    private void drawDroidCard(Canvas canvas, DroidCard c) {
        canvas.drawBitmap(c.bitmap,c.x,0f,paint);
    }

    private void drawLastDroidCard(Canvas canvas, DroidCard c) {
        canvas.drawBitmap(c.bitmap,c.x,0f,paint);
    }
    /**
     * 只画出裁剪出来的一部分图片
     * @param canvas
     */
    private void drawDroidCard(Canvas canvas, List<DroidCard> mDroidCards, int i) {
        DroidCard c=mDroidCards.get(i);
        canvas.save();//保存
        canvas.clipRect((float)c.x,0f,(float)(mDroidCards.get(i+1).x),(float)c.height);
        canvas.drawBitmap(c.bitmap,c.x,0f,paint);
        canvas.restore();//还原
    }

}

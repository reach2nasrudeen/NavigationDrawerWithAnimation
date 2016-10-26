package com.app.navigationdrawer;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextRegular extends TextView {
    public TextRegular(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Amaranth-Regular.ttf"));
    }
}

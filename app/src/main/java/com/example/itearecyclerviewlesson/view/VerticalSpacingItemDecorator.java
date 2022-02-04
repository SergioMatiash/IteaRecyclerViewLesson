package com.example.itearecyclerviewlesson.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class VerticalSpacingItemDecorator extends RecyclerView.ItemDecoration {
    private final int verticalHeight;

    public VerticalSpacingItemDecorator(int verticalHeight) {
        this.verticalHeight = verticalHeight;
    }

    public void getItemOffsets (Rect outRect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        outRect.bottom = verticalHeight;
    }
}

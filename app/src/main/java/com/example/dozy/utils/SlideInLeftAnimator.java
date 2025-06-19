package com.example.dozy.utils;

import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class SlideInLeftAnimator extends DefaultItemAnimator {
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        View view = holder.itemView;
        view.setTranslationX(-view.getWidth());
        view.setAlpha(0);
        view.animate()
                .translationX(0)
                .alpha(1)
                .setDuration(1000)
                .setListener(null);
        return super.animateAdd(holder);
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        View view = holder.itemView;
        view.animate()
                .translationX(view.getWidth())
                .alpha(0)
                .setDuration(400)
                .withEndAction(() -> dispatchRemoveFinished(holder))
                .start();
        return true;
    }

    @Override
    public void onRemoveStarting(RecyclerView.ViewHolder item) {
        item.itemView.setAlpha(1f);
        item.itemView.setTranslationX(0f);
    }

    @Override
    public void onAddStarting(RecyclerView.ViewHolder item) {
        item.itemView.setAlpha(1f);
        item.itemView.setTranslationX(0f);
    }
}

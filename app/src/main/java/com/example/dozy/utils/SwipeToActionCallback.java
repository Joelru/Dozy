package com.example.dozy.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dozy.R;
import com.example.dozy.databinding.FragmentCurrentTaskBinding;

public abstract class SwipeToActionCallback extends ItemTouchHelper.SimpleCallback {
    private final Drawable deleteIcon;
    private final Drawable completeIcon;
    private final ColorDrawable deleteBackground;
    private final ColorDrawable completeBackground;
    private final int iconSize;
    private final int iconMargin;

    public SwipeToActionCallback(Context context) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete);
        completeIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete);
        deleteBackground = new ColorDrawable(Color.parseColor("#FF3B30"));   // rojo
        completeBackground = new ColorDrawable(Color.parseColor("#4CAF50")); // verde

        iconSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 20, context.getResources().getDisplayMetrics()
        );
        iconMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16, context.getResources().getDisplayMetrics()
        );
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;


        int top = itemView.getTop();
        int bottom = itemView.getBottom();
        float radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                15,
                itemView.getContext().getResources().getDisplayMetrics()
        );
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        RectF rect;
        Path path = new Path();

        if (dX < 0) { // Swipe izquierda - eliminar
            paint.setColor(Color.parseColor("#FF3B30")); // rojo

            rect = new RectF(
                    itemView.getRight() + dX,
                    top,
                    itemView.getRight(),
                    bottom
            );

            path.addRoundRect(
                    rect,
                    new float[]{0, 0, radius, radius, radius, radius, 0, 0}, // solo esquinas derechas
                    Path.Direction.CW
            );

            c.drawPath(path, paint);

            if (deleteIcon != null) {
                int iconTop = top + (bottom - top - iconSize) / 2;
                int iconRight = itemView.getRight() - iconMargin;
                int iconLeft = iconRight - iconSize;
                int iconBottom = iconTop + iconSize;

                deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                deleteIcon.draw(c);
            }

        } else if (dX > 0) { // Swipe derecha - completar
            paint.setColor(ContextCompat.getColor(itemView.getContext(), R.color.mint_green)); // verde

            rect = new RectF(
                    itemView.getLeft(),
                    top,
                    itemView.getLeft() + dX,
                    bottom
            );

            path.addRoundRect(
                    rect,
                    new float[]{radius, radius, 0, 0, 0, 0, radius, radius}, // solo esquinas izquierdas
                    Path.Direction.CW
            );

            c.drawPath(path, paint);

            if (completeIcon != null) {
                int iconTop = top + (bottom - top - iconSize) / 2;
                int iconLeft = itemView.getLeft() + iconMargin;
                int iconRight = iconLeft + iconSize;
                int iconBottom = iconTop + iconSize;

                completeIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                completeIcon.draw(c);
            }
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();

        if (direction == ItemTouchHelper.LEFT) {
            onDeleteConfirmed(position); // lógica para eliminar
        } else if (direction == ItemTouchHelper.RIGHT) {
            onCompleteConfirmed(position); // lógica para marcar como completada
        }
    }


    public abstract void onDeleteConfirmed(int position);

    public abstract void onCompleteConfirmed(int position);

}

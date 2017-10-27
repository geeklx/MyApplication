package com.example.shining.p041_glide411.largeimage.glide411;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.request.target.ViewTarget;
import com.shizhefei.view.largeimage.ILargeImageView;
import com.shizhefei.view.largeimage.factory.FileBitmapDecoderFactory;

import java.io.File;

/**
 * A base {@link com.bumptech.glide.request.target.Target} for displaying resources in
 * {@link android.widget.ImageView}s.
 *
 * @param <Z> The type of resource that this target will display in the wrapped {@link android.widget.ImageView}.
 */
public class LargeImageViewTarget extends ViewTarget<View, File>{
    private ILargeImageView largeImageView;
    public <V extends View & ILargeImageView> LargeImageViewTarget(V view) {
        super(view);
        this.largeImageView = view;
    }

    /**
     * Sets the given {@link Drawable} on the view using
     * {@link android.widget.ImageView#setImageDrawable(Drawable)}.
     *
     * @param placeholder {@inheritDoc}
     */
    @Override
    public void onLoadStarted(Drawable placeholder) {
        largeImageView.setImageDrawable(placeholder);
    }

    /**
     * Sets the given {@link Drawable} on the view using
     * {@link android.widget.ImageView#setImageDrawable(Drawable)}.
     *
     * @param errorDrawable {@inheritDoc}
     */
    @Override
    public void onLoadFailed(Drawable errorDrawable) {
        largeImageView.setImageDrawable(errorDrawable);
    }

    @Override
    public void onResourceReady(File resource, Transition<? super File> glideAnimation) {
        largeImageView.setImage(new FileBitmapDecoderFactory(resource));
    }

    /**
     * Sets the given {@link Drawable} on the view using
     * {@link android.widget.ImageView#setImageDrawable(Drawable)}.
     *
     * @param placeholder {@inheritDoc}
     */
    @Override
    public void onLoadCleared(Drawable placeholder) {
        largeImageView.setImageDrawable(placeholder);
    }
}


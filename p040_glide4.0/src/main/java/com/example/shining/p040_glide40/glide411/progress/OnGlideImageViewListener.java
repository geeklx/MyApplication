package com.example.shining.p040_glide40.glide411.progress;

import com.bumptech.glide.load.engine.GlideException;

public interface OnGlideImageViewListener {

    void onProgress(int percent, boolean isDone, GlideException exception);
}

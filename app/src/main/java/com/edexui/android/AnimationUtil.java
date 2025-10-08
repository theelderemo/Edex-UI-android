package com.edexui.android;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Animation utilities for Edex-UI
 * Provides sci-fi themed animations for UI elements
 */
public class AnimationUtil {

    /**
     * Fade in animation
     */
    public static void fadeIn(View view, long duration) {
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    /**
     * Fade out animation
     */
    public static void fadeOut(final View view, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    /**
     * Slide in from left
     */
    public static void slideInFromLeft(View view, long duration) {
        view.setTranslationX(-view.getWidth());
        view.setVisibility(View.VISIBLE);
        
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 
            -view.getWidth(), 0f);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    /**
     * Slide in from right
     */
    public static void slideInFromRight(View view, long duration) {
        view.setTranslationX(view.getWidth());
        view.setVisibility(View.VISIBLE);
        
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 
            view.getWidth(), 0f);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    /**
     * Scale in animation (zoom in)
     */
    public static void scaleIn(View view, long duration) {
        view.setScaleX(0f);
        view.setScaleY(0f);
        view.setVisibility(View.VISIBLE);
        
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f);
        
        scaleX.setDuration(duration);
        scaleY.setDuration(duration);
        scaleX.setInterpolator(new DecelerateInterpolator());
        scaleY.setInterpolator(new DecelerateInterpolator());
        
        scaleX.start();
        scaleY.start();
    }

    /**
     * Pulse animation (for attention)
     */
    public static void pulse(View view, long duration) {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.1f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.1f, 1f);
        
        scaleXAnimator.setDuration(duration);
        scaleYAnimator.setDuration(duration);
        scaleXAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleYAnimator.setRepeatCount(ValueAnimator.INFINITE);
        
        scaleXAnimator.start();
        scaleYAnimator.start();
    }

    /**
     * Glow effect (alpha pulsing)
     */
    public static void glow(View view, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.3f, 1f);
        animator.setDuration(duration);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    /**
     * Rotate animation
     */
    public static void rotate(View view, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        animator.setDuration(duration);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    /**
     * Glitch effect (rapid position changes)
     */
    public static void glitch(final View view, final int intensity, final long duration) {
        final float originalX = view.getTranslationX();
        final float originalY = view.getTranslationY();
        
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                if (Math.random() > 0.7) {
                    view.setTranslationX(originalX + (float) (Math.random() * intensity - intensity / 2));
                    view.setTranslationY(originalY + (float) (Math.random() * intensity - intensity / 2));
                } else {
                    view.setTranslationX(originalX);
                    view.setTranslationY(originalY);
                }
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setTranslationX(originalX);
                view.setTranslationY(originalY);
            }
        });
        animator.start();
    }
}

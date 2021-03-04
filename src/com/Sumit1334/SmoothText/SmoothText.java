package com.Sumit1334.SmoothText;


import android.animation.ValueAnimator;
import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;

@DesignerComponent(description = "An extension for text smoothly on labels and buttons by Sumit Kumar", version = 1, iconName = "https://community.kodular.io/user_avatar/community.kodular.io/sumit1334/120/82654_2.png",nonVisible = true,category = ComponentCategory.EXTENSION)
@SimpleObject(external = true)
public class SmoothText extends AndroidNonvisibleComponent {
    public SmoothText(ComponentContainer container) {
        super(container.$form());
    }

    @SimpleEvent(description = "This event raises when any error occurs")
    public void Error(String error){
        EventDispatcher.dispatchEvent(this,"Error",error);
    }
    @SimpleEvent(description = "This event raises when counting finishes on given component")
    public void CountFinished(Component component){
        EventDispatcher.dispatchEvent(this,"CountFinished",component);
    }
    @SimpleEvent(description = "This event raises when text writing on given component finishes")
    public void TextFinished(Component component){
        EventDispatcher.dispatchEvent(this,"TextFinished",component);
    }

    @SimpleFunction(description = "This block helps you to set the integer in any label smoothly")
    public void CountOnLabel(final Label component,final long duration,final int start, final int end){
        try {
            final ValueAnimator animator=ValueAnimator.ofInt(start,end);
            animator.setDuration(duration);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    component.Text(animator.getAnimatedValue().toString());
                    if (end==Integer.parseInt(animator.getAnimatedValue().toString()))
                        CountFinished(component);
                }
            });
            animator.start();
        }catch (Exception e){
            this.Error(e.toString());
        }
    }
    @SimpleFunction(description = "This block helps you to set the text char by char on any label")
    public void SmoothTextOnLabel(final Label component,final long duration, final String text){
        if (duration > 0) {
        try {
            final char[] array = text.toCharArray();
            component.Text("");
            ValueAnimator valueAnimator=ValueAnimator.ofInt(0,array.length);
            valueAnimator.setDuration(duration);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    String str= text.substring(0,Integer.parseInt(valueAnimator.getAnimatedValue().toString()));
                    component.Text(str);
                    if (component.Text()==text)
                        TextFinished(component);
                }
            });
            valueAnimator.start();
            }catch(Exception e){
                this.Error(e.toString());
            }
        }else
            throw new YailRuntimeError("Invalid duration","duration is less than zero");
    }
    @SimpleFunction(description = "This block helps you to set the text char by char in any button")
    public void SmoothTextOnButton(final Button component, final long duration, final String text){
        if (duration > 0) {
            try {
                final char[] array = text.toCharArray();
                component.Text("");
                ValueAnimator valueAnimator=ValueAnimator.ofInt(0,array.length);
                valueAnimator.setDuration(duration);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        String str= text.substring(0,Integer.parseInt(valueAnimator.getAnimatedValue().toString()));
                        component.Text(str);
                        if (component.Text()==text)
                            TextFinished(component);
                    }
                });
                valueAnimator.start();
            }catch(Exception e){
                this.Error(e.toString());
            }
        }else
            throw new YailRuntimeError("Invalid duration","duration is less than zero");
    }
    @SimpleFunction(description = "This block helps you to set the integer in any Button smoothly")
    public void CountOnButton(final Button component,final long duration,final int start,final int end){
        try {
            final ValueAnimator animator=ValueAnimator.ofInt(start,end);
            animator.setDuration(duration);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    component.Text(animator.getAnimatedValue().toString());
                    if (end==Integer.parseInt(animator.getAnimatedValue().toString()))
                        CountFinished(component);
                }
            });
            animator.start();
        }catch (Exception e){
            this.Error(e.toString());
        }
    }

}

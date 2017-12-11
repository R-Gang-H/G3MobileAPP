// Generated code from Butter Knife. Do not modify!
package com.app.itserv.views;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.Utils;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ImageListView$$ViewBinder<T extends ImageListView> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    target.images = Utils.listOf(
        finder.<ImageView>findRequiredView(source, 2131690435, "field 'images'"), 
        finder.<ImageView>findRequiredView(source, 2131690438, "field 'images'"), 
        finder.<ImageView>findRequiredView(source, 2131690441, "field 'images'"), 
        finder.<ImageView>findRequiredView(source, 2131690444, "field 'images'"), 
        finder.<ImageView>findRequiredView(source, 2131690447, "field 'images'"));
    target.imgCloses = Utils.listOf(
        finder.<ImageView>findRequiredView(source, 2131690436, "field 'imgCloses'"), 
        finder.<ImageView>findRequiredView(source, 2131690439, "field 'imgCloses'"), 
        finder.<ImageView>findRequiredView(source, 2131690442, "field 'imgCloses'"), 
        finder.<ImageView>findRequiredView(source, 2131690445, "field 'imgCloses'"), 
        finder.<ImageView>findRequiredView(source, 2131690448, "field 'imgCloses'"));
    target.layoutFls = Utils.listOf(
        finder.<FrameLayout>findRequiredView(source, 2131690434, "field 'layoutFls'"), 
        finder.<FrameLayout>findRequiredView(source, 2131690437, "field 'layoutFls'"), 
        finder.<FrameLayout>findRequiredView(source, 2131690440, "field 'layoutFls'"), 
        finder.<FrameLayout>findRequiredView(source, 2131690443, "field 'layoutFls'"), 
        finder.<FrameLayout>findRequiredView(source, 2131690446, "field 'layoutFls'"));
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ImageListView> implements Unbinder {
    private T target;

    protected InnerUnbinder(T target) {
      this.target = target;
    }

    @Override
    public final void unbind() {
      if (target == null) throw new IllegalStateException("Bindings already cleared.");
      unbind(target);
      target = null;
    }

    protected void unbind(T target) {
      target.images = null;
      target.imgCloses = null;
      target.layoutFls = null;
    }
  }
}

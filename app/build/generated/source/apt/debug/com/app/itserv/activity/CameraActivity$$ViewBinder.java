// Generated code from Butter Knife. Do not modify!
package com.app.itserv.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class CameraActivity$$ViewBinder<T extends CameraActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689573, "field 'tvCamera' and method 'onClick'");
    target.tvCamera = finder.castView(view, 2131689573, "field 'tvCamera'");
    unbinder.view2131689573 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131689574, "field 'tvAlbum' and method 'onClick'");
    target.tvAlbum = finder.castView(view, 2131689574, "field 'tvAlbum'");
    unbinder.view2131689574 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends CameraActivity> implements Unbinder {
    private T target;

    View view2131689573;

    View view2131689574;

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
      view2131689573.setOnClickListener(null);
      target.tvCamera = null;
      view2131689574.setOnClickListener(null);
      target.tvAlbum = null;
    }
  }
}

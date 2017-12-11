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

public class ShareActivity$$ViewBinder<T extends ShareActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689608, "field 'tvImgUpload'");
    target.tvImgUpload = finder.castView(view, 2131689608, "field 'tvImgUpload'");
    view = finder.findRequiredView(source, 2131689607, "field 'imgAppUpload'");
    target.imgAppUpload = finder.castView(view, 2131689607, "field 'imgAppUpload'");
    view = finder.findRequiredView(source, 2131689572, "method 'onClick'");
    unbinder.view2131689572 = view;
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

  protected static class InnerUnbinder<T extends ShareActivity> implements Unbinder {
    private T target;

    View view2131689572;

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
      target.tvImgUpload = null;
      target.imgAppUpload = null;
      view2131689572.setOnClickListener(null);
    }
  }
}

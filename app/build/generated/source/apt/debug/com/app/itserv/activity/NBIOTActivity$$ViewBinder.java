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

public class NBIOTActivity$$ViewBinder<T extends NBIOTActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689572, "field 'imgBack' and method 'onViewClicked'");
    target.imgBack = finder.castView(view, 2131689572, "field 'imgBack'");
    unbinder.view2131689572 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = finder.findRequiredView(source, 2131689604, "field 'imgAddNBIOT' and method 'onViewClicked'");
    target.imgAddNBIOT = finder.castView(view, 2131689604, "field 'imgAddNBIOT'");
    unbinder.view2131689604 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = finder.findRequiredView(source, 2131689605, "field 'viewsNBIOT'");
    target.viewsNBIOT = finder.castView(view, 2131689605, "field 'viewsNBIOT'");
    view = finder.findRequiredView(source, 2131689606, "field 'tvShow'");
    target.tvShow = finder.castView(view, 2131689606, "field 'tvShow'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends NBIOTActivity> implements Unbinder {
    private T target;

    View view2131689572;

    View view2131689604;

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
      view2131689572.setOnClickListener(null);
      target.imgBack = null;
      view2131689604.setOnClickListener(null);
      target.imgAddNBIOT = null;
      target.viewsNBIOT = null;
      target.tvShow = null;
    }
  }
}

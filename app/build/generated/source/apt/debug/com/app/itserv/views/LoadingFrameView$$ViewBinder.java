// Generated code from Butter Knife. Do not modify!
package com.app.itserv.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class LoadingFrameView$$ViewBinder<T extends LoadingFrameView> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131690449, "field 'container'");
    target.container = finder.castView(view, 2131690449, "field 'container'");
    view = finder.findRequiredView(source, 2131690451, "field 'loadInfoPb'");
    target.loadInfoPb = finder.castView(view, 2131690451, "field 'loadInfoPb'");
    view = finder.findRequiredView(source, 2131690452, "field 'loadInfo'");
    target.loadInfo = finder.castView(view, 2131690452, "field 'loadInfo'");
    view = finder.findRequiredView(source, 2131690458, "field 'noInfoPic'");
    target.noInfoPic = finder.castView(view, 2131690458, "field 'noInfoPic'");
    view = finder.findRequiredView(source, 2131690459, "field 'noInfo'");
    target.noInfo = finder.castView(view, 2131690459, "field 'noInfo'");
    view = finder.findRequiredView(source, 2131690454, "field 'ivRepeatPic'");
    target.ivRepeatPic = finder.castView(view, 2131690454, "field 'ivRepeatPic'");
    view = finder.findRequiredView(source, 2131690455, "field 'tvRepeatInfo'");
    target.tvRepeatInfo = finder.castView(view, 2131690455, "field 'tvRepeatInfo'");
    view = finder.findRequiredView(source, 2131690456, "field 'tvTrybt'");
    target.tvTrybt = finder.castView(view, 2131690456, "field 'tvTrybt'");
    view = finder.findRequiredView(source, 2131690457, "field 'custom_ll'");
    target.custom_ll = finder.castView(view, 2131690457, "field 'custom_ll'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends LoadingFrameView> implements Unbinder {
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
      target.container = null;
      target.loadInfoPb = null;
      target.loadInfo = null;
      target.noInfoPic = null;
      target.noInfo = null;
      target.ivRepeatPic = null;
      target.tvRepeatInfo = null;
      target.tvTrybt = null;
      target.custom_ll = null;
    }
  }
}

// Generated code from Butter Knife. Do not modify!
package com.app.itserv.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class GreenHouseManagerActivity$$ViewBinder<T extends GreenHouseManagerActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689572, "field 'ImgBack'");
    target.ImgBack = finder.castView(view, 2131689572, "field 'ImgBack'");
    view = finder.findRequiredView(source, 2131690357, "field 'tvGhouseSize'");
    target.tvGhouseSize = finder.castView(view, 2131690357, "field 'tvGhouseSize'");
    view = finder.findRequiredView(source, 2131690358, "field 'btAddGreHou'");
    target.btAddGreHou = finder.castView(view, 2131690358, "field 'btAddGreHou'");
    view = finder.findRequiredView(source, 2131690359, "field 'greHouManagerList'");
    target.greHouManagerList = finder.castView(view, 2131690359, "field 'greHouManagerList'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends GreenHouseManagerActivity> implements Unbinder {
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
      target.ImgBack = null;
      target.tvGhouseSize = null;
      target.btAddGreHou = null;
      target.greHouManagerList = null;
    }
  }
}

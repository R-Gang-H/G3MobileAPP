// Generated code from Butter Knife. Do not modify!
package com.app.itserv.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class GreenHouseSortActivity$$ViewBinder<T extends GreenHouseSortActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689572, "field 'ImgBack'");
    target.ImgBack = finder.castView(view, 2131689572, "field 'ImgBack'");
    view = finder.findRequiredView(source, 2131689743, "field 'followListView'");
    target.followListView = finder.castView(view, 2131689743, "field 'followListView'");
    view = finder.findRequiredView(source, 2131689585, "field 'changPsdsbm'");
    target.changPsdsbm = finder.castView(view, 2131689585, "field 'changPsdsbm'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends GreenHouseSortActivity> implements Unbinder {
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
      target.followListView = null;
      target.changPsdsbm = null;
    }
  }
}

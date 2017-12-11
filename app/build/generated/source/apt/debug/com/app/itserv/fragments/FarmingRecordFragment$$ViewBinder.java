// Generated code from Butter Knife. Do not modify!
package com.app.itserv.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FarmingRecordFragment$$ViewBinder<T extends FarmingRecordFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689701, "field 'columnList'");
    target.columnList = finder.castView(view, 2131689701, "field 'columnList'");
    view = finder.findRequiredView(source, 2131689741, "field 'loadView'");
    target.loadView = finder.castView(view, 2131689741, "field 'loadView'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends FarmingRecordFragment> implements Unbinder {
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
      target.columnList = null;
      target.loadView = null;
    }
  }
}

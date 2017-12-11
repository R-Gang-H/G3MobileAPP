// Generated code from Butter Knife. Do not modify!
package com.app.itserv.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class GHMonitoringFragment$$ViewBinder<T extends GHMonitoringFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689701, "field 'columnList'");
    target.columnList = finder.castView(view, 2131689701, "field 'columnList'");
    view = finder.findRequiredView(source, 2131690497, "field 'ldMonitorView'");
    target.ldMonitorView = finder.castView(view, 2131690497, "field 'ldMonitorView'");
    view = finder.findRequiredView(source, 2131690502, "field 'tvNbiotBtn'");
    target.tvNbiotBtn = finder.castView(view, 2131690502, "field 'tvNbiotBtn'");
    view = finder.findRequiredView(source, 2131690503, "field 'tvNbiotNumber'");
    target.tvNbiotNumber = finder.castView(view, 2131690503, "field 'tvNbiotNumber'");
    view = finder.findRequiredView(source, 2131690501, "field 'mainFragmentAlarmJump'");
    target.mainFragmentAlarmJump = finder.castView(view, 2131690501, "field 'mainFragmentAlarmJump'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends GHMonitoringFragment> implements Unbinder {
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
      target.ldMonitorView = null;
      target.tvNbiotBtn = null;
      target.tvNbiotNumber = null;
      target.mainFragmentAlarmJump = null;
    }
  }
}

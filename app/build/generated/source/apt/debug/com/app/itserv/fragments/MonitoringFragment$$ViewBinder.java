// Generated code from Butter Knife. Do not modify!
package com.app.itserv.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MonitoringFragment$$ViewBinder<T extends MonitoringFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131690495, "field 'spSex'");
    target.spSex = finder.castView(view, 2131690495, "field 'spSex'");
    view = finder.findRequiredView(source, 2131690496, "field 'btnRefresh' and method 'onViewClicked'");
    target.btnRefresh = finder.castView(view, 2131690496, "field 'btnRefresh'");
    unbinder.view2131690496 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = finder.findRequiredView(source, 2131689807, "field 'llGender'");
    target.llGender = finder.castView(view, 2131689807, "field 'llGender'");
    view = finder.findRequiredView(source, 2131689809, "field 'llBirthday'");
    target.llBirthday = finder.castView(view, 2131689809, "field 'llBirthday'");
    view = finder.findRequiredView(source, 2131689701, "field 'columnList'");
    target.columnList = finder.castView(view, 2131689701, "field 'columnList'");
    view = finder.findRequiredView(source, 2131690497, "field 'ldMonitorView'");
    target.ldMonitorView = finder.castView(view, 2131690497, "field 'ldMonitorView'");
    view = finder.findRequiredView(source, 2131690498, "field 'btnDetailShed' and method 'onViewClicked'");
    target.btnDetailShed = finder.castView(view, 2131690498, "field 'btnDetailShed'");
    unbinder.view2131690498 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = finder.findRequiredView(source, 2131690499, "field 'btnEnvirCurve' and method 'onViewClicked'");
    target.btnEnvirCurve = finder.castView(view, 2131690499, "field 'btnEnvirCurve'");
    unbinder.view2131690499 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = finder.findRequiredView(source, 2131690500, "field 'btnRealMonitor' and method 'onViewClicked'");
    target.btnRealMonitor = finder.castView(view, 2131690500, "field 'btnRealMonitor'");
    unbinder.view2131690500 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends MonitoringFragment> implements Unbinder {
    private T target;

    View view2131690496;

    View view2131690498;

    View view2131690499;

    View view2131690500;

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
      target.spSex = null;
      view2131690496.setOnClickListener(null);
      target.btnRefresh = null;
      target.llGender = null;
      target.llBirthday = null;
      target.columnList = null;
      target.ldMonitorView = null;
      view2131690498.setOnClickListener(null);
      target.btnDetailShed = null;
      view2131690499.setOnClickListener(null);
      target.btnEnvirCurve = null;
      view2131690500.setOnClickListener(null);
      target.btnRealMonitor = null;
    }
  }
}

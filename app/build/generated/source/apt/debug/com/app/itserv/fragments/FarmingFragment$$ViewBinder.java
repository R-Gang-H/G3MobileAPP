// Generated code from Butter Knife. Do not modify!
package com.app.itserv.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FarmingFragment$$ViewBinder<T extends FarmingFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689685, "field 'indicator'");
    target.indicator = finder.castView(view, 2131689685, "field 'indicator'");
    view = finder.findRequiredView(source, 2131690190, "field 'pager'");
    target.pager = finder.castView(view, 2131690190, "field 'pager'");
    view = finder.findRequiredView(source, 2131690189, "field 'tvFarmingRight'");
    target.tvFarmingRight = finder.castView(view, 2131690189, "field 'tvFarmingRight'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends FarmingFragment> implements Unbinder {
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
      target.indicator = null;
      target.pager = null;
      target.tvFarmingRight = null;
    }
  }
}

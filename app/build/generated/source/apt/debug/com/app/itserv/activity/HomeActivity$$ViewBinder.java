// Generated code from Butter Knife. Do not modify!
package com.app.itserv.activity;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.Utils;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class HomeActivity$$ViewBinder<T extends HomeActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131690396, "field 'tabContent'");
    target.tabContent = finder.castView(view, 2131690396, "field 'tabContent'");
    target.radioButtons = Utils.listOf(
        finder.<TextView>findRequiredView(source, 2131690209, "field 'radioButtons'"), 
        finder.<TextView>findRequiredView(source, 2131690210, "field 'radioButtons'"), 
        finder.<TextView>findRequiredView(source, 2131690211, "field 'radioButtons'"), 
        finder.<TextView>findRequiredView(source, 2131690212, "field 'radioButtons'"));
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends HomeActivity> implements Unbinder {
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
      target.tabContent = null;
      target.radioButtons = null;
    }
  }
}

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

public class AboutActivity$$ViewBinder<T extends AboutActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689795, "field 'btnBack' and method 'onClick'");
    target.btnBack = finder.castView(view, 2131689795, "field 'btnBack'");
    unbinder.view2131689795 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131689796, "field 'titleTxt'");
    target.titleTxt = finder.castView(view, 2131689796, "field 'titleTxt'");
    view = finder.findRequiredView(source, 2131689565, "field 'versionCode'");
    target.versionCode = finder.castView(view, 2131689565, "field 'versionCode'");
    view = finder.findRequiredView(source, 2131689567, "field 'copyright'");
    target.copyright = finder.castView(view, 2131689567, "field 'copyright'");
    view = finder.findRequiredView(source, 2131689566, "field 'content'");
    target.content = finder.castView(view, 2131689566, "field 'content'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends AboutActivity> implements Unbinder {
    private T target;

    View view2131689795;

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
      view2131689795.setOnClickListener(null);
      target.btnBack = null;
      target.titleTxt = null;
      target.versionCode = null;
      target.copyright = null;
      target.content = null;
    }
  }
}

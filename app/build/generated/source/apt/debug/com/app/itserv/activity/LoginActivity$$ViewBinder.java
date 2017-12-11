// Generated code from Butter Knife. Do not modify!
package com.app.itserv.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class LoginActivity$$ViewBinder<T extends LoginActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689805, "field 'username'");
    target.username = finder.castView(view, 2131689805, "field 'username'");
    view = finder.findRequiredView(source, 2131690460, "field 'pwd'");
    target.pwd = finder.castView(view, 2131690460, "field 'pwd'");
    view = finder.findRequiredView(source, 2131690461, "field 'ckShowPwd'");
    target.ckShowPwd = finder.castView(view, 2131690461, "field 'ckShowPwd'");
    view = finder.findRequiredView(source, 2131690462, "field 'remenberPsd'");
    target.remenberPsd = finder.castView(view, 2131690462, "field 'remenberPsd'");
    view = finder.findRequiredView(source, 2131690463, "field 'loginBtn'");
    target.loginBtn = finder.castView(view, 2131690463, "field 'loginBtn'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends LoginActivity> implements Unbinder {
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
      target.username = null;
      target.pwd = null;
      target.ckShowPwd = null;
      target.remenberPsd = null;
      target.loginBtn = null;
    }
  }
}

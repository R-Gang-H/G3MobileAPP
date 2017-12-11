// Generated code from Butter Knife. Do not modify!
package com.app.itserv.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MyFarmingRecordActivity$$ViewBinder<T extends MyFarmingRecordActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689572, "field 'imgBack'");
    target.imgBack = finder.castView(view, 2131689572, "field 'imgBack'");
    view = finder.findRequiredView(source, 2131690122, "field 'titleTextView'");
    target.titleTextView = finder.castView(view, 2131690122, "field 'titleTextView'");
    view = finder.findRequiredView(source, 2131690123, "field 'greenhouse'");
    target.greenhouse = finder.castView(view, 2131690123, "field 'greenhouse'");
    view = finder.findRequiredView(source, 2131690124, "field 'taskname'");
    target.taskname = finder.castView(view, 2131690124, "field 'taskname'");
    view = finder.findRequiredView(source, 2131690125, "field 'fartype'");
    target.fartype = finder.castView(view, 2131690125, "field 'fartype'");
    view = finder.findRequiredView(source, 2131690127, "field 'recorder'");
    target.recorder = finder.castView(view, 2131690127, "field 'recorder'");
    view = finder.findRequiredView(source, 2131690126, "field 'recorder_ll'");
    target.recorder_ll = finder.castView(view, 2131690126, "field 'recorder_ll'");
    view = finder.findRequiredView(source, 2131690128, "field 'startdistritime'");
    target.startdistritime = finder.castView(view, 2131690128, "field 'startdistritime'");
    view = finder.findRequiredView(source, 2131690129, "field 'stopdistritime'");
    target.stopdistritime = finder.castView(view, 2131690129, "field 'stopdistritime'");
    view = finder.findRequiredView(source, 2131690130, "field 'select'");
    target.select = finder.castView(view, 2131690130, "field 'select'");
    view = finder.findRequiredView(source, 2131690131, "field 'changepswreset'");
    target.changepswreset = finder.castView(view, 2131690131, "field 'changepswreset'");
    view = finder.findRequiredView(source, 2131690132, "field 'listview'");
    target.listview = finder.castView(view, 2131690132, "field 'listview'");
    view = finder.findRequiredView(source, 2131689741, "field 'loadView'");
    target.loadView = finder.castView(view, 2131689741, "field 'loadView'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends MyFarmingRecordActivity> implements Unbinder {
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
      target.imgBack = null;
      target.titleTextView = null;
      target.greenhouse = null;
      target.taskname = null;
      target.fartype = null;
      target.recorder = null;
      target.recorder_ll = null;
      target.startdistritime = null;
      target.stopdistritime = null;
      target.select = null;
      target.changepswreset = null;
      target.listview = null;
      target.loadView = null;
    }
  }
}

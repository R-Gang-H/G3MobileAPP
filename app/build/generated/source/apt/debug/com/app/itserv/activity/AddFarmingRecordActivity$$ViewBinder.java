// Generated code from Butter Knife. Do not modify!
package com.app.itserv.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AddFarmingRecordActivity$$ViewBinder<T extends AddFarmingRecordActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689572, "field 'imgBack'");
    target.imgBack = finder.castView(view, 2131689572, "field 'imgBack'");
    view = finder.findRequiredView(source, 2131689648, "field 'tvAddfarmStartdate'");
    target.tvAddfarmStartdate = finder.castView(view, 2131689648, "field 'tvAddfarmStartdate'");
    view = finder.findRequiredView(source, 2131689649, "field 'tvAddfarmEnddate'");
    target.tvAddfarmEnddate = finder.castView(view, 2131689649, "field 'tvAddfarmEnddate'");
    view = finder.findRequiredView(source, 2131689650, "field 'farExaGreenhouse'");
    target.farExaGreenhouse = finder.castView(view, 2131689650, "field 'farExaGreenhouse'");
    view = finder.findRequiredView(source, 2131689651, "field 'farExaClassify'");
    target.farExaClassify = finder.castView(view, 2131689651, "field 'farExaClassify'");
    view = finder.findRequiredView(source, 2131689652, "field 'btnAutoBuild'");
    target.btnAutoBuild = finder.castView(view, 2131689652, "field 'btnAutoBuild'");
    view = finder.findRequiredView(source, 2131689653, "field 'btnManualEdit'");
    target.btnManualEdit = finder.castView(view, 2131689653, "field 'btnManualEdit'");
    view = finder.findRequiredView(source, 2131689654, "field 'farExaTaskDescribe'");
    target.farExaTaskDescribe = finder.castView(view, 2131689654, "field 'farExaTaskDescribe'");
    view = finder.findRequiredView(source, 2131689655, "field 'farExaCkIssue'");
    target.farExaCkIssue = finder.castView(view, 2131689655, "field 'farExaCkIssue'");
    view = finder.findRequiredView(source, 2131689657, "field 'farExaIssueExplain'");
    target.farExaIssueExplain = finder.castView(view, 2131689657, "field 'farExaIssueExplain'");
    view = finder.findRequiredView(source, 2131689656, "field 'llFarExProblemDescription'");
    target.llFarExProblemDescription = finder.castView(view, 2131689656, "field 'llFarExProblemDescription'");
    view = finder.findRequiredView(source, 2131689659, "field 'farExaUploadIssueImg'");
    target.farExaUploadIssueImg = finder.castView(view, 2131689659, "field 'farExaUploadIssueImg'");
    view = finder.findRequiredView(source, 2131689658, "field 'llFarExUploadPictures'");
    target.llFarExUploadPictures = finder.castView(view, 2131689658, "field 'llFarExUploadPictures'");
    view = finder.findRequiredView(source, 2131689661, "field 'farExaPreview3'");
    target.farExaPreview3 = finder.castView(view, 2131689661, "field 'farExaPreview3'");
    view = finder.findRequiredView(source, 2131689660, "field 'llFarExPreview'");
    target.llFarExPreview = finder.castView(view, 2131689660, "field 'llFarExPreview'");
    view = finder.findRequiredView(source, 2131689663, "field 'farExaBtnSubmite'");
    target.farExaBtnSubmite = finder.castView(view, 2131689663, "field 'farExaBtnSubmite'");
    view = finder.findRequiredView(source, 2131689664, "field 'farExaCannel'");
    target.farExaCannel = finder.castView(view, 2131689664, "field 'farExaCannel'");
    view = finder.findRequiredView(source, 2131689662, "field 'farExaLlSupply'");
    target.farExaLlSupply = finder.castView(view, 2131689662, "field 'farExaLlSupply'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends AddFarmingRecordActivity> implements Unbinder {
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
      target.tvAddfarmStartdate = null;
      target.tvAddfarmEnddate = null;
      target.farExaGreenhouse = null;
      target.farExaClassify = null;
      target.btnAutoBuild = null;
      target.btnManualEdit = null;
      target.farExaTaskDescribe = null;
      target.farExaCkIssue = null;
      target.farExaIssueExplain = null;
      target.llFarExProblemDescription = null;
      target.farExaUploadIssueImg = null;
      target.llFarExUploadPictures = null;
      target.farExaPreview3 = null;
      target.llFarExPreview = null;
      target.farExaBtnSubmite = null;
      target.farExaCannel = null;
      target.farExaLlSupply = null;
    }
  }
}

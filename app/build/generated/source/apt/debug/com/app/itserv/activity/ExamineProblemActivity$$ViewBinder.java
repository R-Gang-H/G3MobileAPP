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

public class ExamineProblemActivity$$ViewBinder<T extends ExamineProblemActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131689572, "field 'imgBack' and method 'onClick'");
    target.imgBack = finder.castView(view, 2131689572, "field 'imgBack'");
    unbinder.view2131689572 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131690009, "field 'tvProblemState'");
    target.tvProblemState = finder.castView(view, 2131690009, "field 'tvProblemState'");
    view = finder.findRequiredView(source, 2131690008, "field 'llProblem'");
    target.llProblem = finder.castView(view, 2131690008, "field 'llProblem'");
    view = finder.findRequiredView(source, 2131690010, "field 'problemClass'");
    target.problemClass = finder.castView(view, 2131690010, "field 'problemClass'");
    view = finder.findRequiredView(source, 2131690011, "field 'tvQuestionTime'");
    target.tvQuestionTime = finder.castView(view, 2131690011, "field 'tvQuestionTime'");
    view = finder.findRequiredView(source, 2131690012, "field 'taskName'");
    target.taskName = finder.castView(view, 2131690012, "field 'taskName'");
    view = finder.findRequiredView(source, 2131690013, "field 'taskDescribe'");
    target.taskDescribe = finder.castView(view, 2131690013, "field 'taskDescribe'");
    view = finder.findRequiredView(source, 2131690014, "field 'annexOne'");
    target.annexOne = finder.castView(view, 2131690014, "field 'annexOne'");
    view = finder.findRequiredView(source, 2131690015, "field 'annexTwo'");
    target.annexTwo = finder.castView(view, 2131690015, "field 'annexTwo'");
    view = finder.findRequiredView(source, 2131690016, "field 'annexThree'");
    target.annexThree = finder.castView(view, 2131690016, "field 'annexThree'");
    view = finder.findRequiredView(source, 2131690017, "field 'examineProLv'");
    target.examineProLv = finder.castView(view, 2131690017, "field 'examineProLv'");
    view = finder.findRequiredView(source, 2131690018, "field 'examineProLlNotarize'");
    target.examineProLlNotarize = finder.castView(view, 2131690018, "field 'examineProLlNotarize'");
    view = finder.findRequiredView(source, 2131690020, "field 'tvReplayMessage'");
    target.tvReplayMessage = finder.castView(view, 2131690020, "field 'tvReplayMessage'");
    view = finder.findRequiredView(source, 2131690019, "field 'examineProLlReplyMessage'");
    target.examineProLlReplyMessage = finder.castView(view, 2131690019, "field 'examineProLlReplyMessage'");
    view = finder.findRequiredView(source, 2131690022, "field 'btnAddQuestions' and method 'onClick'");
    target.btnAddQuestions = finder.castView(view, 2131690022, "field 'btnAddQuestions'");
    unbinder.view2131690022 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131690023, "field 'btnCloseProblem' and method 'onClick'");
    target.btnCloseProblem = finder.castView(view, 2131690023, "field 'btnCloseProblem'");
    unbinder.view2131690023 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = finder.findRequiredView(source, 2131690021, "field 'examineProLlSupply'");
    target.examineProLlSupply = finder.castView(view, 2131690021, "field 'examineProLlSupply'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ExamineProblemActivity> implements Unbinder {
    private T target;

    View view2131689572;

    View view2131690022;

    View view2131690023;

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
      view2131689572.setOnClickListener(null);
      target.imgBack = null;
      target.tvProblemState = null;
      target.llProblem = null;
      target.problemClass = null;
      target.tvQuestionTime = null;
      target.taskName = null;
      target.taskDescribe = null;
      target.annexOne = null;
      target.annexTwo = null;
      target.annexThree = null;
      target.examineProLv = null;
      target.examineProLlNotarize = null;
      target.tvReplayMessage = null;
      target.examineProLlReplyMessage = null;
      view2131690022.setOnClickListener(null);
      target.btnAddQuestions = null;
      view2131690023.setOnClickListener(null);
      target.btnCloseProblem = null;
      target.examineProLlSupply = null;
    }
  }
}

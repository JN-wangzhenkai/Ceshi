package com.pdd.ceshi.WeChatImage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

public class CustomWebView extends WebView {
//  //这个出来listview显示不全的问题
//  private int listViewTouchAction;
//  private static final int MAXIMUM_LIST_ITEMS_VIEWABLE = 99;

    // 是否允许向下拖拽到顶部视图
    boolean mAllowDragToTop;
    // 手势 down 时的 Y 轴坐标
    float mDownY;

    public CustomWebView(Context context) {
        super(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写 WebView 的 dispatchTouchEvent() 方法，自定义 touch 事件的分发流程。
     * 1. 默认情况下，WebView 内部的滚动优先，即由 WebView 处理 touch 事件。
     * 2. 当 WebView 滚动到顶部时，此时再次向下拖拽，则将 touch 事件交给父视图处理，其他情况，都是由 WebView 来处理 touch 事件
     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            mDownY = ev.getRawY();
//            // ACTION_DOWN 的时候判断是否允许向下拖拽到顶部视图。
//            // WebView 滑动到顶部，则允许，否则不允许
//            mAllowDragToTop = isAtTop();
//        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//
//            Log.d("0555555", "dispatchTouchEvent: "+mAllowDragToTop+"----"+ ev.getRawY() +"--------"+ mDownY);
//            if (mAllowDragToTop && ev.getRawY() >= mDownY) {
//                // 承接上一个 ACTION_DOWN，如果允许向下拖拽到顶部视图，且再次向下拖拽时，就将 touch 事件交给父视图处理
//                getParent().requestDisallowInterceptTouchEvent(false);
//                return false;
//            }
//        }
//
//        // 默认情况下父视图不拦截 WebView 的 touch 事件
//        getParent().requestDisallowInterceptTouchEvent(true);
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mDownY = ev.getRawY();
            // ACTION_DOWN 的时候判断是否允许向下拖拽到顶部视图。
            // WebView 滑动到顶部，则允许，否则不允许
            mAllowDragToTop = isAtTop();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (mAllowDragToTop && ev.getRawY() > mDownY) {
                // 承接上一个 ACTION_DOWN，如果允许向下拖拽到顶部视图，且再次向下拖拽时，就将 touch 事件交给父视图处理
                getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        }

        // 默认情况下父视图不拦截 WebView 的 touch 事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(ev);
    }

    /**
     * @return WebView 是否滑动到顶部
     */
    private boolean isAtTop() {
        return getScrollY() == 0;

    }
//  @Override
//  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//    super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 4, MeasureSpec.AT_MOST));
//  }


////处理listview显示不全的问题
//  @Override
//  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//    int newHeight = 0;
//    final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//    int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//    if (heightMode != MeasureSpec.EXACTLY) {
//      ListAdapter listAdapter = getAdapter();
//      if (listAdapter != null && !listAdapter.isEmpty()) {
//        int listPosition = 0;
//        for (listPosition = 0; listPosition < listAdapter.getCount()
//                && listPosition < MAXIMUM_LIST_ITEMS_VIEWABLE; listPosition++) {
//          View listItem = listAdapter.getView(listPosition, null, this);
//          //now it will not throw a NPE if listItem is a ViewGroup instance
//          if (listItem instanceof ViewGroup) {
//            listItem.setLayoutParams(new LayoutParams(
//                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//          }
//          listItem.measure(widthMeasureSpec, heightMeasureSpec);
//          newHeight += listItem.getMeasuredHeight();
//        }
//        newHeight += getDividerHeight() * listPosition;
//      }
//      if ((heightMode == MeasureSpec.AT_MOST) && (newHeight > heightSize)) {
//        if (newHeight > heightSize) {
//          newHeight = heightSize;
//        }
//      }
//    } else {
//      newHeight = getMeasuredHeight();
//    }
//    setMeasuredDimension(getMeasuredWidth(), newHeight);
//  }
//  @Override
//  public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//  }
//
//  @Override
//  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//    if (getAdapter() != null && getAdapter().getCount() > MAXIMUM_LIST_ITEMS_VIEWABLE) {
//      if (listViewTouchAction == MotionEvent.ACTION_MOVE) {
//        scrollBy(0, -1);
//      }
//    }
//
//  }
//
//  /**
//   *
//   * 处理listview显示不全
//   * @param v
//   * @param event
//   * @return
//   */
//  @Override
//  public boolean onTouch(View v, MotionEvent event) {
//    if (getAdapter() != null && getAdapter().getCount() > MAXIMUM_LIST_ITEMS_VIEWABLE) {
//      if (listViewTouchAction == MotionEvent.ACTION_MOVE) {
//        scrollBy(0, 1);
//      }
//    }
//    return false;
//
//  }
}

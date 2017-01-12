package com.hotbitmapgg.gank.widget.recyclehelper;

import com.hotbitmapgg.studyproject.R;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * RecycleView通用适配器
 */
public abstract class AbsRecyclerViewAdapter
    extends RecyclerView.Adapter<AbsRecyclerViewAdapter.ClickableViewHolder> {

  private Context context;

  protected RecyclerView mRecyclerView;

  private int mLastPosition = -1;

  private static final int DELAY = 138;

  protected List<RecyclerView.OnScrollListener> mListeners
      = new ArrayList<RecyclerView.OnScrollListener>();


  public AbsRecyclerViewAdapter(RecyclerView recyclerView) {

    this.mRecyclerView = recyclerView;
    this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

      @Override
      public void onScrollStateChanged(RecyclerView rv, int newState) {

        for (RecyclerView.OnScrollListener listener : mListeners) {
          listener.onScrollStateChanged(rv, newState);
        }
      }


      @Override
      public void onScrolled(RecyclerView rv, int dx, int dy) {

        for (RecyclerView.OnScrollListener listener : mListeners) {
          listener.onScrolled(rv, dx, dy);
        }
      }
    });
  }


  public void addOnScrollListener(RecyclerView.OnScrollListener listener) {

    mListeners.add(listener);
  }


  public void showItemAnim(final View view, final int position) {

    if (position > mLastPosition) {
      view.setAlpha(0);
      view.postDelayed(new Runnable() {

        @Override
        public void run() {

          Animation animation = AnimationUtils.loadAnimation(view.getContext(),
              R.anim.slide_in_right);
          animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

              view.setAlpha(1);
            }


            @Override
            public void onAnimationEnd(Animation animation) {

            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
          });
          view.startAnimation(animation);
        }
      }, position);
      mLastPosition = position;
    }
  }


  public interface OnItemClickListener {

    public void onItemClick(int position, ClickableViewHolder holder);
  }

  public interface OnItemLongClickListener {

    public boolean onItemLongClick(int position, ClickableViewHolder holder);
  }

  private OnItemClickListener itemClickListener;

  private OnItemLongClickListener itemLongClickListener;


  public void setOnItemClickListener(OnItemClickListener listener) {

    this.itemClickListener = listener;
  }


  public void setOnItemLongClickListener(OnItemLongClickListener listener) {

    this.itemLongClickListener = listener;
  }


  public void bindContext(Context context) {

    this.context = context;
  }


  public Context getContext() {

    return this.context;
  }


  @Override
  public void onBindViewHolder(final ClickableViewHolder holder, final int position) {

    holder.getParentView().setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        if (itemClickListener != null) {
          itemClickListener.onItemClick(position, holder);
        }
      }
    });
    holder.getParentView().setOnLongClickListener(new View.OnLongClickListener() {

      @Override
      public boolean onLongClick(View v) {

        if (itemLongClickListener != null) {
          return itemLongClickListener.onItemLongClick(position, holder);
        } else {
          return false;
        }
      }
    });
  }


  public class ClickableViewHolder extends RecyclerView.ViewHolder {

    private View parentView;


    public ClickableViewHolder(View itemView) {

      super(itemView);
      this.parentView = itemView;
    }


    public View getParentView() {

      return parentView;
    }


    public <T extends View> T $(@IdRes int id) {

      return (T) parentView.findViewById(id);
    }
  }
}
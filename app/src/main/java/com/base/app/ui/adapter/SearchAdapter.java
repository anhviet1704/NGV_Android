package com.base.app.ui.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.BaseValueItem;
import com.base.app.model.joblasted.JobNewItem;
import com.base.app.ui.callback.OnClickSearch;
import com.base.app.utils.NGVUtils;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter<T> extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> implements Filterable {

    private List<T> movieList;
    private List<T> mDataFilter;
    private Context mContext;
    private OnClickSearch mOnClick;

    public SearchAdapter(Context context, OnClickSearch mOnClick) {
        mContext = context;
        movieList = new ArrayList<>();
        mDataFilter = new ArrayList<>();
        this.mOnClick = mOnClick;
    }

    public void onUpdateData(final List<T> movieList) {
        if (this.movieList == null) {
            this.movieList = movieList;
            this.mDataFilter = movieList;
            notifyItemChanged(0, mDataFilter.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return SearchAdapter.this.movieList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    if (movieList.get(oldItemPosition) instanceof JobNewItem) {
                        JobNewItem oldItem = (JobNewItem) movieList.get(oldItemPosition);
                        JobNewItem newItem = (JobNewItem) movieList.get(newItemPosition);
                        return oldItem.getJobId() == newItem.getJobId();
                    } else if (movieList.get(oldItemPosition) instanceof BaseValueItem) {
                        BaseValueItem oldMovie = (BaseValueItem) movieList.get(oldItemPosition);
                        BaseValueItem newMovie = (BaseValueItem) movieList.get(newItemPosition);
                        return newMovie.getId() == oldMovie.getId();
                    }
                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if (movieList.get(oldItemPosition) instanceof JobNewItem) {
                        JobNewItem oldMovie = (JobNewItem) movieList.get(oldItemPosition);
                        JobNewItem newMovie = (JobNewItem) movieList.get(newItemPosition);
                        return newMovie.getJobName() == oldMovie.getJobName();
                    } else if (movieList.get(oldItemPosition) instanceof BaseValueItem) {
                        BaseValueItem oldMovie = (BaseValueItem) movieList.get(oldItemPosition);
                        BaseValueItem newMovie = (BaseValueItem) movieList.get(newItemPosition);
                        return newMovie.getValue() == oldMovie.getValue();
                    }
                    return false;
                }
            });
            this.movieList = movieList;
            this.mDataFilter = movieList;
            result.dispatchUpdatesTo(this);
        }
    }

    public void onUpdateOnlineData(List<T> list) {
        this.movieList = list;
        this.mDataFilter = list;
        notifyDataSetChanged();

    }

    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.MyViewHolder holder, final int position) {
        if (mDataFilter.get(position) instanceof JobNewItem) {
            JobNewItem item = (JobNewItem) mDataFilter.get(position);
            holder.mTvName.setText(item.getJobName());
            holder.mIvArrow.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClick.onClickItem(view, mDataFilter.get(position));
                }
            });
        } else if (mDataFilter.get(0) instanceof BaseValueItem) {
            BaseValueItem item = (BaseValueItem) mDataFilter.get(position);
            holder.mTvName.setText(item.getValue());
            holder.mIvArrow.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClick.onClickItem(view, mDataFilter.get(position));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        //if (movieList != null) {
        if (mDataFilter != null) {
            return mDataFilter.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mDataFilter = movieList;
                } else {
                    if (movieList.get(0) instanceof JobNewItem) {
                        List<T> filteredList = new ArrayList<>();
                        for (T movie : movieList) {
                            if (NGVUtils.covertStringToChar(((JobNewItem) movie).getJobName().toLowerCase()).contains(NGVUtils.covertStringToChar(charString.toLowerCase()))) {
                                filteredList.add(movie);
                            }
                        }
                        mDataFilter = filteredList;
                    } else if (movieList.get(0) instanceof BaseValueItem) {
                        List<T> filteredList = new ArrayList<>();
                        for (T movie : movieList) {
                            if (NGVUtils.covertStringToChar(((BaseValueItem) movie).getValue().toLowerCase()).contains(NGVUtils.covertStringToChar(charString.toLowerCase()))) {
                                filteredList.add(movie);
                            }
                        }
                        mDataFilter = filteredList;
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataFilter = (ArrayList<T>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MagicTextView mTvName;
        public View mViewRoot;
        private ImageView mIvArrow;

        public MyViewHolder(View view) {
            super(view);
            mTvName = view.findViewById(R.id.tv_name);
            mViewRoot = view.findViewById(R.id.view_root);
            mIvArrow = view.findViewById(R.id.imageView2);
        }
    }
}

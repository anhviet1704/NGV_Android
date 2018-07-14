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

import com.base.app.R;
import com.base.app.model.joblasted.JobNewDetailItem;
import com.base.app.ui.callback.OnClickSearch;
import com.base.app.utils.NGVUtils;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter<T> extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> implements Filterable {

    private List<T> movieList;
    private List<T> movieListFiltered;
    private Context mContext;
    private OnClickSearch mOnClick;

    public SearchAdapter(Context context, OnClickSearch mOnClick) {
        mContext = context;
        movieList = new ArrayList<>();
        movieListFiltered = new ArrayList<>();
        this.mOnClick = mOnClick;
    }

    public void onUpdateData(final List<T> movieList) {
        if (this.movieList == null) {
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            notifyItemChanged(0, movieListFiltered.size());
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
                    if (movieList.get(oldItemPosition) instanceof JobNewDetailItem) {
                        JobNewDetailItem oldItem = (JobNewDetailItem) movieList.get(oldItemPosition);
                        JobNewDetailItem newItem = (JobNewDetailItem) movieList.get(newItemPosition);
                        return oldItem.getJobId() == newItem.getJobId();
                    } else {
                    }
                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if (movieList.get(oldItemPosition) instanceof JobNewDetailItem) {
                        JobNewDetailItem oldMovie = (JobNewDetailItem) movieList.get(oldItemPosition);
                        JobNewDetailItem newMovie = (JobNewDetailItem) movieList.get(newItemPosition);
                        return newMovie.getJobName() == oldMovie.getJobName();
                    }
                    return false;
                }
            });
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.MyViewHolder holder, final int position) {
        if (movieListFiltered.get(position) instanceof JobNewDetailItem) {
            JobNewDetailItem item = (JobNewDetailItem) movieListFiltered.get(position);
            holder.mTvName.setText(item.getJobName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClick.onClickItem(view, item.getJobId());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (movieList != null) {
            return movieListFiltered.size();
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
                    movieListFiltered = movieList;
                } else {
                    if (movieList.get(0) instanceof JobNewDetailItem) {
                        List<T> filteredList = new ArrayList<>();
                        for (T movie : movieList) {
                            if (NGVUtils.covertStringToChar(((JobNewDetailItem) movie).getJobName().toLowerCase()).contains(NGVUtils.covertStringToChar(charString.toLowerCase()))) {
                                filteredList.add(movie);
                            }
                        }
                        movieListFiltered = filteredList;
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = movieListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieListFiltered = (ArrayList<T>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MagicTextView mTvName;
        public ConstraintLayout mViewRoot;

        public MyViewHolder(View view) {
            super(view);
            mTvName = view.findViewById(R.id.tv_name);
            mViewRoot = view.findViewById(R.id.view_root);
        }
    }
}

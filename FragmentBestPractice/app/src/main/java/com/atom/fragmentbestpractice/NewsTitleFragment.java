package com.atom.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private final String TAG = "==========";
    private boolean isTwoPane;

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView newsTitle;

            public ViewHolder(View view) {
                super(view);
                newsTitle = view.findViewById(R.id.news_title);
                Log.d(TAG, newsTitle instanceof TextView ? newsTitle.toString() : "----");
            }
        }

        public NewsAdapter(List<News> newsList) {
            this.mNewsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            Log.d(TAG, "onCreateViewHolder -> " + holder);
            ; // ViewHolder{70663c1 position=-1 id=-1, oldPos=-1, pLpos:-1 unbound no parent}
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        // 如果是双页模式，刷新 NewsContentFragment 中的内容
                        NewsContentFragment newsContentFragment = (NewsContentFragment)
                                getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        // 如果是单页模式，启动 NewsContentActivity
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });

            return holder;
        }

        /**
         * 屏幕外的内容被滚动的屏幕内，就会触发一次【貌似滚进4个才开始触发，然后怎么滚都触发】
         * @param holder 滚进来的对象
         * @param i 滚进来的索引
         */
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
            Log.d(TAG, "onBindViewHolder -> " + holder); // ViewHolder{70663c1 position=0 id=-1, oldPos=-1, pLpos:-1 no parent}
            News news = mNewsList.get(i);
            holder.newsTitle.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView newsTitleRecyclerView = view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(newsAdapter);
        return view;
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            News news = new News();
            news.setTitle("This is news title " + i);
            news.setContent(getRandomLenghtContent("This is news content " + i + ". "));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLenghtContent(String content) {
        Random random = new Random();
        int lenght = random.nextInt(20) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            stringBuilder.append(content);
        }
        return stringBuilder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            this.isTwoPane = true;  // 可以找到 news_content_layout 布局时，为双页模式
        } else {
            this.isTwoPane = false; // 找不到 news_content_layout 布局时，为单页模式
        }
    }
}

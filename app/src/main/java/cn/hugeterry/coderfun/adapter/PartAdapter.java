package cn.hugeterry.coderfun.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.beans.Results;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/19 02:57
 */
public class PartAdapter extends RecyclerView.Adapter<PartAdapter.PartViewHolder> {

    private Context context;
    private List<Results> list = new ArrayList<>();

    public List<Results> getResults() {
        return list;
    }

    public PartAdapter(Context context, List<Results> list) {
        this.context = context;
        if (list != null) {
            this.list = list;
        }
    }

    @Override
    public PartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PartViewHolder holder = new PartViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_part, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(PartViewHolder holder, int position) {
        String type = list.get(position).getType();
        switch (type) {
            case "休息视频":
                holder.iv_video.setVisibility(View.VISIBLE);
                holder.tv_time.setVisibility(View.GONE);
                holder.tv_author.setText("看看视频，休息一下吧......");
                holder.tv_author.setTextColor(Color.parseColor("#41b94d"));
                holder.textView.setText(list.get(position).getDesc());
                holder.tv_time.setText(list.get(position).getPublishedAt());
//                initWebview(holder.webView);
//                holder.webView.loadUrl(list.get(position).getUrl());
                break;
            case "福利":
                holder.draweeView.setVisibility(View.VISIBLE);
                holder.iv_video.setVisibility(View.GONE);
                holder.textView.setVisibility(View.GONE);
                holder.tv_time.setVisibility(View.GONE);
                holder.tv_author.setText("瞧瞧妹纸，扩展扩展视野......");
                holder.tv_author.setTextColor(Color.parseColor("#ffff4444"));
                Uri uri = Uri.parse(list.get(position).getUrl());
                holder.draweeView.setImageURI(uri);
                break;
            default:
                holder.draweeView.setVisibility(View.GONE);
                holder.iv_video.setVisibility(View.GONE);
                holder.textView.setVisibility(View.VISIBLE);
                holder.tv_time.setVisibility(View.VISIBLE);
                holder.tv_author.setText(list.get(position).getWho());
                holder.tv_author.setTextColor(Color.parseColor("#87000000"));
                holder.tv_time.setText(list.get(position).getPublishedAt());
                holder.textView.setText(list.get(position).getDesc());
                break;
        }

        holder.tv_time.setText(list.get(position).getPublishedAt());
        holder.tv_type.setText(type);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void initWebview(WebView webView) {
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
    }

    class PartViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView draweeView;
        ImageView iv_video;
        TextView textView;
        TextView tv_author, tv_time, tv_type;

        public PartViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.part_iv);
            iv_video=(ImageView)itemView.findViewById(R.id.part_video_iv);
            textView = (TextView) itemView.findViewById(R.id.part_tv);
            tv_author = (TextView) itemView.findViewById(R.id.part_tv_author);
            tv_time = (TextView) itemView.findViewById(R.id.part_tv_time);
            tv_type = (TextView) itemView.findViewById(R.id.part_tv_type);
        }
    }
}
package com.atom.uibestpractice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;

        public ViewHolder(View view) {
            super(view);
            leftLayout = view.findViewById(R.id.left_layout);
            rightLayout = view.findViewById(R.id.right_layout);
            leftMsg = view.findViewById(R.id.left_msg);
            rightMsg = view.findViewById(R.id.right_msg);
        }
    }

    public MsgAdapter(List<Msg> msgList) {
        this.mMsgList = msgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.msg_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Msg msg = this.mMsgList.get(i);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            // 收到消息
            viewHolder.leftLayout.setVisibility(View.VISIBLE);  // 显示左边
            viewHolder.rightLayout.setVisibility(View.GONE);    // 隐藏右边
            viewHolder.leftMsg.setText(msg.getContent());
        } else if(msg.getType() == Msg.TYPE_SEND) {
            // 发送消息
            viewHolder.leftLayout.setVisibility(View.GONE);     // 隐藏左边
            viewHolder.rightLayout.setVisibility(View.VISIBLE); // 显示右边
            viewHolder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return this.mMsgList.size();
    }
}

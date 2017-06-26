package com.viperproject.messagelist;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.viperproject.main.domain.MessageViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */
@Accessors(prefix = "m")
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    @Setter MessageView.OnMessageClickListener mOnMessageClickListener;

    List<MessageViewModel> mList;

    public MessageAdapter() {
        mList = new ArrayList<>();
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageView itemView = new MessageView(parent.getContext());
        itemView.setOnMessageClickListener(mOnMessageClickListener);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.renderView(getItem(position));
    }

    private MessageViewModel getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addItems(Collection<MessageViewModel> messages) {
        mList.addAll(messages);
        notifyDataSetChanged();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        public MessageViewHolder(MessageView itemView) {
            super(itemView);
        }

        public void renderView(MessageViewModel item) {
            ((MessageView)itemView).renderView(item);
        }
    }
}

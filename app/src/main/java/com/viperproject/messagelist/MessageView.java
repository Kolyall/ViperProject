package com.viperproject.messagelist;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.viperproject.R;
import com.viperproject.common.Layout;
import com.viperproject.common.views.BaseLinearLayout;
import com.viperproject.main.domain.MessageViewModel;

import butterknife.BindView;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */
@Layout(id = R.layout.item_view_message)
@Accessors(prefix = "m")
public class MessageView extends BaseLinearLayout implements View.OnClickListener {

    @BindView(R.id.message_text_view) TextView mMessageTextView;
    private MessageViewModel mItem;

    public MessageView(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public void renderView(MessageViewModel item) {
        mItem = item;
        mMessageTextView.setText(item.getId()+" "+item.getMessage());
    }

    @Setter OnMessageClickListener mOnMessageClickListener;

    @Override
    public void onClick(View v) {
        if (mOnMessageClickListener != null) {
            mOnMessageClickListener.onMessageClicked(mItem);
        }
    }

    public  interface OnMessageClickListener{

        void onMessageClicked(MessageViewModel item);
    }
}

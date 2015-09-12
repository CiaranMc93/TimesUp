package ciaran.timesup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ciaran on 12/09/2015.
 */
public class TimesUpAdapter extends ArrayAdapter<ChatMessage>{

    private TextView chatText;
    private List<ChatMessage> MessageList = new ArrayList<ChatMessage>();
    private LinearLayout layout;

    public TimesUpAdapter(Context context, int resource) {
        super(context, resource);
    }

    public TimesUpAdapter(Context context, int resource, ChatMessage[] objects) {
        super(context, resource, objects);
    }

    public void add(ChatMessage chatMessage)
    {
        MessageList.add(chatMessage);
        super.add(chatMessage);
    }

    public int getCount()
    {
        return this.MessageList.size();
    }

    public ChatMessage getItem(int index){
        return this.MessageList.get(index);

    }

    public View getView(int position, View ConvertView, ViewGroup parent)
    {
        View v = ConvertView;

        if(v == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.chat,parent,false);
        }

        layout = (LinearLayout)v.findViewById(R.id.message1);
        ChatMessage messageObj = getItem(position);
        chatText = (TextView)v.findViewById(R.id.SingleMessage);

        chatText.setText(messageObj.message);

        chatText.setBackgroundResource(messageObj.left ? R.drawable.patch1 :R.drawable.patch1);
        layout.setGravity(messageObj.left? Gravity.LEFT:Gravity.RIGHT);

        return v;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte)
    {
        //return bitmap
        return BitmapFactory.decodeByteArray(decodedByte,0,decodedByte.length);
    }
}

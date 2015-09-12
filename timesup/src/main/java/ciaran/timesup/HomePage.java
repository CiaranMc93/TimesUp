package ciaran.timesup;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class HomePage extends ActionBarActivity {

    private TimesUpAdapter adp;
    private ListView list;
    private EditText chatText;
    private Button send;

    Intent in;

    private boolean side = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent i = getIntent();

        send = (Button)findViewById(R.id.btn);

        list = (ListView)findViewById(R.id.listview);

        adp = new TimesUpAdapter(getApplicationContext(), R.layout.chat);

        chatText = (EditText)findViewById(R.id.chat);

        chatText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                //get the action from the press
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    //if send, then send chat message
                    return sendChatMessage();
                }

                return false;
            }


        });

        //send the message
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendChatMessage();
            }
        });

        list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        list.setAdapter(adp);


        adp.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged()
            {
                super.onChanged();

                list.setSelection(adp.getCount() -1);
            }
        });
    }

    private boolean sendChatMessage() {

        adp.add(new ChatMessage(side, chatText.getText().toString()));
        chatText.setText("");

        side = !side;

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

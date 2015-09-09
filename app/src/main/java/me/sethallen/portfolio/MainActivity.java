package me.sethallen.portfolio;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView portfolioListView;
    private ArrayAdapter<String> listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ListView object from xml
        portfolioListView = (ListView)findViewById(R.id.portfolioListView);

        // Array of button titles from the strings resources for the ListView items
        String[] portfolioItems = new String[] {
                getString( R.string.button_text_spotify_streamer ),
                getString( R.string.button_text_scores_app ),
                getString( R.string.button_text_library_app ),
                getString( R.string.button_text_build_it_bigger ),
                getString( R.string.button_text_xyz_reader ),
                "Capstone: My Own App"
        };

        ArrayList<String> portfolioList = new ArrayList<String>();
        portfolioList.addAll(Arrays.asList(portfolioItems));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.portfolio_list_item,
                R.id.appName,
                portfolioList );

        // Assign adapter to ListView
        portfolioListView.setAdapter(adapter);

        portfolioListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adpterView, View view, int position, long id) {

                Context context = getApplicationContext();

                triggerToast( view );

                for (int i = 0; i < portfolioListView.getChildCount(); i++) {
                    if (position == i) {
                        portfolioListView.getChildAt(i).setBackground(
                                context.getResources().getDrawable( R.drawable.gradient_bg_selected,
                                                                    context.getTheme() ) );
                    } else {
                        portfolioListView.getChildAt(i).setBackground(
                                context.getResources().getDrawable( R.drawable.gradient_bg,
                                                                    context.getTheme() ) );
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /** Called when the user touches the button */
    public void triggerToast(View view) {
        Context context = getApplicationContext();
        TextView textView = (TextView)view.findViewById(R.id.appName);
        String text = "This will launch my " + textView.getText().toString() + " app!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

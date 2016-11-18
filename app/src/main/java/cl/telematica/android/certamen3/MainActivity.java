package cl.telematica.android.certamen3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cl.telematica.android.certamen3.fragment.ListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentFrame(R.id.content_frame);
        switchContent(ListFragment.newInstance(), null);

        // MyAsyncTaskExecutor.getInstance().executeMyAsynctask(this, mRecyclerView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            /**
             * You should manage the action to show the favorite items saved by the user
             */

            setContentFrame(R.id.content_frame);
            switchContent(ListFragment.newInstance(), null);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

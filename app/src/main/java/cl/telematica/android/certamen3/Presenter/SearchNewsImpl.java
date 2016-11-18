package cl.telematica.android.certamen3.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen3.Presenter.contract.SearchNews;
import cl.telematica.android.certamen3.connection.HttpServerConnection;
import cl.telematica.android.certamen3.model.Feed;
import cl.telematica.android.certamen3.views.ListView;

/**
 * Created by nicolas on 18-11-2016.
 */
public class SearchNewsImpl implements SearchNews {


    private RecyclerView.Adapter mAdapter;
    private ListView mListNewsView;
    public Context context;
  //  private static MyAsyncTaskExecutor instance;

    public SearchNewsImpl(ListView mListNewsView, Context context) {
        this.mListNewsView = mListNewsView;
        this.context = context;

    }
/*
    public static MyAsyncTaskExecutor getInstance() {
        if(instance == null) {
            instance = new MyAsyncTaskExecutor();
        }
        return instance;
    }
*/
    AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected String doInBackground(Void... params) {
            String resultado = new HttpServerConnection().connectToServer("http://www.mocky.io/v2/582eea8b2600007b0c65f068", 15000);
            return resultado;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result != null){
                System.out.println(result);

                //Why god... why
                mAdapter = new DataAdapter(context, getFeeds(result));
                mListNewsView.manageRecyclerView(mAdapter);
                //mRecyclerView.setAdapter(mAdapter);
            }
        }
    };

    public AsyncTask<Void, Void, String> getTask() {
        return task;
    }

    public List<Feed> getFeeds(String result) {
        List<Feed> feeds = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject responseData = jsonObject.getJSONObject("responseData");
            JSONObject feedObj = responseData.getJSONObject("feed");

            JSONArray entries = feedObj.getJSONArray("entries");
            int size = entries.length();
            for(int i = 0; i < size; i++){
                JSONObject entryObj = entries.getJSONObject(i);
                Feed feed = new Feed();

                feed.setTitle(entryObj.optString("title"));
                feed.setLink(entryObj.optString("link"));
                feed.setAuthor(entryObj.optString("author"));
                feed.setPublishedDate(entryObj.optString("publishedDate"));
                feed.setContent(entryObj.optString("content"));
                feed.setImage(entryObj.optString("image"));

                feeds.add(feed);
            }

            return feeds;
        } catch (JSONException e) {
            e.printStackTrace();
            return feeds;
        }
    }


}

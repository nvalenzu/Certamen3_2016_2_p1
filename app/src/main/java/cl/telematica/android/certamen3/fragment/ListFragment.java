package cl.telematica.android.certamen3.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.telematica.android.certamen3.Presenter.SearchNewsImpl;
import cl.telematica.android.certamen3.R;
import cl.telematica.android.certamen3.views.ListView;

/**
 * Created by nicolas on 17-11-2016.
 */
public class ListFragment extends Fragment implements ListView {

    private SearchNewsImpl mListNewsPresenter;

    public RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.fragment_listnews, null);

        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mListNewsPresenter = new SearchNewsImpl(this, ListFragment.this.getActivity());
        mListNewsPresenter.getTask().execute();

        return mainView;
    }

    public void manageRecyclerView(RecyclerView.Adapter mAdapter){
        mRecyclerView.setAdapter(mAdapter);
    }

}

package com.example.enspire.bottomnavigationview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.enspire.bottomnavigationview.dummy.DummyContent;
import com.example.enspire.bottomnavigationview.dummy.DummyContent.DummyItem;
import com.example.enspire.bottomnavigationview.model.Organization;
import com.example.enspire.bottomnavigationview.service.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class OrgFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OrgFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static OrgFragment newInstance(int columnCount) {
        OrgFragment fragment = new OrgFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

   /* public void getOrganization(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Calling API
        APIService service=retrofit.create(APIService.class);
        Call<List<Organization>> call=service.getOrganization(uid);

        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                List<Organization> organizations = response.body();

                String details = "";
                int temp = organizations.size();
                for (int i = 0; i < organizations.size(); i++) {
                    String name = organizations.get(i).getName();

                    details += "Name: " + name + '\n';
                }
                test.setText(details);
                String finalLatitude = locations.get(temp - 1).getLatitude();
                String finalLongitude = locations.get(temp - 1).getLongitude();
                setText.setText(finalLatitude);
                setText2.setText(finalLongitude);

            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {


            }
        });


    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_org_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyOrgRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            Toast.makeText(context,"Notification Fragment",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}

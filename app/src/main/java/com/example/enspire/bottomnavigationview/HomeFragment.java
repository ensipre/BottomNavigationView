package com.example.enspire.bottomnavigationview;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.enspire.bottomnavigationview.model.OrgModel;
import com.example.enspire.bottomnavigationview.model.Organization;
import com.example.enspire.bottomnavigationview.service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //instantiate recycler view
    RecyclerView recyclerView;
    OrgCardAdapter adapter;
    ArrayList<OrgModel> orgList;
    TextView test;
    Button button2;
    ArrayList<String> al=new ArrayList(3);
    ArrayList<String> al2=new ArrayList(3);
    //variables for toolbar
    Toolbar toolbar;

    String uid="22";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        Log.d("onCreate","yes/no");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        Log.d("onCreateview","yes/no");
        toolbar=(Toolbar)rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_items);
        Menu menu=toolbar.getMenu();

        //Build Api interface
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Calling API
        APIService service=retrofit.create(APIService.class);
        Call<List<Organization>> call=service.getOrganization(uid);

        //Api callback
        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                List<Organization> organizations=response.body();
                Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();

                String name="ool";
                String uid;
                Log.d("Size1",al.toString());

                for(int i=0;i<organizations.size();i++)
                {
                    name= organizations.get(i).getName();
                    uid=organizations.get(i).getUid();
                          al.add(name);
                          al2.add(uid);
                          Log.d("name",name);
                    Log.d("uid",uid);
                }

                Log.d("IAl",al.toString());
                Log.d("IAl2",al.get(1).toString());
                Log.d("Size2",al.toString());

                orgList=new ArrayList<>();

                recyclerView= (RecyclerView) rootView.findViewById(R.id.orglist);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                for(int j=0;j<2;j++)
                {

                    String temp=al.get(j).toString();
                    String temp2=al2.get(j).toString();

                    Log.d("Aname",temp);
                    Log.d("Auid",temp2);

                    orgList.add(
                            new OrgModel(al.get(j).toString(),al2.get(j).toString())
                    );
                }
                setHasOptionsMenu(true);
                adapter=new OrgCardAdapter(getContext(),orgList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {
                Toast.makeText(getContext(),"failure",Toast.LENGTH_LONG).show();
                Log.d(t.toString(), "onFailure: ");
            }
        });

        setHasOptionsMenu(true);
        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        setHasOptionsMenu(true);
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

        } else {
            Toast.makeText(context,"Home Fragment",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDetach() {
        setHasOptionsMenu(true);
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d("optoinsmenu","why no menu?");
        inflater.inflate(R.menu.menu_items,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
        Log.d("optoinsmenu","why no menu?");
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        ArrayList<OrgModel> newList=new ArrayList<>();
        for (OrgModel orgmodel: orgList)
        {
            String name=orgmodel.getTitle().toLowerCase();
            if(name.contains(newText))
                newList.add(orgmodel);
        }
        adapter.setFilter(newList);
        return true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}

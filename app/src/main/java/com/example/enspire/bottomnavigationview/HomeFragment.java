package com.example.enspire.bottomnavigationview;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enspire.bottomnavigationview.model.OrgModel;
import com.example.enspire.bottomnavigationview.model.Organization;
import com.example.enspire.bottomnavigationview.service.APIService;

import java.lang.reflect.Array;
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
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //instantiate recycler view
    RecyclerView recyclerView;
    OrgCardAdapter adapter;
    List<OrgModel> orgList;
    TextView test;
    Button button2;
    ArrayList<String> al=new ArrayList(3);
    ArrayList<String> al2=new ArrayList(3);

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
        //Build Api
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//Calling API
        APIService service=retrofit.create(APIService.class);
        Call<List<Organization>> call=service.getOrganization(uid);
        test=(TextView)rootView.findViewById(R.id.testb);
        /*test.setText("dsa");
        button2=(Button)rootView.findViewById(R.id.button2);*/

       /* button2.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
               *//*uid="22";
               Toast.makeText(context,"Notification Fragment",Toast.LENGTH_LONG).show();
               test.setText("What the fuck");*//*
                                               getOrganization();

                                           }
        });*/
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

                test.setText(name);
                orgList=new ArrayList<>();

                recyclerView= (RecyclerView) rootView.findViewById(R.id.orglist);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                for(int j=0;j<1;j++)
                {

                    String temp=al.get(j).toString();
                    String temp2=al2.get(j).toString();
                    Log.d("Aname",temp);
                    Log.d("Auid",temp2);
                    orgList.add(
                            new OrgModel("sdf","sdf")
                    );
                    orgList.add(
                            new OrgModel(al.get(j).toString(),al.get(j).toString())
                    );
                }

                adapter=new OrgCardAdapter(getContext(),orgList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {
                Toast.makeText(getContext(),"failure",Toast.LENGTH_LONG).show();
                Log.d(t.toString(), "onFailure: ");
            }
        });


       /* orgList=new ArrayList<>();

        recyclerView= (RecyclerView) rootView.findViewById(R.id.orglist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.d("Size4",al.toString());*/
/*String [] alArray=al.toArray(new String[al.size()]);
String [] al2Array=al2.toArray(new String[al.size()]);*/


/*Log.d("Full al",al.toString());
        Log.d("Full al2",al2.toString());*/
        /*getOrganization();*/
       /* Log.d("Size",al.toString());*/
      /*  for(int j=0;j<1;j++)
        {

            String temp=al.get(j).toString();
            String temp2=al2.get(j).toString();
            Log.d("Aname",temp);
            Log.d("Auid",temp2);
            orgList.add(
                    new OrgModel("sdf","sdf")
            );
            orgList.add(
                    new OrgModel(al.get(j).toString(),al.get(j).toString())
            );
        }*/
  /*  adapter=new OrgCardAdapter(getContext(),orgList);
    recyclerView.setAdapter(adapter);*/

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
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

        } else {
            Toast.makeText(context,"Home Fragment",Toast.LENGTH_LONG).show();
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

/*    public void getOrganization(){


     *//*  final  ArrayList al=new ArrayList();
       final ArrayList al2=new ArrayList();*//*

*//*test.setText("asd");*//*
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Calling API
        APIService service=retrofit.create(APIService.class);
        Call<List<Organization>> call=service.getOrganization(uid);
//API call back

       *//* test.setText("asdasdasd");

        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                List<Organization> organizations = response.body();
                test.setText("asdasdasd");

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
                Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();

            }
        }*//*
                call.enqueue(new Callback<List<Organization>>() {
                  @Override
                   public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                      List<Organization> organizations=response.body();
                      Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();

                      for(int i=0;i<organizations.size();i++)
                      {
                          String name= organizations.get(i).getName();
                          String uid=organizations.get(i).getUid();
                          *//*al.add(name);
                          al2.add(uid);*//*
                          orgList.add(
                                  new OrgModel("Ashmita","Subedi")
                          );
                          orgList.add(
                                  new OrgModel("Ronit","pradhan")
                          );
                          orgList.add(
                                  new OrgModel("Abhusan","gautam")
                          );
                          orgList.add(new OrgModel(name,uid));
                      }

                  }

                    @Override
                    public void onFailure(Call<List<Organization>> call, Throwable t) {
                        Toast.makeText(getContext(),"failure",Toast.LENGTH_LONG).show();
                        Log.d(t.toString(), "onFailure: ");
                   }
                });



    }*/
}

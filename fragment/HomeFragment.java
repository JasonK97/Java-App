package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.BathroomList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
//    TextView title;
//    Typeface font;
    Button bl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.home, container, false);
        Button btn = (Button) viewRoot.findViewById(R.id.stc);
        Button btn1 = (Button) viewRoot.findViewById(R.id.austin);
        Button btn2 = (Button) viewRoot.findViewById(R.id.hart);
        Button btn3 = (Button) viewRoot.findViewById(R.id.benson);
        Button btn4 = (Button) viewRoot.findViewById(R.id.mc);
        Button btn5 = (Button) viewRoot.findViewById(R.id.library);
        Button btn6 = (Button) viewRoot.findViewById(R.id.smith);
        Button btn7 = (Button) viewRoot.findViewById(R.id.rick);
        Button btn8 = (Button) viewRoot.findViewById(R.id.rigby);
        Button btn9 = (Button) viewRoot.findViewById(R.id.clark);
        Button btn10 = (Button) viewRoot.findViewById(R.id.chapman);
        Button btn11 = (Button) viewRoot.findViewById(R.id.garden);
        Button btn12 = (Button) viewRoot.findViewById(R.id.spori);
        Button btn13 = (Button) viewRoot.findViewById(R.id.kirkham);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "STC");
                startActivity(in);

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Austin");

                startActivity(in);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Hart");

                startActivity(in);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Benson");

                startActivity(in);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "MC");

                startActivity(in);

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Library");

                startActivity(in);

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Smith");

                startActivity(in);

            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Ricks");
                startActivity(in);

            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Rigby");
                startActivity(in);

            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Clarke");
                startActivity(in);

            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Chapman");
                startActivity(in);

            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Ricks Gardens");
                startActivity(in);

            }
        });

  btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Spori");
                startActivity(in);

            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BathroomList.class);
                in.putExtra("Building", "Kirkham");
                startActivity(in);

            }
        });
//        title = viewRoot.findViewById(R.id.textGrid);
//        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/PermanentMarker-Regular.ttf");
//        title.setTypeface(font);
//        bl = viewRoot.findViewById(R.id.stc);
//        bl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                stc Stc = new stc();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.main,Stc);
//                transaction.commit();
//
//
//            }
//        });
        return viewRoot;
 }
}


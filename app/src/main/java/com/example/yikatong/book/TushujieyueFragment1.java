package com.example.yikatong.book;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yikatong.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TushujieyueFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TushujieyueFragment1 extends Fragment {
    private List<Book1> list = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TushujieyueFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TushujieyueFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static TushujieyueFragment1 newInstance(String param1, String param2) {
        TushujieyueFragment1 fragment = new TushujieyueFragment1();
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
        View view = inflater.inflate(R.layout.fragment_tushujieyue1, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_book1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        if (layoutManager != null) {
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }
        init();
        Book1Adapter adapter = new Book1Adapter(list);
        recyclerView.setAdapter(adapter);

        return view;
    }
    private void init(){
        list.add(new Book1("Spring Boot技术实战","张子宪","2024-10-08","2024-12-07","30"));
        list.add(new Book1("数据库原理与应用","张家爱","2024-10-08","2024-12-07","30"));
    }
}
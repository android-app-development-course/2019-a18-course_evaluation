package com.scottmangiapane.courseevaluation.ui.all_courses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.R;

public class AllCoursesFragment extends Fragment {

    private AllCoursesViewModel allCoursesViewModel;

    private AppCompatButton courseDetailBtn;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //allCoursesViewModel = ViewModelProviders.of(this).get(AllCoursesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_allcourses, container, false);

        courseDetailBtn=root.findViewById(R.id.courseBtn1);
        courseDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CourseDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });
//        final TextView textView = root.findViewById(R.id.text_all_courses);
        // allCoursesViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}
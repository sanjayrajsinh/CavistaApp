package com.cavista.cavistaapp.appview.imagelist.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cavista.cavistaapp.R;
import com.cavista.cavistaapp.appview.imagelist.adapter.ImageAdapter;
import com.cavista.cavistaapp.appview.imagelist.model.ImageRes;
import com.cavista.cavistaapp.appview.imagelist.viewmodel.ImageListViewModel;
import com.cavista.cavistaapp.databinding.FragmentImageListBinding;
import com.cavista.cavistaapp.utils.DialogUtils;
import com.cavista.cavistaapp.webservice.ApiCallback;
import com.cavista.cavistaapp.webservice.ApiErrorModel;
import com.cavista.cavistaapp.webservice.ApiManager;
import com.cavista.cavistaapp.utils.ProgressUtils;

import org.jetbrains.annotations.NotNull;

public class ImageListFragment extends Fragment {
    private ImageListViewModel viewModel;
    ImageAdapter adapter;
    RecyclerView recyclerView;
    AppCompatEditText editText;
    AppCompatImageView btnSearch;
    AppCompatTextView emptyField;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rv_images);
        editText=view.findViewById(R.id.et_searchview);
        btnSearch=view.findViewById(R.id.btn_search);
        emptyField=view.findViewById(R.id.txt_empty);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadData();
            }
        });
        setRecyclerView(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel= new ViewModelProvider(this).get(ImageListViewModel.class);
        FragmentImageListBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_image_list, container, false);
        binding.setImgVariable(viewModel);
        return binding.getRoot();
    }
    private void setRecyclerView(View view) {
        adapter = new ImageAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void downloadData(){
        ProgressUtils.INSTANCE.showOldProgressDialog(getContext());
        String searchText=editText.getText()!=null && editText.getText().toString()!=null?editText.getText().toString():"";
        ApiManager.INSTANCE.findImageApiCall(searchText, new ApiCallback<ImageRes>() {
            @Override
            public void onSuccess(ImageRes response) {
                ProgressUtils.INSTANCE.closeOldProgressDialog();
                Log.d("d","==ddddd");
                if (response != null && response.getData() != null && !response.getData().isEmpty()) {
                    adapter.setData(response.getData());
                    emptyField.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    emptyField.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(@NotNull ApiErrorModel apiErrorModel) {
                Log.d("d","==ddddd"+apiErrorModel.getStatus());
                ProgressUtils.INSTANCE.closeOldProgressDialog();
                DialogUtils.INSTANCE.toast(getContext(),apiErrorModel.getMessage());
                recyclerView.setVisibility(View.GONE);
                emptyField.setVisibility(View.VISIBLE);
            }
        });
    }
}

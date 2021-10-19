package com.peluffo.inmobiliariapeluffo.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.peluffo.inmobiliariapeluffo.databinding.FragmentInquilinoBinding;
import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;

import java.util.List;

public class InquilinoFragment extends Fragment {
    private RecyclerView rvInquilino;
    private InquilinoAdapter inquilinoAdapter;
    private InquilinoViewModel inquilinoViewModel;
    private FragmentInquilinoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inquilinoViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvInquilino = binding.rvInquilino;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        rvInquilino.setLayoutManager(gridLayoutManager);
        inquilinoViewModel.getLista().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                inquilinoAdapter = new InquilinoAdapter(inmuebles, root.getContext(), inflater);
                rvInquilino.setAdapter(inquilinoAdapter);
            }
        });
        inquilinoViewModel.cargarInmueblesAlquilados();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

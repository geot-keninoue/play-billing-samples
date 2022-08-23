/*
 * Copyright (C) 2021 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keninoue.trivialdrivesample.ui;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.keninoue.trivialdrivesample.GameViewModel;
import com.keninoue.trivialdrivesample.R;
import com.keninoue.trivialdrivesample.TrivialDriveApplication;
import com.keninoue.trivialdrivesample.databinding.FragmentGameBinding;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * This Fragment represents the game world, but it really just exists to bind the variables used
 * in the views that use DataBinding to observe the ViewModel.
 * <p>
 * There's nothing about billing here; billing informationis abstracted into the BillingRepository.
 */
public class GameFragment extends androidx.fragment.app.Fragment {
    final private String TAG = GameFragment.class.getSimpleName();

    private GameViewModel gameViewModel;
    private FragmentGameBinding binding;

    /*
        We use data binding to bind the game view with this fragment, and this allows us to
        automatically observe changes in our TrivialDriveViewModel from our layout. The ViewModel
        handles most of the UI and game-related business logic.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false);
        // This allows data binding to automatically observe any LiveData we pass in
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v(TAG, "onViewCreated");

        GameViewModel.GameViewModelFactory gameViewModelFactory =
                new GameViewModel.GameViewModelFactory(
                        ((TrivialDriveApplication) getActivity().getApplication()).appContainer
                                .trivialDriveRepository);

        gameViewModel = new ViewModelProvider(this, gameViewModelFactory)
                .get(GameViewModel.class);

        TypedArray gasTankResourceIds = getResources().obtainTypedArray(R.array.gas_tank_images);

        // Set the variables up that we'll be using in data binding
        binding.setGasTankImages(gasTankResourceIds);
        binding.setGvm(gameViewModel);
        binding.setGameFragment(this);
    }

    public void drive() {
        Log.d(TAG, "Drive");
        gameViewModel.drive();
    }

    public void purchase(View view) {
        Navigation.findNavController(view).navigate(R.id.action_gameFragment_to_makePurchaseFragment);
    }

    //追加分
    public void showPrivacyPolicy() {
        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://keinoue8.wixsite.com/privacypolicy"));

        startActivity(intent);
    }

    public void postTest() {
        HashMap<String, String> sampleData = new HashMap();
        sampleData.put("foo", "bar");

//        this.postData("http://10.0.2.2:3000/check/post", sampleData);
        this.postData("http://192.168.0.118:3000/check/post", sampleData);
    }

    public void postData(String url, HashMap data) {
        RequestQueue requstQueue = Volley.newRequestQueue(this.getContext());

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(data),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("LOG_VOLLEY", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("LOG_VOLLEY", error.toString());                    }
                }
        ){
            //here I want to post data to sever
        };
        requstQueue.add(jsonobj);

    }
    //追加分

}

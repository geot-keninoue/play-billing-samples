/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.classytaxijava.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.classytaxijava.SubApp;
import com.example.android.classytaxijava.data.ContentResource;
import com.example.android.classytaxijava.data.DataRepository;
import com.example.android.classytaxijava.data.SubscriptionStatus;

import java.util.List;

public class SubscriptionStatusViewModel extends AndroidViewModel {

    private static final String TAG = "SubViewModel";

    /**
     * Data repository.
     */
    private DataRepository repository;

    /**
     * True when there are pending network requests
     */
    public LiveData<Boolean> loading;

    /**
     * Subscriptions LiveData
     */
    public LiveData<List<SubscriptionStatus>> subscriptions;

    public LiveData<ContentResource> basicContent;

    public LiveData<ContentResource> premiumContent;

    /**
     * Keep track of the last Instance ID to be registered, so that it
     * can be unregistered when the user signs out.
     */
    private String instanceIdToken = null;

    public SubscriptionStatusViewModel(Application application) {
        super(application);
        repository = ((SubApp) application).getRepository();
        loading = repository.getLoading();
        subscriptions = repository.getSubscriptions();
        basicContent = repository.getBasicContent();
        premiumContent = repository.getPremiumContent();
    }

    public void unregisterInstanceId() {
        // TODO
    }

    public void userChanged() {
        // TODO
    }

    public void manualRefresh() {
        // TODO
    }

    private void registerInstanceId(String token) {
        // TODO
    }

    /**
     * Register a new subscription.
     */
    public void registerSubscription(String sku, String purchaseToken) {
        // TODO
    }

    /**
     * Transfer the subscription to this account.
     */
    public void transferSubscriptions() {
        // TODO
    }
}

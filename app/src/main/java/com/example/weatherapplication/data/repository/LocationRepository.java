package com.example.weatherapplication.data.repository;

import android.annotation.SuppressLint;
import android.content.Context;

import android.location.Location;

import androidx.annotation.NonNull;

import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Granularity;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import kotlinx.coroutines.flow.Flow;

public class LocationRepository {

    private Context context;
   private final  FusedLocationProviderClient fusedLocationClient ;
    @Inject
    public LocationRepository(FusedLocationProviderClient fusedLocationClient){
        //this.context = context;
        //this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

        // dependency inject of fusedLocationClient
        this.fusedLocationClient = fusedLocationClient;
    }

    @SuppressLint("MissingPermission")
    public void getLastLocation(){
        //get last known location in callback
        fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    Location location = task.getResult();
                     System.out.println(location);
                }else{
                    task.getException().printStackTrace();
                }
            }
        });
    }



    @SuppressLint("MissingPermission")
    public void getLatestLocation(){
        //get latest known location in callback
        CurrentLocationRequest currentLocationRequest = new CurrentLocationRequest.Builder()
                .setDurationMillis(5000)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setGranularity(Granularity.GRANULARITY_FINE)
                .setMaxUpdateAgeMillis(0)
                .build();
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();

        fusedLocationClient.getCurrentLocation(currentLocationRequest, cancellationTokenSource.getToken()).addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    Location location = task.getResult();
                    System.out.println(location);
                }else{
                    task.getException().printStackTrace();
                }
            }
        });
    }


    @SuppressLint("MissingPermission")
    public Observable<Location> getLatestLocationObservable(){
        //CurrentLocationRequest for getCurrentLocation call
        CurrentLocationRequest currentLocationRequest = new CurrentLocationRequest.Builder()
                .setDurationMillis(5000)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setGranularity(Granularity.GRANULARITY_FINE)
                .setMaxUpdateAgeMillis(0)
                .build();
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        return Observable.create(new ObservableOnSubscribe<Location>() {
        @Override
        public void subscribe(@io.reactivex.rxjava3.annotations.NonNull ObservableEmitter<Location> emitter) throws Throwable {
            fusedLocationClient.getCurrentLocation(currentLocationRequest, cancellationTokenSource.getToken()).addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if(task.isSuccessful()){
                        Location location = task.getResult();
                        //send latest location to view model using rxjava

                        if(location != null){
                            emitter.onNext(location);
                            emitter.onComplete();

                        }else {
                            // Handle the error case
                            //emitter.onError(throwable);
                        }
                        //System.out.println(location);
                    }else{
                        //Exceptions
                        task.getException().printStackTrace();
                    }
                }


            });
        }
    });
    }

}

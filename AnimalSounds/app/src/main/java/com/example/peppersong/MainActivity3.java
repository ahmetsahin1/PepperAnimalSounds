package com.example.peppersong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;

public class MainActivity3 extends RobotActivity implements RobotLifecycleCallbacks {

    MediaPlayer mediaPlayer;
    com.example.peppersong.databinding.ActivityMain3Binding design;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        design= com.example.peppersong.databinding.ActivityMain3Binding.inflate(getLayoutInflater());
        // Register the RobotLifecycleCallbacks to this Activity
        setContentView(design.getRoot());
        QiSDK.register(this, this);
        mediaPlayer=MediaPlayer.create(MainActivity3.this,R.raw.fil);


    }

    @Override
    protected void onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        // The robot focus is gained.
        mediaPlayer.start();
        Animation myAnimation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.elephant_a001)
                .build();

// Build the action.
        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation)
                .build();

// Run the action asynchronously.
       Future<Void> future = animate.async().run();
       future.andThenConsume(birinci->{
           Intent intent=new Intent(MainActivity3.this,MainActivity.class);
           startActivity(intent);
       });

    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        // The robot focus is refused.
    }

}
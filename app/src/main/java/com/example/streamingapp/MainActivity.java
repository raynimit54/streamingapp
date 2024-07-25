package com.example.streamingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
public class MainActivity extends AppCompatActivity {

    private ExoPlayer player;
    private EditText Url;
    private PlayerView playerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        Url = findViewById(R.id.url_link);
        playerView = findViewById(R.id.player_view);
        Button btnStartStream = findViewById(R.id.btn_start);

        btnStartStream.setOnClickListener(v -> {
            String rtspUrl = Url.getText().toString();
            if (!rtspUrl.isEmpty()) {
                startStreaming(rtspUrl);
            }
        });
    }

    private void startStreaming(String rtspUrl) {
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        MediaItem mediaItem = new MediaItem.Builder()
                .setUri(rtspUrl)
                .build();
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }
    @Override
    protected void onStop() {
        super.onStop();
        player.setPlayWhenReady(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            player = null;
        }
    }
}

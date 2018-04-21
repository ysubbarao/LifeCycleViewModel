package com.shvartsy.archcomponents.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.shvartsy.archcomponents.R;
import com.shvartsy.archcomponents.viewmodel.ClickCounterViewModel;
import com.shvartsy.archcomponents.viewmodel.ClickLoggingInterceptor;
import com.shvartsy.archcomponents.viewmodel.LoggingClickCounterViewModel;
import com.shvartsy.archcomponents.viewmodel.LoggingClickCounterViewModelFactory;
import com.shvartsy.archcomponents.viewmodel.MyObserver;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoggingViewModelDemoActivity extends LifecycleActivity {

    @BindView(R.id.click_count_text)
    protected TextView clickCountText;

    private ClickCounterViewModel viewModel;
    private LifecycleRegistry mLifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmodel_demo);
        ButterKnife.bind(this);

        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);


        // the factory and its dependencies instead should be injected with DI framework like Dagger
        LoggingClickCounterViewModelFactory factory =
                new LoggingClickCounterViewModelFactory(new ClickLoggingInterceptor());

        viewModel = ViewModelProviders.of(this, factory).get(LoggingClickCounterViewModel.class);
        displayClickCount(viewModel.getCount());
        getLifecycle().addObserver(new MyObserver());
    }

    @OnClick(R.id.increment_button)
    public void incrementClickCount(View button) {
        viewModel.setCount(viewModel.getCount() + 1);
        displayClickCount(viewModel.getCount());
    }

    private void displayClickCount(int clickCount) {
        Log.d("LifecycleActivity count","displayClickCount "+clickCount);
        clickCountText.setText(String.valueOf(clickCount));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifecycleActivity","onStart"+viewModel.getCount());
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifecycleActivity","onPause"+viewModel.getCount());
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifecycleActivity","onResume"+viewModel.getCount());
        mLifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifecycleActivity","onStop"+viewModel.getCount());
        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifecycleActivity","onDestroy"+viewModel.getCount());
        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
    }
}

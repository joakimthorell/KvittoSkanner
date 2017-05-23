package corp.skaj.foretagskvitton.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import corp.skaj.foretagskvitton.R;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Första sliden", "Förklaring", R.drawable.ic_image , getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("Andra sliden", "Förklaring", R.drawable.ic_image, getColor(R.color.colorAccent)));
        addSlide(AppIntroFragment.newInstance("Tredje sliden", "Förklaring", R.drawable.ic_image, getColor(R.color.colorPrimaryDark)));

    }

    @Override
    public void onSkipPressed(android.support.v4.app.Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed (android.support.v4.app.Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged (@Nullable android.support.v4.app.Fragment oldFragment, @Nullable android.support.v4.app.Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}

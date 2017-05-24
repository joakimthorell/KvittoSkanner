package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import corp.skaj.foretagskvitton.R;

/**
 * Class takes care of the application intro
 */
public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_welcome), getString(R.string.intro_welcometext),
                R.mipmap.ic_launcher_round, getColor(R.color.colorAccent)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_archive), getString(R.string.intro_archivetext),
                R.drawable.ic_image , getColor(R.color.colorPrimary)));
        //TODO add picture of archive front page here
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_company), getString(R.string.intro_companytext),
                R.drawable.ic_image, getColor(R.color.colorAccent)));
        //TODO add picture of company front page here
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_supplier), getString(R.string.intro_suppliertext),
                R.drawable.ic_image, getColor(R.color.colorPrimary)));
        //TODO add picuture of supplier front page here
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_add), getString(R.string.intro_addtext),
                R.drawable.addbutton, getColor(R.color.colorAccent)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_goodluck), getString(R.string.intro_goodlucktext),
                R.mipmap.ic_launcher, getColor(R.color.colorPrimary)));
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

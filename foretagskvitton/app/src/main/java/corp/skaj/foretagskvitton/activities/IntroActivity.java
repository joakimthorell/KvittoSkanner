package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import corp.skaj.foretagskvitton.R;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Välkommen till KvittoSkanner!",
                "Med denna applikation kan du på ett enkelt sätt kan bokföra dina kvitton med hjälp av endast några få klick",
                R.mipmap.ic_launcher_round, getColor(R.color.colorAccent)));
        addSlide(AppIntroFragment.newInstance("Arkiv",
                "Under arkiv kan du hantera alla dina kvitton. Du sortera dem efter pris och datum. " +
                        "Du har möjlighet att lägga till ett nytt kvitto, redigera ett befintligt eller radera ett kvitto helt." ,
                R.drawable.ic_image , getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("Företag",
                "Under företag kan du hantera alla dina företag. Du kan lägga till ett nytt företag, redigera ett befintligt eller radera ett företag helt." +
                        " Du måste alltid ha minst ett företag.",
                R.drawable.ic_image, getColor(R.color.colorAccent)));
        addSlide(AppIntroFragment.newInstance("Grossister",
                "Under grossister kan du hantera alla dina grossister. Du kan lägga till en ny grossist, redigera en befintlig eller radera en grossist helt.",
                R.drawable.ic_image, getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("Lägg till", "För att lägga till något nytt nyttjar du den runda lägg-till knappen som finns på samtliga tre sidor",
                R.drawable.introduction_button, getColor(R.color.colorAccent)));
        addSlide(AppIntroFragment.newInstance("Lycka till med bokföringen!", "",
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

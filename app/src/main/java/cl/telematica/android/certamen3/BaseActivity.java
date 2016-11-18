package cl.telematica.android.certamen3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import cl.telematica.android.certamen3.fragment.ListFragment;


/**
 * Created by nicolas on 18-11-2016.
 */
public class BaseActivity extends AppCompatActivity {

    private int contentFrame;

    protected void setContentFrame(int contentFrame){
        this.contentFrame = contentFrame;
    }

    public void switchContent(ListFragment fragment, String addBackStack) {
        switchContent(fragment, addBackStack, -1, -1);
    }

    /**
     * Switch given fragment on default content holder and add to back stack.
     * Use given an animationIn and animationOut for fragment transaction
     * @param fragment     Fragment to be switched up
     * @param addBackStack Back stack tag
     */
    public void switchContent(Fragment fragment, String addBackStack, int animationIn, int animationOut) {
        try {
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            if (animationIn > 0 && animationOut > 0)
                fragmentTransaction.setCustomAnimations(animationIn, animationOut);

            if (addBackStack != null)
                fragmentTransaction.addToBackStack(addBackStack);
            fragmentTransaction.replace(contentFrame, fragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


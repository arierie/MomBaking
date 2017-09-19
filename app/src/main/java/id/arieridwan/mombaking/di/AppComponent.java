package id.arieridwan.mombaking.di;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import javax.inject.Singleton;
import dagger.Component;
import id.arieridwan.mombaking.data.ApiServices;

/**
 * Created by arieridwan on 27/08/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    ApiServices apiServices();
    SharedPreferences sharedPreferences();
    Gson gson();
}

package id.arieridwan.mombaking;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by arieridwan on 27/08/2017.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {}

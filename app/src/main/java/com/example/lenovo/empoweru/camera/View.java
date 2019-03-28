package com.example.lenovo.empoweru.camera;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Menu;

/**
 * Created by ptyagi on 4/12/17.
 */

public interface View {
    boolean onCreateOptionsMenu(Menu menu);

    void openCamera(Context context, ImageFile imageFile);
    void showImagePreview(@NonNull String uri);
    void showImageError();
}

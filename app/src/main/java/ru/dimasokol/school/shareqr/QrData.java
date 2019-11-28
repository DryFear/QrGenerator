package ru.dimasokol.school.shareqr;

import android.graphics.Bitmap;

import java.io.File;

public class QrData {

    private final Bitmap mBitmap;
    private final File mFile;

    public QrData(Bitmap bitmap, File file) {
        mBitmap = bitmap;
        mFile = file;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public File getFile() {
        return mFile;
    }
}

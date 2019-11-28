package ru.dimasokol.school.shareqr;

import androidx.lifecycle.LiveData;
import ru.dimasokol.school.shareqr.QrData;

public class QrLiveData extends LiveData<QrData> {

    @Override
    protected void postValue(QrData value) {
        super.postValue(value);
    }
}

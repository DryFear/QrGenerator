package ru.dimasokol.school.shareqr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

public class QrFragment extends Fragment {

    public static final String QR_CODE = "qr_code";
    private ImageView mQrCode;
    private View mShareButton;
    private File mResultFile;


    public static QrFragment newInstance(String text) {

        Bundle args = new Bundle();
        args.putString(QR_CODE, text);
        QrFragment fragment = new QrFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private QrFragment(){
        super(R.layout.fragment_qr);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mQrCode = view.findViewById(R.id.qr_code);
        mShareButton = view.findViewById(R.id.btn_share);
        mShareButton.setEnabled(false);
        String text = getArguments().getString(QR_CODE);

        QrGenerator
                .getInstance(requireContext().getFilesDir())
                .generate(text)
                .observe(this, new Observer<QrData>() {
                    @Override
                    public void onChanged(QrData qrData) {
                        mQrCode.setImageBitmap(qrData.getBitmap());
                        mShareButton.setEnabled(true);
                        mResultFile = qrData.getFile();
                    }
                });

        mShareButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                Uri uri = QrProvider.getUriForFile(requireContext(), "ru.sberbank.school.qr", mResultFile);
                shareIntent.setDataAndType(uri, requireContext().getContentResolver().getType(uri));
                shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(shareIntent);
            }
        });

        return view;
    }
}

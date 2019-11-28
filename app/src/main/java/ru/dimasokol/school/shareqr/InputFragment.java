package ru.dimasokol.school.shareqr;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InputFragment extends Fragment {


    private Button mGenerateButton;
    private EditText mInputField;

    private GeneratorHost mHost;

    private InputFragment(){
        super(R.layout.fragment_input);
    }

    public static InputFragment newInstance() {

        Bundle args = new Bundle();

        InputFragment fragment = new InputFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof GeneratorHost){
            mHost = (GeneratorHost) context;
        }else{
            throw new ClassCastException("Cannot cast context to Host");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        initViews(root);
        return root;
    }

    private void initViews(View root) {
        mGenerateButton = root.findViewById(R.id.btn_generate);
        mInputField = root.findViewById(R.id.input_field);
        mGenerateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mHost.proceedToGeneration(mInputField.getText().toString());
            }
        });
        mInputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mGenerateButton.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

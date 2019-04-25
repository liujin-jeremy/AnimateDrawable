package tech.liujin.wuxio.animatedrawable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.threekilogram.wuxio.animatedrawable.R;

/**
 * @author wuxio 2018-05-25:8:09
 */
public class TextFragment extends Fragment {

    public static final String key_text = "KEY_TEXT_TO_SHOW";
    protected View     rootView;
    protected TextView mText;


    public static TextFragment newInstance(String text) {

        TextFragment fragment = new TextFragment();
        Bundle bundle = new Bundle();
        bundle.putString(key_text, text);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        rootView = inflater.inflate( R.layout.fragment_text, container, false );
        initView(rootView);
        return rootView;
    }


    private void initView(View rootView) {

        mText = (TextView) rootView.findViewById(R.id.text);
        Bundle arguments = getArguments();
        String string = arguments.getString(key_text);
        mText.setText(string);
    }
}

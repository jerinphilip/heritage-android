package in.ac.iiit.cvit.heritage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SplashIntroFragment extends Fragment {

    private int pageNumber;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        pageNumber = bundle.getInt("page_number");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = getActivity().getLayoutInflater().inflate(R.layout.fragment_splash_intro, container, false);
        root.setTag(pageNumber);

        imageView = (ImageView) root.findViewById(R.id.imageview_splash_intro);
        switch (pageNumber) {
            case 0:
                imageView.setBackgroundColor(getResources().getColor(R.color.colorRed));
                break;
            case 1:
                imageView.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                break;
            case 2:
                imageView.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                break;
            default:
                break;
        }

        return root;
    }
}

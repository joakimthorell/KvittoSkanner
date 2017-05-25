package corp.skaj.foretagskvitton.view;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;

import corp.skaj.foretagskvitton.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {
    private static final String BUNDLE_KEY = "key_to_bundle";


    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment create(String imageAddress) {
        Bundle b = new Bundle();
        b.putString(BUNDLE_KEY, imageAddress);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);

        String s = getArguments().getString(BUNDLE_KEY);
        Uri URI = Uri.parse(s);

        System.out.println(s);

        PhotoView imageView = (PhotoView) view.findViewById(R.id.photo_view);
        imageView.setImageURI(URI);
        imageView.setZoomable(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

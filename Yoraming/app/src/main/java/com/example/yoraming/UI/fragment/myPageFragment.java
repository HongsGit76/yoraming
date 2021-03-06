package com.example.yoraming.UI.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yoraming.BackPressedForFinish;
import com.example.yoraming.Server.Net;
import com.example.yoraming.UI.activity.LoginActivity;
import com.example.yoraming.UI.activity.MainActivity;
import com.example.yoraming.OnBackPressedListener;
import com.example.yoraming.R;
import com.example.yoraming.UI.activity.MainMajorActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link myPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myPageFragment extends Fragment implements OnBackPressedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MyPageFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth auth;
    private BackPressedForFinish backPressedForFinish;
    private final int GET_GALLERY_IMAGE = 200;
    private CircleImageView iv_profile;
    private Button tv_initialize;
    private GoogleApiClient googleApiClient;
    private static final int REQ_SIGN_GOOGLE = 100; //구글로그인 했을 때 결과 코드
    StorageReference storageReference;

    public myPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment afterScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static myPageFragment newInstance(String param1, String param2) {
        myPageFragment fragment = new myPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        storageReference = FirebaseStorage.getInstance().getReference(); //we can use this reference to upload the images to firebase
        StorageReference profileRef = storageReference.child("users/"+auth.getCurrentUser().getEmail()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(iv_profile);
            }
        });

        FirebaseUser user = auth.getCurrentUser();
        View logout = inflater.inflate(R.layout.fragment_mypage, container, false);

        iv_profile = (CircleImageView) logout.findViewById(R.id.iv_profile);
        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        TextView tv_name = (TextView) logout.findViewById(R.id.tv_name);
        tv_name.setText(user.getDisplayName() + " 님");

        TextView tv_email = (TextView) logout.findViewById(R.id.tv_email);
        tv_email.setText(user.getEmail());

        Button tv_initialize = (Button) logout.findViewById(R.id.tv_initialize);
        tv_initialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences SP_yoram = getActivity().getSharedPreferences("yoram", MODE_PRIVATE);
                String yoramid = SP_yoram.getString("yoram_1","");

                Log.d("mypage 통신", yoramid);

                Call<JsonObject> res1 = Net.getInstance().getyoramFactory().deleteYoram(yoramid);
                res1.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            if(response.body() != null){
                                JsonObject success = response.body();
                                Log.d("mypage 통신", success.get("success").toString());
                                if (success.get("success").toString().equals("true")) {
                                    //Intent intent = new Intent(getActivity(), MainMajorActivity.class);
                                    //startActivity(intent);
                                }else{
                                    Toast.makeText(getActivity().getApplicationContext(),"다시 시도해주세요",Toast.LENGTH_SHORT);
                                }
                            }else{
                                Log.e("MainMajor 통신", "실패 1 response 내용이 없음");
                            }
                        }else{
                            Log.e("MainMajor 통신", "실패 2 서버 에러");
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("MainMajor 통신", "실패 3 통신 에러 "+t.getLocalizedMessage());
                    }
                });
            }
        });

        ImageButton btn_logout = (ImageButton) logout.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "로그아웃", Toast.LENGTH_SHORT).show();
                System.out.println("로그아웃");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("key", 1);
                startActivity(intent);
            }
        });
        return logout;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            iv_profile.setImageURI(selectedImageUri);

            uploadImageToFirebase(selectedImageUri);
        }
    }

    private void uploadImageToFirebase(Uri selectedImageUri) {
        //upload image to firebase storage
        StorageReference fileRef = storageReference.child("users/"+auth.getCurrentUser().getEmail()+"/profile.jpg");
        fileRef.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(iv_profile);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed", Toast.LENGTH_SHORT ).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("key", 0);
        ((MainActivity)getActivity()).replaceFragment(new HomeFragment());
    }

}